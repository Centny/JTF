package org.cny.jtf.z;

import javax.ejb.Stateless;

@Stateless(name = "Z")
public class ZFacade implements ZLocal {
    @Override
    public void show() {
        System.out.println("Z");
    }
}
