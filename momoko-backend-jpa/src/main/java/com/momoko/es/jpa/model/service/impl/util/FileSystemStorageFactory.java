/**
 * FileSystemStorageFactory.java 23-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl.util;

import org.springframework.stereotype.Component;

import com.momoko.es.commons.configuration.MomokoConfiguracion;

/**
 * The type File system storage factory.
 */
@Component
public final class FileSystemStorageFactory {

    /**
     * Get file system storage helper file system storage helper.
     *
     * @param momokoConfiguracion
     *            the momoko configuracion
     * @return the file system storage helper
     */
    public static FileSystemStorageHelper getFileSystemStorageHelper(final MomokoConfiguracion momokoConfiguracion) {
        FileSystemStorageHelper fileSystemStorageHelper;
        if (momokoConfiguracion.isEsServidorLocal()) {
            fileSystemStorageHelper = FileSystemStorageHelperLocal.getInstance(momokoConfiguracion);
        } else {
            fileSystemStorageHelper = FileSystemStorageHelperRemote.getInstance(momokoConfiguracion);
        }
        return fileSystemStorageHelper;
    }

}
