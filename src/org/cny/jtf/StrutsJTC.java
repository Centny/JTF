package org.cny.jtf;

import org.apache.struts2.StrutsJUnit4TestCase;

/**
 * Created by cny on 7/16/14.
 */
public class StrutsJTC<T> extends StrutsJUnit4TestCase<T> {

    protected void initServletMockObjects() {
        resourceLoader = new DefaultResourceLoader();
        super.initServletMockObjects();
    }
}
