package eu.trentorise.opendata.semantics.services.mock;


import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semtext.MeaningKind;
import eu.trentorise.opendata.semtext.MeaningStatus;
import eu.trentorise.opendata.semtext.Meaning;
import eu.trentorise.opendata.semtext.Sentence;
import eu.trentorise.opendata.semtext.Term;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.INLPService;
import eu.trentorise.opendata.semantics.services.TermSearchResult;
import eu.trentorise.opendata.semtext.SemText;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author David Leoni
 */
public class MockNlpService implements INLPService {

    /**
     * number of words tagged by the runNLP method
     */
    public static final int RUN_NLP_TAGGED_WORDS = 4;
    public static final String CONCETTO_1 = "concetto-1";
    public static final String CONCETTO_2 = "concetto-2";
    public static final String ENTITA_1 = "entità-1";
    public static final String ENTITA_2 = "entità-2";
    public static final String MOCK_TEXT = CONCETTO_1 + ", " + CONCETTO_2 + ", " + CONCETTO_2 + ", " + ENTITA_1 + ", " + ENTITA_2 + ",  non-taggata";

    private IEkb ekb;
    
    public MockNlpService(IEkb ekb){
        this.ekb = ekb;
    }
    
    private MockNlpService(){
        
    }
    
    /**
     * Marks first word occurrence as SELECTED
     */
    private Term markWord(String wordStr, Meaning meaning, String containerText) {
        List<Meaning> meanings = new ArrayList();
        meanings.add(meaning);
        return Term.of(containerText.indexOf(wordStr), containerText.indexOf(wordStr) + wordStr.length(), MeaningStatus.SELECTED, meaning, meanings);
    }

    private SemText markFirstWord(String text, Meaning meaning) {
        String firstWordText;

        if (text.contains(" ")) {
            firstWordText = text.substring(0, text.indexOf(" "));
        } else {
            firstWordText = text;
        }

        ArrayList<Meaning> meanings = new ArrayList();

        Meaning sm1 = meaning;
        meanings.add(sm1);

        Term firstTerm = Term.of(0, firstWordText.length(), MeaningStatus.SELECTED, sm1, meanings);

        return SemText.of(Locale.ITALIAN, text, firstTerm);
    }

    /**
     * WARNING: returned text is different from original to
     * have a wide range copyOf tagging possibilities!
     * 
     * If domainURL is null, marks first two words as CONCEPT, third and fourth
     * as ENTITY, remaining text is not tagged. Tagged words have SELECTED
     * meaning status. CONCETTO_2 appears twice in the text, but only first
     * occurence is tagged. 
     * 
     * if domainURL is not null, marks either two concepts or two entities accordingly
     */
    @Override
    public List<SemText> runNLP(Iterable<String> texts, String domainURL) {

        List<SemText> ret = new ArrayList();

        if (domainURL == null) {

            for (String text : texts) {

                List<Sentence> sentences = new ArrayList();
                List<Term> words = new ArrayList();
                words.add(markWord(CONCETTO_1,
                        Meaning.of(MockEkb.SKILIFT_CONCEPT, MeaningKind.CONCEPT, 1.0 / 6.0),
                        MOCK_TEXT));
                words.add(markWord(CONCETTO_2,
                        Meaning.of(MockEkb.FACILITY_CONCEPT, MeaningKind.CONCEPT, 1.0 / 6.0),
                        MOCK_TEXT));
                words.add(markWord(ENTITA_1,
                        Meaning.of(MockEkb.TEST_ENTITY_1, MeaningKind.ENTITY, 1.0 / 6.0),
                        MOCK_TEXT));
                words.add(markWord(ENTITA_2,
                        Meaning.of(MockEkb.TEST_ENTITY_2, MeaningKind.ENTITY, 1.0 / 6.0),
                        MOCK_TEXT));

                Sentence sentence = Sentence.of(0, MOCK_TEXT.length(), words);
                sentences.add(sentence);
                ret.add(SemText.ofSentences(Locale.ITALIAN, MOCK_TEXT, sentences));
            }
            return ret;

        }

        if (domainURL.contains(MockEkb.CONCEPT_PREFIX)) {
            for (String text : texts) {

                List<Term> terms = new ArrayList();
                terms.add(markWord(CONCETTO_1,
                        Meaning.of(MockEkb.SKILIFT_CONCEPT, MeaningKind.CONCEPT, 1.0 / 6.0),
                        MOCK_TEXT));
                terms.add(markWord(CONCETTO_2,
                        Meaning.of(MockEkb.FACILITY_CONCEPT, MeaningKind.CONCEPT, 1.0 / 6.0),
                        MOCK_TEXT));

                ret.add(SemText.of(Locale.ITALIAN, MOCK_TEXT, Sentence.of(0, MOCK_TEXT.length(), terms)));
            }
            return ret;

        }

        if (domainURL.contains(MockEkb.ETYPE_PREFIX)) {
            for (String text : texts) {
                
                List<Term> terms = new ArrayList();
                terms.add(markWord(ENTITA_1,
                        Meaning.of(MockEkb.TEST_ENTITY_1, MeaningKind.ENTITY, 1.0 / 6.0),
                        MOCK_TEXT));
                terms.add(markWord(ENTITA_2,
                        Meaning.of(MockEkb.TEST_ENTITY_2,  MeaningKind.ENTITY, 1.0 / 6.0),
                        MOCK_TEXT));

                
                ret.add(SemText.of(Locale.ITALIAN, MOCK_TEXT, Sentence.of(0, MOCK_TEXT.length(), terms)));
            }
            return ret;
        }

        throw new IllegalArgumentException("Unsupported domain: " + domainURL);

    }


    @Override
    public List<TermSearchResult> freeSearch(String partialName, Locale locale) {
        ArrayList<TermSearchResult> ret = new ArrayList();
        
        IEntity entity = ekb.getEntityService().readEntity(MockEkb.TEST_ENTITY_1);
        IConcept concept = ekb.getKnowledgeService().readConcept(MockEkb.SKILIFT_CONCEPT);
        
        ret.add(TermSearchResult.of(MockEkb.TEST_ENTITY_1, entity.getName(), MeaningKind.ENTITY));
        ret.add(TermSearchResult.of(MockEkb.SKILIFT_CONCEPT, concept.getName(), MeaningKind.CONCEPT));
        return ret;

    }

    /**
     * Always return {@link Locale#ROOT}
     */
    @Override
    public Locale detectLanguage(Iterable<String> strings) {
        return Locale.ROOT;
    }

}
