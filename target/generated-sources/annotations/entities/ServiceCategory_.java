package entities;

import entities.Service;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T18:32:31")
@StaticMetamodel(ServiceCategory.class)
public class ServiceCategory_ { 

    public static volatile SingularAttribute<ServiceCategory, String> serviceCategoryName;
    public static volatile ListAttribute<ServiceCategory, Service> service;
    public static volatile SingularAttribute<ServiceCategory, String> description;
    public static volatile SingularAttribute<ServiceCategory, Long> serviceCategoryId;
    public static volatile SingularAttribute<ServiceCategory, String> status;

}