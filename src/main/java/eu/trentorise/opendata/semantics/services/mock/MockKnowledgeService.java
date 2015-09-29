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
            cb.setId(MockEkb.SKILIFT_CONCEPT);
            registeredConcepts.put(MockEkb.SKILIFT_CONCEPT, cb.build());
        }

        { // facility
            Concept.Builder cb = Concept.builder();

            Dict nameDict = Dict.of(Locale.ENGLISH, FACILITY_CONCEPT_EN_NAME)
                    .with(Locale.ITALIAN, FACILITY_CONCEPT_IT_NAME);
            cb.setName(nameDict);

            Dict descrDict = Dict.of(Locale.ENGLISH, FACILITY_CONCEPT_EN_DESCR)
                    .with(Locale.ITALIAN, FACILITY_CONCEPT_IT_DESCR);
            cb.setDescription(descrDict);
            cb.setId(MockEkb.FACILITY_CONCEPT);
            registeredConcepts.put(MockEkb.FACILITY_CONCEPT, cb.build());
        }

        { // root test concept
            Concept.Builder cb = Concept.builder();

            Dict nameDict = Dict.of(Locale.ENGLISH, ROOT_CONCEPT_EN_NAME)
                    .with(Locale.ITALIAN, ROOT_CONCEPT_IT_NAME);
            cb.setName(nameDict);

            Dict descrDict = Dict.of(Locale.ENGLISH, ROOT_CONCEPT_EN_DESCR)
                    .with(Locale.ITALIAN, ROOT_CONCEPT_IT_DESCR);
            cb.setDescription(descrDict);
            cb.setId(MockEkb.ROOT_CONCEPT);
            registeredConcepts.put(MockEkb.ROOT_CONCEPT, cb.build());
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
        return registeredConcepts.get(MockEkb.ROOT_CONCEPT);
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
