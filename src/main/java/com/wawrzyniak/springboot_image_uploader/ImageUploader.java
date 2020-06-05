package com.wawrzyniak.springboot_image_uploader;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.wawrzyniak.springboot_image_uploader.model.Image;
import com.wawrzyniak.springboot_image_uploader.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

    private Cloudinary cloudinary;          //Tworzenie obiektu

    private ImageRepo imageRepo;

    @Autowired
    public ImageUploader(ImageRepo imageRepo) {                //Inicjalizacja w konstruktorze (Alt+Insert)
        this.imageRepo = imageRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dg3f1a7lo",
                "api_key", "284338645998892",
                "api_secret", "CvsjPFSSlu0uH7fSWKuUGe40zy0"));

    }

    //Metoda do upload'u zdjęć
    public String uploadFileAndSaveToDB (String path){            //Zwracanie tylko stringa (URL)
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadResult.get("url").toString();
    }
}