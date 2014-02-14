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

import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.model.knowledge.INLString;
import eu.trentorise.opendata.semantics.model.knowledge.ISString;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to model Entitypedia data types. They are mapped to Java objects
 * according to the following sheme:
 *
 * STRING : String <br/>
 * BOOLEAN : Boolean <br/>
 * DATE : Date <br/>
 * INTEGER : Integer <br/>
 * FLOAT : Float <br/>
 * LONG : Long <br/>
 * CONCEPT : IConcept <br/>
 * SSTRING : ISString <br/>
 * NLSTRING : INLString <br/>
 * COMPLEX_TYPE : IEntity <br/>
 *
 * @author Ivan Tankoyeu tankoyeu@disi.unitn.it
 * @author David Leoni <david.leoni@trentorise.eu>
 * @date Feb 14, 2014
 *
 */
public class DataTypes {

    public static final String STRING = "STRING";
    public static final String BOOLEAN = "BOOLEAN";
    public static final String DATE = "DATE";
    public static final String INTEGER = "INTEGER";
    public static final String FLOAT = "FLOAT";
    public static final String LONG = "LONG";
    public static final String CONCEPT = "CONCEPT";
    public static final String SSTRING = "SSTRING";
    public static final String NLSTRING = "NLSTRING";
    public static final String COMPLEX_TYPE = "COMPLEX_TYPE";

    /**
     * Provides a map of the supported datatypes. Each datatype name is mapped
     * to the java class or interface that represents it.
     *
     * @return a set of the supported data types
     */
    public static Map<String, Class> getDataTypes() {

        Map ret = new HashMap<String, Class>();
        ret.put(STRING, String.class);
        ret.put(BOOLEAN, Boolean.class);
        ret.put(DATE, Date.class);
        ret.put(INTEGER, Integer.class);
        ret.put(FLOAT, Float.class);
        ret.put(LONG, Long.class);
        ret.put(CONCEPT, IConcept.class);
        ret.put(SSTRING, ISString.class);
        ret.put(NLSTRING, INLString.class);
        ret.put(COMPLEX_TYPE, IEntity.class);
        return ret;
    }

}
