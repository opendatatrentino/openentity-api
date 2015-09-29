package eu.trentorise.opendata.semantics.services;


import java.util.Locale;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eu.trentorise.opendata.commons.BuilderStylePublic;

@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = EntityQuery.class)
@JsonDeserialize(as = EntityQuery.class)
abstract class AEntityQuery extends AQuery {

    /**
     * A partial entity name. It is assumed to be in one of the default locales
     * of the ekb. Defaults to empty string.
     * 
     */
    @Value.Default
    public String getPartialName() {
	return "";
    }

    /**
     * The locale of the search string. If unspecified it defaults to
     * {@link Locale#ROOT} and ekb default locales will be used instead.
     * 
     */
    @Value.Default
    public Locale getLocale() {
	return Locale.ROOT;
    }

    /**
     * The url of the entity type. Entities returned will be instances of that
     * etype (or its descendants). Use the empty string for not filtering by
     * etype.
     */
    @Value.Default
    public String getEtypeId() {
	return "";
    }
}
