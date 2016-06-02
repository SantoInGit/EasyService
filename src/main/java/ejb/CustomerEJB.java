package ejb;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Customer;
import javax.persistence.NoResultException;

@Stateless
public class CustomerEJB {

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    public List<Customer> listCustomers() {
        // TODO not implemented with eclipselink 2.0 TypedQuery query = em.createNamedQuery("findAllBooks", Book.class);
        TypedQuery<Customer> query = em.createNamedQuery("findAllCustomer", Customer.class);
        return query.getResultList();
    }

    public Customer addCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    public void deleteCustomer(Long id) {
        em.remove(getCustomer(id));
    }

    public Customer getCustomer(Long customer_id) {
        Customer customer = em.find(Customer.class, customer_id);
        return customer;
    }

    public List<Customer> getCustomerByEmailAndPassword(String email, String password) {
        TypedQuery<Customer> query = em.createNamedQuery("findCustomerByEmailAndPassword", Customer.class);
        //changed all upper case as in query field, set field name to upper case
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getResultList();

    }

    public boolean checkExistingCustomerByEmail(String email) {
        TypedQuery<Customer> query = em.createNamedQuery("findCustomerByEmail", Customer.class);
        //changed all upper case as in query field, set field name to upper case

        query.setParameter("email", email);
        if (query.getResultList().size() > 0) {
            return true;
        }
        return false;
    }

    public int editCustomerCommit(Customer customer) {
        TypedQuery<Customer> query = em.createNamedQuery("updateCustomer", Customer.class);

        query.setParameter("custEmail", customer.getEmail());
        query.setParameter("custFirstName", customer.getFirstName());
        query.setParameter("custLastName", customer.getLastName());
        query.setParameter("custMiddleName", customer.getMiddleName());
        query.setParameter("custPassword", customer.getPassword());
        query.setParameter("custPhoneNo", customer.getPhoneNo());
        query.setParameter("custStatus", customer.getStatus());
        query.setParameter("custUserType", customer.getUserType());
        query.setParameter("custCity", customer.getAddress().getCity());
        query.setParameter("custCountry", customer.getAddress().getCountry());
        query.setParameter("custState", customer.getAddress().getState());
        query.setParameter("custStreet", customer.getAddress().getStreet());
        query.setParameter("custZipCode", customer.getAddress().getZipCode());
        query.setParameter("custId", customer.getId());

        return query.executeUpdate();
    }

    public Customer getCustomerByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("customer_id");
        Long customer_id = Long.parseLong(id);
        return this.getCustomer(customer_id);
    }

    public List<Customer> search(String search, String searchBy) {
        TypedQuery<Customer> query = em.createNamedQuery("findCustomerBy" + searchBy, Customer.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
