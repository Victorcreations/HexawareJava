package com.hexaware.dao;

import java.util.ArrayList;
import com.hexaware.entity.Appointment;

public interface HospitalService {
    Appointment getAppointmentById(int id) throws Exception;
    ArrayList<Appointment> getAppointmentsForPatient(int patientId) throws Exception;
    ArrayList<Appointment> getAppointmentsForDoctor(int doctorId) throws Exception;
    boolean scheduleAppointment(Appointment appointment);
    boolean updateAppointment(Appointment appointment) throws Exception;
    boolean cancelAppointment(Appointment appointment) throws Exception;
}
