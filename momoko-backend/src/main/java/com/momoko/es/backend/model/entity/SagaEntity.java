package com.momoko.es.backend.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class SagaEntity.
 *
 * @author <a href="DireccionCorreoUsuario@Empresa">Nombre del Autor</a>
 */
@Entity
@Table(name = "saga")
public class SagaEntity extends AuditoriaBasica {

    /** The libro id. */
    private @Id @GeneratedValue Integer sagaId;

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
