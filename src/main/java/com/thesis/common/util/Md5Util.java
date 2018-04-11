package com.thesis.common.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 15:48
 * @Description: md5 加密工具
 */
public class Md5Util {

    static private final char base64_code[] = {'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    /**
     * 获得字符串的md5值
     *
     * @param str 待加密的字符串
     * @return md5加密后的字符串
     */
    public static String getMD5String(String str) {
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return HexUtil.bytes2Hex(bytes);

    }


    /**
     * 获得字符串的md5大写值
     *
     * @param str 待加密的字符串
     * @return md5加密后的大写字符串
     */
    public static String getMD5UpperString(String str) {
        return getMD5String(str).toUpperCase();
    }

    /**
     * 获得文件的md5值
     *
     * @param file 文件对象
     * @return 文件的md5
     */
    public static String getFileMD5String(File file) {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                    file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            ret = HexUtil.bytes2Hex(md5.digest());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ch != null) {
                try {
                    ch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    /**
     * 获得文件md5值大写字符串
     *
     * @param file 文件对象
     * @return 文件md5大写字符串
     */
    public static String getFileMD5UpperString(File file) {
        return getFileMD5String(file).toUpperCase();
    }

    /**
     * 校验文件的md5值
     *
     * @param file 目标文件
     * @param md5  基准md5
     * @return 校验结果
     */
    public static boolean checkFileMD5(File file, String md5) {
        return getFileMD5String(file).equalsIgnoreCase(md5);
    }

    /**
     * 校验字符串的md5值
     *
     * @param str 目标字符串
     * @param md5 基准md5
     * @return 校验结果
     */
    public static boolean checkMD5(String str, String md5) {
        return getMD5String(str).equalsIgnoreCase(md5);
    }

    /**
     * 获得加盐md5，算法过程是原字符串md5后连接加盐字符串后再进行md5
     *
     * @param str  待加密的字符串
     * @param salt 盐
     * @return 加盐md5
     */
    public static String getMD5AndSalt(String str, String salt) {
        return getMD5String(getMD5String(str).concat(salt));
    }

    public static String gensalt(int length) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int bytesLength = base64_code.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(base64_code[random.nextInt(bytesLength)]);
        }
        return sb.toString();
    }


    /**
     * @Author: ZcEdiaos
     * @Date: 2018/4/8 15:48
     * @Description: 进制转换工具
     */
    public static class HexUtil {
        private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        /**
         * 16进制转byte数组
         *
         * @param data 16进制字符串
         * @return byte数组
         * @throws Exception 转化失败的异常
         */
        public static byte[] hex2Bytes(final String data) throws Exception {
            final int len = data.length();

            if ((len & 0x01) != 0) {
                throw new Exception("Odd number of characters.");
            }

            final byte[] out = new byte[len >> 1];

            // two characters form the hex value.
            for (int i = 0, j = 0; j < len; i++) {
                int f = toDigit(data.charAt(j), j) << 4;
                j++;
                f = f | toDigit(data.charAt(j), j);
                j++;
                out[i] = (byte) (f & 0xFF);
            }
            return out;
        }

        /**
         * bytes数组转16进制String
         *
         * @param data bytes数组
         * @return 转化结果
         */
        public static String bytes2Hex(final byte[] data) {
            return bytes2Hex(data, true);
        }

        /**
         * bytes数组转16进制String
         *
         * @param data        bytes数组
         * @param toLowerCase 是否小写
         * @return 转化结果
         */
        public static String bytes2Hex(final byte[] data, final boolean toLowerCase) {
            return bytes2Hex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
        }


        /**
         * bytes数组转16进制String
         *
         * @param data     bytes数组
         * @param toDigits DIGITS_LOWER或DIGITS_UPPER
         * @return 转化结果
         */
        private static String bytes2Hex(final byte[] data, final char[] toDigits) {
            final int l = data.length;
            final char[] out = new char[l << 1];
            // two characters form the hex value.
            for (int i = 0, j = 0; i < l; i++) {
                out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
                out[j++] = toDigits[0x0F & data[i]];
            }
            return new String(out);
        }

        /**
         * 16转化为数字
         *
         * @param ch    16进制
         * @param index 索引
         * @return 转化结果
         * @throws Exception 转化失败异常
         */
        private static int toDigit(final char ch, final int index)
                throws Exception {
            final int digit = Character.digit(ch, 16);
            if (digit == -1) {
                throw new Exception("Illegal hexadecimal character " + ch
                        + " at index " + index);
            }
            return digit;
        }

    }
}
