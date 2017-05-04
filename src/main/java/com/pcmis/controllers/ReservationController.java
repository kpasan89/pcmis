package com.pcmis.controllers;

import com.pcmis.entity.Reservation;
import com.pcmis.controllers.util.JsfUtil;
import com.pcmis.controllers.util.JsfUtil.PersistAction;
import com.pcmis.entity.Customer;
import com.pcmis.facades.CustomerFacade;
import com.pcmis.facades.ReservationFacade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@ManagedBean(name = "reservationController")
@SessionScoped
public class ReservationController implements Serializable {

    @EJB
    private com.pcmis.facades.ReservationFacade ejbFacade;
    private List<Reservation> items = null;
    private Reservation selected;
    @Inject
    private PersonController personController;
    @Inject
    private CustomerController customerController;
    private Customer customer;
    @EJB
    private CustomerFacade customerFacade;

    public ReservationController() {
    }

    public Reservation getSelected() {
        return selected;
    }

    public void setSelected(Reservation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReservationFacade getFacade() {
        return ejbFacade;
    }

    public Reservation prepareCreate() {
        selected = new Reservation();
        initializeEmbeddableKey();
        return selected;
    }

    private Customer resCustomer;
    private int resCount;

    public void selectReservationCustomer() {
        String j = "select c from Customer c where c.retired=false and c.id=:rc";
        Map m = new HashMap();
        m.put("rc", selected.getRes_customer().getId());
        resCustomer = getCustomerFacade().findFirstBySQL(j, m);
        resCount = resCustomer.getReservationCount();
        System.out.println("resCount = " + resCount + "  ..................");
        System.out.println("resCustomer = " + resCustomer+ "  ..................");
    }

    public void createNew() {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                Reservation r = new Reservation();
                Customer c = new Customer();
                r.setAdult_count(selected.getAdult_count());
                r.setArr_airport(selected.getArr_airport());
                r.setDep_airport(selected.getDep_airport());
                r.setKids_count(selected.getKids_count());
                r.setPreff_airline(selected.getPreff_airline());
                r.setPreff_class(selected.getPreff_class());
                r.setRes_customer(selected.getRes_customer());
                r.setReturn_date(selected.getReturn_date());
                r.setTravel_date(selected.getTravel_date());

                selectReservationCustomer();

                if (resCustomer.getReservationCount() < 1) {
                    resCustomer.setPermenant(false);
                    System.out.println("permanent false");
                } else {
                    resCustomer.setPermenant(true);
                    System.out.println("permanent true");
                }

                resCount = resCount + 1;
                resCustomer.setReservationCount(resCount);
                getFacade().edit(r);
                getCustomerFacade().edit(resCustomer);
                JsfUtil.addSuccessMessage("Reservation was successfully created");
                
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
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ReservationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ReservationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ReservationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reservation> getItems() {
        if (items == null) {
            String jpql;
            jpql = "select r from Reservation r  Where r.retired=false order by r.id desc";
            items = getFacade().findBySQL(jpql);
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

    public List<Reservation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reservation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public PersonController getPersonController() {
        return personController;
    }

    public void setPersonController(PersonController personController) {
        this.personController = personController;
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    public void setCustomerController(CustomerController customerController) {
        this.customerController = customerController;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    public Customer getResCustomer() {
        return resCustomer;
    }

    public void setResCustomer(Customer resCustomer) {
        this.resCustomer = resCustomer;
    }

    public com.pcmis.facades.ReservationFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(com.pcmis.facades.ReservationFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getResCount() {
        return resCount;
    }

    public void setResCount(int resCount) {
        this.resCount = resCount;
    }

    @FacesConverter(forClass = Reservation.class)
    public static class ReservationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReservationController controller = (ReservationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reservationController");
            return controller.getFacade().find(getKey(value));
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
            if (object instanceof Reservation) {
                Reservation o = (Reservation) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reservation.class.getName()});
                return null;
            }
        }

    }

}
