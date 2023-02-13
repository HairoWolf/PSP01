package peval3prsp2223;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 13/02/2023
 *
 * @info Class able to create a symmetric key
 */

public class SymmetricKeyGenerator {

    public SymmetricKeyGenerator() {
    }

    public static byte[] generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey key = keyGenerator.generateKey();

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

            DESKeySpec keySpec = (DESKeySpec) keyFactory.getKeySpec(key, DESKeySpec.class);
            return keySpec.getKey();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static Key getKey(byte[] byKey) {
        SecretKey key;
        try {
            DESKeySpec keySpec = new DESKeySpec(byKey);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            key = keyFactory.generateSecret(keySpec);
            return key;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}