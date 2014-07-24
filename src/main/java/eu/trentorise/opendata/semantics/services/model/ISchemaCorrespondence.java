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
 * FOR A PARTICULAR PURPOSE. See  the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************
 */

package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import java.util.List;

/**
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @author Ivan Tankoyeu <tankoyeu@disi.unitn.it>
 * @date Apr 24, 2014
 */
public interface ISchemaCorrespondence {
   
    
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
     * Gets the score assigned to the correspondence. todo range?
     * @return the score. It ranges from 0.0 (worst score) to 1.0 (best score)
     */    
    float getScore();
    
}
