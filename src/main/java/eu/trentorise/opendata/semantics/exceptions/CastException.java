package eu.trentorise.opendata.semantics.exceptions;

import eu.trentorise.opendata.semantics.model.entity.AttrType;
import java.util.Locale;

/**
 * odr new
 * @author David Leoni
 */
public class CastException extends OpenEntityException {

    private static String makeMessage(Object value, AttrType attrType, Locale locale, String reason){
        String javaclass = value == null ? "" : " of Java class " + value.getClass().getName();
        
        return makeMessage(value, attrType.toString(), locale, reason);
    }
    
    private static String makeMessage(Object value, String type, Locale locale, String reason){
        String javaclass = value == null ? "" : " of Java class " + value.getClass().getName();
        
        return "Couldn't cast value " + value + javaclass + "  to type " + type + " (with locale " + locale + "). \nREASON: " + reason;
    }    
    
    public CastException(Object value, AttrType attrType, Locale locale, String reason) {
        super(makeMessage(value, attrType, locale, reason));
    }

    public CastException(Object value, AttrType attrType, Locale locale, String reason, Exception ex) {
       super(makeMessage(value, attrType, locale, reason), ex);
    }
    
    public CastException(Object value, String type, Locale locale, String reason) {
        super(makeMessage(value, type, locale, reason));
    }

    public CastException(Object value, String type, Locale locale, String reason, Exception ex) {
       super(makeMessage(value, type, locale, reason), ex);
    }
    
}
