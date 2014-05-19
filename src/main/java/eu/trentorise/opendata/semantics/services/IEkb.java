/**
 * *****************************************************************************
 * Copyright 2012-2013 Trento Rise (www.trentorise.eu/)
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License (LGPL)
 * version 2.1 which accompanies this distribution, and is available at
 *
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************
 */
package eu.trentorise.opendata.semantics.services;

import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/**
 * Interface for representing an entity knowledge base.
 * @author David Leoni <david.leoni@unitn.it>
 * @author Ivan Tankoyeu <tankoyeu@disi.unitn.it>
 * @date 23 Apr, 2014
 */
public interface IEkb {
    
    /**
     * Returns the locales supported by the ekb.
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
    void setDefaultLocales(List<Locale> locales);
    
    /**
     * Gets the default locales used when returning translations by the Ekb
     * @return the default locales 
     */
    List<Locale> getDefaultLocales();

    /**
     * Gets the service for Natural Language Processing
     * 
     * @return the NLP service if supported, null otherwise.
     */
    @Nullable
    INLPService getNLPService();

    /**
     * Gets the knowledge service 
     * @return the knowledge service if supported, null otherwise.
     */
    @Nullable
    IKnowledgeService getKnowledgeService();

    /**
     * Gets the service for schema matching
     * @return the schema matching service if supported, null otherwise.
     */
    @Nullable
    ISemanticMatchingService getSemanticMatchingService();

    /**
     * Gets the identity management service
     * @return the identity service if supported, null otherwise.
     */
    @Nullable
    IIdentityService getIdentityService();

    /**
     * Gets the entity type management service
     * @return the entity type service if supported, null otherwise.
     */
    @Nullable
    IEntityTypeService getEntityTypeService();

    /**
     * Gets the entity management service
     * @return the entity service if supported, null otherwise.
     */
    @Nullable
    IEntityService getEntityService();
    
    /**
     * Gets the search service
     * @deprecated don't use it anymore
     * @return the search service
     */    
    ISearchService getSearchService();    
}
