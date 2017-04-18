/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcmis.entity;

import com.pcmis.enums.ExpenseType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    private List<Integer> ticket_number;
    private float value_ticket;
    private float points;

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

    public List<Integer> getTicket_number() {
        return ticket_number;
    }

    public void setTicket_number(List<Integer> ticket_number) {
        this.ticket_number = ticket_number;
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
    
}
