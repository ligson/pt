package sshtest;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannel.ClientChannelEvent;
import org.apache.sshd.client.future.AuthFuture;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.util.io.NoCloseInputStream;
import org.apache.sshd.common.util.io.NoCloseOutputStream;

import com.jcraft.jsch.ChannelSftp;

public class SSHTest {
	public static void main(String[] args) throws Exception {
		
		SshClient client = SshClient.setUpDefaultClient();
		client.start();
		ConnectFuture future = client.connect("root", new InetSocketAddress("10.0.0.251", 22));
		//future.wait(2000);
		future.await();
		ClientSession session = future.getSession();
		System.out.println("======"+session);
		session.addPasswordIdentity("sankairoot");
		AuthFuture authFuture = session.auth();
		authFuture.await();
		System.out.println("登陆是否成功："+authFuture.isSuccess());
	
		ClientChannel channel = session.createShellChannel();
		System.out.println("--------");
		channel.setOut(new NoCloseOutputStream(System.out));
		channel.setErr(new NoCloseOutputStream(System.err));
		channel.setIn(new NoCloseInputStream(System.in));
		channel.open();
		System.out.println("--------");
		
		List<ClientChannelEvent> events = new ArrayList<>();
		events.add(ClientChannelEvent.CLOSED);
		System.out.println("--------");
		channel.waitFor(events, 0);
		System.out.println("--------");
		channel.close(false);
		session.close(false);
		client.stop();
	}
}
