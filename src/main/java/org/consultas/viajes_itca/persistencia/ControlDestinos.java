package org.consultas.viajes_itca.persistencia;

import org.consultas.viajes_itca.entity.Destinos;

import javax.persistence.EntityManagerFactory;

public class ControlDestinos extends AbstractJpaController<Destinos> {


    public ControlDestinos(EntityManagerFactory emf, Class<Destinos> entityClass) {
        super(emf, entityClass);
    }

    @Override
    protected Object getId(Destinos entity) {
        return null;
    }
}
