package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.DataTypes;
import static eu.trentorise.opendata.semantics.DataTypes.AT_CONCEPT;
import static eu.trentorise.opendata.semantics.DataTypes.AT_FLOAT;
import static eu.trentorise.opendata.semantics.DataTypes.AT_STRING;
import eu.trentorise.opendata.semantics.model.entity.AttrType;
import eu.trentorise.opendata.semantics.model.entity.AttrDef;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.semantics.services.IEtypeService;
import eu.trentorise.opendata.semantics.services.SearchResult;
import static eu.trentorise.opendata.semantics.services.mock.MockKnowledgeService.ROOT_CONCEPT;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

/**
 *
 * @author David Leoni
 */
public class MockEtypeService implements IEtypeService {

    public static final String ATTR_DEF_PREFIX = "oetest:ad/";
    public static final String ETYPE_PREFIX = "oetest:et/";
    
    public static final String TEST_ROOT_STRUCTURE = ETYPE_PREFIX + "test-root-struct";

    public static final String TEST_ROOT_ETYPE = ETYPE_PREFIX + "test-root-etype";
    public static final String TEST_ROOT_NAME_ATTR = ATTR_DEF_PREFIX + "test-root-name";
    public static final String TEST_ROOT_DESCR_ATTR = ATTR_DEF_PREFIX + "test-root-description";


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

    
    
    /**
     * Simple entity type with 4 attributes, name, description, test-attr-def-1,
     * test-attr-def-2 and test-attr-def-3
     */
    public static final String TEST_ENTITY_TYPE = ETYPE_PREFIX + "test-etype";
    public static final String TEST_NAME_ATTR_ID = ATTR_DEF_PREFIX + "test-name";
    public static final String TEST_DESCR_ATTR_ID = ATTR_DEF_PREFIX + "test-description";
    
    public static final String TEST_ATTR_DEF_1_ID = ATTR_DEF_PREFIX + "test-attr-def-1";
    public static final String TEST_ATTR_DEF_2_ID = ATTR_DEF_PREFIX + "test-attr-def-2";
    public static final String TEST_ATTR_DEF_3_ID = ATTR_DEF_PREFIX + "test-attr-def-3";

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
    
    
    public static final AttrDef TEST_ATTR_DEF_1 = newAttrDef(
            TEST_ATTR_DEF_1_ID, 
            TEST_ATTR_DEF_1_EN_NAME,
            TEST_ATTR_DEF_1_IT_NAME, 
            AT_STRING);
    
    private final Map<String, Etype> registeredETypes;
    private final Map<String, AttrDef> registeredAttrDefs;

    /**
     * 
     * @param parentEtypeURL todo the parent is not really used...
     */
    public final void registerNameDescr(String nameURL, String descrURL, String parentEtypeURL) {
        registerAttrDef(nameURL, "Name", "Nome", AttrType.of(DataTypes.STRING, true));
        registerAttrDef(descrURL, "Description", "Descrizione", AttrType.of(DataTypes.SEMANTIC_TEXT, true));
    }

