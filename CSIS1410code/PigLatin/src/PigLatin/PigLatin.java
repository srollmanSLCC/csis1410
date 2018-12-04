package PigLatin;

/********************************************************
 *
 *  Project :  Pig Latin Sentences
 *  File    :  PigLatin.java
 *  Name    :  Steven Rollman
 *
 *  Description : A class that contains a method that will convert a provided sentence into Pig Latin.
 *
 ********************************************************/

/**
 * PigLatin class
 */
public class PigLatin
{
    /**
     * Takes a word and translates it to pig latin.
     * @param word the word to translate
     * @return the word in pig latin.
     */
    private static String convertToLatin(String word)
    {
        return word.substring(1) + word.substring(0,1) + "ay";
    }

    /**
     * Takes a sentence and translates it to pig latin.
     * @param sentence The sentence to translate.
     * @return The sentence translated to pig latin.
     */
    public String translateSentence(String sentence)
    {
        // store the punctuation in a variable.
        String punc = sentence.substring(sentence.length() - 1);
        // Use split to tokenize the sentence as per spec.
        String[] words = sentence.split(" ");
        int lastIdx = words.length - 1;
        // Replace the punctuation in the last word with nothing, we'll add it later at the end.
        words[lastIdx] = words[lastIdx].replace(punc, "");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++)
        {
            // for the first word, we want to lowercase it and capitalize only the first letter.
            if (i == 0)
            {
                sb.append(convertToLatin(words[i].toLowerCase()) + " ");
            }
            else
            {
                sb.append(convertToLatin(words[i]) + " ");
            }
        }
        // Trim off the last space and add the punctuation.
        String sent = sb.toString().substring(0, sb.toString().length() - 1) + punc;
        sent = sent.substring(0, 1).toUpperCase() + sent.substring(1);
        return sent;
    }
}
