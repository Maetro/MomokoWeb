/**
 * ObtenerPaginaEtiquetaResponse.java 02-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;

/**
 * The Class ObtenerPaginaEtiquetaResponse.
 */
public class ObtenerPaginaEtiquetaResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6545208899252484169L;

    /** The libro. */
    private EtiquetaDTO etiqueta;

    /** The tres ultimas entradas. */
    private List<EntradaSimpleDTO> entradasEtiqueta;

    /** The numero entradas. */
    private Integer numeroEntradas;

    public EtiquetaDTO getEtiqueta() {
        return this.etiqueta;
    }

    public void setEtiqueta(final EtiquetaDTO etiqueta) {
        this.etiqueta = etiqueta;
    }

    public List<EntradaSimpleDTO> getEntradasEtiqueta() {
        return this.entradasEtiqueta;
    }

    public void setEntradasEtiqueta(final List<EntradaSimpleDTO> entradasEtiqueta) {
        this.entradasEtiqueta = entradasEtiqueta;
    }

    public Integer getNumeroEntradas() {
        return this.numeroEntradas;
    }

    public void setNumeroEntradas(final Integer numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }

}
