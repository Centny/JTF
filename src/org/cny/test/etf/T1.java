package org.cny.test.etf;

import org.cny.jtf.Stf2;
import org.cny.test.SampleBEnt;
import org.cny.test.SampleEnt;
import org.junit.Test;

/**
 * Created by cny on 7/14/14.
 */
public class T1 extends Stf2 {

    public static final Class<?>[] ENTITES = {SampleEnt.class, SampleBEnt.class};

    public T1() {
        super(ENTITES);
    }

    @Test
    public void t1_01() {

    }

    @Test
    public void t1_02() {

    }
}
