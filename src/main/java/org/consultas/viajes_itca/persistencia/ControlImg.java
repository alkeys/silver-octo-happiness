package org.consultas.viajes_itca.persistencia;

import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.ImgDestino;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ControlImg  extends AbstractJpaController<ImgDestino> {
    private  EntityManagerFactory emf;
     private ImgDestinoJpaController imgDestinoJpaController;
    public ControlImg(EntityManagerFactory emf, Class<ImgDestino> entityClass) {
        super(emf, entityClass);
        this.emf = emf;
    }

    @Override
    protected Object getId(ImgDestino entity) {
        return null;
    }

    public List<ImgDestino> findImgDestinos(List<Destinos> destinos) {
        imgDestinoJpaController = new ImgDestinoJpaController(emf);
        return imgDestinoJpaController.findImgDestinos(destinos);
    }
}
