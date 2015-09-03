package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.services.IKnowledgeService;
import eu.trentorise.opendata.semantics.services.SearchResult;
import static eu.trentorise.opendata.semantics.services.mock.MockEkb.*;
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

    private Map<String, IConcept> registeredConcepts;
    private static final Logger LOG = Logger.getLogger(MockKnowledgeService.class.getName());

    

    public MockKnowledgeService() {
        registeredConcepts = new HashMap();

        { // skilift 
            MockConcept mc = new MockConcept();

            Dict nameDict = Dict.of(Locale.ENGLISH, SKILIFT_CONCEPT_EN_NAME)
                    .with(Locale.ITALIAN, SKILIFT_CONCEPT_IT_NAME);
            mc.setName(nameDict);

            Dict descrDict = Dict.of(Locale.ENGLISH, SKILIFT_CONCEPT_EN_DESCR)
                    .with(Locale.ITALIAN, SKILIFT_CONCEPT_IT_DESCR);
            mc.setDescription(descrDict);
            mc.setURL(MockEkb.SKILIFT_CONCEPT);
            registeredConcepts.put(MockEkb.SKILIFT_CONCEPT, mc);
        }

        { // facility
            MockConcept mc = new MockConcept();

            Dict nameDict = Dict.of(Locale.ENGLISH, FACILITY_CONCEPT_EN_NAME)
                    .with(Locale.ITALIAN, FACILITY_CONCEPT_IT_NAME);
            mc.setName(nameDict);

            Dict descrDict = Dict.of(Locale.ENGLISH, FACILITY_CONCEPT_EN_DESCR)
                    .with(Locale.ITALIAN, FACILITY_CONCEPT_IT_DESCR);
            mc.setDescription(descrDict);
            mc.setURL(MockEkb.FACILITY_CONCEPT);
            registeredConcepts.put(MockEkb.FACILITY_CONCEPT, mc);
        }

        { // root test concept
            MockConcept mc = new MockConcept();

            Dict nameDict = Dict.of(Locale.ENGLISH, ROAT_CONCEPT_EN_NAME)
                    .with(Locale.ITALIAN, ROAT_CONCEPT_IT_NAME);
            mc.setName(nameDict);

            Dict descrDict = Dict.of(Locale.ENGLISH, ROAT_CONCEPT_EN_DESCR)
                    .with(Locale.ITALIAN, ROAT_CONCEPT_IT_DESCR);
            mc.setDescription(descrDict);
            mc.setURL(MockEkb.ROAT_CONCEPT);
            registeredConcepts.put(MockEkb.ROAT_CONCEPT, mc);
        }

    }

    @Override
    public List<IConcept> readConcepts(List<String> URLs) {

        List<IConcept> concepts = new ArrayList();

        for (String URL : URLs) {
            IConcept concept = registeredConcepts.get(URL);
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
    public IConcept readConcept(String URL) {
        IConcept concept = registeredConcepts.get(URL);
        if (concept == null) {
            LOG.log(Level.WARNING, "Couldn''t find registered concept with URL {0}", URL);
        }
        return concept;
    }

    @Override
    public IConcept readRootConcept() {
        return registeredConcepts.get(MockEkb.ROAT_CONCEPT);
    }

    @Override
    public List<SearchResult> searchConcepts(String partialName, Locale locale) {
        List<SearchResult> ret = new ArrayList();

        for (IConcept concept : registeredConcepts.values()) {
            if (concept.getName().contains(partialName)) {
                SearchResult es = SearchResult.of(concept.getURL(), concept.getName());
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
