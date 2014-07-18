package org.cny.test;


import com.opensymphony.xwork2.ActionProxy;
import org.cny.jtf.ExportData;
import org.cny.jtf.Jtf;
import org.cny.jtf.TCtx;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author Centny.
 */
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

        this.begin();
        this.commit();
        try {
            this.begin();
            this.begin();
        } catch (Exception e) {

        }
        try {
            this.tx.commit();
            this.commit();
        } catch (Exception e) {

        }
    }

    @Test
    public void testNormal2() throws Exception {
        try {
            TCtx.initCtx(new Object());
        } catch (Exception e) {

        }
        TCtx.initCtx(new TestA());
        TCtx.loadCFG("jjjjj");
    }

    @ManagedBean
    public static class TestA {
        @PersistenceContext
        EntityManager em;
    }
}
