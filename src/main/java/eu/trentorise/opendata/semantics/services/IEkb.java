/* 
 * Copyright 2015 TrentoRISE   (trentorise.eu).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.trentorise.opendata.semantics.services;

import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Interface for representing an entity knowledge base. Implementations of this
 * interface must have a public default constructor. Clients are not required to
 * be thread safe.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @author Ivan Tankoyeu <tankoyeu@disi.unitn.it>
 */
// TODO define what happens when services are not available
public interface IEkb {

    /**
     * Returns the locales supported by the ekb.
     *
     * @return the supported locales.
     */
    List<Locale> getSupportedLocales();

    /**
     * Sets the locales in which strings will be returned during subsequent
     * service api calls, if they are available.
     *
     * @param locales the list of desired locales. If empty, English will be
     * used as default locale.
     */
    void setDefaultLocales(Iterable<Locale> locales);

    /**
     * Gets the default locales used when returning translations by the Ekb
     *
     * @return the default locales
     */
    List<Locale> getDefaultLocales();

    /**
     * Set the client specific properties. Their name will have the prefix given
     * by {@link #getPropertyNamespace()}
     *
     */
    void setProperties(Map<String, String> properties);

    /**
     * Returns the namespace used for the client properties.
     */
    String getPropertyNamespace();

    /**
     * Gets the service for Natural Language Processing
     *
     * @return the NLP service if supported, null otherwise.
     */
    INLPService getNLPService();

    /**
     * Gets the knowledge service
     *
     * @return the knowledge service if supported, null otherwise.
     */
    IKnowledgeService getKnowledgeService();

    /**
     * Gets the service for schema matching
     *
     * @return the schema matching service if supported, null otherwise.
     */
    ISchemaMatchingService getSchemaMatchingService();

    /**
     * Gets the identity management service
     *
     * @return the identity service if supported, null otherwise.
     */
    IIdentityService getIdentityService();

    /**
     * Gets the entity type management service
     *
     * @return the entity type service if supported, null otherwise.
     */
    IEtypeService getEtypeService();

    /**
     * Gets the entity management service
     *
     * @return the entity service if supported, null otherwise.
     */
    IEntityService getEntityService();

}
