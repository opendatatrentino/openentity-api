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
package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

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
    HashMap<IAttributeDef, Float> getAttrMap();
    
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
     * todo range?
     * Gets the score associated to the attribute
     * @return the score associated to the attribute
     */
    float getScore();
    
}
