package org.cny.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Centny
 */
public final class JndiTools {

    /**
     * Perform a method to get the facade.
     *
     * @param <T>  the target facade.
     * @param name the facade name.
     * @return the instance of facade.
     */
    @SuppressWarnings("unchecked")
    public static <T> T local(final String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("the jndi name is null  or empty");
        }
        try {
            InitialContext ic = null;
            Object facade = null;
            String jndi = "";
            ic = new InitialContext();
            jndi = SystemConfig.Cfg().getProperty("JNDI_N").replace("$(NAME)", name);
            facade = ic.lookup(jndi);
            return (T) facade;
        } catch (final NamingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * get the data source.
     *
     * @return DataSource.
     */
    public static DataSource ds() {
        try {
            InitialContext ic = new InitialContext();
            String jndi = SystemConfig.Cfg().getProperty("DS_N");
            return (DataSource) ic.lookup(jndi);
        } catch (final NamingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Connection connection() throws SQLException {
        return ds().getConnection();
    }
//    public static <T> T remote(final String name, final Class<T> remote) {
//        if (name == null || name.trim().isEmpty()) {
//            throw new IllegalArgumentException("the jndi name is null  or empty");
//        }
//        if (remote == null) {
//            throw new IllegalArgumentException("the class of session bean interface is null");
//        }
//        InitialContext ic = null;
//        Object facade = null;
//
//        String jndi = "";
//        try {
//            Properties properties = new Properties();
//            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
//            properties.setProperty(Context.PROVIDER_URL, "t3://localhost:7001");
//            ic = new InitialContext(properties);
//            jndi = "java:global._auto_generated_ear_." + PROJECT_NAME + "." + name;
//
//            facade = ic.lookup(jndi);
//            return (T) facade;
//        } catch (final NamingException e) {
//            // e.printStackTrace();
//            throw new IllegalArgumentException(e);
//        }
//    }

    /**
     * private constructor.
     */
    private JndiTools() {

    }

    private static JndiTools _tools = new JndiTools();

    /**
     * static instance.
     *
     * @return static instance
     */
    public static JndiTools tools() {
        return _tools;
    }
}
