package entities;

import entities.Invoice;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T18:32:31")
@StaticMetamodel(Admin.class)
public class Admin_ extends User_ {

    public static volatile ListAttribute<Admin, Invoice> invoiceList;
    public static volatile SingularAttribute<Admin, String> qualification;
    public static volatile SingularAttribute<Admin, String> status;

}