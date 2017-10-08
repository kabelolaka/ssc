package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//	Update the configuration so users with role VIEWER can ALSO access that same URL pattern.
		//	After completing this task, re-deploy the web application. Login as user 'vince' and you
		//	should now be able to access the account list and account details.
		//	However trying to Edit the Account Details again causes an Access Denied error because
		//	vince is not an EDITOR.

		//	Then try to access 'http://localhost:8080/accounts/hidden'.
		//	As you can see, this URL is currently not protected.
		//	Add an antMatchers with pattern /accounts/** to serve as a catch-all BELOW all other antMatchers calls.
		//	For this pattern, all users should be authenticated (no specific role required).
		//	Save and try to access this URL again, you should now be redirected to the login page.
		http
			.formLogin()
				.loginPage("/login")
				.permitAll()  // Unrestricted access to the login page
				.and()
			.exceptionHandling()
				.accessDeniedPage("/denied")
				.and()
			.authorizeRequests()
				.antMatchers("/accounts/resources/**").permitAll()
				.antMatchers("/accounts/edit*").hasRole("EDITOR")
				.antMatchers("/accounts/account*").hasAnyRole("VIEWER", "EDITOR")
				.antMatchers("/accounts/**").authenticated()
				.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/index.html");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//	Save and restart the web application.
		//	If you don't see the login form, log out from the application by clicking on the "log out" link.
		//	You can now log in with the user 'edith'.  
		//	Once logged in, click one of the links to reach the  account details page, then click the "Edit account" link.  
		//	You will be able to edit account details. Try to log in again using 'vince' and double-check 
		//	that vince, who only has 'VIEWER' rights, is still not allowed to edit account information.
		auth
			.inMemoryAuthentication()
				.passwordEncoder(new StandardPasswordEncoder())
				.withUser("vince").password("08c461ad70fce6c74e12745931085508ccb2090f2eae3707f6b62089c634ddd2636f380f40109dfb").roles("VIEWER").and()
				.withUser("edith").password("4cfbf05e4493d17125c547fdba494033d7aceee9310f253f3e96c4f928333d2436d669d63a84fe4f").roles("EDITOR");
	}

	//	For each of the users declared in the inMemoryAuthentication section above, replace the password with the
	//	sha-256 encoded versions of them below.  Afterwards save, restart, and try logging in again.
	//	
	//	Encoded version of vince is 08c461ad70fce6c74e12745931085508ccb2090f2eae3707f6b62089c634ddd2636f380f40109dfb
	//	Encoded version of edith is 4cfbf05e4493d17125c547fdba494033d7aceee9310f253f3e96c4f928333d2436d669d63a84fe4f
	//
	//	If you'd like to generate another password, use (new StandardPasswordEncoder()).encode("thePassword")
	
	
}
