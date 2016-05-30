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

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    public List<Service> listService() {
        TypedQuery<Service> query = em.createNamedQuery("findAllService", Service.class);
        return query.getResultList();
    }

    public List<Service> listServiceLimitFour() {
        TypedQuery<Service> query = em.createNamedQuery("findAllService", Service.class);
        return query.setMaxResults(4).getResultList();
    }

    public Service addService(Service service, String serviceCatId) {
        Long sid = Long.parseLong(serviceCatId);
        ServiceCategory sCategory = em.find(ServiceCategory.class, sid);
        service.setServiceCategory(sCategory);
        em.persist(service);
        return service;
    }

    public void deleteService(Long id) {
        em.remove(getService(id));
    }

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
     * @param service_id
     * @return service
     */
    public Service getService(Long service_id) {
        Service service = em.find(Service.class, service_id);
        return service;
    }

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

    public List<Service> search(String search, String searchBy) {
        TypedQuery<Service> query = em.createNamedQuery("findServiceBy" + searchBy, Service.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

    public List<Service> searchFrontend(String search, String searchBy) {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String search_by = params.get("search_by");
        String search_text = params.get("s");

        if (search_by != null && search_text != null) {
            search = search_text;
            searchBy = search_by;
        }

        TypedQuery<Service> query = em.createNamedQuery("findServiceBy" + searchBy, Service.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        if ("Category".equals(search_by)) {
            Long c_id = Long.parseLong(search);
            query.setParameter(searchBy, c_id);
        } else {
            query.setParameter(searchBy, "%" + search + "%");
        }
        return query.getResultList();
    }

}
