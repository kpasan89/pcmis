package com.pcmis.controllers;

import com.pcmis.entity.Customer;
import com.pcmis.controllers.util.JsfUtil;
import com.pcmis.controllers.util.JsfUtil.PersistAction;
import com.pcmis.facades.CustomerFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

@ManagedBean(name = "customerController")
@SessionScoped
public class CustomerController implements Serializable {

    @EJB
    private com.pcmis.facades.CustomerFacade ejbFacade;
    private List<Customer> items = null;
    private Customer selected;
    private PersonController personController;

    public CustomerController() {
    }

    public Customer getSelected() {
        return selected;
    }

    public void setSelected(Customer selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CustomerFacade getFacade() {
        return ejbFacade;
    }

    public Customer prepareCreate() {
        selected = new Customer();
        initializeEmbeddableKey();
        return selected;
    }

    public void createNew() {
        if (selected != null) {
            setEmbeddableKeys();
            try {

                if (selected.getFirst_name() == null) {
                    JsfUtil.addErrorMessage("No First name");
                }
                if (selected.getFamily_name() == null) {
                    JsfUtil.addErrorMessage("No Last name");
                }
                if (selected.getPassport() == null) {
                    JsfUtil.addErrorMessage("No Passport number");
                }
                if (selected.getGender() == null) {
                    JsfUtil.addErrorMessage("No Gender");
                } else {
                    Customer c = new Customer();
                    c.setAddress(selected.getAddress());
                    c.setBussiness_address(selected.getBussiness_address());
                    c.setBussiness_bought(selected.getBussiness_bought());
                    c.setBussiness_email(selected.getBussiness_email());
                    c.setBussiness_tel(selected.getBussiness_tel());
                    c.setCard_type(selected.getCard_type());
                    c.setCompany_name(selected.getCompany_name());
                    c.setCountry(selected.getCountry());
                    c.setPermenant(false);
                    c.setCustomer_type(selected.getCustomer_type());
                    c.setDob(selected.getDob());
                    c.setDom(selected.getDom());
                    c.setEmail(selected.getEmail());
                    c.setFamily_name(selected.getFamily_name());
                    c.setFax(selected.getFax());
                    c.setFirst_name(selected.getFirst_name());
                    c.setFull_name(selected.getFull_name());
                    c.setGender(selected.getGender());
                    c.setInterest(selected.getInterest());
                    c.setJob_title(selected.getJob_title());
                    c.setMaritial_status(selected.getMaritial_status());
                    c.setMeal(selected.getMeal());
                    c.setMobile(selected.getMobile());
                    c.setNationality(selected.getNationality());
                    c.setNic(selected.getNic());
                    c.setPassport(selected.getPassport());
                    c.setPp_expire_date(selected.getPp_expire_date());
                    c.setPp_issued_date(selected.getPp_issued_date());
                    c.setPrivilage_crd(selected.isPrivilage_crd());
                    c.setReservationCount(0);
                    c.setRetired(false);
                    c.setSelfCustomer(true);
                    c.setSeat(selected.getSeat());
                    c.setSport(selected.getSport());
                    c.setTelepone(selected.getTelepone());
                    c.setTitle(selected.getTitle());
                    c.setIntroducedCustomer("Self Customer");
                //c.setCreatedAt(new Date());
                    //c.setCreater(getPersonController().getLoggedPerson());
                    getFacade().create(c);
                    JsfUtil.addSuccessMessage("Customer was successfully created");
                }

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

    public void createNewIntroducedCustomer() {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                Customer c = new Customer();
                c.setAddress(selected.getAddress());
                c.setBussiness_address(selected.getBussiness_address());
                c.setBussiness_bought(selected.getBussiness_bought());
                c.setBussiness_email(selected.getBussiness_email());
                c.setBussiness_tel(selected.getBussiness_tel());
                c.setCard_type(selected.getCard_type());
                c.setCompany_name(selected.getCompany_name());
                c.setCountry(selected.getCountry());
                c.setPermenant(false);
                c.setCustomer_type(selected.getCustomer_type());
                c.setDob(selected.getDob());
                c.setDom(selected.getDom());
                c.setEmail(selected.getEmail());
                c.setFamily_name(selected.getFamily_name());
                c.setFax(selected.getFax());
                c.setFirst_name(selected.getFirst_name());
                c.setFull_name(selected.getFull_name());
                c.setGender(selected.getGender());
                c.setInterest(selected.getInterest());
                c.setJob_title(selected.getJob_title());
                c.setMaritial_status(selected.getMaritial_status());
                c.setMeal(selected.getMeal());
                c.setMobile(selected.getMobile());
                c.setNationality(selected.getNationality());
                c.setNic(selected.getNic());
                c.setPassport(selected.getPassport());
                c.setPp_expire_date(selected.getPp_expire_date());
                c.setPp_issued_date(selected.getPp_issued_date());
                c.setPrivilage_crd(selected.isPrivilage_crd());
                c.setReservationCount(0);
                c.setRetired(false);
                c.setSelfCustomer(false);
                c.setSeat(selected.getSeat());
                c.setSport(selected.getSport());
                c.setTelepone(selected.getTelepone());
                c.setTitle(selected.getTitle());
                c.setIntroducedCustomer(selected.getIntroducedCustomer());
                //c.setCreatedAt(new Date());
                //c.setCreater(getPersonController().getLoggedPerson());
                getFacade().create(c);
                JsfUtil.addSuccessMessage("Customer was successfully created");

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
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CustomerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CustomerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CustomerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Customer> getItems() {
        if (items == null) {
            String jpql;
            jpql = "select c from Customer c  Where c.retired=false order by c.id desc";
            items = getFacade().findBySQL(jpql);
        }
        return items;
    }

    public String viewCustomerPage() {
//        if (items == null) {
//            items = getFacade().findAll();
//        }
        String jpql;
        jpql = "select c from Customer c  Where c.retired=false order by c.id desc";
        items = getFacade().findBySQL(jpql);

        return "/customer/List.xhtml";
    }
    
    public String viewIntroducedCustomerPage() {
//        if (items == null) {
//            items = getFacade().findAll();
//        }
        String jpql;
        jpql = "select c from Customer c  Where c.retired=false order by c.id desc";
        items = getFacade().findBySQL(jpql);

        return "/introduced_customer/List.xhtml";
    }

    public List<Customer> completeCustomers(String qry) {
        String temSql;
        temSql = "SELECT c FROM Customer c where c.retired=false and LOWER(c.full_name) like '%" + qry.toLowerCase() + "%' order by c.full_name";
        return getFacade().findBySQL(temSql);
    }

    private List<String> introducedCustomerList;

    public List<String> completeIntroducedCustomers(String qry) {
        String temSql;
        temSql = "SELECT c.full_name FROM Customer c where c.retired=false and LOWER(c.full_name) like '%" + qry.toLowerCase() + "%' order by c.full_name";
        introducedCustomerList = getFacade().findString(temSql);
        return introducedCustomerList;
    }

    public List<Customer> completePayCustomers(String qry) {
        String temSql;
        temSql = "SELECT c FROM Customer c where c.retired=false and c.reservation=true and c.payment=false and LOWER(c.full_name) like '%" + qry.toLowerCase() + "%' order by c.full_name";
        return getFacade().findBySQL(temSql);
    }

    public String createReservation() {
        return "/customerReservation/List.xhtml";
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

    public List<Customer> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Customer> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public PersonController getPersonController() {
        if (personController == null) {
            personController = new PersonController();
        }
        return personController;
    }

    public void setPersonController(PersonController personController) {
        this.personController = personController;
    }

    public List<String> getIntroducedCustomerList() {
        return introducedCustomerList;
    }

    public void setIntroducedCustomerList(List<String> introducedCustomerList) {
        this.introducedCustomerList = introducedCustomerList;
    }

    @FacesConverter(forClass = Customer.class)
    public static class CustomerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CustomerController controller = (CustomerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "customerController");
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
            if (object instanceof Customer) {
                Customer o = (Customer) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Customer.class.getName()});
                return null;
            }
        }

    }

}
