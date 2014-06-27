package org.cny.jtf;

import org.cny.jtf.z.ZFacade;

public class Etf2 extends Etf<ZFacade> {

    public Etf2( Class<?>[] usedEntityBeans) {
        super(ZFacade.class, usedEntityBeans);
    }
}
