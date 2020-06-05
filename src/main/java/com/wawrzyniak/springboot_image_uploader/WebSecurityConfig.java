package com.wawrzyniak.springboot_image_uploader;


import com.wawrzyniak.springboot_image_uploader.model.AppUser;
import com.wawrzyniak.springboot_image_uploader.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Zabezpieczanie end-point'ów - ustalanie na który end-point (dany) użytk. może/nie może wejść


    private UserDetailsServiceImpl userDetailsService;
    private AppUserRepo appUserRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AppUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    //Definicja użytkowników w bazie danych
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Przechowywanie użytkowników w bazie danych ( początkowa wersja )
    //      auth.inMemoryAuthentication().withUser(new User("Arek", passwordEncoder().encode("1234"), Collections.singleton(new SimpleGrantedAuthority("user"))));

        //Pobieranie użytkowików z DB
       auth.userDetailsService(userDetailsService);
    }

    //Definicja zabezpieczonych end-pointów
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test1").hasRole("USER")      //By wejść na test1 (w TestApi.java) trzeba być zalogowanym jako USER
                .antMatchers("/test2").hasRole("ADMIN")     //By wejść na test2 (w TestApi.java) trzeba być zalogowanym jako ADMIN
                .antMatchers("/uploadImage").hasRole("ADMIN")       //Tylko ADMIN może upload'ować zdjęcia (ImageUploader i gui->UploadGui)
                .and()
                .formLogin().permitAll()       //dostęp mają wszyscy    //Każdy może wejść na test3
                .and()
                .csrf().disable();

    }

    //Szyfrowanie haseł - BackCrypt
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();         //zdefiniowanie rodzaju szyfrowania i deszyfrowania hasła użytkowników
    }

    //Zawsze się ta funkcja uruchomi, kiedy aplikacja wystartuje
    @EventListener(ApplicationReadyEvent.class)
    public void get(){
        AppUser appUser = new AppUser("Arek", passwordEncoder().encode("1234"),"ROLE_USER");
        AppUser appUserAdmin = new AppUser("Admin", passwordEncoder().encode("1234"),"ROLE_ADMIN");
        appUserRepo.save(appUser);          //zapisywanie USER w DB
        appUserRepo.save(appUserAdmin);
    }

}
