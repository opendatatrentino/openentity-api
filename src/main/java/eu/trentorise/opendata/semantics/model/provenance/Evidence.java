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
package eu.trentorise.opendata.semantics.model.provenance;

import java.util.Date;

/**
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * 
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
