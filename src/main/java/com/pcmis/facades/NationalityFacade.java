/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcmis.facades;

import com.pcmis.entity.Nationality;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pasan
 */
@Stateless
public class NationalityFacade extends AbstractFacade<Nationality> {
    @PersistenceContext(unitName = "pcmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NationalityFacade() {
        super(Nationality.class);
    }
    
}
