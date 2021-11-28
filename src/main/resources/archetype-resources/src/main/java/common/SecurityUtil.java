package ${package}.common;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

public class SecurityUtil {

    private static final String salt = "RIjBL4spFTY38mbq";

    private static final String defaultCharset = "utf-8";

    public static String sign(String text) {
        return DigestUtils.md5Hex(getContentBytes(text + salt, defaultCharset));
    }

    public static String sign(String text, String inputCharset) {
        return DigestUtils.md5Hex(getContentBytes(text + salt, inputCharset));
    }

    public static boolean verify(String text, String sign) {
        String mysign = DigestUtils.md5Hex(getContentBytes(text + salt, defaultCharset));
        return mysign.equals(sign);
    }

    public static boolean verify(String text, String sign, String inputCharset) {
        String mysign = DigestUtils.md5Hex(getContentBytes(text + salt, inputCharset));
        return mysign.equals(sign);
    }

    public static boolean validateTimeStamp(long timeStamp) {
        return (System.currentTimeMillis() - timeStamp) / (1000 * 60) <= 1;
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

}
