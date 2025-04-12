package com.dousheng.user;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

class UserApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testFileType() throws IOException {
        InputStream inputStream = new FileInputStream("C:\\Users\\熊廷杨\\Downloads\\图二.jpg");
        byte[] bytes = IoUtil.readBytes(inputStream);
        String image = Base64.getEncoder().encodeToString(bytes);

        // 1. 解码成二进制
        byte[] fileBytes = Base64.getDecoder().decode(image);

        // 2. 封装成 InputStream
        inputStream = new ByteArrayInputStream(fileBytes);

        // 2. 将二进制写入到当前目录下的图片文件
        // 创建一个File对象，代表当前目录下的avatar_decoded.png文件
        File outputFile = new File( UUID.randomUUID() + "." + FileTypeUtil.getType(inputStream));
        // 创建一个FileOutputStream对象，用于将字节数组写入到文件中
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            // 将解码后的字节数组写入到文件输出流中
            fos.write(fileBytes);
            System.out.println("图片已成功保存到当前目录: " + outputFile.getAbsolutePath());
        }

//        // 3. 调用 Hutool 获取类型
//        String type = FileTypeUtil.getType(inputStream);
//
//        System.out.println("文件类型为: " + type);
    }

    @Test
    void testTiemUnit() {
        System.out.println(TimeUnit.MINUTES.toSeconds(1));
    }
}
