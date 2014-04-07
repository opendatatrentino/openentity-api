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
package eu.trentorise.opendata.semantics.model.provenance;

import java.util.Date;

/**
 *
 * @author Moaz
 */
public class Evidence {

    /** The date of the import process */
    private Date modificationDate;
    /** The user who executes the import process */
    private String userName;
    /** The URL of the dataset which was used during the import  */
    private String URL;
    /** The name of the identifier property in the dataset, e.g. "Codice Fiscale" */
    private String propertyNameID;
    /** The value of the identifier property in the dataset, e.g. "MHM34389C10" */
    private String propertyValueID;

    /**
     * @return the modificationDate
     */
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * @param modificationDate the modificationDate to set
     */
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * @param URL the URL to set
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * @return the PropertyNameID
     */
    public String getPropertyNameID() {
        return propertyNameID;
    }

    /**
     * @param PropertyNameID the PropertyNameID to set
     */
    public void setPropertyNameID(String PropertyNameID) {
        this.propertyNameID = PropertyNameID;
    }

    /**
     * @return the propertyValueID
     */
    public String getPropertyValueID() {
        return propertyValueID;
    }

    /**
     * @param propertyValueID the propertyValueID to set
     */
    public void setPropertyValueID(String propertyValueID) {
        this.propertyValueID = propertyValueID;
    }
}
