/* 
 * Copyright 2013-2015   Trento Rise   trentorise.eu
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
package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import java.util.Map;

/**
 * @author David Leoni <david.leoni@unitn.it>
 * @author Ivan Tankoyeu <tankoyeu@disi.unitn.it>
 * @date Mar 09, 2014
 */
public interface IAttributeCorrespondence {
    
    /**
     * Gets the target attribute definition
     * @return The target attribute def
     */
    IAttributeDef getAttrDef();

    /** 
     * Score range is within 0.0 - 1.0 range
     * Gets the map of candidate attributes associated to their score     
     * @return the map of candidate attributes associated to their score
     */
    Map<IAttributeDef, Float> getAttrMap();
    
    /**
     * Gets the source column index
     * @return the source column index
     */
    int getColumnIndex();

    /**
     * Gets the concept associated to the column header
     * @return the URL of the concept
     */
    String getHeaderConceptURL();

    /**
     * Gets the concept associated to the column header
     * @deprecated use getHeaderConceptURL instead
     * @return the conceptID of the comlumn header
     */
    long getHeaderConceptID();    
    
    /**
     * Gets the score associated to the attribute. It ranges from 0.0 (worst score) to 1.0 (best score).
     * @return the score associated to the attribute
     */
    float getScore();
    
}
