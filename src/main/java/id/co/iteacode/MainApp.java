package id.co.iteacode;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import id.co.iteacode.model.Bagian;
import id.co.iteacode.model.Cuti;
import id.co.iteacode.model.Departement;
import id.co.iteacode.model.Jabatan;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.Role;
import id.co.iteacode.model.User;
import id.co.iteacode.util.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class MainApp {

	@Autowired
	private BCryptPasswordEncoder pass;
	
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}


}