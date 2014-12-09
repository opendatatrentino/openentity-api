package eu.trentorise.opendata.semantics.test;

import eu.trentorise.opendata.semantics.IntegrityChecker;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.IMeaning;
import eu.trentorise.opendata.semantics.model.knowledge.IWord;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningKind;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningStatus;
import eu.trentorise.opendata.semantics.impl.model.knowledge.Dict;
import eu.trentorise.opendata.semantics.impl.model.knowledge.Meaning;
import eu.trentorise.opendata.semantics.impl.model.knowledge.SemanticText;
import eu.trentorise.opendata.semantics.impl.model.knowledge.Sentence;
import eu.trentorise.opendata.semantics.impl.model.knowledge.Word;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author David Leoni
 */
public class SemanticTextTest {

    @Test
    public void testDict_0() {
        IDict dict = new Dict();
        assertTrue(dict.getLocales().isEmpty());
        IntegrityChecker.checkDict(dict);
    }

    @Test
    public void testDict_1() {
        String text = "hello";
        IDict dict = new Dict(text);
        assertEquals(dict.getString(Locale.ENGLISH), text);
        IntegrityChecker.checkDict(dict);
    }

    @Test
    public void testDict_2() {

        Dict dict_1 = new Dict("hello");
        Dict dict_2 = dict_1.putTranslation(Locale.ITALIAN, "ciao");

        assertEquals("hello", dict_1.getString(Locale.ENGLISH));
        assertEquals(null, dict_1.getString(Locale.ITALIAN));

        assertEquals("hello", dict_2.getString(Locale.ENGLISH));
        assertEquals("ciao", dict_2.getString(Locale.ITALIAN));

        IntegrityChecker.checkDict(dict_1);
        IntegrityChecker.checkDict(dict_2);
    }


    @Test
    public void testDictContains() {
        assertFalse(new Dict().contains(""));
        assertTrue(new Dict().putTranslation(Locale.ENGLISH, "Hello").contains("h"));

        IDict dict = new Dict().putTranslation(Locale.ENGLISH, "Hello")
                .putTranslation(Locale.ITALIAN, new ArrayList() {
                    {
                        add("Ciao");
                        add("MONDO");
                    }
                });
        assertTrue(dict.contains("ndo"));
        assertTrue(dict.contains(""));
        assertFalse(dict.contains("123"));

        IntegrityChecker.checkDict(dict);

    }

    @Test
    public void testEquality() {

        assertNotEquals(new Meaning("a", 0.1, MeaningKind.CONCEPT), new Meaning("a", 0.1, MeaningKind.ENTITY));
        assertNotEquals(new Meaning("a", 0.1, MeaningKind.CONCEPT), new Meaning("b", 0.1, MeaningKind.CONCEPT));
        assertEquals(new Meaning("a", 0.1, MeaningKind.CONCEPT), new Meaning("a", 0.9, MeaningKind.CONCEPT));

        assertEquals(new SemanticText(), new SemanticText());
        assertEquals(new SemanticText("a"), new SemanticText("a"));

        assertNotEquals(new SemanticText("a"), new SemanticText("b"));
        assertEquals(new SemanticText("a", Locale.ITALY), new SemanticText("a", Locale.ITALY));

        Sentence s1 = new Sentence(0, 2);
        Sentence s2 = new Sentence(0, 2);

        assertEquals(new SemanticText("ab", Locale.ITALY, s1), new SemanticText("ab", Locale.ITALY, s2));
        assertNotEquals(new SemanticText("ab", Locale.ITALY, s1), new SemanticText("ab", Locale.ITALY));

        assertEquals(new Word(0, 2, MeaningStatus.INVALID, null, new ArrayList()), new Word(0, 2, MeaningStatus.INVALID, null, new ArrayList()));
        assertNotEquals(new Word(0, 2, MeaningStatus.INVALID, null, new ArrayList()), new Word(0, 3, MeaningStatus.INVALID, null, new ArrayList()));

    }

    @Test
    public void testWith() {
        assertEquals(MeaningStatus.NOT_SURE, new Word(0, 1, MeaningStatus.INVALID, null)
                .with(MeaningStatus.NOT_SURE, null)
                .getMeaningStatus());
        assertEquals(MeaningStatus.NOT_SURE, new SemanticText().with(MeaningStatus.NOT_SURE, null).getWord().getMeaningStatus());

        assertEquals("b", new SemanticText("a").with("b").getText());
        assertEquals(Locale.ITALIAN, new SemanticText("a").with(Locale.ITALIAN).getLocale());

    }

