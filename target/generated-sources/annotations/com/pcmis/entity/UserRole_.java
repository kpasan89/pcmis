package com.pcmis.entity;

import com.pcmis.entity.Person;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-08-17T09:17:29")
@StaticMetamodel(UserRole.class)
public class UserRole_ { 

    public static volatile SingularAttribute<UserRole, Date> createdAt;
    public static volatile SingularAttribute<UserRole, Person> retirer;
    public static volatile SingularAttribute<UserRole, Person> creater;
    public static volatile SingularAttribute<UserRole, String> description;
    public static volatile SingularAttribute<UserRole, Date> retiredAt;
    public static volatile SingularAttribute<UserRole, Long> id;
    public static volatile SingularAttribute<UserRole, String> userRole;

}