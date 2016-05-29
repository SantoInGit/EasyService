package controllers;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

@Named(value = "invoiceController")
@RequestScoped
public class InvoiceController implements Serializable {

    public InvoiceController() {
    }


    public String doGenerateInvoice() throws Exception {
            
        return null;
    }
}
