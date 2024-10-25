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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "img_destino")
@NamedQueries({
    @NamedQuery(name = "ImgDestino.findAll", query = "SELECT i FROM ImgDestino i"),
    @NamedQuery(name = "ImgDestino.findById", query = "SELECT i FROM ImgDestino i WHERE i.id = :id"),
    @NamedQuery(name = "ImgDestino.findByUrl", query = "SELECT i FROM ImgDestino i WHERE i.url = :url")})
public class ImgDestino implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "id_destino", referencedColumnName = "destino_id")
    @ManyToOne
    private Destinos idDestino;

    public ImgDestino() {
    }

    public ImgDestino(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Destinos getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Destinos idDestino) {
        this.idDestino = idDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImgDestino)) {
            return false;
        }
        ImgDestino other = (ImgDestino) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.consultas.viajes_itca.entity.ImgDestino[ id=" + id + " ]";
    }
    
}
