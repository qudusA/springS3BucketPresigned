package com.app.easy2excel.s3fileuploadusingpresignedurl;

import com.amazonaws.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FileUploadController {

    @Autowired
    FileUploadService awsS3Service;


    @GetMapping("/generate-presigned-url")
    public ResponseEntity<String> generatePresignedUrl(@RequestParam("img") MultipartFile file) {
        int index = file.getOriginalFilename().lastIndexOf(".");
//        var mil = UUID.randomUUID().toString();
        String extension = file.getOriginalFilename().substring(index + 1);
//        String fileName = mil + "." + extension;

        return ResponseEntity.ok(
                awsS3Service.generatePreSignedUrl(UUID.randomUUID()+"."+extension, HttpMethod.PUT));
    }



    @GetMapping("/get")
    public String getFromS3(@RequestParam("img") String name){
        return awsS3Service.generatePreSignedUrl(name,HttpMethod.GET);
    }




}
