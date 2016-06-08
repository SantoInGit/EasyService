package controllers;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * A controller to find the base url of the project
 */
@Named(value = "helperController")
@RequestScoped
public class HelperController implements Serializable {

    /**
     * Default constructor
     */
    public HelperController() {
        this.baseURL = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
    }

    private String baseURL;

    /**
     *
     * @return
     */
    public String getBaseURL() {
        return baseURL;
    }

    /**
     *
     * @param BaseURL
     */
    public void setBaseURL(String BaseURL) {
        this.baseURL = BaseURL;
    }

}
