package org.ligson.pt.serializable;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by ligson on 2015/11/24.
 *
 * @author ligson
 *         token工具
 */
public class TokenUtils {
    private static final String charset = "UTF-8";

    /****
     * 解密token
     *
     * @param encodeToken 加密后的token
     * @param priKey      解密的私钥
     * @return 解密后的token
     */
    public static String decode(String encodeToken, String priKey) {
        String tokenId = null;
        try {
            PrivateKey privateKey = KeyUtils.decodePrivateKey(priKey);
            byte[] buffer = Base64.getDecoder().decode(encodeToken);
            byte[] buf = RSACryptoUtils.decrypt(privateKey, buffer);
            tokenId = new String(buf, charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenId;
    }

    /***
     * 加密token
     *
     * @param tokenId 生成的tokenId
     * @param pubKey  公钥的base64
     * @return 加密后的token
     */
    public static String encode(String tokenId, String pubKey) {
        PublicKey publicKey = KeyUtils.decodePublicKey(pubKey);
        assert publicKey != null;
        try {
            byte[] buffer = tokenId.getBytes("UTF-8");
            byte[] encodeBuffer = RSACryptoUtils.encrypt(publicKey, buffer);
            String token = Base64.getEncoder().encodeToString(encodeBuffer);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 生成token
     *
     * @return 唯一的
     */
    public static String generateTokenId() {
        return UUID.randomUUID().toString();
    }


}
