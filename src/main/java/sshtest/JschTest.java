package sshtest;

import java.io.FileInputStream;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class JschTest {
	public static void main(String[] args) throws Exception {
		JSch sch = new JSch();
		Session session = sch.getSession("sshd", "10.0.0.251", 22);
		session.setPassword("sankairoot");
		UserInfo userInfo = new UserInfo() {

			@Override
			public void showMessage(String message) {
				System.out.println(message);
			}

			@Override
			public boolean promptYesNo(String message) {
				System.out.println(message);
				return false;
			}

			@Override
			public boolean promptPassword(String message) {
				System.out.println(message);
				return false;
			}

			@Override
			public boolean promptPassphrase(String message) {
				System.out.println(message);
				return false;
			}

			@Override
			public String getPassword() {
				return "sankairoot";
			}

			@Override
			public String getPassphrase() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		session.setUserInfo(userInfo);
		sch.addIdentity("sankairoot");
		ChannelSftp c = (ChannelSftp) session.openChannel("sftp");
		c.connect();
		c.put(new FileInputStream("D:/VC_RED.cab"), "/root");
		c.disconnect();
	}
}
