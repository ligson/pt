package org.ligson.shiro.test;

import java.io.File;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroTest {
	private static Logger logger = LoggerFactory.getLogger(ShiroTest.class);

	@RequiresRoles("admin1")
	public void hello() {
		System.out.println("hello.......");
	}

	public static void main(String[] args) {
		// Using the IniSecurityManagerFactory, which will use the an INI file
		// as the security file.
		ShiroTest test = new ShiroTest();
		
		File file = new File("./src/main/resources/auth.ini");
		System.out.println(file.getAbsolutePath());
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(file.getAbsolutePath());

		// Setting up the SecurityManager...
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject user = SecurityUtils.getSubject();

		logger.info("User is authenticated:  " + user.isAuthenticated());
		UsernamePasswordToken token = new UsernamePasswordToken("ligson", "password");

		System.out.println(token.getPassword());
		user.login(token);
		logger.info("User is authenticated:  " + user.isAuthenticated());
		logger.info("user has admin role:" + user.hasRole("admin"));
		test.hello();
		
		user.logout();
		logger.info("User is authenticated:  " + user.isAuthenticated());
	}
}
