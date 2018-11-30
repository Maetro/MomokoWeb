/**
 * TestLibroDTO.java 27-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * The Class TestLibroDTO.
 */
public class TestLibroDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7760423673070577758L;

    private String titulo;
    private Integer anoEdicion;
    private String resumen;
    private String citaLibro;
    private String enlaceAmazon;

    public TestLibroDTO() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Instantiates a new test libro dto.
     *
     * @param titulo
     *            the titulo
     * @param anoEdicion
     *            the ano edicion
     * @param resumen
     *            the resumen
     * @param citaLibro
     *            the cita libro
     * @param enlaceAmazon
     *            the enlace amazon
     */
    public TestLibroDTO(final String titulo, final Integer anoEdicion, final String resumen, final String citaLibro,
            final String enlaceAmazon) {
        super();
        this.titulo = titulo;
        this.anoEdicion = anoEdicion;
        this.resumen = resumen;
        this.citaLibro = citaLibro;
        this.enlaceAmazon = enlaceAmazon;
    }

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Sets the titulo.
     *
     * @param titulo
     *            the new titulo
     */
    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets the ano edicion.
     *
     * @return the ano edicion
     */
    public Integer getAnoEdicion() {
        return this.anoEdicion;
    }

    /**
     * Sets the ano edicion.
     *
     * @param anoEdicion
     *            the new ano edicion
     */
    public void setAnoEdicion(final Integer anoEdicion) {
        this.anoEdicion = anoEdicion;
    }

    /**
     * Gets the resumen.
     *
     * @return the resumen
     */
    public String getResumen() {
        return this.resumen;
    }

    /**
     * Sets the resumen.
     *
     * @param resumen
     *            the new resumen
     */
    public void setResumen(final String resumen) {
        this.resumen = resumen;
    }

    /**
     * Gets the cita libro.
     *
     * @return the cita libro
     */
    public String getCitaLibro() {
        return this.citaLibro;
    }

    /**
     * Sets the cita libro.
     *
     * @param citaLibro
     *            the new cita libro
     */
    public void setCitaLibro(final String citaLibro) {
        this.citaLibro = citaLibro;
    }

    /**
     * Gets the enlace amazon.
     *
     * @return the enlace amazon
     */
    public String getEnlaceAmazon() {
        return this.enlaceAmazon;
    }

    /**
     * Sets the enlace amazon.
     *
     * @param enlaceAmazon
     *            the new enlace amazon
     */
    public void setEnlaceAmazon(final String enlaceAmazon) {
        this.enlaceAmazon = enlaceAmazon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("titulo", this.titulo).append("anoEdicion", this.anoEdicion)
                .append("resumen", this.resumen).append("citaLibro", this.citaLibro)
                .append("enlaceAmazon", this.enlaceAmazon).toString();
    }

}
