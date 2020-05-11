package com.seeker.apimuca;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.entities.Personagem;
import com.seeker.utils.UtilsPerson;

@SpringBootApplication
@RestController
public class ApimucaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApimucaApplication.class, args);
	}
	
}
