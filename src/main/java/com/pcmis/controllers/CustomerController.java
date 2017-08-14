package com.pcmis.controllers;

import com.pcmis.entity.Customer;
import com.pcmis.controllers.util.JsfUtil;
import com.pcmis.controllers.util.JsfUtil.PersistAction;
import com.pcmis.entity.JobTitle;
import com.pcmis.entity.MealPreference;
import com.pcmis.enums.Gender;
import com.pcmis.enums.Title;
import com.pcmis.facades.CustomerFacade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@ManagedBean(name = "customerController")
@SessionScoped
public class CustomerController implements Serializable {

    @EJB
    private com.pcmis.facades.CustomerFacade ejbFacade;
    private List<Customer> items = null;
    private Customer selected;
    private PersonController personController;
    private CommonController commonController;
    private Date fromDate;
    private Date toDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne
    private JobTitle job_title;
    @ManyToOne
    private MealPreference meal_preference;
    @Enumerated(EnumType.STRING)
    private Title title;
    private Date dob;
    private Date dom;
    @ManyToOne
    private Customer introduced_customer;
    private Date startDate;
    private Date endDate;
    

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
                    c.setCreatedAt(new Date());
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
                c.setCreatedAt(new Date());
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

    public void viewCustomerPage() {
        String jpql;
        jpql = "select c from Customer c  Where c.retired=false order by c.id desc";
        items = getFacade().findBySQL(jpql);
    }

