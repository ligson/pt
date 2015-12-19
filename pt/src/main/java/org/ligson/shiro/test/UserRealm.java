package org.ligson.shiro.test;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class UserRealm implements Realm {

	@Override
	public String getName() {
		return "myuserralm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		if (token instanceof UsernamePasswordToken) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String name = (String) token.getPrincipal();
		String password = (String) token.getCredentials();
		if (!"ligson".equals(name)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!"password".equals(password)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		return new SimpleAuthenticationInfo(name, password, getName());
	}

}
