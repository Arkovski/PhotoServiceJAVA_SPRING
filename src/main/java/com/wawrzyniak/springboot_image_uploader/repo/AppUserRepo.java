package com.wawrzyniak.springboot_image_uploader.repo;

import com.wawrzyniak.springboot_image_uploader.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Reporyztorium uzytkowników

@Repository     //adnotacja
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    //zwracanie AppUser
    AppUser findByUsername(String username);
}
