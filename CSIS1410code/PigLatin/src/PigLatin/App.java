package PigLatin;

import java.util.Scanner;

/********************************************************
 *
 *  Project :  Pig Latin Sentences
 *  File    :  App.java
 *  Name    :  Steven Rollman
 *
 *  Description : Runs an application that will take a sentence that
 *                is read from the user and translates it to pig latin.
 *
 ********************************************************/

public class App
{
    /**
     * Main method for the Pig Latin translator.
     * @param args
     */
    public static void main(String[] args)
    {
        PigLatin p = new PigLatin();
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter a sentence, including punctuation.");

        // Read the sentence.
        String sentence = s.nextLine();

        System.out.println("Translating...");

        // Translate it and print it out.
        System.out.println(p.translateSentence(sentence));
    }


}
