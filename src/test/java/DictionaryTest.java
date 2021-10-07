import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class DictionaryTest {

    Dictionary dictionary;


    @BeforeEach
    public void initializeDictionary () {
        dictionary = new Dictionary();

    }

    @Test
    public void testGetDictionaryEmpty() {
        Collection<String> dictionaryCollection = dictionary.getDictionary();
        Assert.assertTrue(dictionaryCollection.size() == 0);
    }

    @Test
    public void testFillDictionary() throws FileNotFoundException {

        dictionary.setDictionaryWithFileWords();
        Collection<String> dictionaryCollection = dictionary.getDictionary();
        Assert.assertTrue(dictionaryCollection.size() > 0);
    }

    @Test
    public void testAddWord() {

        String word = "reynold";
        String word2 = "julia";

        dictionary.addWord(word);
        dictionary.addWord(word2);

        System.out.println("-------------- Dictionary content --------------" + dictionary.getDictionary());
        Assert.assertTrue(dictionary.getDictionary().size() > 0);

    }

    @Test
    public void testAddWords() {

        String[] words = {"work","king","row","ring","know"};
        dictionary.addWords(words);

        System.out.println("-------------- Dictionary content --------------" + dictionary.getDictionary());
        Assert.assertTrue(dictionary.getDictionary().size() == 6);

    }

    @Test
    public void testInputWithWordsExpected() {

        String[] words = {"yellow","black","car","wol","pen","speed","computer","call","fall","owl"};
        String[] wordsExpected = {"wol", "owl"};

        dictionary.addWords(words);
        String word = "low";

        //assert is here
        validateMatch(word, wordsExpected);

    }

    @Test
    public void testInputWithoutDuplicatedWordsExpected() {

        String[] duplicatedWordsExpected = {"wow","wol","owl","row","saw","know","mall","saw","mall","low"};
        String[] wordsExpected = {"mall", "saw"};

        dictionary.addWords(duplicatedWordsExpected);
        String word = "smallow";

        //assert is here
        Collection<String> matchedWords = validateMatch(word, wordsExpected);
        Set<String> matchedWordsWithoutDuplicates = new HashSet<>(matchedWords);

        System.out.println("-------------- Words matched without duplicates --------------");
        showWords(matchedWordsWithoutDuplicates);
    }

    @Test
    public void testWithFile() throws FileNotFoundException {

        String[] wordsExpected = {"t", "ictic", "ii", "cc"};
        String word = "citic";
        dictionary.setDictionaryWithFileWords();

        Assert.assertFalse("The file don't have any register", dictionary.getDictionary().size() == 0);

        //assert is here
        validateMatch(word, wordsExpected);

    }

    @Test
    public void testWithoutDuplicatedFromFile() throws FileNotFoundException {

        String[] wordsExpected = {"t", "ictic", "ii", "cc"};
        String word = "citic";
        dictionary.setDictionaryWithFileWords();

        Assert.assertFalse("The file don't have any register", dictionary.getDictionary().size() == 0);

        //assert is here
        Collection<String> matchedWords = validateMatch(word, wordsExpected);
        Set<String> matchedWordsWithoutDuplicates = new HashSet<>(matchedWords);

        System.out.println("-------------- Words matched without duplicates --------------");
        showWords(matchedWordsWithoutDuplicates);

    }


    //aux methods
    private Collection<String> getMatchWords(String input) {
        return dictionary.isEnglishWord(input);
    }

    private void showWords (Collection<String> words) {
        for (String w:
                words) {
            System.out.println(w);
        }
    }

    private Collection<String> validateMatch(String word, String[] wordsExpected) {

        System.out.println("-------------- Dictionary content -------------- " + dictionary.getDictionary());
        System.out.println("-------------- Input word --------------" + word);

        Collection<String> matchedWords = getMatchWords(word);

        System.out.println("-------------- Expected words -------------- ");
        showWords(List.of(wordsExpected));

        System.out.println("-------------- Words matched -------------- ");
        showWords(matchedWords);

        for (String w:
                wordsExpected) {
            if (!matchedWords.contains(w)){

                Assert.assertFalse("Word " + w + " doesn't match with any dictionary word ",
                        !matchedWords.contains(w));

            }
        }

        return matchedWords;
    }


}