    public void viewIntroducedCustomerPage() {
        String jpql;
        jpql = "select c from Customer c  Where c.retired=false order by c.id desc";
        items = getFacade().findBySQL(jpql);
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

    private List<Customer> customersByGender = null;
    public String reportCustomerByGender() {
        String jpql;
        Map m = new HashMap();
        m.put("gen", gender);
        jpql = "select c from Customer c Where c.retired=false and c.gender=:gen";
        customersByGender = getFacade().findBySQL(jpql, m);
        return "report_customer_by_gender";
    }
    
    private List<Customer> customersByJobTitle = null;
    public String reportCustomerByJobTitle() {
        String jpql;
        Map m = new HashMap();
        m.put("job", job_title);
        jpql = "select c from Customer c Where c.retired=false and c.job_title=:job";
        customersByJobTitle = getFacade().findBySQL(jpql, m);
        return "report_customer_by_job_title";
    }
    
    private List<Customer> customersByMeal = null;
    public String reportCustomerByMeal() {
        String jpql;
        Map m = new HashMap();
        m.put("meal", meal_preference);
        jpql = "select c from Customer c Where c.retired=false and c.meal=:meal";
        customersByMeal = getFacade().findBySQL(jpql, m);
        return "report_customer_by_meal_preference";
    }
    
    private List<Customer> customersByTitle = null;
    public String reportCustomerByTitle() {
        String jpql;
        Map m = new HashMap();
        m.put("title", title);
        jpql = "select c from Customer c Where c.retired=false and c.title=:title";
        customersByTitle = getFacade().findBySQL(jpql, m);
        return "report_customer_by_title";
    }
    
    private List<Customer> customersByPPExpiryDate = null;
    public String reportCustomerByPassportExpiryRange() {
        String jpql;
        Map m = new HashMap();
        m.put("fwdt", fromDate);
        m.put("tdt", toDate);
        jpql = "select c from Customer c Where c.retired=false and c.pp_expire_date between :fwdt and :tdt";
        customersByPPExpiryDate = getFacade().findBySQL(jpql, m);
        return "report_customer_by_passport_expiry_date";
    }
    
    private List<Customer> customersByDob = null;
    public String reportCustomerByDOB() {
        String jpql;
        Map m = new HashMap();
        m.put("dob", dob);
        jpql = "select c from Customer c Where c.retired=false and c.dob=:dob";
        customersByDob = getFacade().findBySQL(jpql, m);
        return "report_customer_by_dob";
    }
    
    private List<Customer> customersByAniiversary = null;
    public String reportCustomerByAnniversary() {
        String jpql;
        Map m = new HashMap();
        m.put("dom", dom);
        jpql = "select c from Customer c Where c.retired=false and c.dom=:dom";
        customersByAniiversary = getFacade().findBySQL(jpql, m);
        return "report_customer_by_dom";
    }
    
    private List<Customer> customersByIntroducedCustomers = null;
    public String reportCustomerByIntroducedCustomer() {
        String jpql;
        Map m = new HashMap();
        m.put("ic", introduced_customer);
        jpql = "select c from Customer c Where c.retired=false and c.introducedCustomer=:ic";
        customersByIntroducedCustomers = getFacade().findBySQL(jpql, m);
        return "report_customer_by_introduced_customer";
    }
    
    private List<Customer> permenantCustomersByYear = null;
    public String reportPermenantCustomerByYear() {
        String jpql;
        Map m = new HashMap();
        m.put("sdt", startDate);
        m.put("edt", endDate);
        jpql = "select c from Customer c Where c.retired=false and c.permenant=true and c.createdAt between :sdt and :edt";
        permenantCustomersByYear = getFacade().findBySQL(jpql, m);
        return "report_permenant_customer_by_year";
    }
    
    private List<Customer> registeredCustomersByYear = null;
    public String reportRegisteredCustomerByYear() {
        String jpql;
        Map m = new HashMap();
        m.put("sdt", startDate);
        m.put("edt", endDate);
        jpql = "select c from Customer c Where c.retired=false and c.permenant=false and c.createdAt between :sdt and :edt";
        registeredCustomersByYear = getFacade().findBySQL(jpql, m);
        return "report_registered_customer_by_year";
    }
    
    private List<Customer> customersByYear = null;
    public String reportCustomerByYear() {
        String jpql;
        Map m = new HashMap();
        m.put("sdt", startDate);
        m.put("edt", endDate);
        jpql = "select c from Customer c Where c.retired=false and c.createdAt between :sdt and :edt";
        customersByYear = getFacade().findBySQL(jpql, m);
        return "report_customer_by_year";
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public JobTitle getJob_title() {
        return job_title;
    }

    public void setJob_title(JobTitle job_title) {
        this.job_title = job_title;
    }

    public MealPreference getMeal_preference() {
        return meal_preference;
    }

    public void setMeal_preference(MealPreference meal_preference) {
        this.meal_preference = meal_preference;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDom() {
        return dom;
    }

    public void setDom(Date dom) {
        this.dom = dom;
    }

    public List<Customer> getCustomersByGender() {
        return customersByGender;
    }

    public void setCustomersByGender(List<Customer> customersByGender) {
        this.customersByGender = customersByGender;
    }

    public List<Customer> getCustomersByJobTitle() {
        return customersByJobTitle;
    }

    public void setCustomersByJobTitle(List<Customer> customersByJobTitle) {
        this.customersByJobTitle = customersByJobTitle;
    }

    public List<Customer> getCustomersByMeal() {
        return customersByMeal;
    }

    public void setCustomersByMeal(List<Customer> customersByMeal) {
        this.customersByMeal = customersByMeal;
    }

    public List<Customer> getCustomersByTitle() {
        return customersByTitle;
    }

    public void setCustomersByTitle(List<Customer> customersByTitle) {
        this.customersByTitle = customersByTitle;
    }

    public List<Customer> getCustomersByPPExpiryDate() {
        return customersByPPExpiryDate;
    }

    public void setCustomersByPPExpiryDate(List<Customer> customersByPPExpiryDate) {
        this.customersByPPExpiryDate = customersByPPExpiryDate;
    }

    public List<Customer> getCustomersByDob() {
        return customersByDob;
    }

    public void setCustomersByDob(List<Customer> customersByDob) {
        this.customersByDob = customersByDob;
    }

    public List<Customer> getCustomersByAniiversary() {
        return customersByAniiversary;
    }

    public void setCustomersByAniiversary(List<Customer> customersByAniiversary) {
        this.customersByAniiversary = customersByAniiversary;
    }

    public Customer getIntroduced_customer() {
        return introduced_customer;
    }

    public void setIntroduced_customer(Customer introduced_customer) {
        this.introduced_customer = introduced_customer;
    }

    public List<Customer> getCustomersByIntroducedCustomers() {
        return customersByIntroducedCustomers;
    }

    public void setCustomersByIntroducedCustomers(List<Customer> customersByIntroducedCustomers) {
        this.customersByIntroducedCustomers = customersByIntroducedCustomers;
    }

    public Date getStartDate() {
        if (startDate == null) {
            Calendar c = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            c.set(Calendar.YEAR, today.get(Calendar.YEAR));
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            startDate = c.getTime();
        }
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        if (endDate == null) {
            Calendar c = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            c.set(Calendar.YEAR, today.get(Calendar.YEAR));
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            endDate = c.getTime();
        }
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Customer> getPermenantCustomersByYear() {
        return permenantCustomersByYear;
    }

    public void setPermenantCustomersByYear(List<Customer> permenantCustomersByYear) {
        this.permenantCustomersByYear = permenantCustomersByYear;
    }

    public CommonController getCommonController() {
        return commonController;
    }

    public void setCommonController(CommonController commonController) {
        this.commonController = commonController;
    }

    public List<Customer> getRegisteredCustomersByYear() {
        return registeredCustomersByYear;
    }

    public void setRegisteredCustomersByYear(List<Customer> registeredCustomersByYear) {
        this.registeredCustomersByYear = registeredCustomersByYear;
    }

    public List<Customer> getCustomersByYear() {
        return customersByYear;
    }

    public void setCustomersByYear(List<Customer> customersByYear) {
        this.customersByYear = customersByYear;
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
