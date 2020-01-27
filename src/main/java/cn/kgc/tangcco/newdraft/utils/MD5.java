package cn.kgc.tangcco.newdraft.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String getMD5(String plainText,int length){

        try{
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            messageDigest.update(plainText.getBytes());
            byte[] b=messageDigest.digest();

            int i=0;
            StringBuilder builder=new StringBuilder("");
            for (int offset=0;offset<b.length;offset++){
                i=b[offset];
                if (i<0){
                    i+=256;
                }
                if (i<16){
                    builder.append(0);
                }
                builder.append(Integer.toHexString(i));
            }
            return builder.toString().substring(0,length);

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 密码字符串+静态盐组合的32位加密字符串
     * @param str
     * @param ssalt
     * @return
     */
    public static String getMD5_SsaltStrPwd(String str, String ssalt) {
        return getMD5(str + ssalt, 32);
    }

    public static void main(String[] args) {
        String s= MD5.getMD5("mazepeng",32);
        System.out.println(s);
    }


}
