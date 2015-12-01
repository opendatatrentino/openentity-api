/*
 * Copyright 2015 Trento Rise  (trentorise.eu) 
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
package eu.trentorise.opendata.semantics.exceptions;

import java.util.logging.Logger;

/**
 * Thrown when it is not possible to identify an object by a given identifier
 * which could be the url or the natural language name.
 * 
 * @author David Leoni
 */
public class AmbiguousIdentifierException extends OpenEntityException {

    private static final long serialVersionUID = 1L;

    private static Logger LOG = Logger.getLogger(AmbiguousIdentifierException.class.getSimpleName());

    private String identifier;

    protected AmbiguousIdentifierException() {
        super();
    }

    public AmbiguousIdentifierException(String msg, String identifier) {
        super(msg);
        setIdentifier(identifier);
    }

    public AmbiguousIdentifierException(String msg, String url, Throwable ex) {
        super(msg, ex);
        setIdentifier(url);
    }

    private void setIdentifier(String url) {
        if (url == null) {
            LOG.warning("Received null identifier, converting it to string \"null\"");
            url = "null";
        } else {
            this.identifier = url;
        }
        this.identifier = String.valueOf(url);
    }

}
