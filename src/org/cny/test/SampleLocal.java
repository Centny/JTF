package org.cny.test;

import javax.ejb.Local;

/**
 * @author Centny.
 */
@Local
public interface SampleLocal {
    Object list();
}
