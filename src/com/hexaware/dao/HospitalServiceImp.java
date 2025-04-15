package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hexaware.entity.Appointment;
import com.hexaware.utility.DBConnection;
import com.hexaware.myexceptions.EmptyAppointment;
import com.hexaware.myexceptions.InvalidAppId;

public class HospitalServiceImp implements HospitalService{

    Connection connection;

    public HospitalServiceImp(){

        try{
            this.connection = DBConnection.getConnection();
        }catch(Exception e){
            System.out.println("Error in creating connection " + e.getMessage());
        }
    }

    @Override
    public Appointment getAppointmentById(int id) throws Exception{

        Appointment appointment;
        String query = "SELECT * FROM Appointment WHERE appointmentId = ?";

        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);

            ResultSet result = pst.executeQuery();

            if(!result.next())
                throw new EmptyAppointment("It seems there is no appointment");

            appointment = new Appointment(
                result.getInt("appointmentId"),
                result.getInt("patientId"),
                result.getInt("doctorId"),
                result.getString("appointmentDate"),
                result.getString("description")
            );

            return appointment;

        }catch(Exception e)
        {
            throw new SQLException("Data cannot be retreived " + e.getMessage());
        }

    }

    @Override
    public ArrayList<Appointment> getAppointmentsForPatient(int patientId) throws Exception{

        ArrayList<Appointment> appointments = new ArrayList<>();

        String query = "SELECT a.appointmentId,\n" + 
                        "CONCAT(p.firstName,' ',p.lastName) AS patientName,\n" + 
                        "CONCAT(d.firstName,' ',d.lastName) AS doctorName,\n" + 
                        "a.appointmentDate,\n" + 
                        "a.description\n" + 
                        "FROM Appointment a\n" + 
                        "INNER JOIN Doctor d ON a.doctorId = d.doctorId\n" + 
                        "INNER JOIN Patient p ON a.patientId = p.patientId\n" +
                        "WHERE a.patientId = ?";

        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, patientId);

            ResultSet result = pst.executeQuery();

            while(result.next()){
                Appointment newAppointment = new Appointment(
                    result.getInt("appointmentId"),
                    result.getString("patientName"),
                    result.getString("doctorName"),
                    result.getString("appointmentDate"),
                    result.getString("description")
                );

                appointments.add(newAppointment);
            }
        }catch(Exception e){
            throw new SQLException("Error in fetching patients appointment " + e.getMessage());
        }

        return appointments;
    }

    @Override
    public ArrayList<Appointment> getAppointmentsForDoctor(int doctorId) throws Exception{

        ArrayList<Appointment> appointments = new ArrayList<>();

        String query = "SELECT a.AppointmentId,\n" + //
                        "a.patientId,\n" + //
                        "a.appointmentDate,\n" + //
                        "CONCAT(d.firstName,' ',d.lastName) AS doctorName,\n" + //
                        "CONCAT(p.firstName,' ',p.lastName) AS patientName,\n" + //
                        "p.dateOfBirth as patient_dateOfBirth,\n" + //
                        "p.gender\n" + //
                        "FROM Appointment a\n" + //
                        "INNER JOIN Doctor d\n" + //
                        "ON d.doctorId = a.doctorId\n" + //
                        "INNER JOIN Patient p\n" + //
                        "ON p.patientId = a.patientId\n" + //
                        "WHERE a.doctorId = ?";

        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, doctorId);

            ResultSet rst = pst.executeQuery();

            while(rst.next()){

                Appointment newAppointment = new Appointment(
                    rst.getInt("AppointmentId"),
                    rst.getInt("patientId"),
                    rst.getString("appointmentDate"),
                    rst.getString("doctorName"),
                    rst.getString("patientName"),
                    rst.getString("patient_dateOfBirth"),
                    rst.getString("gender")
                );

                appointments.add(newAppointment);
            }
        }catch(Exception e){
            throw new SQLException("Error in fetching doctor's appointment " +  e.getMessage());
        }

        return appointments;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {

        boolean executionResult = false;

        try{
            String query = "INSERT INTO Appointment VALUES(?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, appointment.getAppointmentId());
            pst.setInt(2, appointment.getPatientId());
            pst.setInt(3, appointment.getDoctorId());
            pst.setString(4, appointment.getAppointmentDate());
            pst.setString(5, appointment.getDescription());

            int affected = pst.executeUpdate();
            executionResult = affected > 0;
        }catch(Exception e){
            System.out.println("Error in scheduling appointment " + e.getMessage());
        }

        return executionResult;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) throws Exception{

        boolean execution = false;

        String query = "SELECT * FROM Appointment WHERE appointmentId = ?";

        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, appointment.getAppointmentId());

        ResultSet rst = pst.executeQuery();

        if(rst.next()){

            String updateQuery = "UPDATE Appointment SET patientId = ?,doctorId = ?,appointmentDate = ?,description = ? WHERE appointmentId = ?";
            try{
                PreparedStatement updatepst = connection.prepareStatement(updateQuery);
                updatepst.setInt(1, appointment.getPatientId());
                updatepst.setInt(2, appointment.getDoctorId());
                updatepst.setString(3, appointment.getAppointmentDate());
                updatepst.setString(4, appointment.getDescription());
                updatepst.setInt(5, appointment.getAppointmentId());

                int rowsAffected = updatepst.executeUpdate();
                execution = rowsAffected > 0;   
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            throw new InvalidAppId("Invalid Appointment ID");
        }

        return execution;
    }

    @Override
    public boolean cancelAppointment(Appointment appointment) throws Exception{

        boolean cancelled = false;

        String query = "DELETE FROM Appointment WHERE appointmentId = ?";

        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, appointment.getAppointmentId());

            int rowsAffected = pst.executeUpdate();

            cancelled = rowsAffected > 0;

        }catch(Exception e){
            throw new InvalidAppId("Appointment Id not found" + e.getMessage());
        }

        return cancelled;
    }
    
}
