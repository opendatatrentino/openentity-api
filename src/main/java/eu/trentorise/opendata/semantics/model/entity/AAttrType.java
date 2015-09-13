package eu.trentorise.opendata.semantics.model.entity;

import eu.trentorise.opendata.semantics.DataTypes;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Strings;
import eu.trentorise.opendata.commons.BuilderStylePublic;
import eu.trentorise.opendata.commons.Dict;
import static eu.trentorise.opendata.commons.OdtUtils.checkNotDirtyUrl;
import java.util.Locale;
import org.immutables.value.Value;

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
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = AttrType.class)
@JsonDeserialize(as = AttrType.class)
abstract class AAttrType {

    /**
     * The data type of an attribute definition. Possible values for the
     * datatypes are listed in
     * {@link package eu.trentorise.opendata.semantics.services.DataTypes}
     * class. By default returns {@link DataTypes#ANY_TYPE}
     *
     * @return the data type as string
     */
    @Value.Default
    public String getDatatype() {
        return DataTypes.ANY_TYPE;
    }

    /**
     * Whether the type represent single or multiple values. False by
     * default.
     */
    @Value.Default
    public boolean isList() {
        return false;
    }

    /**
     * The regular expression that all the attribute values should follow
     *
     * @deprecated Doesn't make much sense
     *
     */
    @Value.Default
    public String getRegularExpression() {
        return "";
    }

    /**
     * Tells if the attribute is mandatory or not. False by default.
     */
    @Value.Default
    public boolean isMandatory() {
        return false;
    }

    /**
     * The entity type URL for the range, when the datatype is
     * {@link DataTypes#STRUCTURE} or {@link DataTypes#ENTITY}. Otherwise it
     * must be empty.
     *
     * @see #etypeURL()
     */
    @Value.Default
    public String getEtypeURL() {
        return "";
    }

    /**
     * Returns the etype name, or {@link Dict#of()} if it was not provided
     */
    @Value.Default
    public Dict getEtypeName() {
        return Dict.of();
    }

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

        return AttrType.of(candidateDatatype, lst, "", Dict.of());
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

    public static AttrType of(AttributeDef attrDef) {
        return of(attrDef.getAttrType().getDatatype(), attrDef.getAttrType().isList(), attrDef.getAttrType().getEtypeURL());
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
        return of( datatype, list,  etypeURL, Dict.of());
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
        return AttrType.builder()
                .setDatatype(datatype)
                .setList(list)
                .setEtypeURL(etypeURL)
                .setEtypeName(etypeName)
                .build();
    } 

    @Value.Check
    protected void check() {
        if ((getDatatype().equals(DataTypes.STRUCTURE) || getDatatype().equals(DataTypes.ENTITY))) {
            checkNotDirtyUrl(getEtypeURL(), "Need a valid etypeURL when datatype is a STRUCTURE or ENTITY");
        }
    }

    /**
     * Gives the English version identifying representation of the AttrType i.e.
     * oe:semantic-text[]. For a pretty localized version, see
     * {@link #asPrettyString()}
     */
    @Override
    public String toString() {
        String candidateName;

        if (getDatatype().equals(DataTypes.STRUCTURE) || getDatatype().equals(DataTypes.ENTITY)) {
            candidateName = getEtypeURL();
        } else {
            candidateName = getDatatype();
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

    /**
     * @see
     * OdrDataTypes#isRelationalDataType(eu.trentorise.opendata.opendatarise.types.OdrType)
     */
    public boolean isRelational() {
        return DataTypes.isRelationalDatatype(getDatatype());
    }

    /**
     * @see OdrDataTypes#isNlpProcessable(java.lang.String)
     */
    public boolean isNlpProcessable() {
        return DataTypes.isNlpProcessable(getDatatype());
    }

    /**
     * Returns a pretty localized version of the attrtype.
     *
     * @return a pretty localized version of the odrtype prefixes are stripped.
     * <br/>
     * <br/>
     * Example: <br/>
     * for toString() == "epdt:INTEGER[]" and Italian locale returns "Intero[]".
     * <br/>
     * For STRUCTURE and ENTITY datatypes returns entity type name field if not
     * empty, the range etype URL otherwise.
     */
    public String asPrettyString(Iterable<Locale> locales) {
        String candidateName = null;

        if (getDatatype().equals(DataTypes.STRUCTURE) || getDatatype().equals(DataTypes.ENTITY)) {
            String prettyString = getEtypeName().some(locales).str();
            if (prettyString.length() == 0) {
                candidateName = getEtypeURL();
            } else {
                candidateName = prettyString;
            }
        } else {

            if (locales.iterator().hasNext()) {
                candidateName = DataTypes.prettyDataType(getDatatype(), locales.iterator().next());
            }

        }

        if (Strings.isNullOrEmpty(candidateName)) {
            candidateName = getDatatype();
        }

        if (isList()) {
            return candidateName + " [ ]";
        } else {
            return candidateName;
        }
    }

    /**
     * todo confusing naming...
     */
    public boolean isSemanticText() {
        // odr todo 0.3i let's ignore lists for now
        return // !list    
                // && 
                (getDatatype().equals(DataTypes.SEMANTIC_TEXT)
                || getDatatype().equals(DataTypes.CONCEPT));
    }

    /**
     * Returns the entity type URL for the range, when the datatype is
     * oe:structure or oe:entity
     *
     * @return the entity type URL if the data type is oe:structure or
     * oe:entity, empty string otherwise
     * @throws IllegalStateException if dataType is not STRUCTURE nor ENTITY
     */
    public String etypeURL() {
        if (!(getDatatype().equals(DataTypes.STRUCTURE) || getDatatype().equals(DataTypes.ENTITY))) {
            throw new IllegalStateException("Tried to get etypeURL for datatype " + getDatatype() + "!");
        }
        return getEtypeURL();
    }

}
