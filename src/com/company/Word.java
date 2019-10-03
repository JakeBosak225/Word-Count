package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Word {

    public String word;
    public int timesUsed;

    public Word(String word, int timesUsed) {
        this.word = word;
        this.timesUsed = timesUsed;
    }

    public int getWordTimesUsed() {
        return timesUsed;
    }

    public static List<Word> organizeWords(List<String> wordsList) {
        List<Word> wordsByUse = new ArrayList<>();

        //Add Words to list
        for (String word : wordsList) {
            boolean hasWord = false;
            //Remove Special Characters from word
            word = word.replaceAll("[+.^:;,!?\"]","");

            for (Word w : wordsByUse) {
                //If word is in list add 1 to count
                if (w.word.equalsIgnoreCase(word)) {
                    w.timesUsed++;
                    hasWord = true;
                }
            }
            //List doesn't have word, add it
            if (!hasWord) {
                wordsByUse.add(new Word(word, 1));
            }
        }

        //Orders words based on times used(high to low)
        List<Word> sortedWords = wordsByUse.stream()
                .sorted(Comparator.comparing(Word::getWordTimesUsed).reversed())
                .collect(Collectors.toList());

        return sortedWords;
    }

    public static List<Word> getTopTenWords(List<Word> wordList){
        return wordList.subList(0,10);
    }
}
