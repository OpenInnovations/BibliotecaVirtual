package com.openinnovations.fileprocessinglite.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageService {

    File temporalPdf;

    public StorageService() throws IOException {
        temporalPdf  = File.createTempFile("documento", ".tmp");
    }

    public void descargarFromStorage(String storageFileName) throws IOException {
        Storage storage = StorageOptions.newBuilder()
                .setProjectId("bibliotecavirtual-266119")
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("static/credenciales.json").getInputStream()))
                .build()
                .getService();
        String bucketName = "biblioteca-virtual";
        Blob blob = storage.get(BlobId.of(bucketName, storageFileName));
        ReadChannel readChannel = blob.reader();
        FileOutputStream fileOuputStream = new FileOutputStream(temporalPdf.getAbsolutePath());
        fileOuputStream.getChannel().transferFrom(readChannel, 0, Long.MAX_VALUE);
        fileOuputStream.close();
    }

    public File getTemporalPdf() {
        return temporalPdf;
    }
}
