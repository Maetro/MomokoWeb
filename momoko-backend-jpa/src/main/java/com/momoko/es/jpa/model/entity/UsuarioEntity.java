package com.momoko.es.jpa.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.commons.util.UserUtils;
import com.momoko.es.jpa.domain.AbstractUser;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioEntity extends AbstractUser{

    public static final int NAME_MIN = 1;
    public static final int NAME_MAX = 50;

    private Integer usuarioId;

    /** The usuario login. */
    @JsonView(UserUtils.SignupInput.class)
    @NotBlank(message = "{blank.name}", groups = {UserUtils.SignUpValidation.class, UserUtils.UpdateValidation.class})
    @Size(min=NAME_MIN, max=NAME_MAX, groups = {UserUtils.SignUpValidation.class, UserUtils.UpdateValidation.class})
    @Column(nullable = false, length = NAME_MAX)
    private String usuarioLogin;

    /** The usuario contrasena. */
    private String usuarioContrasena;

    /** The usuario nick. */
    private String usuarioNick;

    /** The pagina web. */
    private String paginaWeb;

    /** The usuario url. */
    private String usuarioUrl;

    /** The usuario fecha registro. */
    private Date usuarioFechaRegistro;

    /** The usuario clave activacion. */
    private String usuarioClaveActivacion;

    /** The usuario status. */
    private Integer usuarioStatus;

    /** The usuario nombre visible. */
    private String usuarioNombreVisible;

    /** The usuario rol id. */
    private Integer usuarioRolId;

    /** The avatar url. */
    private String avatarUrl;

    /** The cargo. */
    private String cargo;

    /** The descripcion. */
    private String descripcion;

    /** The usuario alta. */
    private String usuarioAlta;

    /** The fecha alta. */
    private Date fechaAlta;

    /** The usuario alta. */
    private String usuarioModificacion;

    /** The fecha alta. */
    private Date fechaModificacion;

    /** The usuario alta. */
    private String usuarioBaja;

    /** The fecha alta. */
    private Date fechaBaja;

    /** The imagen cabecera redactor. */
    private String imagenCabeceraRedactor;

    /** The twitter. */
    private String twitter;

    /** The facebook. */
    private String facebook;

    /** The instagram. */
    private String instagram;

    /** The youtube. */
    private String youtube;

    public UsuarioEntity() {
    }

    public UsuarioEntity(String email, String password, String login) {
        this.email = email;
        this.password = password;
        this.usuarioLogin = login;
    }

    /**
     * Gets the usuario alta.
     *
     * @return the usuario alta
     */
    public String getUsuarioAlta() {
        return this.usuarioAlta;
    }

    /**
     * Sets the usuario alta.
     *
     * @param usuarioAlta
     *            the new usuario alta
     */
    public void setUsuarioAlta(final String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
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
     * Gets the usuario modificacion.
     *
     * @return the usuario modificacion
     */
    public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }

    /**
     * Sets the usuario modificacion.
     *
     * @param usuarioModificacion
     *            the new usuario modificacion
     */
    public void setUsuarioModificacion(final String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * Gets the fecha modificacion.
     *
     * @return the fecha modificacion
     */
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    /**
     * Sets the fecha modificacion.
     *
     * @param fechaModificacion
     *            the new fecha modificacion
     */
    public void setFechaModificacion(final Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Gets the usuario baja.
     *
     * @return the usuario baja
     */
    public String getUsuarioBaja() {
        return this.usuarioBaja;
    }

    /**
     * Sets the usuario baja.
     *
     * @param usuarioBaja
     *            the new usuario baja
     */
    public void setUsuarioBaja(final String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    /**
     * Gets the fecha baja.
     *
     * @return the fecha baja
     */
    public Date getFechaBaja() {
        return this.fechaBaja;
    }

    /**
     * Sets the fecha baja.
     *
     * @param fechaBaja
     *            the new fecha baja
     */
    public void setFechaBaja(final Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PuntuacionEntity> puntuaciones;

    /**
     * Obtiene usuario id.
     *
     * @return usuario id
     */
    public Integer getUsuarioId() {
        return this.usuarioId;
    }

    /**
     * Establece usuario id.
     *
     * @param usuarioId
     *            nuevo usuario id
     */
    public void setUsuarioId(final Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Obtiene usuario login.
     *
     * @return usuario login
     */
    public String getUsuarioLogin() {
        return this.usuarioLogin;
    }

    /**
     * Establece usuario login.
     *
     * @param usuarioLogin
     *            nuevo usuario login
     */
    public void setUsuarioLogin(final String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    /**
     * Obtiene usuario contrasena.
     *
     * @return usuario contrasena
     */
    public String getUsuarioContrasena() {
        return this.usuarioContrasena;
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
     * Establece usuario contrasena.
     *
     * @param usuarioContrasena
     *            nuevo usuario contrasena
     */
    public void setUsuarioContrasena(final String usuarioContrasena) {
        this.usuarioContrasena = usuarioContrasena;
    }

    /**
     * Obtiene usuario nick.
     *
     * @return usuario nick
     */
    public String getUsuarioNick() {
        return this.usuarioNick;
    }

    /**
     * Establece usuario nick.
     *
     * @param usuarioNick
     *            nuevo usuario nick
     */
    public void setUsuarioNick(final String usuarioNick) {
        this.usuarioNick = usuarioNick;
    }

    /**
     * Obtiene usuario url.
     *
     * @return usuario url
     */
    public String getUsuarioUrl() {
        return this.usuarioUrl;
    }

    /**
     * Establece usuario url.
     *
     * @param usuarioUrl
     *            nuevo usuario url
     */
    public void setUsuarioUrl(final String usuarioUrl) {
        this.usuarioUrl = usuarioUrl;
    }

    /**
     * Obtiene usuario fecha registro.
     *
     * @return usuario fecha registro
     */
    public Date getUsuarioFechaRegistro() {
        return this.usuarioFechaRegistro;
    }

    /**
     * Establece usuario fecha registro.
     *
     * @param usuarioFechaRegistro
     *            nuevo usuario fecha registro
     */
    public void setUsuarioFechaRegistro(final Date usuarioFechaRegistro) {
        this.usuarioFechaRegistro = usuarioFechaRegistro;
    }

    /**
     * Obtiene usuario clave activacion.
     *
     * @return usuario clave activacion
     */
    public String getUsuarioClaveActivacion() {
        return this.usuarioClaveActivacion;
    }

    /**
     * Establece usuario clave activacion.
     *
     * @param usuarioClaveActivacion
     *            nuevo usuario clave activacion
     */
    public void setUsuarioClaveActivacion(final String usuarioClaveActivacion) {
        this.usuarioClaveActivacion = usuarioClaveActivacion;
    }

    /**
     * Obtiene usuario status.
     *
     * @return usuario status
     */
    public Integer getUsuarioStatus() {
        return this.usuarioStatus;
    }

    /**
     * Establece usuario status.
     *
     * @param usuarioStatus
     *            nuevo usuario status
     */
    public void setUsuarioStatus(final Integer usuarioStatus) {
        this.usuarioStatus = usuarioStatus;
    }

    /**
     * Obtiene usuario nombre visible.
     *
     * @return usuario nombre visible
     */
    public String getUsuarioNombreVisible() {
        return this.usuarioNombreVisible;
    }

    /**
     * Establece usuario nombre visible.
     *
     * @param usuarioNombreVisible
     *            nuevo usuario nombre visible
     */
    public void setUsuarioNombreVisible(final String usuarioNombreVisible) {
        this.usuarioNombreVisible = usuarioNombreVisible;
    }

    /**
     * Obtiene usuario rol id.
     *
     * @return usuario rol id
     */
    public Integer getUsuarioRolId() {
        return this.usuarioRolId;
    }

    /**
     * Establece usuario rol id.
     *
     * @param usuarioRolId
     *            nuevo usuario rol id
     */
    public void setUsuarioRolId(final Integer usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    /**
     * Obtiene puntuaciones.
     *
     * @return puntuaciones
     */
    public List<PuntuacionEntity> getPuntuaciones() {
        return this.puntuaciones;
    }

    /**
     * Establece puntuaciones.
     *
     * @param puntuaciones
     *            nuevo puntuaciones
     */
    public void setPuntuaciones(final List<PuntuacionEntity> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    /**
     * Obtiene avatar url.
     *
     * @return avatar url
     */
    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    /**
     * Establece avatar url.
     *
     * @param avatarUrl
     *            nuevo avatar url
     */
    public void setAvatarUrl(final String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Obtiene cargo.
     *
     * @return cargo
     */
    public String getCargo() {
        return this.cargo;
    }

    /**
     * Establece cargo.
     *
     * @param cargo
     *            nuevo cargo
     */
    public void setCargo(final String cargo) {
        this.cargo = cargo;
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
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof UsuarioEntity)) {
            return false;
        }
        final UsuarioEntity castOther = (UsuarioEntity) other;
        return new EqualsBuilder().append(this.usuarioId, castOther.usuarioId)
                .append(this.usuarioLogin, castOther.usuarioLogin)
                .append(this.usuarioContrasena, castOther.usuarioContrasena)
                .append(this.usuarioNick, castOther.usuarioNick).append(this.email, castOther.email)
                .append(this.usuarioUrl, castOther.usuarioUrl)
                .append(this.usuarioFechaRegistro, castOther.usuarioFechaRegistro)
                .append(this.usuarioClaveActivacion, castOther.usuarioClaveActivacion)
                .append(this.usuarioStatus, castOther.usuarioStatus)
                .append(this.usuarioNombreVisible, castOther.usuarioNombreVisible)
                .append(this.usuarioRolId, castOther.usuarioRolId).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.usuarioId).append(this.usuarioLogin).append(this.usuarioContrasena)
                .append(this.usuarioNick).append(this.email).append(this.usuarioUrl)
                .append(this.usuarioFechaRegistro).append(this.usuarioClaveActivacion).append(this.usuarioStatus)
                .append(this.usuarioNombreVisible).append(this.usuarioRolId).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("usuarioId", this.usuarioId).append("usuarioLogin", this.usuarioLogin)
                .append("usuarioContrasena", this.usuarioContrasena).append("usuarioNick", this.usuarioNick)
                .append("email", this.email).append("usuarioUrl", this.usuarioUrl)
                .append("usuarioFechaRegistro", this.usuarioFechaRegistro)
                .append("usuarioClaveActivacion", this.usuarioClaveActivacion)
                .append("usuarioStatus", this.usuarioStatus).append("usuarioNombreVisible", this.usuarioNombreVisible)
                .append("usuarioRolId", this.usuarioRolId).toString();
    }

    @Override
    public Tag toTag() {

        Tag tag = new Tag();
        tag.setName(usuarioLogin);
        return tag;
    }

    /**
     * Makes a User DTO
     */
    @Override
    public UsuarioDTO toUserDto() {

        UsuarioDTO userDto = new UsuarioDTO();

        userDto.setUsuarioId(getId());
        userDto.setUserId(getId());
        userDto.setUsername(email);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setRoles(roles);
        userDto.setTag(toTag());

        boolean unverified = hasRole(UserUtils.Role.UNVERIFIED);
        boolean blocked = hasRole(UserUtils.Role.BLOCKED);
        boolean admin = hasRole(UserUtils.Role.ADMIN);
        boolean goodUser = !(unverified || blocked);
        boolean goodAdmin = goodUser && admin;

        userDto.setAdmin(admin);
        userDto.setBlocked(blocked);
        userDto.setGoodAdmin(goodAdmin);
        userDto.setGoodUser(goodUser);
        userDto.setUnverified(unverified);

        return userDto;
    }

    public static class Tag implements Serializable {

        private static final long serialVersionUID = -2129078111926834670L;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
