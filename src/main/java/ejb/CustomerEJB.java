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

/**
 *
 * A stateless enterprise java bean to handle all the business logic related to customer object
 */
@Stateless
public class CustomerEJB {

//persistence context name for database persistence   
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

     /**
     * Business logic to find all the persisted customer objects and return the list of these objects
     * @return list of customer objects
     */
    public List<Customer> listCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery("findAllCustomer", Customer.class);
        return query.getResultList();
    }

    /**
     *
     * @param customer to be passed to be persisted in the database
     * @return the currently persisted customer object
     */
    public Customer addCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

     /**
     * Business logic to delete an customer object
     * @param id to be passed to find the customer object to be deleted from the database
     */
    public void deleteCustomer(Long id) {
        em.remove(getCustomer(id));
    }

    /**
     *
     * @param customer_id to be passed to find the customer object in the database
     * @return the currently found customer object
     */
    public Customer getCustomer(Long customer_id) {
        Customer customer = em.find(Customer.class, customer_id);
        return customer;
    }

    /**
     * Business login to find customer, from database, that match the email and password parameter passed
     * @param email to set email parameter
     * @param password to set password parameter
     * @return list of customer objects that matches email and password
     */
    public List<Customer> getCustomerByEmailAndPassword(String email, String password) {
        TypedQuery<Customer> query = em.createNamedQuery("findCustomerByEmailAndPassword", Customer.class);
        //changed all upper case as in query field, set field name to upper case
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getResultList();

    }

    /**
     *  Business logic to find existing customer with passed email parameter
     * @param email to be passed to set email parameter
     * @return boolean as per the existence of customer in question
     */
    public boolean checkExistingCustomerByEmail(String email) {
        TypedQuery<Customer> query = em.createNamedQuery("findCustomerByEmail", Customer.class);
        //changed all upper case as in query field, set field name to upper case

        query.setParameter("email", email);
        if (query.getResultList().size() > 0) {
            return true;
        }
        return false;
    }

     /**
     * Business logic to update an customer object
     * @param customer to be passed to be updated its attributes
     * @return the currently edited customer object
     */
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

    /**
     * Function to find a customer object by id passed as parameter
     * @return the currently found customer object
     */
    public Customer getCustomerByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("customer_id");
        Long customer_id = Long.parseLong(id);
        return this.getCustomer(customer_id);
    }

    /**
     * Business logic to search an customer object
     * @param search to passed to set the search text
     * @param searchBy to be passed to select the search method
     * @return list of customer object which follow the search condition
     */
    public List<Customer> search(String search, String searchBy) {
        TypedQuery<Customer> query = em.createNamedQuery("findCustomerBy" + searchBy, Customer.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
