package controllers;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

@Named(value = "helperController")
@RequestScoped
public class HelperController implements Serializable {

    public HelperController() {
        this.baseURL = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
    }

    private String baseURL;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String BaseURL) {
        this.baseURL = BaseURL;
    }

}
