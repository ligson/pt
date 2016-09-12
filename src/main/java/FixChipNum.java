import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FixChipNum {
	static String user = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost/home";
	static Connection connection;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class MCard {
		long pkMember;
		String chipNumber;
		long pkCard;

		@Override
		public String toString() {
			return "MCard [pkMember=" + pkMember + ", chipNumber=" + chipNumber + ", pkCard=" + pkCard + "]";
		}

	}

	public List<MCard> queryMCard() throws Exception {
		PreparedStatement ps = connection.prepareStatement(
				"SELECT m.pkMember,bc.chipNumber,bc.pkCardManagement,bc.chipHexNumber FROM mem_member m,bd_cardmanagement bc where m.memberNumber=bc.cardNumber AND bc.chipHexNumber is NULL");
		ResultSet rSet = ps.executeQuery();
		List<MCard> mCards = new ArrayList<>();
		while (rSet.next()) {
			MCard mCard = new MCard();
			mCard.pkCard = rSet.getLong(3);
			mCard.pkMember = rSet.getLong(1);
			mCard.chipNumber = rSet.getString(2);
			mCards.add(mCard);
			System.out.println(mCard);
		}
		ps.close();
		return mCards;
	}

	public void fixCardPk(List<MCard> cards) throws Exception {
		for (MCard mCard : cards) {
			PreparedStatement ps = connection.prepareStatement("update mem_member set pkCard=? where pkMember=?");
			ps.setLong(1, mCard.pkCard);
			ps.setLong(2, mCard.pkMember);
			ps.executeUpdate();
			System.out.println("fix member  card pk");
		}
	}

	public void fixCardHexNum(List<MCard> cards) throws Exception {
		for (MCard mCard : cards) {
			PreparedStatement ps = connection
					.prepareStatement("update bd_cardmanagement set chipHexNumber=? where pkCardManagement=?");
			if (mCard.chipNumber.length() == 10) {
				ps.setString(1, new BigInteger(mCard.chipNumber).toString(16).toUpperCase());
			} else {
				ps.setString(1, mCard.chipNumber.toUpperCase());
			}
			ps.setLong(2, mCard.pkCard);
			ps.executeUpdate();
			System.out.println("fix  card hex num ");
		}
	}

	public static void main(String[] args) throws Exception {
		FixChipNum fixChipNum = new FixChipNum();
		List<MCard> cards = fixChipNum.queryMCard();
		System.out.println("need fix records " + cards.size());
		System.out.println("start fix card pk");
		fixChipNum.fixCardPk(cards);
		System.out.println("end fix card pk");
		System.out.println("start fix card hex num");
		fixChipNum.fixCardHexNum(cards);
		System.out.println("end fix card hex num");
	}
}
