package br.com.leonardoramos.conecta_tec.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {

    private final S3Client s3Client;
    private final String bucketName;
    private final String minioUrl;

    public FileStorageService(S3Client s3Client,
                              @Value("${minio.bucket.name}") String bucketName,
                              @Value("${minio.url}") String minioUrl) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.minioUrl = minioUrl;
    }

    /**
     * Faz o upload de um arquivo para o MinIO e retorna a URL pública.
     * @param file O arquivo a ser enviado.
     * @return A URL completa do arquivo no storage.
     * @throws IOException Se houver um erro ao ler o arquivo.
     */
    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalStateException("Não é possível fazer upload de um arquivo vazio.");
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(uniqueFileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return String.format("%s/%s/%s", minioUrl, bucketName, uniqueFileName);
    }
}