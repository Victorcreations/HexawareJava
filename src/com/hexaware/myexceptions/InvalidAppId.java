package com.hexaware.myexceptions;

public class InvalidAppId extends Exception{
    
    public InvalidAppId(){}
    
    public InvalidAppId(String message){
        super(message);
    }
}
