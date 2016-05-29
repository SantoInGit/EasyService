
package ejb;


import entities.Invoice;
import entities.ServiceOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class InvoiceEJB {

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;
    
    public List<Invoice> listInvoices() {
        TypedQuery<Invoice> query = em.createNamedQuery("findAllInvoice", Invoice.class);
        return query.getResultList();
    }

    public Invoice addInvoice(Invoice invoice) {
        em.persist(invoice);
        return invoice;
    }
    
    public void deleteInvoice(Long id){
       em.remove(getInvoice(id));
    }

    public Invoice getInvoice(Long invoiceId) {
        Invoice invoice = em.find(Invoice.class, invoiceId);
        return invoice;
    }
    
    public String generateInvoice(ServiceOrder serviceOrder){
        
        return null;
    }

}
