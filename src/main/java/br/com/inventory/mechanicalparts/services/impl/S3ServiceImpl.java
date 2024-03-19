package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.exceptions.FileException;
import br.com.inventory.mechanicalparts.services.S3Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3ServiceImpl implements S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    @Override
    public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
        try {

            ObjectMetadata objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(contentType);
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetaData));

            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI");
        }

    }
}
