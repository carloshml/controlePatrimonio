/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.LocalBemMaterial;

/**
 *
 * @author JVictor
 */
@Stateless
public class LocalBemMaterialFacade extends AbstractFacade<LocalBemMaterial> {
    @PersistenceContext(unitName = "controlepatrimonioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalBemMaterialFacade() {
        super(LocalBemMaterial.class);
    }
    
}
