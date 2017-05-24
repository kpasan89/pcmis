package com.pcmis.entity;

import com.pcmis.entity.Airline;
import com.pcmis.entity.AirlineClass;
import com.pcmis.entity.Airport;
import com.pcmis.entity.Customer;
import com.pcmis.entity.Person;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-24T10:58:31")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile SingularAttribute<Reservation, Airline> preff_airline;
    public static volatile SingularAttribute<Reservation, Airport> arr_airport;
    public static volatile SingularAttribute<Reservation, Person> retirer;
    public static volatile SingularAttribute<Reservation, Customer> res_customer;
    public static volatile SingularAttribute<Reservation, AirlineClass> preff_class;
    public static volatile SingularAttribute<Reservation, Date> travel_date;
    public static volatile SingularAttribute<Reservation, Airport> dep_airport;
    public static volatile SingularAttribute<Reservation, Date> createdAt;
    public static volatile SingularAttribute<Reservation, Integer> kids_count;
    public static volatile SingularAttribute<Reservation, Person> creater;
    public static volatile SingularAttribute<Reservation, Date> retiredAt;
    public static volatile SingularAttribute<Reservation, Boolean> retired;
    public static volatile SingularAttribute<Reservation, Long> id;
    public static volatile SingularAttribute<Reservation, Integer> adult_count;
    public static volatile SingularAttribute<Reservation, Date> return_date;

}