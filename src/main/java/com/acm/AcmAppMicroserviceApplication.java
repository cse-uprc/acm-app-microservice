package com.acm;

import com.acm.service.activeprofile.ActiveProfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcmAppMicroserviceApplication {

    public static void main(String[] args) {
        ActiveProfile activeProfile = new ActiveProfile();
        activeProfile.setPropertyFile();

        SpringApplication.run(AcmAppMicroserviceApplication.class, args);
    }
}
