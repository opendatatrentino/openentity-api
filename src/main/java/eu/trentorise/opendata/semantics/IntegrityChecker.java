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

import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.model.DataTypes;
import eu.trentorise.opendata.semantics.services.model.IAttributeCorrespondence;
import eu.trentorise.opendata.semantics.services.model.ISchemaCorrespondence;
import java.util.List;
import java.util.Locale;

/**
 * Class to collect checkers for implementations of Open Entity API interfaces.
 * @author David Leoni
 */
public class IntegrityChecker {
    
    private static  void checkScore(Float score){
        if (score == null){
            throw new IntegrityException("Found null score!");
        }
       float prec = 0.01f;       
       if ( score < -prec || score > 1.0f + prec){
           throw new IntegrityException("Score "+ score + " exceeds bounds [-1.01, 1.01].");
       }        
    } 

    /**
     * Checks if provided schema correspondence complies with open entity specs.
     * @param schemaCor to check
     * @throws IntegrityException if provided correspondence is not conformant to OpenEntity specs.    
     */
    
    public static void checkSchemaCorrespondence(ISchemaCorrespondence schemaCor){
        if (schemaCor == null){
            throw new IntegrityException("Schema correspondence is null!");
        }
        
        try {
            checkEntityType(schemaCor.getEtype());
        } catch (IntegrityException ex){
            throw new IntegrityException("Invalid etype in schema correspondence!", ex);
        }  

        if (schemaCor.getAttributeCorrespondences() == null){
            throw new IntegrityException("Schema Correrespondence has null correspondences!");
        }
        
        for (IAttributeCorrespondence attrCor : schemaCor.getAttributeCorrespondences()){
            checkURL(attrCor.getHeaderConceptURL());
            try {
                
                    checkScore(attrCor.getScore());
                } 
            catch (IntegrityException ex){
            
                throw new IntegrityException("Invalid score for attribute correspondence " + attrCor.getScore() + " Found score: " + attrCor.getScore(), ex);
            }
            
            if (attrCor.getColumnIndex()< 0){
                throw new IntegrityException("Attribute correspondence column index is lesss than zero. Found index: " + attrCor.getColumnIndex());
            }
            
            checkAttributeDef(attrCor.getAttrDef());
            
            if (attrCor.getAttrMap() == null){
                throw new IntegrityException("Found null attr map in IAttributeCorrespondence for etype " + schemaCor.getEtype().getURL());                                    
            }
            
            for (IAttributeDef ad : attrCor.getAttrMap().keySet()){
                try {
                    checkScore(attrCor.getAttrMap().get(ad));
                } catch (IntegrityException ex){                
                    throw new IntegrityException("Found invalid score in attribute correspondence map for attribute " + ad.getURL(), ex);   
                }                
            }
            
        }        
    }
    
    /**
     * Checks if provided dictionary complies with open entity specs.
     * @param dict the dictionary to check
     * @throws IntegrityException if provided dict is not conformant to OpenEntity specs.    
     */
    public static void checkDict(IDict dict){
        
        if (dict == null){
            throw new IntegrityException("Dict is null!");
        }
        
        if (dict.getLocales() == null){
            throw new IntegrityException("Dict has null locales!");
        }
        
        for (Locale loc : dict.getLocales()){
            List<String> tr = dict.getStrings(loc);
            if (tr == null){
                throw new IntegrityException("Dict has null translations for locale " + loc);
            }
            for (String t : tr){
                if (t == null){
                    throw new IntegrityException("Found null translation fot locale " + loc);
                }
            }
        }
            
    }
    
    /**
     * Checks if provided URL complies with open entity specs.
     * @param URL the URL to check
     * @throws IntegrityException if provided URL is not conformant to OpenEntity specs.
     */    
    public static void checkURL(String URL){
        if (URL == null){
            throw new IntegrityException("Found null URL!");
        }        
        if (URL.length() == 0){
            throw new IntegrityException("Found empty URL!");
        }
        
    }    
    
    /**
     * Checks if provided entity type complies with open entity specs.
     * @param etype the entity type to check
     * @throws IntegrityException if provided etype is not conformant to OpenEntity specs.
     */
    public static void checkEntityType(IEntityType etype){
        if (etype == null){
            throw new IntegrityException("Found null etype!");
        }
        
        try {
            checkURL(etype.getURL());
        } catch (IntegrityException ex){
            throw new IntegrityException("Invalid etype URL!", ex);
        }
        
        try {
            checkDict(etype.getName());
        } catch (IntegrityException ex){
            throw new IntegrityException("Invalid etype name!", ex);
        }  
                        
        if (etype.getConcept() == null){
            throw new IntegrityException("Found null concept for etype " + etype.getURL());
        }         
        
        /** not supported for now
        if (etype.getUniqueIndexes() == null){
            throw new IntegrityException("Found null unique indexes for etype " + etype.getURL());
        }
        */
                                      
        for (IAttributeDef attrDef : etype.getAttributeDefs()){            
            try {
                checkAttributeDef(attrDef);
            } catch (IntegrityException ex){
                throw new IntegrityException("Found invalid attr def in etype "  + etype.getURL(), ex);                
            }            
        }                
        
    }
    
    
    /**
     * Checks if provided attribute def complies with open entity specs.
     * @param attrDef the attribute definition to check
     * @throws IntegrityException if provided attrDef is not conformant to OpenEntity specs.
     */
    public static void checkAttributeDef(IAttributeDef attrDef){
        if (attrDef == null){
            throw new IntegrityException("Found null attribute def!");
        }
        if (attrDef.getURL() == null){
            throw new IntegrityException("Found null URL for attribute def " + attrDef.getName());
        }        
        if (attrDef.getURL().length() == 0){
            throw new IntegrityException("Found empty URL for attribute def " + attrDef.getName());
        }
        
        if (attrDef.getName() == null){
            throw new IntegrityException("Found null name dict for attribute def " + attrDef.getURL());
        }        
        
        try {
            checkURL(attrDef.getEtypeURL());            
        }  catch(IntegrityException ex){
            throw new IntegrityException("Found invalid etype URL for attribute def " + attrDef.getURL(), ex);
        }
        
        
        if (attrDef.getDataType() == null){
            throw new IntegrityException("Found null datatype for attribute def " + attrDef.getURL());
        }        
        if ((attrDef.getDataType().equals(DataTypes.STRUCTURE) || attrDef.getDataType().equals(DataTypes.ENTITY))){
            try {
                checkURL(attrDef.getRangeEtypeURL());
            } catch (IntegrityException ex){
                throw new IntegrityException("Attribute def " + attrDef.getURL() + " with parent etype "  + attrDef.getEtypeURL() + " is of datatype " + attrDef.getDataType() + ", but is has invalid getRangeEtypeURL()", ex);
                
            }            
        }        
        if (attrDef.getConcept() == null){
            throw new IntegrityException("Found null concept for attribute def " + attrDef.getURL());
        }         
        
    }
    
    /**
     * Checks if provided ekb complies with open entity specs.
     * @param ekb the entity knowledge base to check
     * @throws IntegrityException if provided ekb is not conformant to OpenEntity specs.
     */
    public static void checkEkbQuick(IEkb ekb){
        
        if (ekb.getDefaultLocales() == null){
            throw new IntegrityException("Found null locales list in ekb " + ekb.getClass().getCanonicalName());
        }
        
        if (ekb.getDefaultLocales().get(0) == null) {
            throw new IntegrityException("Found null first locale in ekb " + ekb.getClass().getCanonicalName());
        }                       
    }
        

}
