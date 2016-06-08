package controllers;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Santo
 */
@Named(value = "invoiceController")
@RequestScoped
public class InvoiceController implements Serializable {

    /**
     *
     */
    public InvoiceController() {
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String doGenerateInvoice() throws Exception {
            
        return null;
    }
}
