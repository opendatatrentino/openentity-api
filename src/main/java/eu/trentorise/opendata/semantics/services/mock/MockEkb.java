package eu.trentorise.opendata.semantics.services.mock;

import com.google.common.collect.ImmutableList;

import eu.trentorise.opendata.semantics.model.entity.AttrDef;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.IEntityService;
import eu.trentorise.opendata.semantics.services.IEtypeService;
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
    
    
    public static final String LOCAL_ID_PREFIX = "oetest:";

    /**
     * This is a transitional prefix ONLY for objects created outside ODR having
     * a numerical ID (such as values and attributes), to be removed when we
     * fully switch to a URL-ONLY model.
     */
    public static final String FOREIGN_PREFIX = LOCAL_ID_PREFIX + "foreign/";

    /**
     * This is a transitional prefix ONLY for values ID, to be removed when
     * Val will have getURL() instead of getlocalId()
     */
    public static final String FOREIGN_VALUE_PREFIX = FOREIGN_PREFIX + "value/";

    /**
     * This is a transitional prefix ONLY for attributes ID, to be removed when
     * Attr will have getURL() instead of getlocalId() - or when we get
     * read of Attr alltogether
     */
    public static final String FOREIGN_ATTRIBUTE_PREFIX = FOREIGN_PREFIX + "attribute/";

    private static long guidCounter = 0L;


    private MockNlpService NLPService;
    private MockKnowledgeService knowledgeService;
    private MockSchemaMatchingService schemaMatchingService;
    private IIdentityService identityService;
    private MockEtypeService entityTypeService;
    private IEntityService entityService;
    private ImmutableList<Locale> defaultLocales;


    public MockEkb() {
        this.NLPService = new MockNlpService(this);
        this.entityTypeService = new MockEtypeService();
        this.knowledgeService = new MockKnowledgeService();        
        this.identityService = new MockIdentityService(this);
        this.schemaMatchingService = new MockSchemaMatchingService();
       
        this.entityService = new MockEntityService(this);
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
    public IEtypeService getEtypeService() {
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
    static synchronized private long makeNewLocalId() {
        guidCounter += 1;
        return guidCounter;
    }

    /**
     * generates a new local entity URL
     */
    static synchronized public String makeNewLocalEntityURL() {
        return MockEntityService.ENTITY_PREFIX + makeNewLocalId();
    }

    /**
     * generates a new local entity URL
     */
    static synchronized public String makeNewLocalStructURL() {
        return MockEntityService.STRUCTURE_PREFIX + makeNewLocalId();
    }

}
