package com.pcmis.entity;

import com.pcmis.entity.Country;
import com.pcmis.entity.JobTitle;
import com.pcmis.entity.MealPreference;
import com.pcmis.entity.Nationality;
import com.pcmis.entity.Person;
import com.pcmis.entity.SeatPreference;
import com.pcmis.entity.SportPreference;
import com.pcmis.enums.Card;
import com.pcmis.enums.CustomerType;
import com.pcmis.enums.Gender;
import com.pcmis.enums.Status;
import com.pcmis.enums.Title;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-12T22:50:32")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, Country> country;
    public static volatile SingularAttribute<Customer, Boolean> goldCustomer;
    public static volatile SingularAttribute<Customer, String> nic;
    public static volatile SingularAttribute<Customer, Boolean> selfCustomer;
    public static volatile SingularAttribute<Customer, Date> pp_expire_date;
    public static volatile SingularAttribute<Customer, Status> maritial_status;
    public static volatile SingularAttribute<Customer, Date> createdAt;
    public static volatile SingularAttribute<Customer, String> passport;
    public static volatile SingularAttribute<Customer, Boolean> reservation;
    public static volatile SingularAttribute<Customer, Boolean> payment;
    public static volatile SingularAttribute<Customer, Long> id;
    public static volatile SingularAttribute<Customer, String> customerCategory;
    public static volatile SingularAttribute<Customer, String> fax;
    public static volatile SingularAttribute<Customer, JobTitle> job_title;
    public static volatile SingularAttribute<Customer, Date> pp_issued_date;
    public static volatile SingularAttribute<Customer, Boolean> silverCustomer;
    public static volatile SingularAttribute<Customer, Boolean> privilage_crd;
    public static volatile SingularAttribute<Customer, Boolean> normalCustomer;
    public static volatile SingularAttribute<Customer, String> bussiness_bought;
    public static volatile SingularAttribute<Customer, SeatPreference> seat;
    public static volatile SingularAttribute<Customer, Boolean> platinumCustomer;
    public static volatile SingularAttribute<Customer, String> full_name;
    public static volatile SingularAttribute<Customer, Nationality> nationality;
    public static volatile SingularAttribute<Customer, Date> dob;
    public static volatile SingularAttribute<Customer, String> company_name;
    public static volatile SingularAttribute<Customer, Person> creater;
    public static volatile SingularAttribute<Customer, Boolean> permenant;
    public static volatile SingularAttribute<Customer, CustomerType> customer_type;
    public static volatile SingularAttribute<Customer, Date> dom;
    public static volatile SingularAttribute<Customer, Gender> gender;
    public static volatile SingularAttribute<Customer, Person> retirer;
    public static volatile SingularAttribute<Customer, Title> title;
    public static volatile SingularAttribute<Customer, String> telepone;
    public static volatile SingularAttribute<Customer, String> interest;
    public static volatile SingularAttribute<Customer, String> bussiness_email;
    public static volatile SingularAttribute<Customer, Boolean> retired;
    public static volatile SingularAttribute<Customer, String> first_name;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, Integer> reservationCount;
    public static volatile SingularAttribute<Customer, String> address;
    public static volatile SingularAttribute<Customer, String> mobile;
    public static volatile SingularAttribute<Customer, Card> card_type;
    public static volatile SingularAttribute<Customer, String> introducedCustomer;
    public static volatile SingularAttribute<Customer, MealPreference> meal;
    public static volatile SingularAttribute<Customer, String> bussiness_address;
    public static volatile SingularAttribute<Customer, Integer> pointEarned;
    public static volatile SingularAttribute<Customer, String> bussiness_tel;
    public static volatile SingularAttribute<Customer, Date> retiredAt;
    public static volatile SingularAttribute<Customer, String> family_name;
    public static volatile SingularAttribute<Customer, SportPreference> sport;

}