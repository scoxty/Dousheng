package com.dousheng.video.toolkit;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.UUID;

public class OssUtil {
    private static final String Oss = "https://dousheng-xty.oss-cn-guangzhou.aliyuncs.com/";
    private static final String endPoint = "https://oss-cn-guangzhou.aliyuncs.com/";
    private static final String bucketName = "dousheng-xty";
    private static final String videoDir = "video/";
    private static final String pictureDir = "picture/";

    private static OSS getOssClient() throws ClientException {
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        return new OSSClientBuilder().build(endPoint, credentialsProvider);
    }

    public static String uploadVideo(String localPath, String videoName) throws ClientException, OSSException {
        OSS ossClient = getOssClient();

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, videoDir + videoName, new File(localPath));
            ossClient.putObject(putObjectRequest);
        } finally {
            ossClient.shutdown();
        }

        return Oss + videoDir + videoName;
    }
}