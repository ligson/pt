package pt.reflect.util;

import java.util.Date;

public class IamGod {
	class GirlFriend {
		private String name;
		private Date birth;

		public void ml(Object object) {
			System.out.println("make love with " + object);
		}

		public GirlFriend(String name, Date birth) {
			super();
			this.name = name;
			this.birth = birth;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getBirth() {
			return birth;
		}

		public void setBirth(Date birth) {
			this.birth = birth;
		}

	}

	public static void main(String[] args) {
		IamGod iamGod = new IamGod();
		for (int i = 0; i < 100; i++) {
			GirlFriend girlFriend = iamGod.new GirlFriend("test" + i, new Date());
			girlFriend.ml("me");
		}
	}
}
