/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.consultas.viajes_itca.entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "destinos")
@NamedQueries({
    @NamedQuery(name = "Destinos.findAll", query = "SELECT d FROM Destinos d"),
    @NamedQuery(name = "Destinos.findByDestinoId", query = "SELECT d FROM Destinos d WHERE d.destinoId = :destinoId"),
    @NamedQuery(name = "Destinos.findByNombre", query = "SELECT d FROM Destinos d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Destinos.findByPais", query = "SELECT d FROM Destinos d WHERE d.pais = :pais"),
    @NamedQuery(name = "Destinos.findByTipoDestino", query = "SELECT d FROM Destinos d WHERE d.tipoDestino = :tipoDestino"),
    @NamedQuery(name = "Destinos.findByClima", query = "SELECT d FROM Destinos d WHERE d.clima = :clima"),
    @NamedQuery(name = "Destinos.findByPopularidad", query = "SELECT d FROM Destinos d WHERE d.popularidad = :popularidad"),
    @NamedQuery(name = "Destinos.findByUrl", query = "SELECT d FROM Destinos d WHERE d.url = :url")})
public class Destinos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "destino_id")
    private Integer destinoId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "pais")
    private String pais;
    @Column(name = "tipo_destino")
    private String tipoDestino;
    @Column(name = "clima")
    private String clima;
    @Column(name = "popularidad")
    private Integer popularidad;
    @Column(name = "url")
    private String url;

    public Destinos() {
    }

    public Destinos(Integer destinoId) {
        this.destinoId = destinoId;
    }

    public Integer getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Integer destinoId) {
        this.destinoId = destinoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipoDestino() {
        return tipoDestino;
    }

    public void setTipoDestino(String tipoDestino) {
        this.tipoDestino = tipoDestino;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public Integer getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(Integer popularidad) {
        this.popularidad = popularidad;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (destinoId != null ? destinoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destinos)) {
            return false;
        }
        Destinos other = (Destinos) object;
        if ((this.destinoId == null && other.destinoId != null) || (this.destinoId != null && !this.destinoId.equals(other.destinoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.consultas.viajes_itca.entity.Destinos[ destinoId=" + destinoId + " ]";
    }
    
}
