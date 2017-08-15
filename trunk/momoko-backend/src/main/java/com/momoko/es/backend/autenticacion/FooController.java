/**
 * FooController.java 14-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.autenticacion;

import org.springframework.web.bind.annotation.PathVariable;

import com.momoko.es.backend.autenticacion.bean.Foo;

//@Controller
public class FooController {

    // @PreAuthorize("#oauth2.hasScope('read')")
    // @RequestMapping(method = RequestMethod.GET, value = "/foos/{id}")
    // @ResponseBody
    public Foo findById(@PathVariable final long id) {
        return new Foo(Long.parseLong(randomNumeric(2)), randomAlphabetic(4));
    }

    private String randomAlphabetic(final int i) {
        // TODO Auto-generated method stub
        return "qwer";
    }

    private String randomNumeric(final int i) {
        // TODO Auto-generated method stub
        return "22";
    }
}