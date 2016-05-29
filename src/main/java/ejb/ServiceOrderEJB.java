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

@Stateless
public class ServiceOrderEJB {

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

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
