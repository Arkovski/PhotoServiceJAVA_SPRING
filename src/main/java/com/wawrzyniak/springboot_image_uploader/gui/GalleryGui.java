//package com.wawrzyniak.springboot_image_uploader.gui;
//
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.html.Label;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.Route;
//import com.wawrzyniak.springboot_image_uploader.ImageUploader;
//import com.wawrzyniak.springboot_image_uploader.model.Image;
//import com.wawrzyniak.springboot_image_uploader.repo.ImageRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//@Route("gallery")          //Widok z endpointem uploadImage  -  Vaadin (nie może być / przed uploadImage)
//public class GalleryGui extends VerticalLayout {    //układanie elementów jeden pod drugim
//
//    private ImageRepo imageUploader;
//
//    @Autowired
//    public GalleryGui(ImageUploader imageUploader) {
//        this.imageUploader = imageUploader;
//        List<Image> all = imageUploader.findAll();
//
//        all.stream().forEach(getElement -> {
//           com.vaadin.flow.component.html.Image image = new com.vaadin.flow.component.html.Image(element.getImageAddress(), "break");
//        });
//    }
//}
