package br.com.leonardoramos.conecta_tec;

import software.amazon.awssdk.services.s3.S3Client;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConectaTecApplicationTests {

    @Mock
    private S3Client s3Client;

    @Test
    void contextLoads() {
    }

}