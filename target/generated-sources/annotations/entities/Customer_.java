package entities;

import entities.ServiceOrder;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T18:47:00")
@StaticMetamodel(Customer.class)
public class Customer_ extends User_ {

    public static volatile ListAttribute<Customer, ServiceOrder> serviceOrder;
    public static volatile SingularAttribute<Customer, String> status;

}