    public MockEtypeService() {
        this.registeredETypes = new HashMap();
        this.registeredAttrDefs = new HashMap();

        registerStructType(TEST_ROOT_STRUCTURE, "test root struct", "struttura radice di test");

        registerNameDescr(TEST_ROOT_NAME_ATTR, TEST_ROOT_DESCR_ATTR, TEST_ROOT_ETYPE);
        registerEtype(TEST_ROOT_ETYPE, "test root etype", "etype radice di test",
                MockKnowledgeService.ROOT_CONCEPT, 
                TEST_ROOT_NAME_ATTR,
                TEST_ROOT_DESCR_ATTR);

                
        registerAttrDef(TEST_ATTR_DEF_1_ID, TEST_ATTR_DEF_1_EN_NAME, TEST_ATTR_DEF_1_IT_NAME, AT_STRING);
        registerAttrDef(TEST_ATTR_DEF_2_ID, TEST_ATTR_DEF_2_EN_NAME, TEST_ATTR_DEF_2_IT_NAME, AT_STRING);
        registerAttrDef(TEST_ATTR_DEF_3_ID, TEST_ATTR_DEF_3_EN_NAME, TEST_ATTR_DEF_3_IT_NAME, AT_STRING);        
        
        registerNameDescr(TEST_NAME_ATTR_ID, TEST_DESCR_ATTR_ID, TEST_ENTITY_TYPE);
        registerEtype(TEST_ENTITY_TYPE, "test etype", "etype di test", ROOT_CONCEPT, TEST_NAME_ATTR_ID, TEST_DESCR_ATTR_ID,
                TEST_ATTR_DEF_1_ID, TEST_ATTR_DEF_2_ID, TEST_ATTR_DEF_3_ID);

        // recursive stuff begin
        registerAttrDef(TEST_REC_ATTR_DEF, "test rec attr 1 in recursive struct",
                "Attributo di test in struttura ricorsiva",
                AttrType.of(DataTypes.STRUCTURE, false, TEST_REC_STRUCTURE));
        registerStructType(TEST_REC_STRUCTURE, "test rec struct", "struttura ricorsiva di test", TEST_REC_ATTR_DEF);

        registerAttrDef(TEST_REC_OUTER_ATTR_DEF, "test rec struct attr 1", "Attr di test per struttura ricorsiva",
                AttrType.of(DataTypes.STRUCTURE, false, TEST_REC_STRUCTURE));

        registerNameDescr(TEST_REC_NAME_ATTR, TEST_REC_DESCR_ATTR, TEST_REC_ENTITY_TYPE);
        registerEtype(TEST_REC_ENTITY_TYPE, "test recursive etype", "etype ricorsivo di test", ROOT_CONCEPT,
                TEST_REC_NAME_ATTR, TEST_REC_DESCR_ATTR, TEST_REC_OUTER_ATTR_DEF);
        // recursive stuff end

        registerAttrDef(ADDRESS_MUNICIPALITY_ATTR, "Municipality", "Comune",
                AttrType.of(DataTypes.ENTITY, false, LOCATION));

        registerStructType(ADDRESS, "Address", "Indirizzo", ADDRESS_MUNICIPALITY_ATTR);

        registerNameDescr(LOCATION_NAME_ATTR, LOCATION_DESCR_ATTR, LOCATION);
        registerEtype(LOCATION, "Location", "Località", ROOT_CONCEPT, LOCATION_NAME_ATTR, LOCATION_DESCR_ATTR);

        registerNameDescr(FARMFED_PROD_NAME_ATTR, FARMFED_PROD_DESCR_ATTR, FARMFED_PRODUCER);
        registerEtype(FARMFED_PRODUCER, "Producer", "Produttore di alimentari", ROOT_CONCEPT, FARMFED_PROD_NAME_ATTR,
                FARMFED_PROD_DESCR_ATTR);

        registerAttrDef(CERTIFIED_PRODUCT_CERTIFICATE_ATTR, "certificate", "certificazione", AT_STRING);
        registerAttrDef(CERTIFIED_PRODUCT_REGULATIONS_ATTR, "regulations", "norme", AT_STRING);
        registerAttrDef(CERTIFIED_PRODUCT_PRODUCTION_SITE_ATTR, "production site", "luogo di produzione",
                AttrType.of(DataTypes.ENTITY, true, LOCATION));
        registerAttrDef(CERTIFIED_PRODUCT_PRODUCER_ATTR, "producer", "produttore",
                AttrType.of(DataTypes.ENTITY, false, FARMFED_PRODUCER));
        registerAttrDef(CERTIFIED_PRODUCT_WEBSITE_ATTR, "website", "sito web", AT_STRING);

        registerNameDescr(CERTIFIED_PRODUCT_NAME_ATTR, CERTIFIED_PRODUCT_DESCR_ATTR, CERTIFIED_PRODUCT);
        registerEtype(CERTIFIED_PRODUCT, "Certified product", "Prodotto certificato", ROOT_CONCEPT,
                CERTIFIED_PRODUCT_NAME_ATTR, CERTIFIED_PRODUCT_DESCR_ATTR, CERTIFIED_PRODUCT_CERTIFICATE_ATTR,
                CERTIFIED_PRODUCT_REGULATIONS_ATTR, CERTIFIED_PRODUCT_PRODUCTION_SITE_ATTR,
                CERTIFIED_PRODUCT_PRODUCER_ATTR, CERTIFIED_PRODUCT_WEBSITE_ATTR);

        registerAttrDef(OPENING_TIME_ATTR, "Opening time", "Orario di apertura", AT_STRING);
        registerAttrDef(CLOSING_TIME_ATTR, "Closing time", "Orario di chiusura", AT_STRING);

        registerStructType(OPENING_HOURS, "Opening Hours", "Orario di apertura", OPENING_TIME_ATTR, CLOSING_TIME_ATTR);

        registerAttrDef(FACILITY_LATITUDE_ATTR, "Latitude", "Latitudine", AT_FLOAT);
        registerAttrDef(FACILITY_LONGITUDE_ATTR, "Longitude", "Longitudine", AT_FLOAT);
        registerAttrDef(FACILITY_OPENING_HOURS_ATTR, "Opening hours", "Orari",
                AttrType.of(DataTypes.STRUCTURE, false, OPENING_HOURS));
        registerAttrDef(FACILITY_CLASS_ATTR, "Class", "Classe", AT_CONCEPT);
        registerAttrDef(COMPLEX_NAME_NAME_ATTR, "Name", "Nome", AttrType.of(DataTypes.STRING, true));
        registerStructType(COMPLEX_NAME, "NameType", "Tipo nome", COMPLEX_NAME_NAME_ATTR);

        registerAttrDef(FACILITY_NAME_ATTR, "Name", "Nome", AttrType.of(DataTypes.STRUCTURE, true, COMPLEX_NAME));
        registerAttrDef(FACILITY_DESCR_ATTR, "Description", "Descrizione", AttrType.of(DataTypes.SEMANTIC_TEXT, true));
        registerAttrDef(FACILITY_ADDRESS_ATTR, "Address", "Indirizzo", AttrType.of(DataTypes.STRUCTURE, false, ADDRESS));

        registerEtype(FACILITY, "Facility", "Infrastruttura", MockKnowledgeService.FACILITY_CONCEPT, FACILITY_NAME_ATTR, FACILITY_DESCR_ATTR,
                FACILITY_LATITUDE_ATTR, FACILITY_LONGITUDE_ATTR, FACILITY_OPENING_HOURS_ATTR, FACILITY_CLASS_ATTR,
                FACILITY_ADDRESS_ATTR);

    }

