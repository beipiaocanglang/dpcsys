package dpcsys.consumption.frame.util;


import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;


/**
 * MD5
 *
 * @author : lijianjun
 * @version V1.0
 * @Description: TODO
 * @Company:Justin
 * @date: 2015年11月23日 下午2:57:44
 */
public class Md5Security {

    /**
     * @param str
     * @return String
     * @method: getMD5
     * @Description: 获得32位长度的字符串 ,16位的数组经过编码转换后的变换
     */
    public static String getMD5(String str) {
        byte[] bs = getmd5Digest(str);
        if (bs == null) return null;

        StringBuffer sb = new StringBuffer();
        int itmp = 0;

        for (int i = 0; i < bs.length; i++) {
            if (bs[i] < 0) {
                itmp = 256 + bs[i];
            } else {
                itmp = bs[i];
            }

            if (itmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(itmp));
        }
        return sb.toString();
    }

    /**
     * @param str
     * @return byte[]
     * @method: getmd5Digest
     * @Description: md5 encryption
     */
    public static byte[] getmd5Digest(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(str.getBytes());
            return md.digest();
            //return(new BASE64Encoder().encode(digest));
        } catch (Exception e) {
            return null;
        }
    }

    //获得24位长度的字符,16位的数组经过base转换后的变换
    public static String getmd5base64(String str) {
        byte[] bs = getmd5Digest(str);
        if (bs == null) return null;

        return (new Base64().encodeAsString(bs));
    }

    public static void main(String[] args) {
        System.out.println(getMD5("111111"));
    }
}