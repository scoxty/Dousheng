package com.dousheng.user.toolkit;

import cn.hutool.core.io.FileTypeUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyuncs.exceptions.ClientException;

import java.io.*;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;

public class FileUtil {
    private static final Set<String> IMAGE_TYPES = Set.of("jpg", "jpeg", "png", "gif", "bmp", "webp", "tiff");
    private static final String Oss = "https://dousheng-xty.oss-cn-guangzhou.aliyuncs.com/";
    private static final String endPoint = "https://oss-cn-guangzhou.aliyuncs.com/";
    private static final String bucketName = "dousheng-xty";
    private static final String pictureDir = "picture/";

    public static boolean isImage(String base64Image) {
        byte[] fileBytes = Base64.getDecoder().decode(base64Image);
        InputStream inputStream = new ByteArrayInputStream(fileBytes);
        String type = FileTypeUtil.getType(inputStream);
        return type != null && IMAGE_TYPES.contains(type.toLowerCase());
    }

    private static OSS getOssClient() throws ClientException {
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        return new OSSClientBuilder().build(endPoint, credentialsProvider);
    }

    public static String uploadPicture(String base64Image) throws ClientException, IOException {
        OSS ossClient = getOssClient();

        byte[] fileBytes = Base64.getDecoder().decode(base64Image);
        String suffix = FileTypeUtil.getType(new ByteArrayInputStream(fileBytes));
        InputStream inputStream = new ByteArrayInputStream(fileBytes);
        String pictureName = UUID.randomUUID() + "." + suffix;

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, pictureDir + pictureName, inputStream);
            ossClient.putObject(putObjectRequest);
        } finally {
            ossClient.shutdown();
        }

        return Oss + pictureDir + pictureName;
    }
}
