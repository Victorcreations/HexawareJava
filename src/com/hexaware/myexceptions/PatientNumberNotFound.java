package com.hexaware.myexceptions;

public class PatientNumberNotFound extends Exception{
    
    public PatientNumberNotFound(){}

    public PatientNumberNotFound(String message){
        super(message);
    }
}