    public Etype registerStructType(String URL, String engName, String itName, String... attrsURLs) {
        List<AttrDef> attrs = new ArrayList();
        for (String au : attrsURLs) {
            attrs.add(registeredAttrDefs.get(au));
        }

        Etype ret = newEtype(URL, engName, itName, attrs, MockKnowledgeService.ROOT_CONCEPT);
        registeredETypes.put(URL, ret);
        return ret;
    }

    private Etype newEtype(String URL, String engName, String itName, List<AttrDef> attrs, String conceptUrl) {
        Etype.Builder b = Etype.builder();
        b.setId(URL);
        b.setName(Dict.builder()
                      .put(Locale.ENGLISH, engName)
                      .put(Locale.ITALIAN, itName)
                      .build());

        for (AttrDef attrDef : attrs) {
            b.putAttrDefs(attrDef.getId(), attrDef);
        }
        b.setConceptId(conceptUrl);
        return b.build();
    }

    public Etype registerEtype(String URL, String engName, String itName, @Nullable String conceptURL,
            String nameAttrDefURL, String descrAttrDefURL, String... attrsURLs) {

        List<AttrDef> attrs = new ArrayList();
        for (String au : attrsURLs) {
            attrs.add(registeredAttrDefs.get(au));
        }
        attrs.add(registeredAttrDefs.get(nameAttrDefURL));
        attrs.add(registeredAttrDefs.get(descrAttrDefURL));
        
        Etype ret = Etype.of(URL, engName, itName, conceptURL, nameAttrDefURL, descrAttrDefURL, attrs);
        registeredETypes.put(URL, ret);
        return ret;
    }
        
    public AttrDef registerAttrDef(String URL, String engName, String itName, AttrType attrType) {
        AttrDef ret = newAttrDef(URL, engName, itName, attrType);
        registeredAttrDefs.put(URL, ret);
        return ret;
    }

    static AttrDef newAttrDef(String URL, String engName, String itName, AttrType attrType) {
        return AttrDef.builder()
                      .setId(URL)
                      .setName(Dict.builder()
                                   .put(Locale.ENGLISH, engName)
                                   .put(Locale.ITALIAN, itName)
                                   .build())
                      .setType(attrType)
                      .build();
    }

    /**
     * @return unmodifiable list
     */
    public Map<String, AttrDef> getRegisteredAttrDefs() {
        return Collections.unmodifiableMap(registeredAttrDefs);
    }

    /**
     * @return unmodifiable list
     */
    public Map<String, Etype> getRegisteredETypes() {
        return Collections.unmodifiableMap(registeredETypes);
    }

    @Override
    public Etype readEtype(String URL) {
        for (Etype et : this.registeredETypes.values()) {
            if (et.getId()
                  .equals(URL)) {
                return et;
            }
        }
        return null;
    }

    @Override
    public List<Etype> readEtypes(Iterable<String> URLs) {
        List<Etype> ret = new ArrayList();

        for (String URL : URLs) {
            ret.add(readEtype(URL));
        }
        return ret;
    }

    /**
     * Returns all the mock entity types
     */
    @Override
    public List<SearchResult> searchEtypes(String partialName, Locale locale) {
        List<SearchResult> ret = new ArrayList();

        for (Etype et : readAllEtypes()) {
            if (et.getName()
                  .contains(partialName)) {
                SearchResult es = SearchResult.of(et.getId(), et.getName());
                ret.add(es);
            }
        }

        return ret;
    }

    @Override
    public Etype readRootEtype() {
        return registeredETypes.get(TEST_ROOT_ETYPE);
    }

    @Override
    public Etype readRootStruct() {
        return registeredETypes.get(TEST_ROOT_STRUCTURE);
    }

    @Override
    public List<Etype> readAllEtypes() {
        return new ArrayList(registeredETypes.values());
    }

    @Override
    public AttrDef readAttrDef(String url) {
        return registeredAttrDefs.get(url);
    }

}
