package com.pcmis.controllers;

import com.pcmis.entity.AirlineClass;
import com.pcmis.controllers.util.JsfUtil;
import com.pcmis.controllers.util.JsfUtil.PersistAction;
import com.pcmis.facades.AirlineClassFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("airlineClassController")
@SessionScoped
public class AirlineClassController implements Serializable {

    @EJB
    private com.pcmis.facades.AirlineClassFacade ejbFacade;
    private List<AirlineClass> items = null;
    private AirlineClass selected;

    public AirlineClassController() {
    }

    public AirlineClass getSelected() {
        return selected;
    }

    public void setSelected(AirlineClass selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AirlineClassFacade getFacade() {
        return ejbFacade;
    }

    public AirlineClass prepareCreate() {
        selected = new AirlineClass();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AirlineClassCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AirlineClassUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AirlineClassDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AirlineClass> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public AirlineClass getAirlineClass(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<AirlineClass> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AirlineClass> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public List<AirlineClass> completeAirlineClass(String qry) {
        String temSql;
        temSql = "SELECT x FROM AirlineClass x where x.retired=false and LOWER(x.name) like '%" + qry.toLowerCase() + "%' order by x.name";
        return getFacade().findBySQL(temSql);
    }

    @FacesConverter(forClass = AirlineClass.class)
    public static class AirlineClassControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AirlineClassController controller = (AirlineClassController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "airlineClassController");
            return controller.getAirlineClass(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AirlineClass) {
                AirlineClass o = (AirlineClass) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AirlineClass.class.getName()});
                return null;
            }
        }

    }

}
