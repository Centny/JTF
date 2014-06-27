package org.cny.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Centny
 */
public class SystemConfig extends Properties {
    /**
     * configure file name.
     */
    final public static String CFG_F = "conf.properties";

    private static SystemConfig _cfg = null;

    /**
     * get the static instance.
     *
     * @return
     */
    public static SystemConfig Cfg() {
        if (_cfg == null) {
            _cfg = new SystemConfig();
            _cfg.load();
        }
        return _cfg;
    }

    private void load() {
        try {
            this.putAll(System.getenv());
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(CFG_F);
            if (in != null) {
                this.load(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
