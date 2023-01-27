package com.calatheas.skeletonj.common.encrypt;

import com.calatheas.skeletonj.common.code.Profile;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@Slf4j
public final class EncryptUtil {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    private AWSKMSClient awskmsClient;

    public byte[] encryptData(@NonNull final String data) {
        try {
            if (!(Profile.fromCode(activeProfile).isLocalEnvironment())) {
                return encrypt(data);
            } else {
                log.info("No encrypion in local environment");
                return data.getBytes(StandardCharsets.UTF_8);
            }
        } catch (Exception ex) {
            log.error("Can not encrypt data - {}", data);
            throw ex;
        }
    }

    public String decryptData(@NonNull final byte[] data) {
        try {
            if (!(Profile.fromCode(activeProfile).isLocalEnvironment())) {
                return decrypt(data);
            } else {
                log.info("No encrypion in local environment");
                return new String(data, StandardCharsets.UTF_8);
            }
        } catch (Exception ex) {
            log.error("Can not decrypt data - {}" + data);
            throw ex;
        }
    }

    public byte[] encrypt(String data) {
        return awskmsClient.encrypt(data);
    }

    public String decrypt(byte[] data) {
        return awskmsClient.decrypt(data);
    }

    public String encryptBase64(String data) {
        return new String(Base64.getEncoder().encode(encrypt(data)), StandardCharsets.UTF_8);
    }

    public String decryptBase64(String base64data) {
        return decrypt(Base64.getDecoder().decode(base64data.getBytes(StandardCharsets.UTF_8)));
    }
}
