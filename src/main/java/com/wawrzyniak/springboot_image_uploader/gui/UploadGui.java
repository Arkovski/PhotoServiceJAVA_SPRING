package com.wawrzyniak.springboot_image_uploader.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.wawrzyniak.springboot_image_uploader.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;

@Route("upload")          //Widok z endpointem uploadImage  -  Vaadin (nie może być / przed uploadImage)
public class UploadGui extends VerticalLayout {    //układanie elementów jeden pod drugim

    private ImageUploader imageUploader;

    @Autowired
    public UploadGui(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;

        Label label = new Label();
        TextField textField = new TextField();          //Sciezka do zdj
        Button button = new Button("upload");

        button.addClickListener(clickEvent ->
        {
            String uploadedImage = imageUploader.uploadFileAndSaveToDB(textField.getValue());      //Wrzucenie obrazka
            Image image = new Image(uploadedImage, "Wrzucono obrazek");             //Info o wrzuceniu obrazka dla użytkownika
            label.setText("Dodano obrazek");
            add(label);
            add(image);

        });

        add(textField);
        add(button);

    }
}
