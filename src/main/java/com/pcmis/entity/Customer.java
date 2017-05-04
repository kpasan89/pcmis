/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcmis.entity;

import com.pcmis.enums.Card;
import com.pcmis.enums.Country;
import com.pcmis.enums.CustomerType;
import com.pcmis.enums.Gender;
import com.pcmis.enums.Job;
import com.pcmis.enums.Meal;
import com.pcmis.enums.Nationality;
import com.pcmis.enums.Seat;
import com.pcmis.enums.Sport;
import com.pcmis.enums.Status;
import com.pcmis.enums.Title;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Pasan
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Title title;
    @Enumerated(EnumType.STRING)
    private Status maritial_status;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dom;
    private String first_name;
    private String family_name;
    private String full_name;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dob;
    @Enumerated(EnumType.STRING)
    private Nationality nationality;
    @Enumerated(EnumType.STRING)
    private Country country;
    private String address;
    private String bussiness_address;
    private String passport;
    private String nic;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date pp_issued_date;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date pp_expire_date;
    @Enumerated(EnumType.STRING)
    private CustomerType customer_type;
    @Enumerated(EnumType.STRING)
    private Job job_title;
    private String company_name;
    private String telepone;
    private String mobile;
    private String fax;
    private String email;
    @Enumerated(EnumType.STRING)
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private Sport sport;
    private String interest;
    @Enumerated(EnumType.STRING)
    private Meal meal;
    private boolean privilage_crd;
    @Enumerated(EnumType.STRING)
    private Card card_type;
    private String bussiness_bought;
    private String bussiness_tel;
    private String bussiness_email;
    private boolean retired = false;
    private boolean permenant = false;
    private int reservationCount;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    private Person creater;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date retiredAt;
    @ManyToOne
    private Person retirer;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pcmis.pack.entity.Customer[ id=" + id + " ]";
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Status getMaritial_status() {
        return maritial_status;
    }

    public void setMaritial_status(Status maritial_status) {
        this.maritial_status = maritial_status;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDom() {
        return dom;
    }

    public void setDom(Date dom) {
        this.dom = dom;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBussiness_address() {
        return bussiness_address;
    }

    public void setBussiness_address(String bussiness_address) {
        this.bussiness_address = bussiness_address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Date getPp_issued_date() {
        return pp_issued_date;
    }

    public void setPp_issued_date(Date pp_issued_date) {
        this.pp_issued_date = pp_issued_date;
    }

    public Date getPp_expire_date() {
        return pp_expire_date;
    }

    public void setPp_expire_date(Date pp_expire_date) {
        this.pp_expire_date = pp_expire_date;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getTelepone() {
        return telepone;
    }

    public void setTelepone(String telepone) {
        this.telepone = telepone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public boolean isPrivilage_crd() {
        return privilage_crd;
    }

    public void setPrivilage_crd(boolean privilage_crd) {
        this.privilage_crd = privilage_crd;
    }

    public Card getCard_type() {
        return card_type;
    }

    public void setCard_type(Card card_type) {
        this.card_type = card_type;
    }

    public String getBussiness_bought() {
        return bussiness_bought;
    }

    public void setBussiness_bought(String bussiness_bought) {
        this.bussiness_bought = bussiness_bought;
    }

    public String getBussiness_tel() {
        return bussiness_tel;
    }

    public void setBussiness_tel(String bussiness_tel) {
        this.bussiness_tel = bussiness_tel;
    }

    public String getBussiness_email() {
        return bussiness_email;
    }

    public void setBussiness_email(String bussiness_email) {
        this.bussiness_email = bussiness_email;
    }

    public CustomerType getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(CustomerType customer_type) {
        this.customer_type = customer_type;
    }

    public Job getJob_title() {
        return job_title;
    }

    public void setJob_title(Job job_title) {
        this.job_title = job_title;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }

    public boolean isPermenant() {
        return permenant;
    }

    public void setPermenant(boolean permenant) {
        this.permenant = permenant;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Person getCreater() {
        return creater;
    }

    public void setCreater(Person creater) {
        this.creater = creater;
    }

    public Date getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Date retiredAt) {
        this.retiredAt = retiredAt;
    }

    public Person getRetirer() {
        return retirer;
    }

    public void setRetirer(Person retirer) {
        this.retirer = retirer;
    }
}
