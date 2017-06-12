package com.pcmis.entity;

import com.pcmis.entity.Customer;
import com.pcmis.entity.Person;
import com.pcmis.entity.Reservation;
import com.pcmis.enums.ExpenseType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-12T22:50:32")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, Float> value_ticket;
    public static volatile SingularAttribute<Payment, Date> createdAt;
    public static volatile SingularAttribute<Payment, ExpenseType> expenseType;
    public static volatile SingularAttribute<Payment, Person> retirer;
    public static volatile SingularAttribute<Payment, Customer> pay_customer;
    public static volatile SingularAttribute<Payment, Person> creater;
    public static volatile SingularAttribute<Payment, Date> retiredAt;
    public static volatile SingularAttribute<Payment, Reservation> reservation;
    public static volatile SingularAttribute<Payment, Boolean> retired;
    public static volatile SingularAttribute<Payment, Long> id;
    public static volatile SingularAttribute<Payment, String> ticket_number;
    public static volatile SingularAttribute<Payment, Float> points;

}