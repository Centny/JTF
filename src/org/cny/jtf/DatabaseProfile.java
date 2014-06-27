/*
 *
 * The DbUnit Database Testing Framework
 * Copyright (C)2002-2004, DbUnit.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package org.cny.jtf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The database profile configure.
 *
 * @author Centny
 */
public class DatabaseProfile {
    /**
     * the log.
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * the profile driver class name.
     */
    public static final String PROFILE_DRIVER_CLASS = "profile.driver.class";
    /**
     * the URL of the database.
     */
    public static final String PROFILE_URL = "profile.url";
    /**
     * the schema name of the database.
     */
    public static final String PROFILE_SCHEMA = "profile.schema";
    /**
     * the user name of the database.
     */
    public static final String PROFILE_USER = "profile.user";
    /**
     * the password of the database.
     */
    public static final String PROFILE_PASSWORD = "profile.password";

    /**
     * the target tables name to export or import.
     */
    public static final String PROFILE_TARGETS = "profile.targets";
    /**
     * the properties.
     */
    private final Properties _properties = new Properties();

    /**
     * the default constructor.
     */
    public DatabaseProfile() {
        super();
    }

    /**
     * @return the driver class name.
     */
    public String getDriverClass() {
        return _properties.getProperty(PROFILE_DRIVER_CLASS);
    }

    /**
     * @return the password of database.
     */
    public String getPassword() {
        return _properties.getProperty(PROFILE_PASSWORD);
    }

    /**
     * @return the schema of database.
     */
    public String getSchema() {
        return _properties.getProperty(PROFILE_SCHEMA, null);
    }

    /**
     * @return the URL of the database.
     */
    public String getURL() {
        return _properties.getProperty(PROFILE_URL);
    }

    /**
     * @return the user name of database.
     */
    public String getUser() {
        return _properties.getProperty(PROFILE_USER);
    }

    /**
     * @return the target tables name of database.
     */
    public String getTargets() {
        return _properties.getProperty(PROFILE_TARGETS);
    }

    /**
     * @param dirverClass the target driver class to database.
     * @return the database profile.
     */
    public DatabaseProfile setDriverClass(String dirverClass) {
        this._properties.setProperty(PROFILE_DRIVER_CLASS, dirverClass);
        return this;
    }

    /**
     * @param password the password of database.
     * @return the database profile.
     */
    public DatabaseProfile setPassword(String password) {
        this._properties.setProperty(PROFILE_PASSWORD, password);
        return this;
    }

    /**
     * @param schema the schema of database.
     * @return the database profile.
     */
    public DatabaseProfile setSchema(String schema) {
        this._properties.setProperty(PROFILE_SCHEMA, schema);
        return this;
    }

    /**
     * @param url the target URL of the database.
     * @return the database profile.
     */
    public DatabaseProfile setURL(String url) {
        this._properties.setProperty(PROFILE_URL, url);
        return this;
    }

    /**
     * @param user the user name of database.
     * @return the database profile.
     */
    public DatabaseProfile setUser(String user) {
        this._properties.setProperty(PROFILE_USER, user);
        return this;
    }

    /**
     * @param targets the user name of database.
     * @return the database profile.
     */
    public DatabaseProfile setTargets(String targets) {
        this._properties.setProperty(PROFILE_TARGETS, targets);
        return this;
    }

    /**
     * @param input the properties file input stream.
     * @throws java.io.IOException
     */
    public DatabaseProfile load(InputStream input) throws IOException {
        this._properties.load(input);
        return this;
    }

    /**
     * print the information.
     */
    public void print() {
        this.log.info("-------------------the profile configure"
                + "-------------------------");
        this.print(PROFILE_URL);
        this.print(PROFILE_DRIVER_CLASS);
        this.print(PROFILE_SCHEMA);
        this.print(PROFILE_USER);
        this.print(PROFILE_PASSWORD);
        this.print(PROFILE_TARGETS);
        this.log.info("----------------------------------------"
                + "----------------------------------");
    }

    /**
     * @param key the key.
     */
    private void print(String key) {
        this.log.info("the " + key + ":" + this._properties.getProperty(key));
    }
}
