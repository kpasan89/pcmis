/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcmis.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
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
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Customer res_customer;
    private String customerName;
    private int adult_count;
    private int kids_count;
    @ManyToOne
    private Airport dep_airport;
    @ManyToOne
    private Airport arr_airport;
    @ManyToOne
    private Airline preff_airline;
    @ManyToOne
    private AirlineClass preff_class;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date travel_date;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date return_date;
    private boolean retired = false;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    private Person creater;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date retiredAt;
    @ManyToOne
    private Person retirer;
    private boolean payment = false;
    private boolean reservation = false;

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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pcmis.pack.entity.Reservation[ id=" + id + " ]";
    }

    public int getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(int adult_count) {
        this.adult_count = adult_count;
    }

    public int getKids_count() {
        return kids_count;
    }

    public void setKids_count(int kids_count) {
        this.kids_count = kids_count;
    }

    public Airport getDep_airport() {
        return dep_airport;
    }

    public void setDep_airport(Airport dep_airport) {
        this.dep_airport = dep_airport;
    }

    public Airport getArr_airport() {
        return arr_airport;
    }

    public void setArr_airport(Airport arr_airport) {
        this.arr_airport = arr_airport;
    }

    public Airline getPreff_airline() {
        return preff_airline;
    }

    public void setPreff_airline(Airline preff_airline) {
        this.preff_airline = preff_airline;
    }

    public AirlineClass getPreff_class() {
        return preff_class;
    }

    public void setPreff_class(AirlineClass preff_class) {
        this.preff_class = preff_class;
    }

    public Date getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(Date travel_date) {
        this.travel_date = travel_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public Customer getRes_customer() {
        if(res_customer == null){
            res_customer = new Customer();
        }
        return res_customer;
    }

    public void setRes_customer(Customer res_customer) {
        this.res_customer = res_customer;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
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

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
}
