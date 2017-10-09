/**
 * ValidatorContrasenasIguales.java 19-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.validacion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.momoko.es.api.dto.RegistroNuevoUsuarioDTO;

/**
 * The Class ValidatorContrasenasIguales.
 */
public class ValidatorContrasenasIguales implements ConstraintValidator<ContrasenasIguales, Object> {

    @Override
    public void initialize(final ContrasenasIguales constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final RegistroNuevoUsuarioDTO usuario = (RegistroNuevoUsuarioDTO) obj;
        return usuario.getContrasena().equals(usuario.getContrasenaRepetida());
    }

}
