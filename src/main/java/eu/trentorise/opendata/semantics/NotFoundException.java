package eu.trentorise.opendata.semantics;

/**
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 8, 2014
 */
public class NotFoundException extends OpenEntityException {


    public NotFoundException(Exception ex) {
        super(ex);
    }

    public NotFoundException(String s, Exception ex) {
        super(s, ex);
    }

    public NotFoundException(String s) {
        super(s);
    }
}
