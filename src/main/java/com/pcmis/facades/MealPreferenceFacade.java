/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcmis.facades;

import com.pcmis.entity.MealPreference;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pasan
 */
@Stateless
public class MealPreferenceFacade extends AbstractFacade<MealPreference> {
    @PersistenceContext(unitName = "pcmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MealPreferenceFacade() {
        super(MealPreference.class);
    }
    
}
