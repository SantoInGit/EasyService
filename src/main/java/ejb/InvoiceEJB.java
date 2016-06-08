
package ejb;


import entities.Invoice;
import entities.ServiceOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * A stateless enterprise java bean to handle all the business logic related to invoice object
 */
@Stateless
public class InvoiceEJB {

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;
    
    /**
     * Business logic to list all invoice objects
     * @return list of all invoice objects
     */
    public List<Invoice> listInvoices() {
        TypedQuery<Invoice> query = em.createNamedQuery("findAllInvoice", Invoice.class);
        return query.getResultList();
    }

    /**
     * Business login to persist an invoice object
     * @param invoice to be passed to be persisted in the database
     * @return the currently persisted invoice object
     */
    public Invoice addInvoice(Invoice invoice) {
        em.persist(invoice);
        return invoice;
    }
    
    /**
     * Business logic to delete an invoice object
     * @param id to be passed to find the invoice object to be deleted from the database
     */
    public void deleteInvoice(Long id){
       em.remove(getInvoice(id));
    }

    /**
     *
     * @param invoiceId to be passed to find the invoice object in the database
     * @return the currently found invoice object
     */
    public Invoice getInvoice(Long invoiceId) {
        Invoice invoice = em.find(Invoice.class, invoiceId);
        return invoice;
    }
    
    /**
     *
     * @param serviceOrder
     * @return
     */
    public String generateInvoice(ServiceOrder serviceOrder){
        
        return null;
    }

}
