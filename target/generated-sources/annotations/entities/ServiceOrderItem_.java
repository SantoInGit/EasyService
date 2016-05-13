package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-13T14:44:42")
@StaticMetamodel(ServiceOrderItem.class)
public class ServiceOrderItem_ { 

    public static volatile SingularAttribute<ServiceOrderItem, Long> orderItemId;
    public static volatile SingularAttribute<ServiceOrderItem, String> serviceStatus;
    public static volatile SingularAttribute<ServiceOrderItem, String> orderItemName;
    public static volatile SingularAttribute<ServiceOrderItem, Date> scheduleDate;

}