package com.pcmis.controllers;

import com.pcmis.entity.SportPreference;
import com.pcmis.controllers.util.JsfUtil;
import com.pcmis.controllers.util.JsfUtil.PersistAction;
import com.pcmis.facades.SportPreferenceFacade;

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

@Named("sportPreferenceController")
@SessionScoped
public class SportPreferenceController implements Serializable {

    @EJB
    private com.pcmis.facades.SportPreferenceFacade ejbFacade;
    private List<SportPreference> items = null;
    private SportPreference selected;

    public SportPreferenceController() {
    }

    public SportPreference getSelected() {
        return selected;
    }

    public void setSelected(SportPreference selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SportPreferenceFacade getFacade() {
        return ejbFacade;
    }

    public SportPreference prepareCreate() {
        selected = new SportPreference();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SportPreferenceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SportPreferenceUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SportPreferenceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SportPreference> getItems() {
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

    public SportPreference getSportPreference(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<SportPreference> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SportPreference> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public List<SportPreference> completeSportPreferences(String qry) {
        String temSql;
        temSql = "SELECT x FROM SportPreference x where x.retired=false and LOWER(x.name) like '%" + qry.toLowerCase() + "%' order by x.name";
        return getFacade().findBySQL(temSql);
    }

    @FacesConverter(forClass = SportPreference.class)
    public static class SportPreferenceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SportPreferenceController controller = (SportPreferenceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sportPreferenceController");
            return controller.getSportPreference(getKey(value));
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
            if (object instanceof SportPreference) {
                SportPreference o = (SportPreference) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SportPreference.class.getName()});
                return null;
            }
        }

    }

}
