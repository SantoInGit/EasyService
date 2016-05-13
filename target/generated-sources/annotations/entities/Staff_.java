package entities;

import entities.ServiceOrderItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-13T14:44:42")
@StaticMetamodel(Staff.class)
public class Staff_ extends User_ {

    public static volatile SingularAttribute<Staff, String> qualification;
    public static volatile ListAttribute<Staff, ServiceOrderItem> serviceOrderItem;
    public static volatile SingularAttribute<Staff, String> jobTitle;
    public static volatile SingularAttribute<Staff, String> status;

}