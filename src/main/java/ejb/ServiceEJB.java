/**
 * @author jagatrp<Jagat Ram Prajapati>
 * @email prajapatijagat2009@gmail.com
 */
package ejb;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Service;
import entities.ServiceCategory;

@Stateless
public class ServiceEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<Service> listService() {
        // TODO not implemented with eclipselink 2.0 TypedQuery query = em.createNamedQuery("findAllBooks", Book.class);
        TypedQuery<Service> query = em.createNamedQuery("findAllService", Service.class);
        return query.getResultList();
    }

    /**
     *
     * @param customer
     * @param address
     * @return customer
     */
    public Service addService(Service service, String serviceCatId) {
        Long sid = Long.parseLong(serviceCatId);
        ServiceCategory sCategory = em.find(ServiceCategory.class, sid);
        service.setServiceCategory(sCategory);
        em.persist(service);
        return service;
    }

    /**
     *
     * @param service_id
     * @return customer
     */
    public Service getService(Long service_id) {
        Service service = em.find(Service.class, service_id);
        return service;
    }

    /**
     *
     * @return customer
     */
    public Service getServiceByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("service_id");
        Long service_id = Long.parseLong(id);
        return this.getService(service_id);
    }

    /**
     * method to search customer by using different attribute
     *
     * @param search
     * @param searchBy
     * @return List Customer
     */
    public List<Service> search(String search, String searchBy) {
        TypedQuery<Service> query = em.createNamedQuery("findServiceBy" + searchBy, Service.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }
    
  

}
