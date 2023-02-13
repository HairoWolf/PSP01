package peval3prsp2223;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 13/02/2023
 *
 * @info Class that is able to encrypt messages using a symmetric key.
 */

public class SymmetricEncryptor {

    public SymmetricEncryptor() {
    }

    public static byte[] encryptMessage(byte[] key, String message){
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, SymmetricKeyGenerator.getKey(key));
            return cipher.doFinal(message.getBytes());
        }
        catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}