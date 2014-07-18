package org.cny.test;


import com.opensymphony.xwork2.ActionProxy;
import org.apache.openejb.api.LocalClient;
import org.cny.jtf.*;
import org.cny.jtf.z.ZLocal;
import org.cny.util.JndiTools;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author Centny.
 */
@LocalClient
public class JtfTest extends Jtf {

    @Before
    public void importTestData() throws Exception {
        InputStream input = this.getClass().getResourceAsStream("sample2.xls");
        super.importData(input);
    }

    @Test
    public void testData() throws Exception {
        // assert the data in database.
        List<SampleBEnt> dataes;
        dataes = this.em.createQuery(
                "select s from SAMPLE_B s").getResultList();
        Assert.assertEquals(30, dataes.size());
        dataes = this.em.createQuery(
                "select s from SAMPLE_B s where s.name='mail'")
                .getResultList();
        Assert.assertEquals(1, dataes.size());
        Assert.assertEquals("value8", dataes.get(0).getValue());
        System.err.println("end");
    }

    @Test
    public void testData2() {
        // assert the data in database.
        List<SampleBEnt> dataes;
        dataes = this.em.createQuery(
                "select s from SAMPLE_B s").getResultList();
        Assert.assertEquals(30, dataes.size());
        dataes = this.em.createQuery(
                "select s from SAMPLE_B s where s.name='mail'")
                .getResultList();
        Assert.assertEquals(1, dataes.size());
        Assert.assertEquals("value8", dataes.get(0).getValue());
    }

    @Test
    public void testAction() throws Exception {
        ActionProxy ap = this.getActionProxy("/api/z");
        ap.execute();
    }

    @Test
    public void testNormal() throws Exception {
        new ExportData();
        super.importData(new File("data/sample.xls"));
        super.importData("data/sample.xls");
        super.importData("data/sample.xls", TCtx.schema());
        super.importData(new File("data/sample.xls"), TCtx.schema());
        String schema = TCtx.schema();
        try {
            TCtx.CFG.setProperty("etf.schema", "");
            super.importData("data/sample.xls");
        } catch (Exception e) {

        }
        TCtx.CFG.setProperty("etf.schema", schema);
        this.getDataSource();
        this.getEntityManager();
        this.getTransaction();

        try {
            FileInputStream fis = null;
            super.importData(fis, "");
        } catch (Exception e) {

        }
    }

    //    @PersistenceContext
//    private EntityManager emm;
////
//    @EJB
//    ZLocal z;
//
//    @Before
//    public void bbbbb() {
//        TCtx.initCtx(this);
//    }

//    @Test
//    public void testNormal2() throws Exception {
////        z.show();
//    }

    //
//
    //    private EntityManager emm;
////
    @EJB
    ZLocal z;

    @Test
    public void testNormal3() throws Exception {
        try {
            TCtx.loadCFG("jjjjj");
        } catch (Exception e) {

        }
        TCtx.loadCFG("jtf.properties");
        //
        this.bind(this);
        this.begin();
        z.show();
        this.commit();
        try {
            this.begin();
            this.begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.tx.commit();
            this.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.begin();
        this.commit();
        try {
            this.bind(null);
        } catch (Exception e) {

        }
        TCtx.initCtx(this);
        try {
            TCtx.initCtx(null);
        } catch (Exception e) {

        }
    }

    @Test
    public void testJndi() throws Exception {
        JndiTools.tools();

        try {
            JndiTools.local("jjjjj");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JndiTools.local(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
