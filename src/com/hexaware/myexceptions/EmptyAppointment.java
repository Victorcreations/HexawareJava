package com.hexaware.myexceptions;

public class EmptyAppointment extends Exception{
    
    public EmptyAppointment(){}

    public EmptyAppointment(String messaage){
        super(messaage);
    }
}