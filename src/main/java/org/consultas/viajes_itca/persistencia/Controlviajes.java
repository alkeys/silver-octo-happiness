package org.consultas.viajes_itca.persistencia;

import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.entity.ViajesPorHacer;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Controlviajes extends AbstractJpaController<ViajesPorHacer> {
    private EntityManagerFactory emf = null;
    private ViajesPorHacerJpaController controlViajes = null;

    public Controlviajes(EntityManagerFactory emf, Class<ViajesPorHacer> entityClass) {
        super(emf, entityClass);
        this.emf = emf;
    }


    @Override
    protected Object getId(ViajesPorHacer entity) {
        return null;
    }

    public ViajesPorHacer findViajesPorHacer(Integer userId, Integer destinoId) {
        controlViajes = new ViajesPorHacerJpaController(emf);
        return controlViajes.findViajesPorHacer(userId, destinoId);
    }

    public List<ViajesPorHacer> findViajesPorHacerList(Usuarios usuario) {
        controlViajes = new ViajesPorHacerJpaController(emf);
        return controlViajes.findViajesPorHacerList(usuario);
    }
    /**
     * este metoo devolvera la cantidad de usuarios que tienen el destino en sus viajes por hacer
     * @param destinoId
     * @return
     */
    public int findCantidadIdusuariosDestino(Destinos destinoId) {
        controlViajes = new ViajesPorHacerJpaController(emf);
        return (int) controlViajes.findCantidadIdusuariosDestino(destinoId);
    }
}
