package anagram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountLetters {
	public static void main(String[] args) {

		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("words.txt"));
			int alphabetCount[] = new int[26];
			
			//Count how many of each letter occurs in dictionary
			while ((sCurrentLine = br.readLine()) != null) {
				for (int i = 0; i < sCurrentLine.length(); i++) {
					for (int j = 0; j < 26; j++) {
						if (sCurrentLine.charAt(i) == ('a' + j) || sCurrentLine.charAt(i) == ('A' + j)) {
							alphabetCount[j]++;
						}
					}
				}
			}
			
//			for (int i = 0; i < 26; i++) {
//				System.out.print((char)('a' + i) + ": ");
//				System.out.println(alphabetCount[i]);
//			}
			
			int lastMax = Integer.MAX_VALUE;
			for(int j = 0; j < 26; j++){
			int maxIndex = 0;
			int maxValue = 0;
			for(int i = 0; i < 26; i++){
				if(alphabetCount[i] > maxValue && alphabetCount[i] < lastMax){
					maxValue = alphabetCount[i];
					maxIndex = i;
				}
			}
			lastMax = alphabetCount[maxIndex];
			System.out.println((char)('a' + maxIndex));
			}
			
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
