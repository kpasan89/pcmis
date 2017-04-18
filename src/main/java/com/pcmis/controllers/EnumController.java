package com.pcmis.controllers;

import com.pcmis.enums.Airline;
import com.pcmis.enums.AirlineClass;
import com.pcmis.enums.Airport;
import com.pcmis.enums.Appoinment;
import com.pcmis.enums.Card;
import com.pcmis.enums.Country;
import com.pcmis.enums.CustomerType;
import com.pcmis.enums.ExpenseType;
import com.pcmis.enums.Gender;
import com.pcmis.enums.Job;
import com.pcmis.enums.Meal;
import com.pcmis.enums.Nationality;
import com.pcmis.enums.Seat;
import com.pcmis.enums.Sport;
import com.pcmis.enums.Status;
import com.pcmis.enums.Title;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class EnumController {

    /**
     * Creates a new instance of EnumController
     */
    public EnumController() {
    }
    
    public Airline[] getAirline(){
        return Airline.values();
    }
    
    public AirlineClass[] getAirlineClass(){
        return AirlineClass.values();
    }
    
    public Airport[] getAirport(){
        return Airport.values();
    }
    
    public Appoinment[] getAppoinment(){
        return Appoinment.values();
    }
    
    public Card[] getCard(){
        return Card.values();
    }
    
    public Country[] getCountry(){
        return Country.values();
    }
    
    public CustomerType[] getCustomerType(){
        return CustomerType.values();
    }
    
    public ExpenseType[] getExpenseType(){
        return ExpenseType.values();
    }
    
    public Gender[] getGender(){
        return Gender.values();
    }
    
    public Job[] getJob(){
        return Job.values();
    }
    
    public Meal[] getMeal(){
        return Meal.values();
    }
    
    public Nationality[] getNationality(){
        return Nationality.values();
    }
    
    public Seat[] getSeat(){
        return Seat.values();
    }
    
    public Sport[] getSport(){
        return Sport.values();
    }
    
    public Status[] getStatus(){
        return Status.values();
    }
    
    public Title[] getTitle(){
        return Title.values();
    }
}