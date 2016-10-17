package handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

import entities.Words;

@Component
public class WordsHashMap {

	private HashMap<Double, String[]> wordMap = new HashMap<>();

	public WordsHashMap() {
		// System.out.println(wordsDAO);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AnagramJPA");

		EntityManager em = emf.createEntityManager();
		System.out.println("**********************************************");
		System.out.println(em);
		System.out.println("**********************************************");
		String query = "Select w from Words w";
		List<Words> wordList = null;
		try {
			wordList = em.createQuery(query, Words.class).getResultList();
		} catch (Exception e) {
			System.out.println("IN CATCH");
			wordList = new ArrayList<Words>();
		}
		// System.out.println("**********************************************");
		// System.out.println(wordList);
		// System.out.println("**********************************************");

		for (Words word : wordList) {

			if (wordMap.containsKey(word.getHash())) {
				String[] oldWords = wordMap.get(word.getHash());
				String[] newWords = new String[oldWords.length + 1];
				for (int i = 0; i < oldWords.length; i++) {
					newWords[i] = oldWords[i];
				}
				newWords[newWords.length - 1] = word.getWord();
				// System.out.println(newWords);
				wordMap.put(word.getHash(), newWords);
			} else {
				String[] newWord = { word.getWord() };
				wordMap.put(word.getHash(), newWord);
			}
		}

		System.out.println("**************************************");
		System.out.println("Loaded");
		System.out.println("**************************************");
	}

	public HashMap<Double, String[]> getWordMap() {
		return wordMap;
	}

	// May need to be updated to only add 1 word at a time
	public void addToWordMap(double hash, String word) {
		if (wordMap.containsKey(hash)) {
			String[] oldWords = wordMap.get(hash);
			String[] newWords = new String[oldWords.length + 1];
			for (int i = 0; i < oldWords.length; i++) {
				newWords[i] = oldWords[i];
			}
			newWords[newWords.length - 1] = word;
			// System.out.println(newWords);
			wordMap.put(hash, newWords);
		} else {
			String[] newWord = { word };
			wordMap.put(hash, newWord);
		}
	}

	public void removeFromWordMap(double hash, String word) {
		if (wordMap.get(hash).length > 1) {
			if (wordMap.containsKey(hash)) {
				String[] oldWords = wordMap.get(hash);
				String[] newWords = new String[oldWords.length - 1];
				int newWordsCount = 0;
				for (int i = 0; i < oldWords.length; i++) {
					if (!oldWords[i].equals(word)) {
						System.out.println("nw: " + newWordsCount + " i: " + i );
						newWords[newWordsCount] = oldWords[i];
						newWordsCount++;
					}
				}
				wordMap.put(hash, newWords);
			}
		}
		else if (wordMap.get(hash).length == 1) {
			wordMap.remove(hash);
		}

	}

}
