package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.services.IKnowledgeService;
import eu.trentorise.opendata.semantics.services.SearchResult;
import static eu.trentorise.opendata.semantics.services.mock.MockEkb.*;
import eu.trentorise.opendata.traceprov.types.Concept;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Leoni
 */
public class MockKnowledgeService implements IKnowledgeService {

    public static final String CONCEPT_PREFIX = "oetest:cc/";

    public static final String ROOT_CONCEPT = CONCEPT_PREFIX + "test-root-concept";
    public static final String ROOT_CONCEPT_IT_NAME = "concetto radice di test";
    public static final String ROOT_CONCEPT_EN_NAME = "test root concept";
    public static final String ROOT_CONCEPT_IT_DESCR = "Descrizione del concetto radice di test";
    public static final String ROOT_CONCEPT_EN_DESCR = "Description of test root concept";   
    
    public static final String SKILIFT_CONCEPT = MockKnowledgeService.CONCEPT_PREFIX + "skilift";
    public static final String SKILIFT_CONCEPT_IT_NAME = "Impianto di risalita";
    public static final String SKILIFT_CONCEPT_EN_NAME = "Chairlift";
    public static final String SKILIFT_CONCEPT_IT_DESCR = "Tipo di impianto di risalita, adibito al trasporto di persone in zone di difficile accesso e con grandi dislivelli altimetrici.";
    public static final String SKILIFT_CONCEPT_EN_DESCR = "Type of aerial lift, which consists of a continuously circulating steel cable loop strung between two end terminals";

    public static final String FACILITY_CONCEPT = MockKnowledgeService.CONCEPT_PREFIX + "facility";
    public static final String FACILITY_CONCEPT_IT_NAME = "Infrastruttura";
    public static final String FACILITY_CONCEPT_EN_NAME = "Facility";
    public static final String FACILITY_CONCEPT_IT_DESCR = "Uno o la serie di elementi strutturati che intermedia i rapporti tra i vari componenti di una struttura ";
    public static final String FACILITY_CONCEPT_EN_DESCR = "Infrastruct is the basic physical and organizational struct needed for the operation of a society or enterprise";

    
    private Map<String, Concept> registeredConcepts;
    private static final Logger LOG = Logger.getLogger(MockKnowledgeService.class.getName());

    

    public MockKnowledgeService() {
        registeredConcepts = new HashMap();

        { // skilift 
            Concept.Builder cb =  Concept.builder();

            Dict nameDict = Dict.of(Locale.ENGLISH, SKILIFT_CONCEPT_EN_NAME)
                    .with(Locale.ITALIAN, SKILIFT_CONCEPT_IT_NAME);
            cb.setName(nameDict);

            Dict descrDict = Dict.of(Locale.ENGLISH, SKILIFT_CONCEPT_EN_DESCR)
                    .with(Locale.ITALIAN, SKILIFT_CONCEPT_IT_DESCR);
            cb.setDescription(descrDict);
            cb.setId(SKILIFT_CONCEPT);
            registeredConcepts.put(SKILIFT_CONCEPT, cb.build());
        }

        { // facility
            Concept.Builder cb = Concept.builder();

            Dict nameDict = Dict.of(Locale.ENGLISH, FACILITY_CONCEPT_EN_NAME)
                    .with(Locale.ITALIAN, FACILITY_CONCEPT_IT_NAME);
            cb.setName(nameDict);

            Dict descrDict = Dict.of(Locale.ENGLISH, FACILITY_CONCEPT_EN_DESCR)
                    .with(Locale.ITALIAN, FACILITY_CONCEPT_IT_DESCR);
            cb.setDescription(descrDict);
            cb.setId(FACILITY_CONCEPT);
            registeredConcepts.put(FACILITY_CONCEPT, cb.build());
        }

        { // root test concept
            Concept.Builder cb = Concept.builder();

            Dict nameDict = Dict.of(Locale.ENGLISH, ROOT_CONCEPT_EN_NAME)
                    .with(Locale.ITALIAN, ROOT_CONCEPT_IT_NAME);
            cb.setName(nameDict);

            Dict descrDict = Dict.of(Locale.ENGLISH, ROOT_CONCEPT_EN_DESCR)
                    .with(Locale.ITALIAN, ROOT_CONCEPT_IT_DESCR);
            cb.setDescription(descrDict);
            cb.setId(ROOT_CONCEPT);
            registeredConcepts.put(ROOT_CONCEPT, cb.build());
        }

    }

    @Override
    public List<Concept> readConcepts(List<String> URLs) {

        List<Concept> concepts = new ArrayList();

        for (String URL : URLs) {
            Concept concept = registeredConcepts.get(URL);
            if (concept == null) {
                LOG.log(Level.WARNING, "Couldn''t find registered concept with URL {0}", URL);
                concepts.add(null);
            } else {
                concepts.add(readConcept(URL));
            }
        }

        return concepts;
    }

    @Override
    public Concept readConcept(String URL) {
        Concept concept = registeredConcepts.get(URL);
        if (concept == null) {
            LOG.log(Level.WARNING, "Couldn''t find registered concept with URL {0}", URL);
        }
        return concept;
    }

    @Override
    public Concept readRootConcept() {
        return registeredConcepts.get(ROOT_CONCEPT);
    }

    @Override
    public List<SearchResult> searchConcepts(String partialName, Locale locale) {
	
	if (locale == null){
	    throw new NullPointerException("Invalid locale. If unknown, use Locale.ROOT instead");
	}
	
        List<SearchResult> ret = new ArrayList();

        for (Concept concept : registeredConcepts.values()) {
            if (concept.getName().contains(partialName)) {
                SearchResult es = SearchResult.of(concept.getId(), concept.getName());
                ret.add(es);
            }
        }

        return ret;
    }

    /**
     * Returns number calculated on hash
     */
    @Override
    public double getConceptsDistance(String sourceUrl, String targetUrl) {
        if (targetUrl.equals(sourceUrl)) {
            return 1.0;
        } else {
            double tot = sourceUrl.hashCode() + targetUrl.hashCode();
            return sourceUrl.hashCode() / tot;
        }

    }

}
