package com.company;

import java.util.Collections;
import java.util.List;

public class Sentence {
    public String sentence;
    public int sequence;

    public Sentence(String sentence, int sequence) {
        this.sentence = sentence;
        this.sequence = sequence;
    }


    public static String getLastTopWord(Word topWord, List<Sentence> sentences) {
        //Reverse List
        Collections.reverse(sentences);

        for (Sentence sentence : sentences) {
            //Get all words from sentence
            String[] sentenceWords = sentence.sentence.split("\\s+");

            //Check if any of the words in the sentence match Top Word
            for (String word : sentenceWords) {
                if (word.equalsIgnoreCase(topWord.word)) {
                    return "Sentence " + sentence.sequence +
                            ": " + sentence.sentence;
                }
            }
        }
        return null;
    }

    public static String removeSpaceAtStart(String sentence) {
        if (sentence != null && sentence.length() > 0 &&
                Character.isWhitespace(sentence.charAt(0))) {
            sentence = sentence.substring(1);
        }

        return sentence;
    }
}