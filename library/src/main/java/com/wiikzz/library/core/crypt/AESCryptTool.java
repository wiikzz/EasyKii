package com.wiikzz.library.core.crypt;

import android.text.TextUtils;

import com.wiikzz.library.core.utils.MD5Utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by wiikii on 2018/8/29.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class AESCryptTool {

    private static final String AES_CRYPT_ALGORITHM = "AES";
    private static final String AES_CRYPT_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    // 加解密对应的密钥
    private static final String sIvParamString = "abcdtqIv_wiikzz_";
    private static final String sSecretKeyString = "abcdandroid_key_";

    /**
     * AES加密
     * @param content 要加密的字串
     * @param ivParamString 偏移量字串
     * @param secretKeyString 密钥
     * @return 加密后的字节数组，return null if any exception occur.
     */
    private static byte[] encrypt(String content, String ivParamString, String secretKeyString) {
        if (TextUtils.isEmpty(content)) {
            //throw new Exception("CryptTool encrypt content is empty.");
            return null;
        }

        if ((TextUtils.isEmpty(ivParamString) || (ivParamString.length() != 16))
                || (TextUtils.isEmpty(secretKeyString) || (secretKeyString.length() != 16
                && secretKeyString.length() != 24 && secretKeyString.length() != 32))) {
            // SecretKeySpec AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即16bytes。
            // IvParameterSpec AES为16bytes. DES为8bytes.
            //throw new Exception("CryptTool encrypt ivParameterSpec invalid or secretKeySpec invalid.");
            return null;
        }

        byte [] encryptByteData = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyString.getBytes(), AES_CRYPT_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParamString.getBytes());

            Cipher cipher = Cipher.getInstance(AES_CRYPT_TRANSFORMATION); // 可以使用"AES/ECB/PKCS5Padding"
            //初始化，此方法可以采用三种方式，按服务器要求来添加。
            // （1）无第三个参数
            // （2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)
            // （3）采用此代码中的IvParameterSpec
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            encryptByteData = cipher.doFinal(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptByteData;
    }

    /**
     * AES解密
     * @param content 要解密的字节数组
     * @param ivParamString 偏移量字串
     * @param secretKeyString 密钥
     * @return 解密后的字节数组，return null if any exception occur.
     */
    private static byte[] decrypt(byte[] content, String ivParamString, String secretKeyString) {
        if (content == null) {
            //throw new Exception("CryptTool decrypt content is null.");
            return null;
        }

        if ((TextUtils.isEmpty(ivParamString) || (ivParamString.length() != 16))
                || (TextUtils.isEmpty(secretKeyString) || (secretKeyString.length() != 16
                && secretKeyString.length() != 24 && secretKeyString.length() != 32))) {
            // SecretKeySpec AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即16bytes。
            // IvParameterSpec AES为16bytes. DES为8bytes.
            //throw new Exception("CryptTool decrypt ivParameterSpec invalid or secretKeySpec invalid.");
            return null;
        }

        byte [] decryptByteData = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyString.getBytes(), AES_CRYPT_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParamString.getBytes());

            Cipher cipher = Cipher.getInstance(AES_CRYPT_TRANSFORMATION);
            //初始化，此方法可以采用三种方式，按服务器要求来添加。
            // （1）无第三个参数
            // （2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)
            // （3）采用此代码中的IvParameterSpec
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            decryptByteData = cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException
                | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        return decryptByteData;
    }

    // 加密主接口
    public static String encrypt(String sourceString) {
        byte [] resultBytes = encrypt(sourceString, sIvParamString, sSecretKeyString);
        if (resultBytes == null) {
            return null;
        }

        return MD5Utils.bytesToHexString(resultBytes);
    }

    // 解密主接口
    public static String decrypt(String sourceString) {
        if (TextUtils.isEmpty(sourceString)) {
            return null;
        }

        byte [] resultBytes = decrypt(MD5Utils.hexStringToBytes(sourceString),
                sIvParamString, sSecretKeyString);
        return byteArrayToString(resultBytes);
    }

    private static String byteArrayToString(byte [] byteArray) {
        if (byteArray == null) {
            return null;
        }

        try {
            return new String(byteArray, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}
