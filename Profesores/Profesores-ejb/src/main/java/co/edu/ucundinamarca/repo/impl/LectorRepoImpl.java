/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo.impl;

import co.edu.ucundinamarca.entity.Lector;
import co.edu.ucundinamarca.repo.AbstractFacade;
import co.edu.ucundinamarca.repo.IAutorLectorRepo;
import co.edu.ucundinamarca.repo.ILectorRepo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PROFESIONAL
 */
@Stateless
public class LectorRepoImpl extends AbstractFacade<Lector, Integer> implements ILectorRepo{
    @PersistenceContext(unitName = "co.edu.ucundinamarca_Profesores-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    public LectorRepoImpl() {
        super(Lector.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entity;
    }
}
