package entities;

import entities.ServiceOrderItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T18:32:31")
@StaticMetamodel(ServiceOrder.class)
public class ServiceOrder_ { 

    public static volatile ListAttribute<ServiceOrder, ServiceOrderItem> serviceOrderItem;
    public static volatile SingularAttribute<ServiceOrder, String> serviceOrderName;
    public static volatile SingularAttribute<ServiceOrder, String> serviceOrderStatus;
    public static volatile SingularAttribute<ServiceOrder, Date> serviceOrderDate;
    public static volatile SingularAttribute<ServiceOrder, Long> serviceOrderId;

}