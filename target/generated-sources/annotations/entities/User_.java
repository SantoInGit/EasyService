package entities;

import entities.Address;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T17:18:36")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Address> address;
    public static volatile SingularAttribute<User, String> middleName;
    public static volatile SingularAttribute<User, String> userType;
    public static volatile SingularAttribute<User, Long> userId;
    public static volatile SingularAttribute<User, String> phoneNo;
    public static volatile SingularAttribute<User, String> email;

}