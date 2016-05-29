package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.ServiceOrder;
import entities.ServiceOrderItem;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ServiceOrderEJB {

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

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
