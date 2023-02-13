package peval3prsp2223;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 13/02/2023
 *
 * @info Class able to create an asymmetric key pair
 */

public class AsymmetricKeyGenerator {

    public AsymmetricKeyGenerator() {
    }

    public static void generateKeysAndSaveToFile(File publicKeyPath, File privateKeyPath) {
        try {
            //Número random seguro para el tamaño de la clave y generamos el par de claves
            SecureRandom random = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512, random);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            //Creación claves asimétricas
            RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);

            //Inserción clave publica en el fichero
            FileOutputStream fos = new FileOutputStream(publicKeyPath);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(privateKeySpec.getModulus());
            pw.println(privateKeySpec.getPrivateExponent());
            pw.close();

            //Inserción clave privada en el fichero
            FileOutputStream fos2 = new FileOutputStream(privateKeyPath);
            PrintWriter pw2 = new PrintWriter(fos2);
            pw2.println(publicKeySpec.getModulus());
            pw2.println(publicKeySpec.getPublicExponent());
            pw2.close();
        }
        catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey getPrivateKey(File privateKeyPath) {
        PrivateKey privateKey;
        try {
            FileReader fr = new FileReader(privateKeyPath);
            BufferedReader br = new BufferedReader(fr);

            BigInteger modulus = new BigInteger(br.readLine());
            BigInteger exponente = new BigInteger(br.readLine());

            br.close();

            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, exponente);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
            return privateKey;
        }
        catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static Key getPublicKey(File publicKeyPath) {
        Key publicKey;
        try {
            FileReader fr = new FileReader(publicKeyPath);
            BufferedReader br = new BufferedReader(fr);

            BigInteger modulus = new BigInteger(br.readLine());
            BigInteger exponente = new BigInteger(br.readLine());

            br.close();

            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponente);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
            return publicKey;
        }
        catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}