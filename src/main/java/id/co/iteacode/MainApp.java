package id.co.iteacode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import id.co.iteacode.util.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class MainApp extends SpringBootServletInitializer {

	@Autowired
	private BCryptPasswordEncoder pass;
	
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}


}