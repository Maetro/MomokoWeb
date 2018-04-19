/**
 * RedactorDTO.java 20-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class RedactorDTO.
 */
public class RedactorDTO implements Serializable {

    private static final long serialVersionUID = -8106699885540550978L;
    private Integer usuarioId;
    private String nombre;
    private String nick;
    private String imagenCabeceraRedactor;
    private String avatarRedactor;
    private String urlRedactor;
    private String descripcion;
    private String twitter;
    private String facebook;
    private String instagram;
    private String youtube;
    private String paginaWeb;
    private BigDecimal mediaPuntuaciones;
    private Date fechaAlta;
    private Date fechaUltimaEntrada;

    /**
     * Gets the usuario id.
     *
     * @return the usuario id
     */
    public Integer getUsuarioId() {
        return this.usuarioId;
    }

    /**
     * Sets the usuario id.
     *
     * @param usuarioId
     *            the new usuario id
     */
    public void setUsuarioId(final Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the new nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the nick.
     *
     * @return the nick
     */
    public String getNick() {
        return this.nick;
    }

    /**
     * Sets the nick.
     *
     * @param nick
     *            the new nick
     */
    public void setNick(final String nick) {
        this.nick = nick;
    }

    /**
     * Gets the imagen cabecera redactor.
     *
     * @return the imagen cabecera redactor
     */
    public String getImagenCabeceraRedactor() {
        return this.imagenCabeceraRedactor;
    }

    /**
     * Sets the imagen cabecera redactor.
     *
     * @param imagenCabeceraRedactor
     *            the new imagen cabecera redactor
     */
    public void setImagenCabeceraRedactor(final String imagenCabeceraRedactor) {
        this.imagenCabeceraRedactor = imagenCabeceraRedactor;
    }

    /**
     * Gets the avatar redactor.
     *
     * @return the avatar redactor
     */
    public String getAvatarRedactor() {
        return this.avatarRedactor;
    }

    /**
     * Sets the avatar redactor.
     *
     * @param avatarRedactor
     *            the new avatar redactor
     */
    public void setAvatarRedactor(final String avatarRedactor) {
        this.avatarRedactor = avatarRedactor;
    }

    /**
     * Gets the url redactor.
     *
     * @return the url redactor
     */
    public String getUrlRedactor() {
        return this.urlRedactor;
    }

    /**
     * Sets the url redactor.
     *
     * @param urlRedactor
     *            the new url redactor
     */
    public void setUrlRedactor(final String urlRedactor) {
        this.urlRedactor = urlRedactor;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion
     *            the new descripcion
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the twitter.
     *
     * @return the twitter
     */
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets the twitter.
     *
     * @param twitter
     *            the new twitter
     */
    public void setTwitter(final String twitter) {
        this.twitter = twitter;
    }

    /**
     * Gets the facebook.
     *
     * @return the facebook
     */
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets the facebook.
     *
     * @param facebook
     *            the new facebook
     */
    public void setFacebook(final String facebook) {
        this.facebook = facebook;
    }

    /**
     * Gets the instagram.
     *
     * @return the instagram
     */
    public String getInstagram() {
        return this.instagram;
    }

    /**
     * Sets the instagram.
     *
     * @param instagram
     *            the new instagram
     */
    public void setInstagram(final String instagram) {
        this.instagram = instagram;
    }

    /**
     * Gets the youtube.
     *
     * @return the youtube
     */
    public String getYoutube() {
        return this.youtube;
    }

    /**
     * Sets the youtube.
     *
     * @param youtube
     *            the new youtube
     */
    public void setYoutube(final String youtube) {
        this.youtube = youtube;
    }

    /**
     * Gets the fecha alta.
     *
     * @return the fecha alta
     */
    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    /**
     * Sets the fecha alta.
     *
     * @param fechaAlta
     *            the new fecha alta
     */
    public void setFechaAlta(final Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Gets the fecha ultima entrada.
     *
     * @return the fecha ultima entrada
     */
    public Date getFechaUltimaEntrada() {
        return this.fechaUltimaEntrada;
    }

    /**
     * Sets the fecha ultima entrada.
     *
     * @param fechaUltimaEntrada
     *            the new fecha ultima entrada
     */
    public void setFechaUltimaEntrada(final Date fechaUltimaEntrada) {
        this.fechaUltimaEntrada = fechaUltimaEntrada;
    }

    /**
     * Gets the pagina web.
     *
     * @return the pagina web
     */
    public String getPaginaWeb() {
        return this.paginaWeb;
    }

    /**
     * Sets the pagina web.
     *
     * @param paginaWeb
     *            the new pagina web
     */
    public void setPaginaWeb(final String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    /**
     * Gets the media puntuaciones.
     *
     * @return the media puntuaciones
     */
    public BigDecimal getMediaPuntuaciones() {
        return this.mediaPuntuaciones;
    }

    /**
     * Sets the media puntuaciones.
     *
     * @param mediaPuntuaciones
     *            the new media puntuaciones
     */
    public void setMediaPuntuaciones(final BigDecimal mediaPuntuaciones) {
        this.mediaPuntuaciones = mediaPuntuaciones;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof RedactorDTO)) {
            return false;
        }
        final RedactorDTO castOther = (RedactorDTO) other;
        return new EqualsBuilder().append(this.usuarioId, castOther.usuarioId).append(this.nombre, castOther.nombre)
                .append(this.nick, castOther.nick).append(this.imagenCabeceraRedactor, castOther.imagenCabeceraRedactor)
                .append(this.avatarRedactor, castOther.avatarRedactor).append(this.urlRedactor, castOther.urlRedactor)
                .append(this.descripcion, castOther.descripcion).append(this.twitter, castOther.twitter)
                .append(this.facebook, castOther.facebook).append(this.instagram, castOther.instagram)
                .append(this.youtube, castOther.youtube).append(this.fechaAlta, castOther.fechaAlta)
                .append(this.fechaUltimaEntrada, castOther.fechaUltimaEntrada).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.usuarioId).append(this.nombre).append(this.nick)
                .append(this.imagenCabeceraRedactor).append(this.avatarRedactor).append(this.urlRedactor)
                .append(this.descripcion).append(this.twitter).append(this.facebook).append(this.instagram)
                .append(this.youtube).append(this.fechaAlta).append(this.fechaUltimaEntrada).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("usuarioId", this.usuarioId).append("nombre", this.nombre)
                .append("nick", this.nick).append("imagenCabeceraRedactor", this.imagenCabeceraRedactor)
                .append("avatarRedactor", this.avatarRedactor).append("urlRedactor", this.urlRedactor)
                .append("descripcion", this.descripcion).append("twitter", this.twitter)
                .append("facebook", this.facebook).append("instagram", this.instagram).append("youtube", this.youtube)
                .append("fechaAlta", this.fechaAlta).append("fechaUltimaEntrada", this.fechaUltimaEntrada).toString();
    }

}
