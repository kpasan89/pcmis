package com.pcmis.controllers;

import com.pcmis.entity.Person;
import com.pcmis.controllers.util.JsfUtil;
import com.pcmis.controllers.util.JsfUtil.PersistAction;
import com.pcmis.entity.Customer;
import com.pcmis.enums.Appoinment;
import com.pcmis.facades.CustomerFacade;
import com.pcmis.facades.PersonFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "personController")
@SessionScoped
public class PersonController implements Serializable {

    @EJB
    private com.pcmis.facades.PersonFacade ejbFacade;
    @EJB
    private CustomerFacade customerFacade;
    private List<Person> items = null;
    private Person selected;

    private Person loggedPerson;
    private boolean logged;
    private String username;
    private String password;
    private String addPassword;
    private String addCunfirmPassword;
    private PersonController personController;

    private boolean systemAdministrator = false;
    private boolean ticketingOfficer = false;
    private boolean operator = false;
    private boolean accountant = false;
    private boolean officerCommanding = false;
    private CustomerController customerController;

    public PersonController() {
    }

    public String login() {

        if (!getUsername().trim().matches("\\p{Alnum}*")) {
            logged = false;
            loggedPerson = null;
            JsfUtil.addErrorMessage("Invalid Characters");
            return "index";
        }

        if (getFacade().count() == 0) {
            Person p = new Person();
            p.setFull_name(username);
            p.setUsername(username);
            p.setAppoinment(Appoinment.System_Administrator);
            p.setPassword(CommonController.makeHash(password));
            getFacade().create(p);
            loggedPerson = p;
            logged = true;
        }
        String j = "select p from Person p where p.retired=false and lower(p.username)=:un";
        Map m = new HashMap();
        m.put("un", username.toLowerCase());
        Person p = getFacade().findFirstBySQL(j, m);
        if (p == null) {
            logged = false;
            loggedPerson = null;
            JsfUtil.addErrorMessage("No such user");
            return "index";
        }
        if (getUsername() == null || getPassword() == null) {
            logged = false;
            loggedPerson = null;
            JsfUtil.addErrorMessage("Empty Fields");
            return "index";
        }
        if (CommonController.checkPassword(password, p.getPassword())) {
            logged = true;
            loggedPerson = p;
            customerCounts();
            JsfUtil.addSuccessMessage("Logged Successfully");
            if (p.getAppoinment().equals(Appoinment.System_Administrator)) {
                systemAdministrator = true;
            } else if (p.getAppoinment().equals(Appoinment.Accountant)) {
                accountant = true;
            } else if (p.getAppoinment().equals(Appoinment.Operator)) {
                operator = true;
            } else if (p.getAppoinment().equals(Appoinment.Officer_Commanding)) {
                officerCommanding = true;
            } else if (p.getAppoinment().equals(Appoinment.Ticketing_Officer)) {
                ticketingOfficer = true;
            }
        } else {
            logged = false;
            loggedPerson = null;
            username = null;
            JsfUtil.addErrorMessage("Wrong Username or Password");
            return "index";
        }
        return "index";
    }

    public String logout() {
        logged = false;
        loggedPerson = null;
        username = null;
        systemAdministrator = false;
        accountant = false;
        operator = false;
        ticketingOfficer = false;
        officerCommanding = false;
        return "/index";
    }
    
    public void customerCounts(){
        nonReservationCustomerCount();
        regularCustomerCount();
        silverCustomerCount();
        goldCustomerCount();
        platinumCustomerCount();
        registeredCustomerCount();
        permenantCustomerCount();
        reservedCustomerCount();
    }
    
    private long nonReservationCount;
    public void nonReservationCustomerCount(){
        String jpql;
        jpql = "select count(c) from Customer c Where c.retired=false and c.normalCustomer=false and c.silverCustomer=false and c.goldCustomer=false and c.platinumCustomer=false";
        nonReservationCount = getCustomerFacade().countBySql(jpql);
    }
    
    private long regularCount;
    public void regularCustomerCount(){
        String jpql;
        jpql = "select count(c) from Customer c Where c.retired=false and c.normalCustomer=true";
        regularCount = getCustomerFacade().countBySql(jpql);
    }
    
    private long silverCount;
    public void silverCustomerCount(){
        String jpql;
        jpql = "select count(c) from Customer c Where c.retired=false and c.silverCustomer=true";
        silverCount = getCustomerFacade().countBySql(jpql);
    }
    
    private long goldCount;
    public void goldCustomerCount(){
        String jpql;
        jpql = "select count(c) from Customer c Where c.retired=false and c.goldCustomer=true";
        goldCount = getCustomerFacade().countBySql(jpql);
    }
    
    private long platinumCount;
    public void platinumCustomerCount(){
        String jpql;
        jpql = "select count(c) from Customer c Where c.retired=false and c.platinumCustomer=true";
        platinumCount = getCustomerFacade().countBySql(jpql);
    }
    
    private long registeredCount;
    public void registeredCustomerCount(){
        String jpql;
        jpql = "select count(c) from Customer c Where c.retired=false and c.permenant=false";
        registeredCount = getCustomerFacade().countBySql(jpql);
    }
    
    private long permenantCount;
    public void permenantCustomerCount(){
        String jpql;
        jpql = "select count(c) from Customer c Where c.retired=false and c.permenant=true";
        permenantCount = getCustomerFacade().countBySql(jpql);
    }
    
