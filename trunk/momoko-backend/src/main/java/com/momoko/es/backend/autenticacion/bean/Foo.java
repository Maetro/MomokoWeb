/**
 * Foo.java 14-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.autenticacion.bean;

/**
 * The Class Foo.
 */
public class Foo {
    private long id;
    private String name;

    /**
     * Instantiates a new foo.
     *
     * @param id
     *            the id
     * @param name
     *            the name
     */
    public Foo(final long id, final String name) {
        super();
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
