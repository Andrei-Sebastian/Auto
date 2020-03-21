package principalPACK.clase.preluate;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Criptare {


    private static Cipher ecipher;
    private static Cipher dcipher;
    // 8-byte Salt
    private static byte[] salt = {
            (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    // Iteration count
    private static int  iterationCount = 19;

    public Criptare() {

    }

    public static String encrypt(String secretKey, String plainText) {
        try {
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            String charSet = "UTF-8";
            byte[] in = plainText.getBytes(charSet);
            byte[] out = ecipher.doFinal(in);
            return new String(Base64.getEncoder().encode(out));
        } catch (Exception e) {
        }
        return null;
    }

    public static String decrypt(String secretKey, String encryptedText) {
        try {
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            dcipher = Cipher.getInstance(key.getAlgorithm());
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            byte[] enc = Base64.getDecoder().decode(encryptedText);
            byte[] utf8 = dcipher.doFinal(enc);
            String charSet = "UTF-8";
            return new String(utf8, charSet);
        } catch (Exception e) {
        }
        return null;
    }
}

