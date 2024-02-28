package br.com.inventory.mechanicalparts.services;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

public interface S3Service {
    URI uploadFile(MultipartFile multipartFile);

}
