package sshtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @ClassName: SFTP
 * @Author: Ares
 * @Description: TODO(sftp demo)
 * @Date: 2013-3-1 下午12:25:24
 */
public class SFTP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SFTP ftp = new SFTP();
		String host = "10.0.0.251";
		int port = 22;
		String username = "root";
		String password = "sankairoot";
		String directory = "/root/";
		String uploadFile = "D:/VC_RED.cab";
		String downloadFile = "VC_RED.cab";
		String saveFile = "D:/VC_RED.cab";
		String deleteFile = "VC_RED.cab";

		ChannelSftp sftp = ftp.connect(host, port, username, password);
		ftp.upload(directory, uploadFile, sftp);
		ftp.download(directory, downloadFile, saveFile, sftp);
		//ftp.delete(directory, deleteFile, sftp);
		try {
			sftp.cd(directory);
			sftp.mkdir("createFolder");
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			// 如果没有sesstion的disconnect，程序不会退出
			sftp.getSession().disconnect();
			sftp.disconnect();
			sftp.exit();
		}
	}

	/**
	 * @AddBy: Ares
	 * @Description: TODO(connect the host)
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 */
	public ChannelSftp connect(String host, int port, String username, String password) {
		ChannelSftp csftp = null;
		JSch jsch = new JSch();
		try {
			Session sshSession = jsch.getSession(username, host, port);
			System.out.println("jsch session created, user=" + username);

			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("session is connected.");

			Channel channel = sshSession.openChannel("sftp");
			channel.connect();

			csftp = (ChannelSftp) channel;
			System.out.println("connected to host:" + host);

		} catch (JSchException e) {
			e.printStackTrace();
		}
		return csftp;
	}

	/**
	 * @AddBy: Ares
	 * @Description: TODO(upload file to host)
	 * @param directory
	 * @param uploadFile
	 * @param sftp
	 * @return
	 */
	public boolean upload(String directory, String uploadFile, ChannelSftp sftp) {
		File file = new File(uploadFile);
		try {
			sftp.cd(directory);
			sftp.put(new FileInputStream(file), file.getName());
			System.out.println("upload file success, file:" + uploadFile);
		} catch (Exception e) {
			System.err.println("upload file failed, file:" + uploadFile);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @AddBy: Ares
	 * @Description: TODO(download file from host)
	 * @param directory
	 * @param downloadFile
	 * @param saveFile
	 * @param sftp
	 * @return
	 */
	public boolean download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
		File file = new File(saveFile);
		try {
			sftp.cd(directory);
			sftp.get(downloadFile, new FileOutputStream(file));
			System.out.println("download file success, file:" + downloadFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SftpException e) {
			System.err.println("download file failed, file:" + downloadFile);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @AddBy: Ares
	 * @Description: TODO(delete file of host)
	 * @param directory
	 * @param deleteFile
	 * @param sftp
	 * @return
	 */
	public boolean delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
			System.out.println("delete file success, file:" + deleteFile);
		} catch (SftpException e) {
			System.err.println("delete file failed, file:" + deleteFile);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @AddBy: Ares
	 * @Description: TODO(get file list from directory of host)
	 * @param directory
	 * @param sftp
	 * @return
	 */
	public Vector<?> listFiles(String directory, ChannelSftp sftp) {
		try {
			return sftp.ls(directory);
		} catch (SftpException e) {
			System.err.println("list directory failed, directory:" + directory);
			e.printStackTrace();
		}
		return null;
	}

}