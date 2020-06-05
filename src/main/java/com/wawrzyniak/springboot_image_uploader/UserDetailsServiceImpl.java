package com.wawrzyniak.springboot_image_uploader;

import com.wawrzyniak.springboot_image_uploader.model.AppUser;
import com.wawrzyniak.springboot_image_uploader.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//Zwracanie usera na podstawie loginu

//Pobieranie (na podstawie loginu użytkownika) danych z DB - pośredniczy login między DB a formularzem logowania
//Potem formularz (hasło: 1234) łączy się z DB (hasło: @!#@!#d4$ad@E12sdd2) i sprawdza czy hasło jest poprawne
@Service        //Klasa zarządzana przez kontekst Springa
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepo appUserRepo;

    @Autowired              //Od Springa 4.2 nie jest konieczna ta adnotacja, ale dajemy innemu programiście info o wstrzyknięciu
    public UserDetailsServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(s);
    }

}
