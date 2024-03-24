package com.app.easy2excel.s3fileuploadusingpresignedurl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class FileUploadService {
    @Autowired
    private  AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;


    public String generatePreSignedUrl(String filePath,
                                       HttpMethod httpMethod) {
        Date date = new Date(System.currentTimeMillis() + 1000 * 60 * 5);
//        calendar.setTime(new Date());
//        calendar.add(Calendar.MINUTE, 10); //validity of 10 minutes
        return amazonS3.generatePresignedUrl(bucketName, filePath, date, httpMethod).toString();
    }
}
