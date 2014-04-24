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

package eu.trentorise.opendata.semantics;

/**
 * Common exception for errors raised in OpenEntity library. 
 * @author David Leoni
 */
public class OpenEntityException extends RuntimeException {    
    public OpenEntityException(String s){
        super(s);
    }

    public OpenEntityException(String s, Exception ex) {        
        super(s + " - " + ex.getMessage(), ex);        
    }

    OpenEntityException(Exception ex) {
        this(ex.getMessage(), ex); 
    }
}
