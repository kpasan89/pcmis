package com.pcmis.controllers;

import com.pcmis.entity.Payment;
import com.pcmis.controllers.util.JsfUtil;
import com.pcmis.controllers.util.JsfUtil.PersistAction;
import com.pcmis.entity.Customer;
import com.pcmis.facades.CustomerFacade;
import com.pcmis.facades.PaymentFacade;

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

@ManagedBean(name = "paymentController")
@SessionScoped
public class PaymentController implements Serializable {

    @EJB
    private com.pcmis.facades.PaymentFacade ejbFacade;
    private List<Payment> items = null;
    private Payment selected;
    @EJB
    private CustomerFacade customerFacade;

    public PaymentController() {
    }

    public Payment getSelected() {
        return selected;
    }

    public void setSelected(Payment selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PaymentFacade getFacade() {
        return ejbFacade;
    }

    public Payment prepareCreate() {
        selected = new Payment();
        initializeEmbeddableKey();
        return selected;
    }
    
    private Customer payCustomer;
    
    public void selectPayCustomer() {
        String j = "select c from Customer c where c.retired=false and c.reservation=true and c.payment=false and c.id=:pc";
        Map m = new HashMap();
        m.put("pc", selected.getPay_customer().getId());
        payCustomer = getCustomerFacade().findFirstBySQL(j, m);
    }
    
    private int customerPoint;
    
    public void createNew(){
        if (selected != null) {
            setEmbeddableKeys();
            try {
                Payment p = new Payment();
                p.setPay_customer(selected.getPay_customer());
                p.setTicket_number(selected.getTicket_number());
                p.setValue_ticket(selected.getValue_ticket());
                
                selectPayCustomer();
                
                float a = selected.getValue_ticket();
                System.out.println("a = " + a);
                
                if(payCustomer.isPermenant() == true){
                    int permenentPoint = (int)  Math.round((a/1000));
                    System.out.println("permenent Point = " + permenentPoint);
                    customerPoint = payCustomer.getPointEarned() + permenentPoint;
                    System.out.println("customerPoint = " + customerPoint);
                }
                if(payCustomer.isPermenant() == false){
                    int nonPermentntPoint = (int) Math.round((a/10000));
                    System.out.println("non Permentnt Point = " + nonPermentntPoint);
                    customerPoint = payCustomer.getPointEarned() + nonPermentntPoint;
                    System.out.println("customerPoint = " + customerPoint);
                }
                
                if(customerPoint < 1000){
                    payCustomer.setCustomerCategory("Normal Customer");
                    payCustomer.setNormalCustomer(true);
                    payCustomer.setSilverCustomer(false);
                    payCustomer.setGoldCustomer(false);
                    payCustomer.setPlatinumCustomer(false);
                    System.out.println("payCustomer = " + payCustomer.getCustomerCategory());
                }
                if(customerPoint >= 1000 && customerPoint <= 2000){
                    payCustomer.setCustomerCategory("Silver Customer");
                    payCustomer.setNormalCustomer(false);
                    payCustomer.setSilverCustomer(true);
                    payCustomer.setGoldCustomer(false);
                    payCustomer.setPlatinumCustomer(false);
                    System.out.println("payCustomer = " + payCustomer.getCustomerCategory());
                }
                if(customerPoint >= 2001 && customerPoint <= 4000){
                    payCustomer.setCustomerCategory("Gold Customer");
                    payCustomer.setNormalCustomer(false);
                    payCustomer.setSilverCustomer(false);
                    payCustomer.setGoldCustomer(true);
                    payCustomer.setPlatinumCustomer(false);
                    System.out.println("payCustomer = " + payCustomer.getCustomerCategory());
                }
                if(customerPoint > 4000){
                    payCustomer.setCustomerCategory("Platinum Customer");
                    payCustomer.setNormalCustomer(false);
                    payCustomer.setSilverCustomer(false);
                    payCustomer.setGoldCustomer(false);
                    payCustomer.setPlatinumCustomer(true);
                    System.out.println("payCustomer = " + payCustomer.getCustomerCategory());
                }
                
                payCustomer.setPointEarned(customerPoint);
                payCustomer.setPayment(true);
                payCustomer.setReservation(false);
                getCustomerFacade().edit(payCustomer);
                getFacade().create(p);
                JsfUtil.addSuccessMessage("Payment was successfully Added");
                
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
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PaymentCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PaymentUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PaymentDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void retire(){
        if (selected != null) {
            setEmbeddableKeys();
            try {
                Payment p = new Payment();
                p.setPay_customer(selected.getPay_customer());
                p.setTicket_number(selected.getTicket_number());
                p.setValue_ticket(selected.getValue_ticket());
                
                selectPayCustomer();
                
                payCustomer.setPointEarned(customerPoint);
                payCustomer.setPayment(true);
                payCustomer.setReservation(false);
                getCustomerFacade().edit(payCustomer);
                getFacade().create(p);
                JsfUtil.addSuccessMessage("Payment was successfully Added");
                
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
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Payment> getItems() {
        if (items == null) {
            String jpql;
            jpql = "select p from Payment p  Where p.retired=false order by p.id desc";
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

    public List<Payment> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Payment> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    public Customer getPayCustomer() {
        return payCustomer;
    }

    public void setPayCustomer(Customer payCustomer) {
        this.payCustomer = payCustomer;
    }

    public int getCustomerPoint() {
        return customerPoint;
    }

    public void setCustomerPoint(int customerPoint) {
        this.customerPoint = customerPoint;
    }

    @FacesConverter(forClass = Payment.class)
    public static class PaymentControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaymentController controller = (PaymentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paymentController");
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
            if (object instanceof Payment) {
                Payment o = (Payment) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Payment.class.getName()});
                return null;
            }
        }

    }

}
