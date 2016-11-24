# Anagrams

Created by Nick Lang

This project finds anagrams for any word entered in constant time.

##How it Works
This project calculates a word's value by multiplying the prime numbers associated with each letter in a word. The value of the word is stored as the key of a hashmap. Every time another word's value maches that key, the word is added to a string array in the value portion of the hashmap. Since all anagrams are pre-computed this allows for a lookup time that is O(1).

###Slightly more detail
First I wrote a program to count how many of each letter exists in my macbook's dictionary. I then assigned '2' to the letter that occured most frequently in the dictionary, and '101' to the letter that occured least frequently. Assigning primes by frequency allowed for word values to theoretically remain small.

The words and their values were loaded into a SQL database via another Java program. When the server is first queried it loads the words into a hashmap (I need to find a way to load them without user interaction, or only store the words in one place). When a user enters a new word it is placed into the hashmap and database.

##Folders
AnagramDbBuilder holds the files used to create the dictionary database. The folders Anagram, and AnagramJPA hold the web app 
files.
