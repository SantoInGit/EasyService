package ejb;

import entities.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.ServiceOrder;
import entities.ServiceOrderItem;
import entities.Staff;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;

/**
 *
 * A stateless enterprise java bean to handle all the business logic related to service order object
 */
@Stateless
public class ServiceOrderEJB {

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;
    private static ServiceOrder serviceOrder;

    /**
     * Business logic to list all service orders
     * @return list of all service orders
     */
    public List<ServiceOrder> listServiceOrders() {
        TypedQuery<ServiceOrder> query = em.createNamedQuery("findAllServiceOrders", ServiceOrder.class);
        return query.getResultList();
    }

    /**
     * Business logic to find the list of service order made by customer with id passed in parameter
     * @param id to set the parameter id to find the customer
     * @return
     */
    public List<ServiceOrder> customerListServiceOrders(Long id) {
        TypedQuery<ServiceOrder> query = em.createNamedQuery("findServiceOrdersByCustomer", ServiceOrder.class);
        query.setParameter("CID", id);
        return query.getResultList();
    }

    /**
     * Business login to persist service order object
     * @param serviceOrder to be passed to be persisted in the database
     * @return the currently persisted service order object
     */
    public ServiceOrder addServiceOrder(ServiceOrder serviceOrder) {
        em.persist(serviceOrder);
        return serviceOrder;
    }

    /**
     * Business logic to delete an service order object
     * @param id to be passed to find the service order object to be deleted from the database
     */
    public void deleteServiceOrder(Long id) {
        em.remove(getServiceOrder(id));
    }

    /**
     * Business logic to change the status of the service order as per the status parameter passed
     * @param id to find service order object
     * @param status to set the status of the service order
     * @return
     */
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
                query.setParameter("status", "Staff assigned");
                query.setParameter("serOrderId", id);
                return query.executeUpdate();

            case "rescheduled":
                query.setParameter("status", "Rescheduled");
                query.setParameter("serOrderId", id);
                return query.executeUpdate();
            default:
                return 0;
        }

    }

    /**
     *
     * @param id
     * @return
     */
    public ServiceOrder createInvoice(Long id) {

        TypedQuery<ServiceOrder> query = em.createNamedQuery("findServiceOrderByOrderId", ServiceOrder.class);
        query.setParameter("OrderId", id);
        return query.getSingleResult();
    }
    
    /**
     *
     * @param id to be passed to find the service order object in the database
     * @return the currently found service order object
     */
    public ServiceOrder getServiceOrder(Long id) {
        return em.find(ServiceOrder.class, id);
    }

    /**
     *
     * Function to find the service order of given id
     * @return service order object of provided id
     */
    public ServiceOrder rescheduleServiceOrder(Long id) {
        return getServiceOrder(id);
    }

    /**
     * Business logic to reschedule a service order
     * @param so to set the service order object
     * @param id to find the service order object
     * @return rescheduled service order
     */
    public ServiceOrder rescheduleServiceOrderCommit(ServiceOrder so, Long id) {
        serviceOrder = getServiceOrder(id);
        //int pkey = updateServiceOrder(so,id);
        //System.out.println(pkey);
        if (updateServiceOrder(so, id) > 0) {
            changeStatusServiceOrder(id, "Rescheduled");
            //  System.out.println("rescheduled");
        }
        return serviceOrder;
    }

    /**
     * Business logic to update a service order
     * @param so to set the service order object
     * @param id to find the service order object
     * @return integer value to show the number of records updated
     */
    public int updateServiceOrder(ServiceOrder so, Long id) {
        TypedQuery<ServiceOrder> query = em.createNamedQuery("updateServiceOrderReschedule", ServiceOrder.class);
        query.setParameter("serFromDate", so.getFromDate());
        query.setParameter("serToDate", so.getToDate());
        query.setParameter("serHrsPerDay", so.getHoursPerDay());
        query.setParameter("serStaffReqd", so.getStaffRequired());
        query.setParameter("serCustNote", so.getCustomerNote());
        query.setParameter("serId", so.getServiceOrderId());
        return query.executeUpdate();
    }

    /**
     * Function to find a service order object to confirm the booking
     * @param id to be passed to set paramter id
     * @return service order object
     */
    public ServiceOrder confirmServiceOrder(Long id) {
        return getServiceOrder(id);
    }

    /**
     * Business logic to confirm a service order, update staff object as per order and change the status of the service order
     * @param id to be passed to find service order
     * @param staffid to be passed to find list of staff to be updated
     * @return service order object
     */
    public ServiceOrder confirmServiceOrderCommit(Long id, List<String> staffid) {
        serviceOrder = getServiceOrder(id);
        updateAssignedStaff(staffid);
        changeStatusServiceOrder(id, "Staff Assigned");
        return serviceOrder;
    }

    /**
     * Business logic to update the staff as per the service orders they are assigned to
     * @param staffid to set the list of staff to be assigned
     */
    public void updateAssignedStaff(List<String> staffid) {

        for (String id : staffid) {
            TypedQuery<Staff> query = em.createNamedQuery("updateAssignedStaff", Staff.class);
            query.setParameter("stJobTitle", serviceOrder.getServiceOrderItem().toString());
            query.setParameter("stFromDate", serviceOrder.getFromDate());
            query.setParameter("stToDate", serviceOrder.getToDate());
            query.setParameter("stStatus", "Assigned");
            query.setParameter("stId", Long.parseLong(id));
            query.executeUpdate();
        }
    }

    /**
     * Business logic to search an service order object
     * @param search to passed to set the search text
     * @param searchBy to be passed to select the search method
     * @return list of service order object which follow the search condition
     */
    public List<ServiceOrder> search(String search, String searchBy) {
        TypedQuery<ServiceOrder> query = em.createNamedQuery("findServiceOrderBy" + searchBy, ServiceOrder.class);

        if ("OrderId".equals(searchBy)) {
            query.setParameter(searchBy, Long.parseLong(search));
        } else {
            search = search.toUpperCase();
            query.setParameter(searchBy, "%" + search + "%");
        }

        return query.getResultList();

    }

    /**
     *
     * 
     * Business login to persist service order object    
     * @param serOrder to set the service order object
     * @param service_id to set to find the service in that order
     * @param service_name to set the name of the service item
     * @param service_rate to set the rate of the service item
     * @param customer_id to set to find the customer who made the service order
     * @return
     */
    public ServiceOrder addServiceOrder(ServiceOrder serOrder, int service_id, String service_name, String service_rate, String customer_id) {

        Long c_id = Long.parseLong(customer_id);
        Customer customer = em.find(Customer.class, c_id);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            System.out.print(serOrder.getFromDate());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = df.parse(serOrder.getFromDate());
            Date toDate = df.parse(serOrder.getToDate());
            serOrder.setFromDate(df.format(fromDate));
            serOrder.setToDate(df.format(toDate));
        } catch (ParseException ex) {
            Logger.getLogger(ServiceOrderEJB.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<ServiceOrderItem> SOI = new ArrayList<>();
        ServiceOrderItem s = new ServiceOrderItem();
        s.setOrderItemName(service_name);
        s.setOrderItemRate(service_rate);
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
