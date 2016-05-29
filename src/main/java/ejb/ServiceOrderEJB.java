
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.ServiceOrder;
import entities.ServiceOrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;


@Stateless
public class ServiceOrderEJB {


    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<ServiceOrder> listServiceOrders() {
        TypedQuery<ServiceOrder> query = em.createNamedQuery("findAllServiceOrders", ServiceOrder.class);
        return query.getResultList();
    }
   
    public ServiceOrder addServiceOrder(ServiceOrder serviceOrder) {
        em.persist(serviceOrder);
        return serviceOrder;
    }
    
    public void deleteServiceOrder(Long id){
       em.remove(getServiceOrder(id));
    }

    public ServiceOrder getServiceOrder(Long id) {
        return em.find(ServiceOrder.class, id);
         
    }

    
    public List<ServiceOrder> search(String search, String searchBy) {
        TypedQuery<ServiceOrder> query = em.createNamedQuery("findServiceOrderBy" + searchBy, ServiceOrder.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();

    }
    public ServiceOrder addServiceOrder(ServiceOrder serOrder, int service_id, String service_name) {
        List<ServiceOrderItem> SOI = new ArrayList<>();
        ServiceOrderItem s = new ServiceOrderItem();
        s.setOrderItemName(service_name);
        s.setServiceStatus("processing");
        SOI.add(s);
        serOrder.setServiceOrderStatus("processing");
        serOrder.setServiceOrderItem(SOI);
        em.persist(serOrder);
        return serOrder;

    }

}
