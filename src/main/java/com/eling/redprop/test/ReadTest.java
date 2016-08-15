package com.eling.redprop.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Properties;

public class ReadTest {
	static Connection connection;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/elconfig?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		File root = new File("F:/wk2/elingcong");
		File[] files = root.listFiles();
		for (File dir : files) {
			File[] propFiles = dir.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith("properties");
				}
			});
			for (File propFile : propFiles) {
				Properties properties = new Properties();
				properties.load(new FileInputStream(propFile));
				Enumeration<?> enumeration = properties.propertyNames();
				while (enumeration.hasMoreElements()) {
					String key = enumeration.nextElement().toString();
					String value = properties.getProperty(key);
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					System.out.println("catalog:" + dir.getName() + "	key:" + key + "		value:" + value);
					PreparedStatement ps = connection
							.prepareStatement("select id,value from cfg_properties_copy where `catalog`=? and `key`=?");
					ps.setString(1, dir.getName());
					ps.setString(2, key);
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						String oldValue = rs.getString(1);
						if (oldValue != value) {
							PreparedStatement ps2 = connection
									.prepareStatement("update cfg_properties_copy set `value`=? where id=?");
							ps2.setString(1, value);
							ps2.setInt(2, rs.getInt(1));
							ps2.executeUpdate();
						}
					} else {
						PreparedStatement ps2 = connection.prepareStatement(
								"insert into cfg_properties_copy(`catalog`,`key`,`value`) values(?,?,?)");
						ps2.setString(1, dir.getName());
						ps2.setString(2, key);
						ps2.setString(3, properties.getProperty(key));
						ps2.execute();
					}
				}
			}

		}
	}
}
