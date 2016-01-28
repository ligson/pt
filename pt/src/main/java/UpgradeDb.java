import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.naming.spi.DirStateFactory.Result;

class PersonalAccount {
	private BigInteger account_id;
	private String passport;
	private String mobile;
	private String email;
	private String pwd;
	private String source;
	private int status;
	private int auth_status;
	private Date ctime;

	@Override
	public String toString() {
		return "PersonalAccount [account_id=" + account_id + ", passport=" + passport + ", mobile=" + mobile
				+ ", email=" + email + ", pwd=" + pwd + ", source=" + source + ", status=" + status + ", auth_status="
				+ auth_status + ", ctime=" + ctime + "]";
	}

	public BigInteger getAccount_id() {
		return account_id;
	}

	public void setAccount_id(BigInteger account_id) {
		this.account_id = account_id;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAuth_status() {
		return auth_status;
	}

	public void setAuth_status(int auth_status) {
		this.auth_status = auth_status;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public PersonalAccount(BigInteger account_id, String passport, String mobile, String email, String pwd,
			String source, int status, int auth_status, Date ctime) {
		super();
		this.account_id = account_id;
		this.passport = passport;
		this.mobile = mobile;
		this.email = email;
		this.pwd = pwd;
		this.source = source;
		this.status = status;
		this.auth_status = auth_status;
		this.ctime = ctime;
	}

}

class BizInfo {
	private BigInteger acc_bizinfo_id;
	private String name;
	private String logo;
	private String license_img;
	private int type;
	private String corporate_name;
	private String address;
	private String phone;
	private Date ctime;
	private String description;

	public BigInteger getAcc_bizinfo_id() {
		return acc_bizinfo_id;
	}

	@Override
	public String toString() {
		return "BizInfo [acc_bizinfo_id=" + acc_bizinfo_id + ", name=" + name + ", logo=" + logo + ", license_img="
				+ license_img + ", type=" + type + ", corporate_name=" + corporate_name + ", address=" + address
				+ ", phone=" + phone + ", ctime=" + ctime + ", description=" + description + "]";
	}

	public void setAcc_bizinfo_id(BigInteger acc_bizinfo_id) {
		this.acc_bizinfo_id = acc_bizinfo_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLicense_img() {
		return license_img;
	}

	public void setLicense_img(String license_img) {
		this.license_img = license_img;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCorporate_name() {
		return corporate_name;
	}

	public void setCorporate_name(String corporate_name) {
		this.corporate_name = corporate_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BizInfo(BigInteger acc_bizinfo_id, String name, String logo, String license_img, int type,
			String corporate_name, String address, String phone, Date ctime, String description) {
		super();
		this.acc_bizinfo_id = acc_bizinfo_id;
		this.name = name;
		this.logo = logo;
		this.license_img = license_img;
		this.type = type;
		this.corporate_name = corporate_name;
		this.address = address;
		this.phone = phone;
		this.ctime = ctime;
		this.description = description;
	}

}

class BizKlh {
	private String name;
	private String province;
	private String city;
	private String address;
	private int type;
	private String phone;
	private String license_img;
	private Date ctime;
	private String real_name;
	private BigInteger account_id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLicense_img() {
		return license_img;
	}

	public void setLicense_img(String license_img) {
		this.license_img = license_img;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public BigInteger getAccount_id() {
		return account_id;
	}

	public void setAccount_id(BigInteger account_id) {
		this.account_id = account_id;
	}

	public BizKlh(String name, String province, String city, String address, int type, String phone, String license_img,
			Date ctime, String real_name, BigInteger account_id) {
		super();
		this.name = name;
		this.province = province;
		this.city = city;
		this.address = address;
		this.type = type;
		this.phone = phone;
		this.license_img = license_img;
		this.ctime = ctime;
		this.real_name = real_name;
		this.account_id = account_id;
	}

}

public class UpgradeDb {
	private static String USER = "mysqldev";
	private static String PWD = "mysqldev";
	private static String URL = "jdbc:mysql://10.0.0.252:3306/account_center_0114?useUnicode=true&characterEncoding=utf8";
	private static Connection connection;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PWD);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	public BigInteger buildId() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
		String uuid = simpleDateFormat.format(new java.util.Date());
		int random = (int) (Math.random() * Math.pow(10, 3));
		BigInteger bigInteger = new BigInteger(uuid + random);
		return bigInteger;
	}

	// 检查手机号是否存在
	public boolean mobileIsExist(String mobile) throws Exception {
		if (mobile != null) {
			String sql2 = "select count(account_id) from personal_account where mobile=?";
			PreparedStatement ps = connection.prepareStatement(sql2);
			ps.setString(1, mobile);
			// print "检查手机号:"+str(param1)
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		}
		return false;
	}

	// 检查手机号是否存在
	public boolean emailIsExist(String email) throws Exception {
		if (email != null) {
			String sql2 = "select count(account_id) from personal_account where email=?";
			PreparedStatement ps = connection.prepareStatement(sql2);
			ps.setString(1, email);
			// print "检查手机号:"+str(param1)
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		}
		return false;
	}

	// 检查手机号是否存在
	public boolean accountIdIsExist(BigInteger accId) throws Exception {
		if (accId != null) {
			String sql2 = "select count(account_id) from personal_account where account_id=?";
			PreparedStatement ps = connection.prepareStatement(sql2);
			ps.setBigDecimal(1, new BigDecimal(accId));
			// print "检查手机号:"+str(param1)
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		}
		return false;
	}

	// 检查手机号是否存在
	public boolean bizNameIsExist(String bizName) throws Exception {
		if (bizName != null) {
			String sql2 = "select count(acc_bizinfo_id) from business_info where name=?";
			PreparedStatement ps = connection.prepareStatement(sql2);
			ps.setString(1, bizName);
			// print "检查手机号:"+str(param1)
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		}
		return false;
	}

	// 合并账户到个人账户表
	public void insertPersonalAcct(PersonalAccount pa) throws Exception {
		String sql3 = "insert into personal_account(account_id,passport,mobile,email,pwd,source,status,auth_status,ctime) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql3);
		ps.setBigDecimal(1, new BigDecimal(pa.getAccount_id()));
		ps.setString(2, pa.getPassport());
		ps.setString(3, pa.getMobile());
		ps.setString(4, pa.getEmail());
		ps.setString(5, pa.getPwd());
		ps.setString(6, pa.getSource());
		ps.setInt(7, pa.getStatus());
		ps.setInt(8, pa.getAuth_status());
		ps.setDate(9, pa.getCtime());
		ps.execute();
		System.out.println(sql3);
		System.out.println(pa);
	}

	public void insertBizInfo(BizInfo bizInfo) throws Exception {
		String bizName = bizInfo.getName();
		String sql = "select count(acc_bizinfo_id) from business_info where name=?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, bizName);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next()) {
			int n = resultSet.getInt(1);
			if (n > 0) {
				return;
			}
		}
		String sql3 = "insert into business_info(acc_bizinfo_id,name,logo,license_img,type,corporate_name,address,phone,ctime,description) values(?,?,?,?,?,?,?,?,?,?)";
		// print u"插入数据sql:"+ sql3
		// print u"插入数据值:"+ str(row)
		PreparedStatement ps = connection.prepareStatement(sql3);
		ps.setBigDecimal(1, new BigDecimal(bizInfo.getAcc_bizinfo_id()));
		ps.setString(2, bizInfo.getName());
		ps.setString(3, bizInfo.getLogo());
		ps.setString(4, bizInfo.getLicense_img());
		ps.setInt(5, bizInfo.getType());
		ps.setString(6, bizInfo.getCorporate_name());
		ps.setString(7, bizInfo.getAddress());
		ps.setString(8, bizInfo.getPhone());
		ps.setDate(9, bizInfo.getCtime());
		ps.setString(10, bizInfo.getDescription());
		ps.execute();

	}

	public void updateBizInfoRelated(BigInteger bizInfoId, String name) throws Exception {

		// print "info id:"+str(bizInfoId)+"====name:"+str(name)
		// cursor2 = conn.cursor()
		// params=(bizInfoId,name)
		String sql = "update business_klh set acc_bizinfo_id=? where name=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setBigDecimal(1, new BigDecimal(bizInfoId));
		ps.setString(2, name);
		ps.execute();

		sql = "update bussiness_yun set acc_bizinfo_id=? where name=?";
		ps = connection.prepareStatement(sql);
		ps.setBigDecimal(1, new BigDecimal(bizInfoId));
		ps.setString(2, name);
		ps.execute();
	}

	public void updateBizAcctRelated(BigInteger bizAcctId, BigInteger newBizAcctId) throws Exception {
		if (bizAcctId == newBizAcctId) {
			return;
		}

		// print str("更新bizAccountId")+"从"+str(bizAcctId)+"到"+str(newBizAcctId)
		// params = (newBizAcctId,bizAcctId)
		// cursor2 = conn.cursor()

		String klhSql = "update business_klh set account_id=? where account_id=?";
		PreparedStatement ps = connection.prepareStatement(klhSql);
		ps.setBigDecimal(1, new BigDecimal(newBizAcctId));
		ps.setBigDecimal(2, new BigDecimal(bizAcctId));
		ps.execute();

		String yunSql = "update bussiness_yun set account_id=? where account_id=?";
		ps = connection.prepareStatement(yunSql);
		ps.setBigDecimal(1, new BigDecimal(newBizAcctId));
		ps.setBigDecimal(2, new BigDecimal(bizAcctId));
		ps.execute();
	}

	public void updateMobileAccountId(String mobile, BigInteger bizAcctId) throws Exception {
		if (mobile != null) {
			String sql2 = "select account_id from personal_account where mobile=?";
			PreparedStatement ps = connection.prepareStatement(sql2);
			ps.setString(1, mobile);
			ResultSet rSet = ps.executeQuery();
			if (rSet.next()) {
				BigDecimal bigDecimal = rSet.getBigDecimal(1);
				updateBizAcctRelated(bizAcctId, bigDecimal.toBigInteger());
			}

		}
	}

	public void updateEmailAccountId(String email, BigInteger bizAcctId) throws Exception {
		if (email != null) {
			String sql2 = "select account_id from personal_account where email=?";
			PreparedStatement ps = connection.prepareStatement(sql2);
			ps.setString(1, email);
			ResultSet rSet = ps.executeQuery();
			if (rSet.next()) {
				BigDecimal bigDecimal = rSet.getBigDecimal(1);
				updateBizAcctRelated(bizAcctId, bigDecimal.toBigInteger());
			}

		}
	}

	public BizKlh queryBizKlh(BigInteger accountId) throws Exception {

		String sql = "select name,province,city,address,type,phone,license_img,ctime,real_name from business_klh where account_id=?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setBigDecimal(1, new BigDecimal(accountId));
		ResultSet rSet = pStatement.executeQuery();
		if (rSet.next()) {
			BizKlh bizKlh = new BizKlh(rSet.getString(1), rSet.getString(2), rSet.getString(3), rSet.getString(4),
					rSet.getInt(5), rSet.getString(6), rSet.getString(7), rSet.getDate(8), rSet.getString(9),
					accountId);
			return bizKlh;
		}
		return null;
	}

	public void start() throws Exception {
		String sql = "select account_id,name,passport,mobile,email,pwd,source,status,ctime from business_account";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		ResultSet rs = pStatement.executeQuery();
		while (rs.next()) {
			String mobile = rs.getString(4);
			String email = rs.getString(5);
			BigInteger accountId = rs.getBigDecimal(1).toBigInteger();
			String name = rs.getString(2);
			boolean exist1 = mobileIsExist(mobile);
			boolean exist2 = emailIsExist(email);
			boolean exist3 = accountIdIsExist(accountId);
			boolean exist4 = bizNameIsExist(name);
			System.out.println("accountId:" + accountId);
			System.out.println("检查手机号:" + mobile);
			System.out.println("检查邮箱:" + email);
			System.out.println("企业名称：" + name);

			if ((!exist1) && (!exist2) && (!exist3)) {
				
				int status = 0;
				try{
					status = rs.getInt(7);	
				}catch(Exception e){
				}
				
				PersonalAccount personalAccount = new PersonalAccount(accountId, rs.getString(3), mobile, email,
						rs.getString(6), rs.getString(7),status , 2, rs.getDate(9));
				insertPersonalAcct(personalAccount);
			}

			if (exist1) {
				updateMobileAccountId(mobile, accountId);
			}
			if (exist2) {
				updateEmailAccountId(email, accountId);
			}

			if (!exist4) {
				// print name
				// #name,province,city,address,type,phone,license_img,ctime,real_name
				// #acc_bizinfo_id,name,logo,license_img,type,corporate_name,address,phone,ctime,description
				BizKlh bizKlh = queryBizKlh(accountId);
				// print str(krow)
				if (bizKlh != null) {
					BizInfo bizInfo = new BizInfo(buildId(), name, bizKlh.getName(), bizKlh.getLicense_img(),
							bizKlh.getType(), bizKlh.getReal_name(), bizKlh.getAddress(), bizKlh.getPhone(),
							bizKlh.getCtime(), null);
					insertBizInfo(bizInfo);
				}else{
					System.out.println(accountId+"----klh 不存在");
				}
			}

		}
	
		
		sql = "select acc_bizinfo_id,name from business_info";
		pStatement = connection.prepareStatement(sql);
		rs = pStatement.executeQuery();
		while(rs.next()){
			BigInteger bizInfoId = rs.getBigDecimal(1).toBigInteger();
			String name = rs.getString(2);
			updateBizInfoRelated(bizInfoId, name);
		}

	}

	public static void main(String[] args) {
		UpgradeDb upgradeDb = new UpgradeDb();

		try {
			upgradeDb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			connection.close();
		}
	}

}
