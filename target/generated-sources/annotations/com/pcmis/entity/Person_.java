package com.pcmis.entity;

import com.pcmis.controllers.PersonController;
import com.pcmis.entity.Person;
import com.pcmis.enums.Appoinment;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-03T15:51:54")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, Date> joined_date;
    public static volatile SingularAttribute<Person, Person> retirer;
    public static volatile SingularAttribute<Person, PersonController> personController;
    public static volatile SingularAttribute<Person, Date> createdAt;
    public static volatile SingularAttribute<Person, String> password;
    public static volatile SingularAttribute<Person, String> full_name;
    public static volatile SingularAttribute<Person, Appoinment> appoinment;
    public static volatile SingularAttribute<Person, Person> creater;
    public static volatile SingularAttribute<Person, Date> retiredAt;
    public static volatile SingularAttribute<Person, String> cunfirm_password;
    public static volatile SingularAttribute<Person, Boolean> retired;
    public static volatile SingularAttribute<Person, Long> id;
    public static volatile SingularAttribute<Person, String> username;

}