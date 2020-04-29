package com.heycm.utils;

import com.aliyun.oss.OSS;
import com.heycm.config.CustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-29 0:54
 */
@Component
public class OSSUtil {

    @Resource
    CustomConfig customConfig;
    @Autowired
    private OSS ossClient;

    // 上传图片
    public String uploadImg2OSS(InputStream is, String fileName){
        if (!ossClient.doesBucketExist(customConfig.getOssImgBucketName())){
            ossClient.createBucket(customConfig.getOssImgBucketName());
        }
        fileName = customConfig.getOssImgDir() + "/" + fileName;
        ossClient.putObject(customConfig.getOssImgBucketName(), fileName, is);
        return customConfig.getOssImgHost() + "/" +fileName;
    }

    // 上传文件
    public String uploadFile2OSS(InputStream is, String fileName){
        if (!ossClient.doesBucketExist(customConfig.getOssFileBucketName())){
            ossClient.createBucket(customConfig.getOssFileBucketName());
        }
        fileName = customConfig.getOssFileDir() + "/" + fileName;
        ossClient.putObject(customConfig.getOssFileBucketName(), fileName, is);
        Date expire = new Date(new Date().getTime() + (1000L * 3600 * 24 * 356 * 10));
        URL url = ossClient.generatePresignedUrl(customConfig.getOssFileBucketName(), fileName, expire);
        return url.toString();
    }

    // 查找图片
    public boolean foundImg(String imgName) {
       return ossClient.doesObjectExist(customConfig.getOssImgBucketName(), customConfig.getOssImgDir()+"/"+imgName);
    }

    // 删除图片
    public void deleteImg(String imgName) {
        ossClient.deleteObject(customConfig.getOssImgBucketName(), customConfig.getOssImgDir()+"/"+imgName);
    }

    // 查找文件
    public boolean foundFile(String fileName) {
        return ossClient.doesObjectExist(customConfig.getOssFileBucketName(), customConfig.getOssFileDir()+"/"+fileName);
    }

    // 删除文件
    public void deleteFile(String fileName) {
        ossClient.deleteObject(customConfig.getOssFileBucketName(), customConfig.getOssFileDir()+"/"+fileName);
    }
}
