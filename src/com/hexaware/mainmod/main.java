package com.hexaware.mainmod;

import java.util.ArrayList;

import com.hexaware.dao.HospitalServiceImp;
import com.hexaware.entity.Appointment;

public class main {
    
    public static String updateAppointment(HospitalServiceImp hs,Appointment newAppointment){
        boolean status = false;
        try{
            status = hs.updateAppointment(newAppointment);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }   

        if(status)
        {
            return "Appointment update successfully for appointment id " + newAppointment.getAppointmentId();
        }

        return "Updation failed";
    }

    public static String getAppointment(HospitalServiceImp hs,Appointment newAppointment){

        String result = "";

        try{
            Appointment received = hs.getAppointmentById(newAppointment.getAppointmentId());

            if(received != null){
                result = received.toString();
            }

        }catch(Exception e){
            result = e.getMessage();
        }

        return result;
    }

    public static String getPatientsAppointment(HospitalServiceImp hs,int patientId){

        String result = "";

        ArrayList<Appointment> appointments = new ArrayList<>();

        try{
            appointments = hs.getAppointmentsForPatient(patientId);
            
            for(Appointment ap : appointments)
            {
                result += ap.toPateintAppointmentString() + "\n";
            }
        }catch(Exception e){
            result = e.getMessage();
        }

        return result;
    }

    public static String getDoctorsAppointment(HospitalServiceImp hs,int doctorId){
        
        String result = "";

        ArrayList<Appointment> appointments = new ArrayList<>();

        try{
            appointments = hs.getAppointmentsForDoctor(doctorId);

            for(Appointment ap : appointments){
                result += ap.toDoctorAppointmentString() + "\n";
            }
        }catch(Exception e){
            result = e.getMessage();
        }
        
        return result;
    }

    public static String cancelAppointment(HospitalServiceImp hs,Appointment newAppointment){

        String result = "";

        try{
            boolean cancellation = hs.cancelAppointment(newAppointment);

            if(cancellation){
                result = "Appointment cancelled successfully";
            }else{
                result = "No appointment ID found";
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static void main(String[] args) {

        // For appointment update
        HospitalServiceImp hs = new HospitalServiceImp();

        Appointment updateAppointment = new Appointment(1,1, 1, "2025-04-25", "Changed");
        System.out.println(updateAppointment(hs, updateAppointment));

        // Cancel Appointment
        Appointment cancelAppointment = new Appointment(1,1, 1, "2025-04-25", "Changed");
        System.out.println(updateAppointment(hs, cancelAppointment));

        // For getting appointment by id
        Appointment getAppointment = new Appointment(1,1, 1, "2025-04-25", "Changed");
        System.out.println(updateAppointment(hs, getAppointment));

        // For getting patients appointment
        int patientId = 1;
        System.out.println(getPatientsAppointment(hs, patientId));

        // For getting doctors appointment
        int doctorId = 1;
        System.out.println(getDoctorsAppointment(hs, doctorId));
    }
}