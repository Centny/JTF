package org.cny.jtf.z.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.cny.jtf.z.ZLocal;
import org.cny.util.JndiTools;

@Namespace("/api")
@ParentPackage("json-default")
public class ZAction extends ActionSupport {
    @Override
    @Action(value = "z", results = {
            @Result(name = ActionSupport.SUCCESS, type = "json", params = {"ignoreHierarchy", "false"}),
            @Result(name = ActionSupport.ERROR, type = "json", params = {"ignoreHierarchy", "false"})})
    public String execute() throws Exception {
        ZLocal l = JndiTools.local("Z");
        l.show();
        return super.execute();
    }
}
