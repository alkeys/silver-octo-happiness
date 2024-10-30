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


@Entity
@Table(name = "viajes_por_hacer")
@NamedQueries({
    @NamedQuery(name = "ViajesPorHacer.findAll", query = "SELECT v FROM ViajesPorHacer v"),
    @NamedQuery(name = "ViajesPorHacer.findByViajeId", query = "SELECT v FROM ViajesPorHacer v WHERE v.viajeId = :viajeId")})
public class ViajesPorHacer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "viaje_id")
    private Integer viajeId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Usuarios userId;
    @JoinColumn(name = "destino_id", referencedColumnName = "destino_id")
    @ManyToOne
    private Destinos destinoId;

    public ViajesPorHacer() {
    }

    public ViajesPorHacer(Integer viajeId) {
        this.viajeId = viajeId;
    }

    public Integer getViajeId() {
        return viajeId;
    }

    public void setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
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
        hash += (viajeId != null ? viajeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ViajesPorHacer)) {
            return false;
        }
        ViajesPorHacer other = (ViajesPorHacer) object;
        if ((this.viajeId == null && other.viajeId != null) || (this.viajeId != null && !this.viajeId.equals(other.viajeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.consultas.viajes_itca.entity.ViajesPorHacer[ viajeId=" + viajeId + " ]";
    }
    
}