    /**
     * One word replaced with another
     *
     * <pre>
     *
     *  N
     *  E
     *  0
     *
     * </pre>
     */
    @Test
    public void testUpdateSemText_1() {
        List<IWord> words = new ArrayList();
        List<IMeaning> meanings = new ArrayList();
        meanings.add(new Meaning("someurl", 0.3, MeaningKind.ENTITY));
        words.add(new Word(0, 1, MeaningStatus.TO_DISAMBIGUATE, null, meanings));
        
        IWord newWord = new Word(0, 1, MeaningStatus.NOT_SURE, null);

        SemanticText semText = new SemanticText("a", Locale.ITALIAN, words);
        SemanticText updated = semText.update(newWord);
        assertEquals(1, updated.getWords().size());
        assertEquals(MeaningStatus.NOT_SURE, updated.getWords().get(0).getMeaningStatus());
        
        // meanings should be merged
        assertEquals(1, updated.getWords().get(0).getMeanings().size());
        assertEquals(MeaningKind.ENTITY, updated.getWords().get(0).getMeanings().get(0).getKind());
    }

    /**
     * <pre>
     *
     * One new word, two existing words, deletes both
     *
     *   N1N1
     * E1E1E2E2
     * 0 1 2 3
     *
     * </pre>
     */
    @Test
    public void testUpdateSemText_2() {
        List<IWord> words = new ArrayList();
        words.add(new Word(0, 2, MeaningStatus.TO_DISAMBIGUATE, null));
        words.add(new Word(2, 4, MeaningStatus.TO_DISAMBIGUATE, null));
        IWord newWord = new Word(1, 3, MeaningStatus.NOT_SURE, null);

        SemanticText semText = new SemanticText("abcd", Locale.ITALIAN, words);
        SemanticText updatedSemText = semText.update(newWord);
        assertEquals(1, updatedSemText.getWords().size());
        assertEquals(MeaningStatus.NOT_SURE, updatedSemText.getWords().get(0).getMeaningStatus());

    }

    /**
     * <pre>
     *
     * One new word, two existing words, deletes first
     *
     *   N1
     *   E1E1E2E2
     *   0 1 2 3
     *
     * </pre>
     */
    @Test
    public void testUpdateSemText_3() {
        List<IWord> words = new ArrayList();
        words.add(new Word(0, 2, MeaningStatus.TO_DISAMBIGUATE, null));
        words.add(new Word(2, 4, MeaningStatus.TO_DISAMBIGUATE, null));
        IWord newWord = new Word(0, 1, MeaningStatus.NOT_SURE, null);

        SemanticText semText = new SemanticText("abcd", Locale.ITALIAN, words);
        SemanticText updatedSemText = semText.update(newWord);
        assertEquals(2, updatedSemText.getWords().size());
        assertEquals(MeaningStatus.NOT_SURE, updatedSemText.getWords().get(0).getMeaningStatus());
        assertEquals(MeaningStatus.TO_DISAMBIGUATE, updatedSemText.getWords().get(1).getMeaningStatus());
    }

    /**
     * <pre>
     *
     * One new word, two existing words, deletes second
     *
     *       N1
     *   E1E1E2E2
     *   0 1 2 3
     *
     * </pre>
     */
    @Test
    public void testUpdateSemText_4() {
        List<IWord> words = new ArrayList();
        words.add(new Word(0, 2, MeaningStatus.TO_DISAMBIGUATE, null));
        words.add(new Word(2, 4, MeaningStatus.TO_DISAMBIGUATE, null));
        IWord newWord = new Word(2, 3, MeaningStatus.NOT_SURE, null);

        SemanticText semText = new SemanticText("abcd", Locale.ITALIAN, words);
        SemanticText updatedSemText = semText.update(newWord);
        assertEquals(2, updatedSemText.getWords().size());
        assertEquals(MeaningStatus.TO_DISAMBIGUATE, updatedSemText.getWords().get(0).getMeaningStatus());
        assertEquals(MeaningStatus.NOT_SURE, updatedSemText.getWords().get(1).getMeaningStatus());
    }
    
    

}
