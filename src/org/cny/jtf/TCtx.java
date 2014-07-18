package org.cny.jtf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by cny on 7/16/14.
 */
public class TCtx {
    private static Logger log = LoggerFactory.getLogger(Jtf.class);
    public static Properties CFG;

    static {
        loadCFG("jtf.properties");
    }

    public static void loadCFG(String pf) {
        try {
            InputStream input = Jtf.class.getClassLoader().getResourceAsStream(pf);
            CFG = new Properties();
            CFG.load(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static EJBContainer C;
    static Context CTX;

    public static EJBContainer Ctn() {
        if (C == null) {
            C = EJBContainer.createEJBContainer(CFG);
            show();
        }
        return C;
    }

    public static Context Ctx() {
        if (CTX == null) {
            CTX = Ctn().getContext();
        }
        return CTX;
    }

    public static final void initCtx(Object o) {
        try {
            if (o instanceof ResVisiblable) {
                ResVisiblable rv = (ResVisiblable) o;
                Ctx();//initial.
                ResLocal res = (ResLocal) new InitialContext().lookup("ResLocal");
                rv.setRes(res);
            } else {
                Ctx().bind("inject", o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void show() {
        try {
            InitialContext ctx = new InitialContext();
            NamingEnumeration<NameClassPair> ne = ctx.list("");
            StringBuffer sb = new StringBuffer();
            while (ne.hasMore()) {
                NameClassPair ncp = ne.next();
                Object o = ctx.lookup(ncp.getName());
                sb.append("\t" + ncp.getName() + ":  " + o + "\n");
            }
            log.info("list JNDI:\n" + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String schema() {
        return CFG.getProperty("etf.schema");
    }
}
