
package ejb;

import entities.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.ServiceOrder;
import entities.ServiceOrderItem;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;


@Stateless
public class ServiceOrderEJB {



    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;



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
    
    public void cancelServiceOrder(Long id){
        TypedQuery<ServiceOrder> query = em.createNamedQuery("cancelOrder", ServiceOrder.class);
        
        query.setParameter("status","Cancelled");
        query.setParameter("serOrderId",id);
        
    }
    
    public void  createInvoice(Long id){
    }

    public ServiceOrder getServiceOrder(Long id) {
        return em.find(ServiceOrder.class, id);
       
    }

    public List<ServiceOrder> search(String search, String searchBy) {
        TypedQuery<ServiceOrder> query = em.createNamedQuery("findServiceOrderBy" + searchBy, ServiceOrder.class);
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();

    }

    public ServiceOrder addServiceOrder(ServiceOrder serOrder, int service_id, String service_name, String customer_id) {

        Long c_id = Long.parseLong(customer_id);
        Customer customer = em.find(Customer.class, c_id);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48


        List<ServiceOrderItem> SOI = new ArrayList<>();
        ServiceOrderItem s = new ServiceOrderItem();
        s.setOrderItemName(service_name);
        s.setServiceStatus("processing");
        SOI.add(s);
        serOrder.setServiceOrderStatus("processing");
        serOrder.setServiceOrderItem(SOI);

        serOrder.setCustomer(customer);
        serOrder.setServiceOrderDate(dateFormat.format(date));

        em.persist(serOrder);
        return serOrder;

    }

}
