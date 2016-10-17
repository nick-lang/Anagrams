# Anagrams

This project finds anagrams for any word entered. To run, anagramdb.sql must be loaded,
and then Anagram and AnagramJPA folders must be run on a server.

##Design Explanation
AnagramDbBuilder holds the files used to create the dictionary database. The folders Anagram, and AnagramJPA hold the web app 
files. The words from the database are loaded into a hashmap of string arrays when the web app runs. This allows words to be searched for their anagrams with constant time complexity.
