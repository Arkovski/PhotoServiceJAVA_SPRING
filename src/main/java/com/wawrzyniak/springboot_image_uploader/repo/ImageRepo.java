package com.wawrzyniak.springboot_image_uploader.repo;

import com.wawrzyniak.springboot_image_uploader.model.AppUser;
import com.wawrzyniak.springboot_image_uploader.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Przechowywanie Image

@Repository     //adnotacja
public interface ImageRepo extends JpaRepository<Image, Long> {


}
