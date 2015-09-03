package eu.trentorise.opendata.semantics.model.entity;

import eu.trentorise.opendata.semantics.DataTypes;
import javax.annotation.concurrent.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.base.Strings;
import eu.trentorise.opendata.commons.Dict;
import static eu.trentorise.opendata.commons.OdtUtils.checkNotDirtyUrl;
import java.util.Locale;

/**
 *
 *
 * Descriptor of complex types in Open entity, such as arrays of base types.
 * Note this is a temporary solution until we transition to the generic
 * TraceProv {@link eu.trentorise.opendata.traceprov.types.Type}
 *
 * May seem redundant given we have AttributeDef and 'primitive' dataType but
 * sometimes is handy.
 *
 * @author David Leoni
 */
@Immutable
public class AttrType {

    final private boolean list;
    final private String datatype;
    final private String etypeURL;
    final private Dict etypeName;

    /**
     * @param type a string representation of a type todo put specs
     */
    public static AttrType of(String type) {

        boolean lst;

        String candidateDatatype;
        if (type.endsWith("[]")) {
            lst = true;
            try {
                candidateDatatype = type.substring(0, type.length() - 2);
            }
            catch (Exception ex) {
                throw new IllegalArgumentException("Couldn't read type " + type, ex);
            }
        } else {
            candidateDatatype = type;
            lst = false;
        }

        return new AttrType(candidateDatatype, lst, "", Dict.of());
    }

    /**
     *
     * @param datatype a dataType as defined in
     * {@link eu.trentorise.opendata.opendatarise.types.OdrDataTypes}
     * @param list
     */
    public static AttrType of(String datatype, boolean list) {
        return AttrType.of(datatype, list, "", Dict.of());
    }

    public static AttrType of(IAttributeDef attrDef) {
        return of(attrDef.getDatatype(), attrDef.isList(), attrDef.getRangeEtypeURL());
    }

    /**
     *
     * @param datatype a dataType as defined in
     * {@link eu.trentorise.opendata.opendatarise.types.OdrDataTypes}.
     * @param list
     * @param etypeURL an entity type URL in case the dataType is either a
     * STRUCTURE or an ENTITY. Otherwise, it must be empty.
     */
    public static AttrType of(String datatype, boolean list, String etypeURL) {
        return of(datatype, list, etypeURL, Dict.of());
    }

    /**
     * *
     *
     * @param datatype a dataType as defined in {@link }.
     * @param list
     * @param etypeURL an entity type URL in case the dataType is either a
     * STRUCTURE or an ENTITY. Otherwise, it must be empty.
     * @param etypeName the name of the etype in case the data either a
     * STRUCTURE or an ENTITY. Otherwise, it must be {@link Dict#of()}.
     */
    public static AttrType of(String datatype, boolean list, String etypeURL, Dict etypeName) {
        return new AttrType(datatype, list, etypeURL, etypeName);
    }

    /**
     * *
     *
     * @param datatype a dataType as defined in {@link }.
     * @param list
     * @param etypeURL an entity type URL in case the dataType is either a
     * STRUCTURE or an ENTITY. Otherwise, it must be empty.
     * @param etypeName the name of the etype in case the data either a
     * STRUCTURE or an ENTITY. Otherwise, it must be {@link Dict#of()}.
     */
    private AttrType(String datatype, boolean list, String etypeURL, Dict etypeName) {
        checkNotNull(datatype, "Invalid datatype!");
        checkNotNull(etypeURL, "Invalid etypeURL!");
        checkNotNull(etypeName, "Invalid etypeName!");

        this.list = list;
        this.datatype = datatype;
        if ((datatype.equals(DataTypes.STRUCTURE) || datatype.equals(DataTypes.ENTITY))) {
            checkNotDirtyUrl(etypeURL, "Need a valid etypeURL when datatype is a STRUCTURE or ENTITY");
        }

        this.etypeURL = etypeURL;
        this.etypeName = etypeName;

    }

    private AttrType() {
        this.list = false;
        this.datatype = DataTypes.ANY_TYPE;
        this.etypeURL = "";
        this.etypeName = Dict.of();
    }

    /**
     * Gives the English version identifying representation of the AttrType
     * i.e. oe:semantic-text[]. For a pretty localized version, see
     * {@link #asPrettyString()}
     */
    @Override
    public String toString() {
        String candidateName;

        if (datatype.equals(DataTypes.STRUCTURE) || datatype.equals(DataTypes.ENTITY)) {
            candidateName = etypeURL;
        } else {
            candidateName = datatype;
        }
        if (isList()) {
            return candidateName + "[]";
        } else {
            return candidateName;
        }
    }

    /**
     * So we can send it to the browser. Calls {@link #toString()}.
     */
    @JsonProperty
    public String asString() {
        return toString();
    }

    @JsonProperty
    public boolean isList() {
        return list;
    }

    /**
     * @see
     * OdrDataTypes#isRelationalDataType(eu.trentorise.opendata.opendatarise.types.OdrType)
     */
    public boolean isRelational() {
        return DataTypes.isRelationalDatatype(datatype);
    }

    /**
     * @see OdrDataTypes#isNlpProcessable(java.lang.String)
     */
    public boolean isNlpProcessable() {
        return DataTypes.isNlpProcessable(datatype);
    }

    public String getDatatype() {
        return datatype;
    }

    /**
     * Returns a pretty localized version of the attrtype.
     *
     * @return a pretty localized version of the odrtype  prefixes are
     * stripped. <br/>
     * <br/>
     * Example: <br/>
     * for toString() == "epdt:INTEGER[]" and Italian locale returns "Intero[]".
     * <br/>
     * For STRUCTURE and ENTITY datatypes returns entity type name field if not
     * empty, the range etype URL otherwise.
     */    
    public String asPrettyString(Iterable<Locale> locales) {
        String candidateName = null;

        if (datatype.equals(DataTypes.STRUCTURE) || datatype.equals(DataTypes.ENTITY)) {
            String prettyString = etypeName.some(locales).str();
            if (prettyString.length() == 0) {
                candidateName = etypeURL;
            } else {
                candidateName = prettyString;
            }
        } else {
            
            if (locales.iterator().hasNext()){
                candidateName = DataTypes.prettyDataType(datatype, locales.iterator().next());
            }            
            
        }

        if (Strings.isNullOrEmpty(candidateName)){
                candidateName = datatype;                
        }
        
        if (isList()) {
            return candidateName + " [ ]";
        } else {
            return candidateName;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.list ? 1 : 0);
        hash = 67 * hash + (this.datatype != null ? this.datatype.hashCode() : 0);
        hash = 67 * hash + (this.etypeURL != null ? this.etypeURL.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

    public boolean isSemanticText() {
        // odr todo 0.3i let's ignore lists for now
        return // !list    
                // && 
                (datatype.equals(DataTypes.SEMANTIC_TEXT)
                || datatype.equals(DataTypes.CONCEPT));
    }

    /**
     * Returns the etype URL.
     *
     * @throws IllegalStateException if dataType is not STRUCTURE nor ENTITY
     */
    public String etypeURL() {
        if (!(datatype.equals(DataTypes.STRUCTURE) || datatype.equals(DataTypes.ENTITY))) {
            throw new IllegalStateException("Tried to get etypeURL for datatype " + datatype + "!");
        }
        return etypeURL;
    }

}
