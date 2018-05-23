package com.momoko.es.backend.model.service.impl.util;

import com.momoko.es.backend.configuration.MomokoConfiguracion;

/**
 * The type File system storage factory.
 */
public final class FileSystemStorageFactory {

    /**
     * Get file system storage helper file system storage helper.
     *
     * @param momokoConfiguracion the momoko configuracion
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
