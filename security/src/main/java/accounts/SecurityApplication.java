/**
 * 
 */
package accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import config.MvcConfig;
import config.RootConfig;
import config.SecurityConfig;


/**
 * <p>
 * Access the home page at http://localhost:8080/.
 * <p>
 * Click on the "View Account List" link, you should reach the list of accounts.
 * If the application is not working, please ask your instructor for help
 */

@EnableAutoConfiguration
@Configuration
@ComponentScan
@Import({MvcConfig.class,RootConfig.class, SecurityConfig.class})
public class SecurityApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "jpa");
		SpringApplication.run(SecurityApplication.class, args);
	}

}
