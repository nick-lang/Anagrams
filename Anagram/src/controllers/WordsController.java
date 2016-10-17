package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.WordsDAO;

@RestController
public class WordsController {

	@Autowired
	private WordsDAO wordsDAO;
	
	@RequestMapping(path = "anagram/{word}", method = RequestMethod.GET)
	public String[] showAnagrams(@PathVariable String word) {
		return wordsDAO.show(word);
	}
	@RequestMapping(path = "anagram/{word}/count", method = RequestMethod.GET)
	public int showCount(@PathVariable String word) {
		wordsDAO.updateSearchCount(word);
		return wordsDAO.showSearchCount(word);
	}
	@RequestMapping(path = "anagram/total", method = RequestMethod.GET)
	public int showTotal() {
		return wordsDAO.showTotalSearches();
	}
	@RequestMapping(path = "anagram/", method = RequestMethod.POST)
	public void create(@RequestBody String newWord) {
		newWord = newWord.substring(0, newWord.length() - 1);
		wordsDAO.create(newWord);
	}
	@RequestMapping(path = "anagram/{word}", method = RequestMethod.DELETE)
	public void destroy(@PathVariable String word) {
		
		wordsDAO.destroy(word);
	}
}
