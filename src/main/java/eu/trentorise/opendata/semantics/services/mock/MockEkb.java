package eu.trentorise.opendata.semantics.services.mock;

import com.google.common.collect.ImmutableList;

import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.IEntityService;
import eu.trentorise.opendata.semantics.services.IEntityTypeService;
import eu.trentorise.opendata.semantics.services.IIdentityService;
import eu.trentorise.opendata.semantics.services.IKnowledgeService;
import eu.trentorise.opendata.semantics.services.INLPService;
import eu.trentorise.opendata.semantics.services.ISchemaMatchingService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mock implementation of an IEkb, used when runmode is DEBUG It's not in
 * debug.ekb because we need it to stay in same package as Ekb.
 *
 * @author David Leoni
 */
public class MockEkb implements IEkb {

    private static final Logger LOG = Logger.getLogger(MockEkb.class.getName());

    public static final String MOCK_EKB_NAMESPACE = "oe.mockekb";

    public static final String MOCK_EKB_URL = "https://github.com/opendatatrentino/openentity-api/mockekb#";

    public static final String CONCEPT_PREFIX = "oetest:cc/";
    public static final String ATTR_DEF_PREFIX = "oetest:ad/";
    public static final String ETYPE_PREFIX = "oetest:et/";
    
    public static final String LOCAL_ID_PREFIX = "oetest:";
    /**
     * ID prefix for entities created in mockekb
     */
    public static final String ENTITY_PREFIX = LOCAL_ID_PREFIX + "entity/";

    /**
     * ID prefix for values created in mockekb
     */
    public static final String VALUE_PREFIX = LOCAL_ID_PREFIX + "value/";

    /**
     * ID prefix for attributes created in mockekb
     */
    public static final String ATTRIBUTE_PREFIX = LOCAL_ID_PREFIX + "attribute/";

    /**
     * ID prefix for entities created in mockekb
     */
    public static final String STRUCTURE_PREFIX = LOCAL_ID_PREFIX + "structure/";

    /**
     * This is a transitional prefix ONLY for objects created outside ODR having
     * a numerical ID (such as values and attributes), to be removed when we
     * fully switch to a URL-ONLY model.
     */
    public static final String FOREIGN_PREFIX = LOCAL_ID_PREFIX + "foreign/";

    /**
     * This is a transitional prefix ONLY for values ID, to be removed when
     * IValue will have getURL() instead of getlocalID()
     */
    public static final String FOREIGN_VALUE_PREFIX = FOREIGN_PREFIX + "value/";

    /**
     * This is a transitional prefix ONLY for attributes ID, to be removed when
     * IAttribute will have getURL() instead of getlocalID() - or when we get
     * read of IAttribute alltogether
     */
    public static final String FOREIGN_ATTRIBUTE_PREFIX = FOREIGN_PREFIX + "attribute/";

    private static long guidCounter = 0L;

    

    public static final String TEST_ROAT_STRUCTURE = ETYPE_PREFIX + "test-root-structure";

    public static final String ROAT_ETYPE = ETYPE_PREFIX + "test-root-etype";
    public static final String ROAT_NAME_ATTR = ATTR_DEF_PREFIX + "test-root-name";
    public static final String ROAT_DESCR_ATTR = ATTR_DEF_PREFIX + "test-root-description";

    /**
     * Simple entity type with 4 attributes, name, description, test-attr-def-1,
     * test-attr-def-2 and test-attr-def-3
     */
    public static final String TEST_ENTITY_TYPE = ETYPE_PREFIX + "test-etype";
    public static final String TEST_NAME_ATTR = ATTR_DEF_PREFIX + "test-name";
    public static final String TEST_DESCR_ATTR = ATTR_DEF_PREFIX + "test-description";

    public static final String TEST_ATTR_DEF_1 = ATTR_DEF_PREFIX + "test-attr-def-1";
    public static final String TEST_ATTR_DEF_2 = ATTR_DEF_PREFIX + "test-attr-def-2";
    public static final String TEST_ATTR_DEF_3 = ATTR_DEF_PREFIX + "test-attr-def-3";

    static public final String TEST_ATTR_DEF_1_EN_NAME = "test attr 1";
    static public final String TEST_ATTR_DEF_2_EN_NAME = "test attr 2";
    static public final String TEST_ATTR_DEF_3_EN_NAME = "test attr 3";

    static public final String TEST_ATTR_DEF_1_IT_NAME = "Attributo di test 1";
    static public final String TEST_ATTR_DEF_2_IT_NAME = "Attributo di test 2";
    static public final String TEST_ATTR_DEF_3_IT_NAME = "Attributo di test 3";

