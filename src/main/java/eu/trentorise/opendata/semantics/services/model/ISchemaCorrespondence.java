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
package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import java.util.List;

/**
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @author Ivan Tankoyeu <tankoyeu@disi.unitn.it>
 * 
 */
public interface ISchemaCorrespondence {

    /** Scores are considered equals up to this margin */
    public final double SCORE_TOLERANCE = 0.01;

    
    /**
     * Gets a list of attribute correspondences
     * 
     * @deprecated use getAttributeCorrespondences() instead
     * 
     * @return the list of correspondences      
    */
    List<IAttributeCorrespondence> getAttributeCorrespondence();
    
    
    /**
     * Gets a list of attribute correspondences
     * @return the list of correspondences      
    */
    List<IAttributeCorrespondence> getAttributeCorrespondences();

    
    
    /**
     * Gets the target entity type
     * @return the target entity type
     */    
    IEntityType getEtype();

    /**
     * Gets the score assigned to the correspondence. 
     * @return the score. It ranges from 0.0 (worst score) to 1.0 (best score).
     * @see #SCORE_TOLERANCE
     */    
    float getScore();
    
}
