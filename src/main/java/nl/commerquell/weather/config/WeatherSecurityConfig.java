package nl.commerquell.weather.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
public class WeatherSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*
		// use jdbc authentication
		auth.jdbcAuthentication().dataSource(dataSource);
		
		System.out.println("Data source set to " + dataSource);
*/
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication().withUser(users.username("gast").password("welkom").roles("USER"));
		auth.inMemoryAuthentication().withUser(users.username("admin").password("Ond3w3d3r!").roles("ADMIN", "USER"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//			.anyRequest().authenticated()
			.antMatchers("/maint/**").hasAnyRole("ADMIN", "USER")
			.antMatchers("/").permitAll()
			.antMatchers("/api/**").permitAll()
			.and()
			.formLogin()
			  .loginPage("/showLoginPage")
			  .loginProcessingUrl("/authenticateTheUser")
			  .permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}

}