    public static final String TEST_REC_STRUCTURE = ETYPE_PREFIX + "rec-et";
    public static final String TEST_REC_ATTR_DEF = ETYPE_PREFIX + "rec-attr-def";

    public static final String TEST_REC_NAME_ATTR = ATTR_DEF_PREFIX + "rec-et-name";
    public static final String TEST_REC_DESCR_ATTR = ATTR_DEF_PREFIX + "rec-et-descr";
    public static final String TEST_REC_ENTITY_TYPE = ETYPE_PREFIX + "rec-et";
    public static final String TEST_REC_OUTER_ATTR_DEF = ATTR_DEF_PREFIX + "rec-struct";

    public static final long TEST_SENSE_1_ID = 1L;
    public static final long TEST_SENSE_2_ID = 2L;

    public static final String ROAT_CONCEPT = CONCEPT_PREFIX + "test-root-concept";
    public static final String ROAT_CONCEPT_IT_NAME = "concetto radice di test";
    public static final String ROAT_CONCEPT_EN_NAME = "test root concept";
    public static final String ROAT_CONCEPT_IT_DESCR = "Descrizione del concetto radice di test";
    public static final String ROAT_CONCEPT_EN_DESCR = "Description of test root concept";

    public static final String COMPLEX_NAME = ETYPE_PREFIX + "complex-name";
    public static final String COMPLEX_NAME_NAME_ATTR = ATTR_DEF_PREFIX + "complex-name-name";

    public static final String LOCATION = ETYPE_PREFIX + "location";
    public static final String LOCATION_NAME_ATTR = ATTR_DEF_PREFIX + "location-name";
    public static final String LOCATION_DESCR_ATTR = ATTR_DEF_PREFIX + "location-description";

    public static final String FARMFED_PRODUCER = ETYPE_PREFIX + "farmfed-producer";
    public static final String FARMFED_PROD_NAME_ATTR = ATTR_DEF_PREFIX + "farmfed-prod-name";
    public static final String FARMFED_PROD_DESCR_ATTR = ATTR_DEF_PREFIX + "farmfed-prod-description";

    public static final String CERTIFIED_PRODUCT = ETYPE_PREFIX + "certified-product";
    public static final String CERTIFIED_PRODUCT_NAME_ATTR = ATTR_DEF_PREFIX + "cert-prod-name";
    public static final String CERTIFIED_PRODUCT_DESCR_ATTR = ATTR_DEF_PREFIX + "cert-prod-descr";
    public static final String CERTIFIED_PRODUCT_CERTIFICATE_ATTR = ATTR_DEF_PREFIX + "cert-prod-certificate";
    public static final String CERTIFIED_PRODUCT_REGULATIONS_ATTR = ATTR_DEF_PREFIX + "cert-prod-regulations";
    public static final String CERTIFIED_PRODUCT_PRODUCTION_SITE_ATTR = ATTR_DEF_PREFIX + "cert-prod-production-site";
    public static final String CERTIFIED_PRODUCT_PRODUCER_ATTR = ATTR_DEF_PREFIX + "cert-prod-producer";
    public static final String CERTIFIED_PRODUCT_WEBSITE_ATTR = ATTR_DEF_PREFIX + "cert-prod-website";

    public static final String FACILITY = ETYPE_PREFIX + "facility";
    public static final String FACILITY_NAME_ATTR = ATTR_DEF_PREFIX + "facility-name";
    public static final String FACILITY_DESCR_ATTR = ATTR_DEF_PREFIX + "facility-description";
    public static final String FACILITY_LATITUDE_ATTR = ATTR_DEF_PREFIX + "facility-latitude";
    public static final String FACILITY_LONGITUDE_ATTR = ATTR_DEF_PREFIX + "facility-longitude";
    public static final String FACILITY_OPENING_HOURS_ATTR = ATTR_DEF_PREFIX + "facility-opening-hours";
    public static final String FACILITY_CLASS_ATTR = ATTR_DEF_PREFIX + "class";
    public static final String FACILITY_ADDRESS_ATTR = ATTR_DEF_PREFIX + "address";

    public static final String OPENING_HOURS = ETYPE_PREFIX + "opening-hours";
    public static final String OPENING_TIME_ATTR = ATTR_DEF_PREFIX + "opening-time";
    public static final String CLOSING_TIME_ATTR = ATTR_DEF_PREFIX + "closing-time";

