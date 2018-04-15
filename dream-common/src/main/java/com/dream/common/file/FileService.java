package com.dream.common.file;

import com.dream.common.exception.StorageException;

import java.io.InputStream;

public interface FileService {
    /**
     * save
     */
    void save(String type, String key, InputStream input, int length, String contentType) throws StorageException;

    /**
     * download
     */
    InputStream download(String type, String key) throws StorageException;
}

