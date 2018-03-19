/**
 * GuardarSagaRequestDTO.java 25-feb-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.request;

import java.io.Serializable;

import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.dto.SagaDTO;

public class GuardarSagaRequestDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2459781401302219605L;

    /** The saga. */
    private SagaDTO saga;

    private PuntuacionDTO puntuacion;

    public SagaDTO getSaga() {
        return this.saga;
    }

    public void setSaga(final SagaDTO saga) {
        this.saga = saga;
    }

    public PuntuacionDTO getPuntuacion() {
        return this.puntuacion;
    }

    public void setPuntuacion(final PuntuacionDTO puntuacion) {
        this.puntuacion = puntuacion;
    }
}
