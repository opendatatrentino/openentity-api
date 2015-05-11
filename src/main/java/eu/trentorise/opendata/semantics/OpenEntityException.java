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
        super(s, ex);        
    }

    OpenEntityException(Exception ex) {
        super(ex); 
    }
}
