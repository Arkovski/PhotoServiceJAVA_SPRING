package com.wawrzyniak.springboot_image_uploader;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {


    //Sprawdzenie poprawności działania Spring Security
    @GetMapping("/test1")               //end point 1
    public String test1() {
        return "test1";
    }

    @GetMapping("/test2")               //end point 2
    public String test2() {
        return "test2";
    }
    @GetMapping("/test3")               //end point 3
    public String test3() {
        return "test3";
    }

}
