package dpcsys.api.dpcnmm.util;

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
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取base64 加密字符串
     *
     * @param str
     * @return
     * @method: getBase64Str
     * @Description: TODO
     * @author : liujx
     * @date 2018年5月9日 下午7:31:24
     */
    public static String getBase64Str(String str) {
        return (new Base64().encodeAsString(str.getBytes()));
    }

    /**
     * md5 加密成数组  base64字符串
     *
     * @param str
     * @return
     * @method: getmd5base64
     * @Description: TODO
     * @author : liujx
     * @date 2018年5月9日 下午7:25:31
     */
    public static String getmd5ByteBase64(String str) {
        byte[] bs = getmd5Digest(str);
        if (bs == null) return null;

        return (new Base64().encodeAsString(bs));
    }

    /**
     * md5 加密字符串  base64 加密
     *
     * @param str
     * @return
     * @method: getmd5StrBase64
     * @Description: TODO
     * @author : liujx
     * @date 2018年5月9日 下午7:24:19
     */
    public static String getmd5StrBase64(String str) {
        String md5 = getMD5(str);
        return (new Base64().encodeAsString(md5.getBytes()));
    }

    /**
     * md5 转换成byte数组 base64 加密 之后的判断方法
     *
     * @param base64str
     * @param md5str
     * @return
     * @method: getmd5StrBase64decode
     * @Description: TODO
     * @author : liujx
     * @date 2018年5月9日 下午7:26:12
     */
    public static boolean getmd5ByteBase64decode(String base64str, String md5str) {
        StringBuilder stringbuilder = new StringBuilder();
        byte[] getmd5Digest = Base64.decodeBase64(base64str);
        for (int i = 0; i < getmd5Digest.length; i++) {
            if ((getmd5Digest[i] & 0xff) < 0x10) {
                stringbuilder.append("0");
            }
            stringbuilder.append(Long.toString(getmd5Digest[i] & 0xff, 16));
        }
        System.out.println(stringbuilder.toString());
        if (stringbuilder.toString().equals(md5str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param base64str
     * @param md5str
     * @return
     * @method: getmd5StrBase64
     * @Description: TODO
     * @author : liujx
     * @date 2018年5月9日 下午7:28:43
     */
    public static boolean getmd5StrBase64(String base64str, String md5str) {
        if (base64str.equals(new Base64().encodeAsString(md5str.getBytes()))) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String getmd5base64str = getmd5StrBase64("123456");
        System.out.println(getmd5base64str);
    }
}