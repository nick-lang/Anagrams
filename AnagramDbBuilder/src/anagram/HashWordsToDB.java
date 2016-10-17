package anagram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class HashWordsToDB {
	public static void main(String[] args) {
		int primes[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
				101 };
		char letters[] = { 'e', 'i', 'a', 'o', 'r', 'n', 't', 's', 'l', 'c', 'u', 'p', 'm', 'd', 'h', 'y', 'g', 'b',
				'f', 'v', 'k', 'w', 'z', 'x', 'q', 'j' };
		// Defines values of each letter
		HashMap<Character, Integer> letterVals = new HashMap<Character, Integer>();
		for (int i = 0; i < 26; i++) {
			letterVals.put(letters[i], primes[i]);
		}

		BufferedReader br = null;

		try {

			String sCurrentLine;

			try {
				String url = "jdbc:mysql://localhost:3306/anagramdb";
				String user = "admin";
				String pword = "admin";
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Connection conn = DriverManager.getConnection(url, user, pword);

				br = new BufferedReader(new FileReader("words.txt"));
				while ((sCurrentLine = br.readLine()) != null) {
					sCurrentLine = sCurrentLine.toLowerCase().replaceAll("[^a-z]", "");
					double value = 1;
					for (int i = 0; i < sCurrentLine.length(); i++) {
						double newCharVal = letterVals.get(sCurrentLine.charAt(i));

						if (value > Double.MAX_VALUE / newCharVal) {
							System.out.println(sCurrentLine);
							throw new DoubleOverflow();
						}
						value *= letterVals.get(sCurrentLine.charAt(i));
					}

					Statement stmt = conn.createStatement();
					String sqlString = "INSERT into words (word, hash) VALUES ('" + sCurrentLine + "', " + value + ")";
					stmt.executeUpdate(sqlString);

					stmt.close();

				}
				conn.close();
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
