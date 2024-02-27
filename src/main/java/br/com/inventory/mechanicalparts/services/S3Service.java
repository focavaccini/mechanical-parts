package br.com.inventory.mechanicalparts.services;

public interface S3Service {
    //    URI uploadFile(MultipartFile multipartFile);
//    URI uploadFile(InputStream inputStream, String fileName, String contentType);
    void uploadFile(String multipartFile);
}
