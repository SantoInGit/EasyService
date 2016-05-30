package ejb;

import entities.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.ServiceOrder;
import entities.ServiceOrderItem;
import entities.Staff;
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
    private static ServiceOrder serviceOrder;

    public List<ServiceOrder> listServiceOrders() {
        TypedQuery<ServiceOrder> query = em.createNamedQuery("findAllServiceOrders", ServiceOrder.class);
        return query.getResultList();
    }

    public ServiceOrder addServiceOrder(ServiceOrder serviceOrder) {
        em.persist(serviceOrder);
        return serviceOrder;
    }

    public void deleteServiceOrder(Long id) {
        em.remove(getServiceOrder(id));
    }

    public int changeStatusServiceOrder(Long id, String status) {
        TypedQuery<ServiceOrder> query = em.createNamedQuery("changeServiceOrderStatus", ServiceOrder.class);
        switch (status.toLowerCase()) {
            case "cancelled":
                query.setParameter("status", "Cancelled");
                query.setParameter("serOrderId", id);
                return query.executeUpdate();

            case "completed":
                query.setParameter("status", "Completed");
                query.setParameter("serOrderId", id);
                return query.executeUpdate();
            case "staff assigned":
                query.setParameter("status","Staff assigned");
                query.setParameter("serOrderId", id);
                return query.executeUpdate();
            default:
                return 0;

        }

    }

    
    public ServiceOrder createInvoice(Long id){
        
        TypedQuery<ServiceOrder> query = em.createNamedQuery("findServiceOrdersById", ServiceOrder.class);
        query.setParameter("serOrderId",id);
        return query.getSingleResult();
  }

    public ServiceOrder getServiceOrder(Long id) {
        return em.find(ServiceOrder.class, id);

    }
    public ServiceOrder confirmServiceOrder(Long id){
        return getServiceOrder(id);
    }

    public ServiceOrder confirmServiceOrderCommit(Long id, List<String> staffid) {
        serviceOrder = getServiceOrder(id);
        updateAssignedStaff(staffid);
        changeStatusServiceOrder(id, "Staff Assigned");
        return serviceOrder;
    }

    public void updateAssignedStaff(List<String> staffid) {
        
        for (String id : staffid) {
            TypedQuery<Staff> query = em.createNamedQuery("updateAssignedStaff", Staff.class);
            query.setParameter("stJobTitle", serviceOrder.getServiceOrderId().toString());
            query.setParameter("stFromDate", serviceOrder.getFromDate());
            query.setParameter("stToDate", serviceOrder.getToDate());
            query.setParameter("stStatus", "Assigned");
            query.setParameter("stId", Long.parseLong(id));
            query.executeUpdate();
        }
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
