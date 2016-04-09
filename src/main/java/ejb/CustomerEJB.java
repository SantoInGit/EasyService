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
import entities.Address;
import entities.Customer;

@Stateless
public class CustomerEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<Customer> listCustomers() {
        // TODO not implemented with eclipselink 2.0 TypedQuery query = em.createNamedQuery("findAllBooks", Book.class);
        TypedQuery<Customer> query = em.createNamedQuery("findAllCustomer", Customer.class);
        return query.getResultList();
    }

    /**
     *
     * @param customer
     * @param address
     * @return customer
     */
    public Customer addCustomer(Customer customer, Address address) {

        customer.setAddress(address);
        em.persist(customer);
        return customer;
    }

    /**
     *
     * @param customer_id
     * @return customer
     */
    public Customer getCustomer(Long customer_id) {
        Customer customer = em.find(Customer.class, customer_id);
        return customer;
    }

    /**
     *
     * @return customer
     */
    public Customer getCustomerByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("customer_id");
        Long customer_id = Long.parseLong(id);
        return this.getCustomer(customer_id);
    }

    /**
     * method to search customer by using different attribute
     *
     * @param search
     * @param searchBy
     * @return List Customer
     */
    public List<Customer> search(String search, String searchBy) {
        TypedQuery<Customer> query = em.createNamedQuery("findCustomerBy" + searchBy, Customer.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
