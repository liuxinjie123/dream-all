package com.dream.common.file;

import com.dream.common.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    /**
     * param localPath - 文件上传的root目录
     */
    @Value("${filePath}")
    private String localPath;

    /**
     * 举例:
     * localpath =  /data
     * type = mall
     * key = abc.jpg
     * 文件将会被存储在/data/mall/abc.jpg
     */
    @Override
    public void save(String type, String key, InputStream input, int length, String contentType) throws StorageException {
        Path path = Paths.get(localPath, type, key);
        File file = path.toFile();
        FileOutputStream fos = null;

        File fpath = new File(file.getParent());
        if(!fpath.exists()){
            fpath.mkdirs();
        }

        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new StorageException(e);
        }
        byte[] buffer = new byte[8096];
        while (true) {
            try {
                int cur = input.read(buffer, 0, 8096);
                if (cur > 0) {
                    fos.write(buffer, 0, cur);
                } else {
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public InputStream download(String bucket, String key) throws StorageException {
        Path path = Paths.get(localPath, bucket, key);
        File file = path.toFile();
        makeDirs(file);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new StorageException(e);
        }
    }

    /**
     *
     */
    private void makeDirs(File file) {
        file.getParentFile().mkdirs();
    }



}

