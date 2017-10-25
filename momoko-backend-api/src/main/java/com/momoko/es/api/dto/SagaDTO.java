package com.momoko.es.api.dto;

import java.io.Serializable;

/**
 * The Class SagaDTO.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class SagaDTO implements Serializable{

    private static final long serialVersionUID = -4725553152718656061L;

    /** The libro id. */
    private Integer sagaId;

    private String descripcionSaga;

    /**
     * Obtiene saga id.
     *
     * @return saga id
     */
    public Integer getSagaId() {
        return sagaId;
    }

    /**
     * Establece saga id.
     *
     * @param sagaId
     *            nuevo saga id
     */
    public void setSagaId(Integer sagaId) {
        this.sagaId = sagaId;
    }

    /**
     * Obtiene descripcion saga.
     *
     * @return descripcion saga
     */
    public String getDescripcionSaga() {
        return descripcionSaga;
    }

    /**
     * Establece descripcion saga.
     *
     * @param descripcionSaga
     *            nuevo descripcion saga
     */
    public void setDescripcionSaga(String descripcionSaga) {
        this.descripcionSaga = descripcionSaga;
    }

}
