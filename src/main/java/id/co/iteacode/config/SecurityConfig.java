package id.co.iteacode.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.pripurna" })
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/assets/**").permitAll()
				.antMatchers("/pengajuan-cuti/add", 
						"/pengajuan-cuti-saya")
				.hasAnyRole("HR", "KARYAWAN")
				.antMatchers("/departement/**", 
						"/bagian/**",
						"/jabatan/**",
						"/cuti/**",
						"/pegawai/**",
						"/user/**",
						"/pegawai-cuti/**",
						"/pegawai-cuti-detail/**",
						"/pegawai-cuti/**",
						"/pegawai-cuti/**",
						"/cuti/**")
				.hasAnyRole("HR")
				.antMatchers("/pegawai/**",
						"/user/**", 
						"/pegawai-cuti-detail/**")
				.hasAnyRole("PIMPINAN", "HR")
				.antMatchers("/login").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/login")
				.loginProcessingUrl("/login")
//				.defaultSuccessUrl("/", true)
				.successHandler(myAuthenticationSuccessHandler())
//        .failureHandler(authenticationFailureHandler())
				.failureUrl("/login?error=true").and().logout().deleteCookies("JSESSIONID").and().rememberMe()
				.key("uniqueAndSecret").tokenValiditySeconds(3600);
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new MySimpleUrlAuthenticationSuccessHandler();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public LocalDateUtil localDateUtil() {
//		return new LocalDateUtil();
//	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
