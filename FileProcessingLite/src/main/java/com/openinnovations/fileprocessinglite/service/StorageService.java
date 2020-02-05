package com.openinnovations.fileprocessinglite.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageService {

    public void descargarFromStorage(String storageFileName) throws IOException {
        Storage storage = StorageOptions.newBuilder()
                .setProjectId("bibliotecavirtual-266119")
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(ResourceUtils.getFile("classpath:static/credenciales.json"))))
                .build()
                .getService();
        String bucketName = "biblioteca-virtual";
        Blob blob = storage.get(BlobId.of(bucketName, storageFileName));
        ReadChannel readChannel = blob.reader();
        FileOutputStream fileOuputStream = new FileOutputStream(ResourceUtils.getFile("classpath:input/documento.pdf"));
        fileOuputStream.getChannel().transferFrom(readChannel, 0, Long.MAX_VALUE);
        fileOuputStream.close();
    }

}
