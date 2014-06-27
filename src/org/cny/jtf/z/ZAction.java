package org.cny.jtf.z;

import com.opensymphony.xwork2.ActionSupport;
import org.cny.util.JndiTools;

public class ZAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        ZLocal l = JndiTools.local("Z");
        l.show();
        return super.execute();
    }
}
