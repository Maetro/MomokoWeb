/**
 * SuscripcionEntity.java 22-dic-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.suscription;

import com.momoko.es.jpa.common.entity.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suscripcion")
public class SuscripcionEntity extends AuditableEntity{

    /** The libro id. */
    private @Id @GeneratedValue Integer suscripcionId;

    /** The descripcion saga. */
    private String email;

    public Integer getSuscripcionId() {
        return this.suscripcionId;
    }

    public void setSuscripcionId(final Integer suscripcionId) {
        this.suscripcionId = suscripcionId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}
