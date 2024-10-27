package org.consultas.viajes_itca.persistencia;

import javax.persistence.EntityManagerFactory;
import org.consultas.viajes_itca.entity.Usuarios;

public class ControlUsuario extends AbstractJpaController<Usuarios>{
    private  EntityManagerFactory emf;
   private  UsuariosJpaController control=null;

    public ControlUsuario(EntityManagerFactory emf, Class<Usuarios> entityClass) {
        super(emf, entityClass);
        this.emf = emf;
        control = new UsuariosJpaController(emf);
    }

    @Override
    protected Object getId(Usuarios entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public Usuarios findUsuariosEmail(String email) {
        return control.findUsuarios(email);
    }

    public Usuarios findUsuariosEmailPass(String email, String password) {
        return control.finUsuarios(email, password);
    }

    public long findUsuariosCount() {
        return control.getUsuariosCount();
    }
}
