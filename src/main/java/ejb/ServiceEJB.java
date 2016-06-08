
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

/**
 *
 * A stateless enterprise java bean to handle all the business logic related to service object
 */
@Stateless
public class ServiceEJB {
    
    //persistence context name for database persistence   
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    /**
     * Business logic to list all services
     * @return list of all services
     */
    public List<Service> listService() {
        TypedQuery<Service> query = em.createNamedQuery("findAllService", Service.class);
        return query.getResultList();
    }

    /**
     * Business logic to list all active services at the front end
     * @return list of all active services
     */
    public List<Service> listFrontendService() {
        TypedQuery<Service> query = em.createNamedQuery("findAllActiveService", Service.class);
        String active = "Active";
        active = active.toUpperCase();
        query.setParameter("status", active);
        return query.getResultList();
    }

    /**
     * Business login to list four active services
     * @return list of four active services
     */
    public List<Service> listServiceLimitFour() {
        TypedQuery<Service> query = em.createNamedQuery("findAllActiveService", Service.class);
        String active = "Active";
        active = active.toUpperCase();
        query.setParameter("status", active);
        return query.setMaxResults(4).getResultList();
    }

    /**
     * Business login to persist service object
     * @param service to be passed to be persisted in the database
     * @param serviceCatId to be passed to set category for the service
     * @return the currently persisted staff object
     */
    public Service addService(Service service, String serviceCatId) {
        Long sid = Long.parseLong(serviceCatId);
        ServiceCategory sCategory = em.find(ServiceCategory.class, sid);
        service.setServiceCategory(sCategory);
        em.persist(service);
        return service;
    }

    /**
     * Business logic to delete an service object
     * @param id to be passed to find the service object to be deleted from the database
     */
    public void deleteService(Long id) {
        em.remove(getService(id));
    }

    /**
     * Business logic to update an service object
     * @param service to be passed to be updated its attributes
     * @return the currently edited service object
     */
    public int editServiceCommit(Service service) {
        TypedQuery<Service> query = em.createNamedQuery("updateService", Service.class);

        query.setParameter("serDescription", service.getDescription());
        query.setParameter("serRatePerHour", service.getRatePerHour());
        query.setParameter("serServiceName", service.getServiceName());
        query.setParameter("serStatus", service.getStatus());
        query.setParameter("serId", service.getServiceId());

        return query.executeUpdate();
    }

    /**
     *
     * @param service_id to be passed to find the staff object in the database
     * @return the currently found service object
     */
    public Service getService(Long service_id) {
        Service service = em.find(Service.class, service_id);
        return service;
    }

    /**
     * Function to find a service object by id passed as parameter
     * @return the currently found service object
     */
    public Service getServiceByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("service_id");
        Long service_id;
        if (id != null) {
            service_id = Long.parseLong(id);
        } else {
            service_id = Long.parseLong("0");
        }
        return this.getService(service_id);
    }

     /**
     * Business logic to search an service object
     * @param search to passed to set the search text
     * @param searchBy to be passed to select the search method
     * @return list of service object which follow the search condition
     */
    public List<Service> search(String search, String searchBy) {
        TypedQuery<Service> query = em.createNamedQuery("findServiceBy" + searchBy, Service.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

     /**
     * Business logic to search an service object from the frontend 
     * @param search to passed to set the search text
     * @param searchBy to be passed to select the search method
     * @return list of service object which follow the search condition
     */
    public List<Service> searchFrontend(String search, String searchBy) {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String search_by = params.get("search_by");
        String search_text = params.get("s");

        if (search_by != null && search_text != null) {
            search = search_text;
            searchBy = search_by;
        }

        TypedQuery<Service> query = em.createNamedQuery("findServiceByFrontend" + searchBy, Service.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        if ("Category".equals(search_by)) {
            Long c_id = Long.parseLong(search);
            query.setParameter(searchBy, c_id);
        } else {
            query.setParameter(searchBy, "%" + search + "%");
        }
        String active = "Active";
        active = active.toUpperCase();
        query.setParameter("status", active);
        return query.getResultList();
    }

}
