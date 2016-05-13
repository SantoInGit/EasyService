package entities;

import entities.ServiceOrderItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T18:47:00")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile SingularAttribute<Service, Float> ratePerHour;
    public static volatile SingularAttribute<Service, String> description;
    public static volatile SingularAttribute<Service, Long> serviceId;
    public static volatile SingularAttribute<Service, String> serviceName;
    public static volatile ListAttribute<Service, ServiceOrderItem> serviceOrderItemList;
    public static volatile SingularAttribute<Service, String> status;

}