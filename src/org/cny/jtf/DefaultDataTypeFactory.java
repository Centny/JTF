package org.cny.jtf;

import java.util.Collection;
import java.util.HashSet;

public class DefaultDataTypeFactory extends org.dbunit.dataset.datatype.DefaultDataTypeFactory {

    @Override
    public Collection getValidDbProducts() {
        Collection cls = new HashSet();
        cls.addAll(super.getValidDbProducts());
        cls.add("H2");
        cls.add("Oracle");
        return cls;
    }
}
