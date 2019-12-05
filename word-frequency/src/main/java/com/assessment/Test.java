package com.assessment;

public class Test {

}

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.authenticationProvider(keycloakAuthenticationProvider());
}

