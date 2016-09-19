package pt.tuling.chat.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.common.http.util.ClientUtils;
import pt.tuling.chat.service.ChatService;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by ligso on 2016/9/19.
 */
public class TLChatServiceImpl implements ChatService {
    private static final String apiKey = "f835f4c0880c4ec5bc8eab730ad579c0";
    private static final String url = "http://www.tuling123.com/openapi/api";
    private static final String secret = "6ba1beb0f02c3ae1";
    private static Logger logger = LoggerFactory.getLogger(TLChatServiceImpl.class);

    public TLChatServiceImpl() {

    }

    @Override
    public String sendMsg(String msg) {
        String param = "{\n" +
                "\"key\": \"" + apiKey + "\",\n" +
                "\"info\": \"" + msg + "\",\n" +
                "\"loc\":\"北京市中关村\",\n" +
                "\"userid\":\"123456\"\n" +
                "}";
        String timestamp = new Date().getTime()+"";
        String keyParam = secret+timestamp+apiKey;
        String aesKey = DigestUtils.md5Hex(keyParam);
        String result = "{\"key\":\""+apiKey+"\"," +
                "\"timestamp\":"+timestamp+",";
        try {
            SecretKey secretKey = new SecretKeySpec(Hex.decodeHex(aesKey.toCharArray()),"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            String data = Base64.encodeBase64String(cipher.doFinal(param.getBytes("UTF-8")));
            result+="\"data\":\""+data+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("send data:{}",result);
        HttpResponse response = ClientUtils.post(result, url);
        if (response.getStatusLine().getStatusCode() == 200) {
            try {
                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                logger.debug("rec info : {}",json);
                JSONObject jsonObject = JSONObject.parseObject(json);
                return jsonObject.getString("info");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
