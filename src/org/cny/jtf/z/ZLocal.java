package org.cny.jtf.z;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
@Remote
public interface ZLocal {
    void show();
}
