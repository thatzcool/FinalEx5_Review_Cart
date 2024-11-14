package com.ssg.finalex5_review_cart.upload.util;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class UploadUtil {


    @Value("${com.ssg.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {

        File tempFolder = new File(uploadPath);

        if(tempFolder.exists() == false) {
            tempFolder.mkdir();
        }

        uploadPath = tempFolder.getAbsolutePath();

        log.info("-------------------------------------");
        log.info(uploadPath);
    }

    public List<String> upload(MultipartFile[] files) {

        List<String> result = new ArrayList<>();

        for (MultipartFile file:files) {
            log.info("-------------------------------");
            log.info("name " + file.getOriginalFilename());

            if(file.getContentType().startsWith("image") == false){
                log.error("File type not supported " + file.getContentType());
                continue;
            }

            String uuid = UUID.randomUUID().toString();

            String saveFileName = uuid + "_" + file.getOriginalFilename();

            try (InputStream in = file.getInputStream();
                 OutputStream out = new FileOutputStream(uploadPath + File.separator + saveFileName)
            ) {

                FileCopyUtils.copy(in, out);

                Thumbnails.of( new File(uploadPath+ File.separator+saveFileName))
                        .size(200,200)
                        .toFile(uploadPath+File.separator+"s_"+saveFileName);


                result.add(saveFileName);

            } catch (Exception e) {
                log.error(e.getMessage());
            }//end catch
        }
        return result;
    }

    public void deleteFile(String fileName) {
        File file = new File(uploadPath + File.separator + fileName);
        File thumbFile = new File(uploadPath + File.separator + "s_" + fileName);

        try{
            if(file.exists()) {
                file.delete();
            }

            if(thumbFile.exists()) {
                thumbFile.delete();
            }
        }catch(Exception e) {
            log.error(e.getMessage());
        }

    }

}