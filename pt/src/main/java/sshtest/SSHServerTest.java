package sshtest;

import javax.mail.PasswordAuthentication;

import org.apache.sshd.server.SshServer;

public class SSHServerTest {
	public static void main(String[] args) throws Exception{
		SshServer server = SshServer.setUpDefaultServer();
		server.setHost("10.0.0.251");
		server.setPort(22);
		server.start();
		
	}
}
