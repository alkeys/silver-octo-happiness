/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.consultas.viajes_itca.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author enoc
 */
@Entity
@Table(name = "favoritos")
@NamedQueries({
    @NamedQuery(name = "Favoritos.findAll", query = "SELECT f FROM Favoritos f"),
    @NamedQuery(name = "Favoritos.findByFavoritoId", query = "SELECT f FROM Favoritos f WHERE f.favoritoId = :favoritoId")})
public class Favoritos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "favorito_id")
    private Integer favoritoId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Usuarios userId;
    @JoinColumn(name = "destino_id", referencedColumnName = "destino_id")
    @ManyToOne
    private Destinos destinoId;

    public Favoritos() {
    }

    public Favoritos(Integer favoritoId) {
        this.favoritoId = favoritoId;
    }

    public Integer getFavoritoId() {
        return favoritoId;
    }

    public void setFavoritoId(Integer favoritoId) {
        this.favoritoId = favoritoId;
    }

    public Usuarios getUserId() {
        return userId;
    }

    public void setUserId(Usuarios userId) {
        this.userId = userId;
    }

    public Destinos getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Destinos destinoId) {
        this.destinoId = destinoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (favoritoId != null ? favoritoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favoritos)) {
            return false;
        }
        Favoritos other = (Favoritos) object;
        if ((this.favoritoId == null && other.favoritoId != null) || (this.favoritoId != null && !this.favoritoId.equals(other.favoritoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.consultas.viajes_itca.entity.Favoritos[ favoritoId=" + favoritoId + " ]";
    }
    
}
