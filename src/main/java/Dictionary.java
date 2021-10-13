import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionary {

    private Collection<String> dictionary;

    Dictionary(){
        dictionary = new ArrayList<>();
    }

     /**
     * Get dictionary content
     * @return String list with the dictionary content
     */
    public Collection<String> getDictionary() {
        return dictionary;
    }


    /**
     * Set dictionary content with txt file
     */
    public void setDictionaryWithFileWords() throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/test/resources/EnglishWords"));

        while (s.hasNext()){
            dictionary.add(s.next());
        }
        s.close();
    }

    /**
     * Add word to dictionary
     */
    public void addWord(String word) {
        dictionary.add(word);
    }

    /**
     * Add list of String word to dictionary
     * @param words is a List<String> with the words to add to dictionary
     */
    public void addWords(String[] words) {
        for (String w:
                words) {
            addWord(w);
        }
    }

    /**
     * Find all possible words in a given string
     * @param input any given string
     * @return String list with all the possible word combinations based on the given input
     */
    public Collection<String> isEnglishWord(String input) {
        ArrayList<String> matches = new ArrayList<>();

        input = input.toLowerCase();

        //for each word in dictionary
        for (String word : dictionary) {

            boolean match = true;

            //for each character in the word of dictionary
            for (char ch : word.toCharArray()) {
                String w = Character.toString(ch);

                // if the length current word of dictionary minus the number of times the current character appears
                // is different that length of the given word minus the number of times the current character appears
                // then the words can't be matched

                if (word.length() - word.replace(w, "").length() !=
                        input.length() - input.replace(w, "").length()) {
                    match = false;
                    break;
                }
            }
            if (match) {
                matches.add(word);
            }
        }

        return matches;
    }


    /**
     * Return new list without duplicates
     * @param lst list of given words with possible duplicates
     * @return collection without duplicates words
     */

    public Collection<String> removeDuplicates(Collection<String> lst) {
        return new HashSet<>(lst);
    }
}