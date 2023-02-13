package peval3prsp2223;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 13/02/2023
 *
 * @info Class that is able to decrypt messages using a symmetric key.
 */

public class SymmetricDecrypter {

    public SymmetricDecrypter() {
    }

    public static byte[] decryptMessage(byte[] key, byte[] encryptedMessage) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, SymmetricKeyGenerator.getKey(key));
            return cipher.doFinal(encryptedMessage);
        }
        catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}