package com.bplow.netconn.base.utils;

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * MD5文件摘要 适用于大文件。
 * 
 * @author wenzhi.wang
 * @version $Id: MD5Util.java, v 0.1 2012-4-3 下午8:09:57 wenzhi.wang Exp $
 */
public class MD5Util {

    /** 日志 */
    private static final Logger log         = LoggerFactory.getLogger(MD5Util.class);

    /** 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合 */
    protected static char       hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'   };

    /** MD5 */
    public final static String  MD5         = "MD5";

    /** 缓冲大小 */
    private final static int    BUFFER_SIZE = 8192;

    /**
     * 生成信息摘要
     * 
     * @return  信息摘要
     * @throws Exception
     */
    protected static MessageDigest digestInit() throws Exception {
        return digestInit(MD5);
    }

    /**
     * 生成信息摘要
     * 
     * @return  信息摘要
     * @throws Exception
     */
    protected static MessageDigest digestInit(String digestType) throws Exception {
        MessageDigest messagedigest = null;
        try {
            messagedigest = MessageDigest.getInstance(digestType);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e);
        }
        return messagedigest;
    }

    /**
     * 适用于上G大的文件进行MD5进行MD5摘要
     * @param file
     * @return
     */
    public static String getFileMD5String(File file, String digestType) throws Exception {
        FileInputStream in = null;
        BufferedInputStream bufferInputStream = null;
        MessageDigest messagedigest = digestInit(digestType);

        try {
            //fileContent
            in = new FileInputStream(file);
            bufferInputStream = new BufferedInputStream(in);
            byte[] byet = new byte[BUFFER_SIZE];
            while (true) {
                int readLenth = bufferInputStream.read(byet);
                if (readLenth == -1) {
                    break;
                }
                messagedigest.update(byet, 0, readLenth);
            }

            return bufferToHex(messagedigest.digest());
        } finally {
            IOUtils.closeQuietly(bufferInputStream);
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 对传入的字符串做MD5摘要
     * @param s
     * @return
     * @throws Exception 
     */
    public static String getMD5String(String s) throws Exception {
        return getMD5String(s.getBytes());
    }

    /**
     * 对传入的byte流做MD5摘要
     * @param bytes
     * @return
     * @throws Exception 
     */
    public static String getMD5String(byte[] bytes) throws Exception {
        MessageDigest messagedigest = digestInit();
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    /**
     * byte流转换成十六进制
     * @param bytes
     * @return
     */
    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    /**
     * byte流转换成十六进制
     * @param bytes
     * @param m
     * @param n
     * @return
     */
    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    /**
     * 
     * @param bt
     * @param stringbuffer
     */
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
    
    public static void main(String[] args) throws Exception {
    	 String serialMD5No = MD5Util.getMD5String("/home/admin/sharedata/finsupport/filefactory/remit/20151214/SHBANK/POUT_2015121400007121295.txt");
    	 System.out.println(serialMD5No);
    	 serialMD5No = MD5Util.getMD5String("NULL");
    	 System.out.println(serialMD5No);
	}

}
