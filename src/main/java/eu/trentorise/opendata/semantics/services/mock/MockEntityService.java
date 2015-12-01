package eu.trentorise.opendata.semantics.services.mock;

import static com.google.common.base.Preconditions.checkNotNull;
import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.model.entity.AStruct;
import eu.trentorise.opendata.semantics.model.entity.Entity;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.semantics.model.entity.Struct;
import eu.trentorise.opendata.semantics.services.EntityQuery;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.IEntityService;
import eu.trentorise.opendata.semantics.services.SearchResult;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

import com.google.common.collect.Iterables;

/**
 *
 * @author David Leoni
 */
public class MockEntityService implements IEntityService {

    /**
     * ID prefix for entities created in mockekb
     */
    public static final String ENTITY_PREFIX = MockEkb.LOCAL_ID_PREFIX + "entity/";

    /**
     * ID prefix for values created in mockekb
     */
    public static final String VALUE_PREFIX = MockEkb.LOCAL_ID_PREFIX + "value/";

    /**
     * ID prefix for attributes created in mockekb
     */
    public static final String ATTRIBUTE_PREFIX = MockEkb.LOCAL_ID_PREFIX + "attribute/";

    /**
     * ID prefix for entities created in mockekb
     */
    public static final String STRUCTURE_PREFIX = MockEkb.LOCAL_ID_PREFIX + "struct/";

    
    public static String TEST_ENTITY_1 = ENTITY_PREFIX + "test-entity-1";
    public static String TEST_ENTITY_2 = ENTITY_PREFIX + "test-entity-2";

    
    private static final Logger LOG = Logger.getLogger(MockEntityService.class.getName());

    private IEkb ekb;

    public MockEntityService(IEkb ekb) {
	checkNotNull(ekb);
	this.ekb = ekb;
    }

    @Override
    public Entity createEntity(Entity entity) {
	LOG.log(Level.INFO, "Creating mock entity {0}", entity.getName());

	String URL = MockEkb.makeNewLocalEntityURL();
	LOG.log(Level.INFO, "Assigning URL to mock entity {0}", entity.getName());
	return entity.withId(URL);

    }

    @Override
    public void updateEntity(Entity entity) {
	LOG.log(Level.INFO, "updating mock entity {0}", entity.getId());

    }

    @Override
    public void deleteEntity(String entityURL) {
	LOG.log(Level.INFO, "Deleting mock entity {0}", entityURL);
    }

    public Entity newEntity(String id, Etype etype, Dict name, Dict descr) {

	return Entity.builder().setNameAttr(name, etype.getId(), ekb.getEtypeService())
		.setDescrAttr(descr, etype.getId(), ekb.getEtypeService()).setId(id).setEtypeId(etype.getId()).build();
    }

    /**
     * Always returns the same entity, but with the provided URL.
     */
    @Override
    public Entity readEntity(final String entityURL) {
	LOG.log(Level.INFO, "reading mock entity {0}", entityURL);

	LOG.warning("TODO - ENTITY READ: GENERATING FAKE ENTITY!");
	
	return newEntity(entityURL, ekb.getEtypeService().readRootEtype(),
		Dict.of("Generated test entity").with(Locale.ITALIAN,
			"Entità di prova automaticamente generata con URL " + entityURL),
		Dict.of("Entity generated for testing purposes.").with(Locale.ITALIAN,
			"Entità generata per testare ODR"));
    }

    /**
     * Always returns the same structure, but with the provided URL.
     */
    @Override
    public AStruct readStruct(final String structURL) {
	LOG.log(Level.INFO, "reading mock entity {0}", structURL);

	LOG.warning("TODO - STRUCT READ: GENERATING FAKE ABSURD STRUCT!");
	
	return newEntity(structURL, ekb.getEtypeService().readRootStruct(),
		Dict.of("Generated test structure").with(Locale.ITALIAN,
			"Struct di prova automaticamente generata con URL " + structURL),
		Dict.of("Struct generated for testing purposes.").with(Locale.ITALIAN,
			"Struct generata per testing"));
    }
    
        
    public Entity readEntity(String entityURL, String etypeUrl) {
	return newEntity(entityURL, ekb.getEtypeService().readEtype(etypeUrl),
		Dict.of("Generated test entity").with(Locale.ITALIAN,
			"Entità di prova automaticamente generata con URL " + entityURL),
		Dict.of("Entity generated for testing purposes.").with(Locale.ITALIAN,
			"Entità generata per testare ODR"));
    }

