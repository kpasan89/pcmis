/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcmis.entity;

import com.pcmis.enums.ExpenseType;
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
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Customer pay_customer;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
    private String ticket_number;
    private float value_ticket;
    private float points;
    
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
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pcmis.pack.entity.Payment[ id=" + id + " ]";
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public float getValue_ticket() {
        return value_ticket;
    }

    public void setValue_ticket(float value_ticket) {
        this.value_ticket = value_ticket;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public Customer getPay_customer() {
        return pay_customer;
    }

    public void setPay_customer(Customer pay_customer) {
        this.pay_customer = pay_customer;
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

    public String getTicket_number() {
        return ticket_number;
    }

    public void setTicket_number(String ticket_number) {
        this.ticket_number = ticket_number;
    }
}
