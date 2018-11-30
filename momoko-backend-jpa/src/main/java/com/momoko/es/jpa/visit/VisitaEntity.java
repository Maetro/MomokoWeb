/**
 * VisitaEntity.java 19-dic-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.visit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * The Class VisitaEntity.
 */
@Entity
@Table(name = "visita")
public class VisitaEntity {

    private @Id @GeneratedValue Long idVisita;

    /** The url visita. */
    private String urlVisita;

    /** The tipo visita. */
    private String tipoVisita;

    /** The fecha visita. */
    private Date fechaVisita;

    /** The usuario visita. */
    private String usuarioVisita;

    /**
     * Gets the id visita.
     *
     * @return the id visita
     */
    public Long getIdVisita() {
        return this.idVisita;
    }

    /**
     * Sets the id visita.
     *
     * @param idVisita
     *            the new id visita
     */
    public void setIdVisita(final Long idVisita) {
        this.idVisita = idVisita;
    }

    /**
     * Gets the url visita.
     *
     * @return the url visita
     */
    public String getUrlVisita() {
        return this.urlVisita;
    }

    /**
     * Sets the url visita.
     *
     * @param urlVisita
     *            the new url visita
     */
    public void setUrlVisita(final String urlVisita) {
        this.urlVisita = urlVisita;
    }

    /**
     * Gets the tipo visita.
     *
     * @return the tipo visita
     */
    public String getTipoVisita() {
        return this.tipoVisita;
    }

    /**
     * Sets the tipo visita.
     *
     * @param tipoVisita
     *            the new tipo visita
     */
    public void setTipoVisita(final String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    /**
     * Gets the fecha visita.
     *
     * @return the fecha visita
     */
    public Date getFechaVisita() {
        return this.fechaVisita;
    }

    /**
     * Sets the fecha visita.
     *
     * @param fechaVisita
     *            the new fecha visita
     */
    public void setFechaVisita(final Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    /**
     * Gets the usuario visita.
     *
     * @return the usuario visita
     */
    public String getUsuarioVisita() {
        return this.usuarioVisita;
    }

    /**
     * Sets the usuario visita.
     *
     * @param usuarioVisita
     *            the new usuario visita
     */
    public void setUsuarioVisita(final String usuarioVisita) {
        this.usuarioVisita = usuarioVisita;
    }

}
