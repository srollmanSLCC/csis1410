package midterm2;

import java.util.Random;

public class RandomSentences
{
    // Requirements:
    // Build sentences using random words form arrays. - Check
    // Sentence should be in the format: article, noun, verb, preposition, article and noun - Check
    // As each word is picked, concatenate it to the previous words in the sentence. - Check
    // When the final sentence is output, it should start with a capital letter and end with a period. - Check (makeStatement())


    private static final String[] articles = new String[]{"the", "a", "one", "some", "any"};
    private static final String[] nouns = new String[]{"boy", "girl", "dog", "town", "car"};
    private static final String[] prepositions = new String[]{"to", "from", "over", "under", "on"};
    private static final String[] verbs = new String[]{"drove", "jumped", "ran", "walked", "skipped"};

    public static void main(String[] args)
    {
        for (int i = 0; i < 20; i++)
        {
            StringBuilder sb = new StringBuilder();


            // I think the commented out code below is cleaner,
            // but it doesn't satisfy the "Concatenate it to the previous..." requirement.
            // String word = getRandomWord(articles);
            // System.out.println(String.format(
            //     "%s %s %s %s %s %s.",
            //     // Add article (capitalized)
            //     (word.substring(0, 1).toUpperCase() + word.substring(1))
            //     // Add noun
            //     getRandomWord(nouns),
            //     // Add verb
            //     getRandomWord(verbs),
            //     // Add preposition
            //     getRandomWord(prepositions),
            //     // Add article
            //     getRandomWord(articles),
            //     // Add noun followed by a "."
            //     getRandomWord(nouns)
            // ));

            // Here is my actual implementation to satisfy the spec.
            // Add article
            sb.append(String.format("%s ", getRandomWord(articles)));

            // Add noun
            sb.append(String.format("%s ", getRandomWord(nouns)));

            // Add verb
            sb.append(String.format("%s ", getRandomWord(verbs)));

            // Add preposition
            sb.append(String.format("%s ", getRandomWord(prepositions)));

            // Add article
            sb.append(String.format("%s ", getRandomWord(articles)));

            // Add noun and follow with "."
            sb.append(String.format("%s", getRandomWord(nouns)));

            // Print out our output.
            System.out.println(makeStatement(sb));
        }
    }

    /**
     * Takes a String[] and returns a random String from inside of it.
     *
     * @param words The String[] containing words.
     * @return A String representing a random word from the provided String[]
     */
    private static String getRandomWord(String[] words)
    {
        // Normally I would write a test to make sure that words isn't empty,
        // but I can guarantee that my array has words in this instance.
        Random r = new Random();
        return words[r.nextInt(words.length)];
    }

    /**
     * Capitalizes the first word of a chain of words separated by spaces
     * and appends a period at the end to make it a statement.
     *
     * @param wordChain
     * @return A String that is a statement that starts with a capital letter and ends with a period.
     */

    private static String makeStatement(StringBuilder wordChain)
    {
        return (wordChain.substring(0, 1).toUpperCase() + wordChain.substring(1)) + ".";
    }
}
