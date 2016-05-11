package entities;

import entities.ServiceOrder;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T17:18:36")
@StaticMetamodel(Invoice.class)
public class Invoice_ { 

    public static volatile SingularAttribute<Invoice, Float> price;
    public static volatile SingularAttribute<Invoice, Long> invoiceId;
    public static volatile SingularAttribute<Invoice, ServiceOrder> serviceOrder;
    public static volatile SingularAttribute<Invoice, Date> invoiceDate;

}