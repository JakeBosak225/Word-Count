package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String line;
        int totalWordCount = 0;
        int totalSentenceCount = 0;
        int sentenceSequence = 1;
        Word topWord;

        List<Word> wordsByUse;
        List<Word> topTenWords;
        List<String> wordList = new ArrayList<>();
        List<Sentence> sentenceList = new ArrayList<>();

        //Get file
        File file = new File("passage.txt");
        FileInputStream fileStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader reader = new BufferedReader(input);

        //Loop though text
        while ((line = reader.readLine()) != null) {
            if (!(line.equals(""))) {

                //Get Words and Total Word Count
                String[] words = line.split("\\s+");
                totalWordCount += words.length;

                //Get Sentences and Total Sentence Count
                String[] sentences = line.split("[!?.]+");
                totalSentenceCount += sentences.length;

                //Put Words into list
                for (String word : words) {
                    wordList.add(word);
                }

                //Put Sentences into list
                for (String sentence : sentences) {
                    sentence = Sentence.removeSpaceAtStart(sentence);
                    sentenceList.add(new Sentence(sentence, sentenceSequence));
                    sentenceSequence++;
                }
            }
        }

        //count word usage and organize by amount used(high to low)
        wordsByUse = Word.organizeWords(wordList);

        //Get top 10 most used Words
        topTenWords = Word.getTopTenWords(wordsByUse);

        //Get Top Word
        topWord = topTenWords.get(0);

        //Display Word and Sentence Count
        System.out.println("Total Word Count: " + totalWordCount);
        System.out.println("Total Sentence Count: " + totalSentenceCount);
        System.out.printf("%n");

        //Display Top 10 Words
        System.out.println("Top 10 Most Used Words: ");
        for (Word word : topTenWords) {
            System.out.println(word.word + " : " + word.timesUsed
                    + " times");
        }
        System.out.printf("%n");

        //Last Sentence with most used word
        System.out.println("The last sentence with the top word: '" +
                topWord.word + "' is...");
        System.out.println(Sentence.getLastTopWord(topWord, sentenceList));
    }
}