    public static final String ADDRESS = ETYPE_PREFIX + "address";
    public static final String ADDRESS_MUNICIPALITY_ATTR = ATTR_DEF_PREFIX + "address-municipality";

    public static final String SKILIFT_CONCEPT = CONCEPT_PREFIX + "skilift";
    public static final String SKILIFT_CONCEPT_IT_NAME = "Impianto di risalita";
    public static final String SKILIFT_CONCEPT_EN_NAME = "Chairlift";
    public static final String SKILIFT_CONCEPT_IT_DESCR = "Tipo di impianto di risalita, adibito al trasporto di persone in zone di difficile accesso e con grandi dislivelli altimetrici.";
    public static final String SKILIFT_CONCEPT_EN_DESCR = "Type of aerial lift, which consists of a continuously circulating steel cable loop strung between two end terminals";

    public static final String FACILITY_CONCEPT = CONCEPT_PREFIX + "facility";
    public static final String FACILITY_CONCEPT_IT_NAME = "Infrastruttura";
    public static final String FACILITY_CONCEPT_EN_NAME = "Facility";
    public static final String FACILITY_CONCEPT_IT_DESCR = "Uno o la serie di elementi strutturati che intermedia i rapporti tra i vari componenti di una struttura ";
    public static final String FACILITY_CONCEPT_EN_DESCR = "Infrastructure is the basic physical and organizational structure needed for the operation of a society or enterprise";

    private MockNlpService NLPService;
    private MockKnowledgeService knowledgeService;
    private MockSchemaMatchingService schemaMatchingService;
    private IIdentityService identityService;
    private MockEntityTypeService entityTypeService;
    private IEntityService entityService;
    private ImmutableList<Locale> defaultLocales;

    public static String TEST_ENTITY_1 = ENTITY_PREFIX + "test-entity-1";
    public static String TEST_ENTITY_2 = ENTITY_PREFIX + "test-entity-2";

    public MockEkb() {
        this.NLPService = new MockNlpService(this);
        this.entityTypeService = new MockEntityTypeService();
        this.knowledgeService = new MockKnowledgeService();
        LOG.warning("NOT INITIALIZING ENTITY SERVICE, todo we need to import it from odr once cast is implmented in traceprov "); 
        this.identityService = null; // new MockIdentityService();
        this.schemaMatchingService = new MockSchemaMatchingService();
        LOG.warning("NOT INITIALIZING ENTITY SERVICE, todo we need to import it from odr once cast is implmented in traceprov "); 
        this.entityService = null;  //new MockEntityService(this);
        this.defaultLocales = ImmutableList.of(Locale.ENGLISH);
    }

    @Override
    public void setDefaultLocales(Iterable<Locale> locales) {
        LOG.warning("Setting default locales without updating anything else.");
        this.defaultLocales = ImmutableList.copyOf(locales);
    }

    @Override
    public List<Locale> getDefaultLocales() {
        return defaultLocales;
    }

    @Override
    public INLPService getNLPService() {
        return NLPService;
    }

    @Override
    public IKnowledgeService getKnowledgeService() {
        return knowledgeService;
    }

    @Override
    public ISchemaMatchingService getSchemaMatchingService() {
        return schemaMatchingService;
    }

    @Override
    public IIdentityService getIdentityService() {
        return identityService;
    }

    @Override
    public IEntityTypeService getEntityTypeService() {
        return entityTypeService;
    }

    @Override
    public IEntityService getEntityService() {
        return entityService;
    }

    @Override
    public List<Locale> getSupportedLocales() {
        List<Locale> ret = new ArrayList();
        ret.add(Locale.ITALIAN);
        ret.add(Locale.ENGLISH);
        return ret;
    }

    static public String makeURL() {
        return MOCK_EKB_URL + UUID.randomUUID().toString();
    }

    @Override
    public void setProperties(Map<String, String> properties) {
        if (properties.size() > 0) {
            LOG.log(Level.WARNING, "ignoring {0} properties", properties.size());
        }
    }

    @Override
    public String getPropertyNamespace() {
        return MOCK_EKB_NAMESPACE;
    }

        /**
     * generates a new local ID
     */
    static synchronized private long makeNewLocalID() {
        guidCounter += 1;
        return guidCounter;
    }

    /**
     * generates a new local entity URL
     */
    static synchronized public String makeNewLocalEntityURL() {
        return ENTITY_PREFIX + makeNewLocalID();
    }

    /**
     * generates a new local entity URL
     */
    static synchronized public String makeNewLocalStructureURL() {
        return STRUCTURE_PREFIX + makeNewLocalID();
    }

}
