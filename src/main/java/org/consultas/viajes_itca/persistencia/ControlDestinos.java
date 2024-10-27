package org.consultas.viajes_itca.persistencia;

import org.consultas.viajes_itca.entity.Destinos;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ControlDestinos extends AbstractJpaController<Destinos> {
    private  EntityManagerFactory emf;
    private DestinosJpaController destinosJpaController;
    public ControlDestinos(EntityManagerFactory emf, Class<Destinos> entityClass) {
        super(emf, entityClass);
        this.emf=emf;
    }

    @Override
    protected Object getId(Destinos entity) {
        return null;
    }


    public List<Destinos> findDestinosMasValorados() {
     destinosJpaController = new DestinosJpaController(emf);
        return destinosJpaController.findDestinosMasValorados();
    }

    public List<Destinos> findDestinosMasValorados(int i) {
        destinosJpaController = new DestinosJpaController(emf);
        return destinosJpaController.findDestinosMasValorados(i);
    }

}
