package application.andrei.kukshinov.security;


import application.andrei.kukshinov.security.jwt.JwtProvider;
import application.andrei.kukshinov.security.jwt.JwtUserNamePasswordAuthenticationFilter;
import application.andrei.kukshinov.security.jwt.JwtValidationTokenFilter;
import application.andrei.kukshinov.security.service.impl.UserDetailServiceImplementation;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService(getUserDetailsService()).passwordEncoder(getEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	   http.csrf().disable() //for now
			 .sessionManagement()
			 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			 .and()
			 .addFilter(new JwtUserNamePasswordAuthenticationFilter(authenticationManagerBean()))
			 .addFilterAfter(getJwtValidationFilter(),JwtUserNamePasswordAuthenticationFilter.class).authorizeRequests()
			 .antMatchers("/", "/register", "index", "/css/*", "/js/*").permitAll()
			 .anyRequest().authenticated();
    }

    @Bean
    public RestTemplate getRestTemplate() {
	   ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
			 HttpClients.createDefault());
	   return new RestTemplate(requestFactory);
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
	   userDetailsService = new UserDetailServiceImplementation();
	   return userDetailsService;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
	   return authenticationManager();
    }

    @Bean
    public PasswordEncoder getEncoder() {
	   return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public JwtProvider getProvider() {
	   return new JwtProvider();
    }

    @Bean
    public JwtValidationTokenFilter getJwtValidationFilter() {
	   JwtValidationTokenFilter tokenFilter = new JwtValidationTokenFilter();
	   tokenFilter.setJwtProvider(getProvider());
	   tokenFilter.setUserDetailsService(getUserDetailsService());
	   return tokenFilter;
    }
}