    private long reservedCount;
    public void reservedCustomerCount(){
        String jpql;
        jpql = "select count(c) from Customer c Where c.retired=false and c.reservation=true and c.payment=false";
        reservedCount = getCustomerFacade().countBySql(jpql);
    }

    public Person getSelected() {
        return selected;
    }

    public void setSelected(Person selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonFacade getFacade() {
        return ejbFacade;
    }

    public Person prepareCreate() {
        selected = new Person();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (!selected.getUsername().trim().matches("\\p{Alnum}*")) {
                    JsfUtil.addErrorMessage("User name contains with special characters. Please remove special characters");
                } else if (!userNameAvailable(selected.getUsername())) {
                    JsfUtil.addErrorMessage("User name already exists. Please enter another username");
                } else if (!selected.getPassword().equals(selected.getCunfirm_password())) {
                    JsfUtil.addErrorMessage("Password and Re-entered password are not matching");
                } else {

                    addPassword = CommonController.makeHash(selected.getPassword());
                    addCunfirmPassword = CommonController.makeHash(selected.getCunfirm_password());

                    Person p = new Person();
                    p.setFull_name(selected.getFull_name());
                    p.setAppoinment(selected.getAppoinment());
                    p.setJoined_date(selected.getJoined_date());
                    p.setUsername(selected.getUsername());
                    p.setPassword(addPassword);
                    p.setCunfirm_password(addCunfirmPassword);
                    p.setCreatedAt(new Date());
                    getFacade().create(p);
                    JsfUtil.addSuccessMessage("Person was successfully created");
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

    public Boolean userNameAvailable(String username) {
        Boolean available = true;
        List<Person> allUsers = getFacade().findAll();
        for (Person p : allUsers) {
            if (username.toLowerCase().equals(p.getUsername())) {
                available = false;
            }
        }
        return available;
    }

    public void createOld() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Person> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Person> completePersons(String qry) {
        String temSql;
        temSql = "SELECT p FROM Person p where p.retired=false and LOWER(p.full_name) like '%" + qry.toLowerCase() + "%' order by p.full_name";
        return getFacade().findBySQL(temSql);
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

    public List<Person> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Person> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Person getLoggedPerson() {
        if (loggedPerson == null) {
            loggedPerson = new Person();
        }
        return loggedPerson;
    }

    public void setLoggedPerson(Person loggedPerson) {
        this.loggedPerson = loggedPerson;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddPassword() {
        return addPassword;
    }

    public void setAddPassword(String addPassword) {
        this.addPassword = addPassword;
    }

    public String getAddCunfirmPassword() {
        return addCunfirmPassword;
    }

    public void setAddCunfirmPassword(String addCunfirmPassword) {
        this.addCunfirmPassword = addCunfirmPassword;
    }

    public boolean isSystemAdministrator() {
        return systemAdministrator;
    }

    public void setSystemAdministrator(boolean systemAdministrator) {
        this.systemAdministrator = systemAdministrator;
    }

    public boolean isOperator() {
        return operator;
    }

    public void setOperator(boolean operator) {
        this.operator = operator;
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

    public boolean isTicketingOfficer() {
        return ticketingOfficer;
    }

    public void setTicketingOfficer(boolean ticketingOfficer) {
        this.ticketingOfficer = ticketingOfficer;
    }

    public boolean isAccountant() {
        return accountant;
    }

    public void setAccountant(boolean accountant) {
        this.accountant = accountant;
    }

    public boolean isOfficerCommanding() {
        return officerCommanding;
    }

    public void setOfficerCommanding(boolean officerCommanding) {
        this.officerCommanding = officerCommanding;
    }

    public CustomerController getCustomerController() {
        if(customerController == null){
            customerController = new CustomerController();
        }
        return customerController;
    }

    public void setCustomerController(CustomerController customerController) {
        this.customerController = customerController;
    }

    public long getRegularCount() {
        return regularCount;
    }

    public void setRegularCount(long regularCount) {
        this.regularCount = regularCount;
    }

    public long getSilverCount() {
        return silverCount;
    }

    public void setSilverCount(long silverCount) {
        this.silverCount = silverCount;
    }

    public long getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(long goldCount) {
        this.goldCount = goldCount;
    }

    public long getPlatinumCount() {
        return platinumCount;
    }

    public void setPlatinumCount(long platinumCount) {
        this.platinumCount = platinumCount;
    }

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    public long getNonReservationCount() {
        return nonReservationCount;
    }

    public void setNonReservationCount(long nonReservationCount) {
        this.nonReservationCount = nonReservationCount;
    }

    public long getRegisteredCount() {
        return registeredCount;
    }

    public void setRegisteredCount(long registeredCount) {
        this.registeredCount = registeredCount;
    }

    public long getPermenantCount() {
        return permenantCount;
    }

    public void setPermenantCount(long permenantCount) {
        this.permenantCount = permenantCount;
    }

    public long getReservedCount() {
        return reservedCount;
    }

    public void setReservedCount(long reservedCount) {
        this.reservedCount = reservedCount;
    }

    @FacesConverter(forClass = Person.class)
    public static class PersonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonController controller = (PersonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personController");
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
            if (object instanceof Person) {
                Person o = (Person) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Person.class.getName()});
                return null;
            }
        }

    }

}
