package br.com.inventory.mechanicalparts.services;

import java.io.InputStream;
import java.net.URI;

public interface S3Service {
    URI uploadFile(InputStream inputStream, String fileName, String contentType);

}
