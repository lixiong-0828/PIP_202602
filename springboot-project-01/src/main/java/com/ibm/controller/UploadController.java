package com.ibm.controller;

import com.aliyuncs.exceptions.ClientException;
import com.ibm.pojo.Result;
import com.ibm.utils.AliyunOSSutils;
import com.ibm.utils.GetProperties;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.UUID;


@Slf4j
@RestController
public class UploadController {


//        @PostMapping("/upload")
//    public Result uploadFile(String username,
//                           Integer age,
//                           MultipartFile file) throws IOException {
//        log.info("Upload File: name={}, age={}, file={}", username, age, file);
//
//         String originalFilename = file.getOriginalFilename();;
//         int index =  originalFilename.lastIndexOf(".");
//         String suffix = originalFilename.substring(index);
//         //log.info("file name {}",originalFilename.substring(0,index) );
//
//         String newFileName = UUID.randomUUID().toString() + suffix;
//
//        file.transferTo(new File("C:/image/" + newFileName));
//        return Result.success();
//    }

//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint;
//    @Value("${aliyun.oss.bucketName}")
//    private String bucketName;


    @Autowired
    private GetProperties getProperties;

    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) throws IOException, ClientException {
        log.info("Upload File: file={}", file);

        String originalFilename = file.getOriginalFilename();;
        int index =  originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);


        String url = AliyunOSSutils.upload(getProperties.getEndpoint(),getProperties.getBucketName(),originalFilename.getBytes(),suffix);
        return Result.success(url);
    }
}
