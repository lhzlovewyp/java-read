package com.sea.common.utils;

import com.sun.crypto.provider.SunJCE;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * Created by lvhaizhen on 2018/8/23.
 */
public class DesUtils {

    private static String strDefaultKey = "java-reanddfdsasdf112312";
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    public static DesUtils getInstance() {
        return new DesUtils(strDefaultKey);
    }

    public static String byteArray2HexStr(byte[] arrB) {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);

        for(int i = 0; i < iLen; ++i) {
            int intTmp;
            for(intTmp = arrB[i]; intTmp < 0; intTmp += 256) {
                ;
            }

            if(intTmp < 16) {
                sb.append("0");
            }

            sb.append(Integer.toString(intTmp, 16));
        }

        return sb.toString();
    }

    public static byte[] hexStr2ByteArray(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];

        for(int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }

        return arrOut;
    }

    public DesUtils(String strKey) {
        Security.addProvider(new SunJCE());

        try {
            Key e = this.getKey(strKey.getBytes());
            this.encryptCipher = Cipher.getInstance("DES");
            this.encryptCipher.init(1, e);
            this.decryptCipher = Cipher.getInstance("DES");
            this.decryptCipher.init(2, e);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public byte[] encrypt(byte[] arrB) {
        byte[] encrypt = null;

        try {
            encrypt = this.encryptCipher.doFinal(arrB);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return encrypt;
    }

    public String encrypt(String strIn) {
        return byteArray2HexStr(this.encrypt(strIn.getBytes()));
    }

    public byte[] decrypt(byte[] arrB) {
        byte[] decrypt = null;

        try {
            decrypt = this.decryptCipher.doFinal(arrB);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return decrypt;
    }

    public String decrypt(String strIn) {
        return new String(this.decrypt(hexStr2ByteArray(strIn)));
    }

    private Key getKey(byte[] arrBTmp) throws Exception {
        byte[] arrB = new byte[8];

        for(int key = 0; key < arrBTmp.length && key < arrB.length; ++key) {
            arrB[key] = arrBTmp[key];
        }

        SecretKeySpec var4 = new SecretKeySpec(arrB, "DES");
        return var4;
    }

    public static void main(String[] args) {
        String content = "111111";

        try {
            DesUtils e = getInstance();
            String passwd = e.encrypt(content);
            System.out.println(passwd);

            System.out.println(e.decrypt("31011f66b9e01ba2"));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
