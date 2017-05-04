/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcmis.facades;

import com.pcmis.entity.JobTitle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pasan
 */
@Stateless
public class JobTitleFacade extends AbstractFacade<JobTitle> {
    @PersistenceContext(unitName = "pcmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobTitleFacade() {
        super(JobTitle.class);
    }
    
}