    @Override
    public void exportToRdf(Iterable<String> entityIds, Writer writer) {
	try {
	    writer.write("<?xml version=\"1.0\"?>\n" + "\n" + "<rdf:RDF\n"
		    + "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n"
		    + "xmlns:cd=\"http://www.recshop.fake/cd#\">>\n" + "</rdf:RDF>");
	} catch (IOException ex) {
	    LOG.log(Level.SEVERE, "Couldn't write rdf to file", ex);
	}
    }

    @Override
    public void exportToJsonLd(Iterable<String> entityIds, Writer writer) {
	try {
	    writer.write("    {\n" + "  \"@context\": {\n" + "    \"name\": \"http://xmlns.com/foaf/0.1/name\",\n"
		    + "    \"homepage\": {\n" + "      \"@id\": \"http://xmlns.com/foaf/0.1/homepage\",\n"
		    + "      \"@type\": \"@id\"\n" + "    }\n" + "  },\n" + "  \"name\": \"Test\",\n"
		    + "  \"homepage\": \"http://test.org/\"\n" + "}");
	} catch (IOException ex) {
	    LOG.log(Level.SEVERE, "Couldn't write json-ld to file", ex);
	}

    }

    @Override
    public void exportToCsv(Iterable<String> entityIds, Writer writer) {
	try {
	    writer.write("testcol1,testcol2,testcol3,testcol3");
	} catch (IOException ex) {
	    LOG.log(Level.SEVERE, "Couldn't write csv to file", ex);
	}
	/*
	 * CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new
	 * FileOutputStream(absSemantifiedFile), "UTF-8"), ',');
	 * ArrayList<String> headerStrings = new ArrayList<String>();
	 * List<Column> columns = getProject().getColumnModel().getColumns();
	 * for (int i = 0; i < columns.size(); i++) { if
	 * (OdrColumnOverlay.getOverlay(getProject(), i) instanceof
	 * TargetColumnOverlay) { headerStrings.add(columns.strs(i).getName());
	 * } }
	 * 
	 * csvWriter.writeNext(headerStrings.toArray(new
	 * String[headerStrings.size()]));
	 * 
	 * for (Row row : getProject().getRows()) { ArrayList<String>
	 * cellStrings = new ArrayList<String>(); for (int i = 0; i <
	 * columns.size(); i++) { if (OdrColumnOverlay.getOverlay(getProject(),
	 * i) instanceof TargetColumnOverlay) {
	 * cellStrings.add(OdrDataTypes.formatForCsv(row.getCell(columns.strs(i)
	 * .getCellIndex()))); } } csvWriter.writeNext(cellStrings.toArray(new
	 * String[cellStrings.size()])); }
	 * 
	 * 
	 * 
	 * csvWriter.close();
	 */

    }


    @Override
    public List<Entity> readEntities(Iterable<String> entityURLs) {
	LOG.log(Level.INFO, "Batch reading mock {0} entities", Iterables.size(entityURLs));
	List<Entity> ret = new ArrayList();
	for (String entityURL : entityURLs) {
	    ret.add(readEntity(entityURL));
	}
	return ret;
    }

    @Override
    public List<SearchResult> searchEntities(EntityQuery query) {
	List<SearchResult> ret = new ArrayList();

	List<Entity> entities = new ArrayList();

	entities.add(readEntity("http://bla.org/test-entity-1", query.getEtypeId()));
	entities.add(readEntity("http://bla.org/test-entity-2", query.getEtypeId()));
	entities.add(readEntity("http://bla.org/test-entity-3", query.getEtypeId()));

	for (Entity ent : entities) {
	    if (ent.getName().contains(query.getPartialName())) {
		ret.add(SearchResult.of(ent.getId(), ent.getName()));
	    }
	}

	return ret;
    }

    @Override
    public boolean isTemporaryURL(String temporaryEntityURL) {
	return temporaryEntityURL.contains("/new/");
    }

    @Override
    public List<? extends AStruct> readStructs(Iterable<String> structIds) {
	LOG.log(Level.INFO, "Batch reading mock {0} structs", Iterables.size(structIds));
	List<Entity> ret = new ArrayList();
	for (String structURL : structIds) {
	    ret.add(readEntity(structURL));
	}
	return ret;
    }

}
