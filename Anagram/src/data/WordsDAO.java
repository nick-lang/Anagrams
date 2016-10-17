package data;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import entities.Words;
import handlers.WordsHashMap;

@Transactional
public class WordsDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private WordsHashMap wordsHashMap;

	public List<Words> index() {
		String query = "Select w from Words w";
		return em.createQuery(query, Words.class).getResultList();
	}

	public String[] show(String word) {
		Double wordHash = hash(word);
		return wordsHashMap.getWordMap().get(wordHash);
		// return null;
	}

	public void updateSearchCount(String word) {
		String query = "Select w from Words w where word = '" + word + "'";
		Words thisWord = em.createQuery(query, Words.class).getSingleResult();
		thisWord.setCount(thisWord.getCount() + 1);
		System.out.println(thisWord.getCount());
	}

	public int showSearchCount(String word) {
		String query = "Select w from Words w where word = '" + word + "'";
		Words thisWord = em.createQuery(query, Words.class).getSingleResult();
		return thisWord.getCount();
	}

	public void create(String newWord) {
		// TODO Auto-generated method stub
		Words word = new Words();
		word.setWord(newWord);
		word.setHash(hash(newWord));
		em.persist(word);
		em.flush();
		wordsHashMap.addToWordMap(hash(newWord), newWord);
	}

	public void destroy(String word) {
		try {
			String query = "Select w from Words w where word = '" + word + "'";
			Words thisWord = em.createQuery(query, Words.class).getSingleResult();
			em.remove(thisWord);
			em.flush();
			wordsHashMap.removeFromWordMap(hash(word), word);
		} catch (NonUniqueResultException n) {
			System.out.println("in catch");
			String query = "Select w from Words w where word = '" + word + "'";
			List<Words> theseWords = em.createQuery(query, Words.class).getResultList();
			for (Words thisWord : theseWords) {
				em.remove(thisWord);
				wordsHashMap.removeFromWordMap(hash(word), word);
			}
		}

		System.out.println("in destroy" + word);
	}
	public int showTotalSearches() {
		String query = "Select w.count from Words w where w.count > 0";
		List<Integer> counts = em.createQuery(query, Integer.class).getResultList();
		int total = 0;
		for (Integer count : counts) {
			total += count;
		}
		System.out.println(total);
		return total;
	}

	// Calculate word value
	private Double hash(String word) {
		word = word.toLowerCase().replaceAll("[^a-z]", "");
		double value = 1;

		for (int i = 0; i < word.length(); i++) {
			value *= letterVals.get(word.charAt(i));
		}

		return value;
	}

	static int primes[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
			97, 101 };
	static char letters[] = { 'e', 'i', 'a', 'o', 'r', 'n', 't', 's', 'l', 'c', 'u', 'p', 'm', 'd', 'h', 'y', 'g', 'b',
			'f', 'v', 'k', 'w', 'z', 'x', 'q', 'j' };

	// Defines values of each letter
	static HashMap<Character, Integer> letterVals = new HashMap<Character, Integer>();
	static {
		for (int i = 0; i < 26; i++) {
			letterVals.put(letters[i], primes[i]);
		}
	}
}
