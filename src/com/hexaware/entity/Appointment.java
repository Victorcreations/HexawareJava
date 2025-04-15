package com.hexaware.entity;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String appointmentDate;
    private String description;
    private String patientName;
    private String doctorName;
    private String dateOfBirth;
    private String gender;
    
    
    public Appointment(){}

    public Appointment(int patientId,int doctorId,String appointmentDate,String description){
        
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.description = description;

    }

    public Appointment(int appointmentId,int patientId,int doctorId,String appointmentDate,String description){

        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.description = description;

    }

    public Appointment(int appointmentId,String patientName,String doctorName,String appointmentDate,String description){
        
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    public Appointment(int appointmentId,int patientId,String appointmentDate,String doctorName,String patientName,String dateOfBirth,String gender){
        
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    @Override
    public String toString(){
        return "Appointment Id : " + appointmentId + " , " +
        "Patient Id : " + patientId + " , " + 
        "Doctor Id : " + doctorId + " , " + 
        "Appointment Date : " + appointmentDate + " , " + 
        "Description " + description + " , " +
        "Doctor Name : " + doctorName + " , " + 
        "Patient Name : " + patientName;
    }
    
    public String toPateintAppointmentString(){
        return "Appointment Id : " + appointmentId + " , " +
        "Appointment Date : " + appointmentDate + " , " + 
        "Description " + description + " , " +
        "Doctor Name : " + doctorName + " , " + 
        "Patient Name : " + patientName;
    }

    public String toDoctorAppointmentString(){
        return "Appointment Id : " + appointmentId + " , " +
        "Patient Id : " + patientId + " , " + 
        "Appointment Date " + appointmentDate + " , " +
        "Doctor Name : " + doctorName + " , " + 
        "Patient Name : " + patientName + " , " +
        "Patient Date of Birth : " + dateOfBirth + " , " +
        "Gender : " + gender;
    }

    /**
     * @return int return the appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return int return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * @return int return the doctorId
     */
    public int getDoctorId() {
        return doctorId;
    }

    /**
     * @param doctorId the doctorId to set
     */
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * @return String return the appointmentDate
     */
    public String getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * @param appointmentDate the appointmentDate to set
     */
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return String return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param patientName the patientName to set
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    /**
     * @return String return the doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @param doctorName the doctorName to set
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    /**
     * @return String return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

}
