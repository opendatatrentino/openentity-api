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
package eu.trentorise.opendata.semantics.model.knowledge;

import eu.trentorise.opendata.semantics.services.model.DataTypes;

/**
 * @author David Leoni <david.leoni@unitn.it>
 * @date 06 Oct 2014
 */
public enum MeaningKind {    
    ENTITY,
    CONCEPT;
    
    /** 
     * @param datatype either {@link DataTypes#CONCEPT} or {@link DataTypes#ENTITY}
     */
    public static MeaningKind from(String datatype){
        if (DataTypes.ENTITY.equals(datatype)){
            return ENTITY;
        }
        if (DataTypes.CONCEPT.equals(datatype)){
            return CONCEPT;
        }
        throw new IllegalArgumentException("Error while creating MeaningKind. Provided string ->" + datatype + "<- doesn't contain any supported datatype!");
    }
}
