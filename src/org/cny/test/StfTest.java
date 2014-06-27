package org.cny.test;

import com.opensymphony.xwork2.ActionProxy;
import org.cny.jtf.Stf2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by cny on 6/27/14.
 */
public class StfTest extends Stf2 {
    public static final Class<?>[] ENTITES = {SampleEnt.class, SampleBEnt.class};

    public StfTest() {
        super(ENTITES);
    }

    @Before
    public void importTestData() throws Exception {
        InputStream input = this.getClass().getResourceAsStream("sample2.xls");
        super.importData(input,"cny");
    }

    @Test
    public void testAction() throws Exception {
        ActionProxy ap = this.getActionProxy("/Z");
        String res = ap.execute();
        System.out.println(res);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testData() {
        // assert the data in database.
        List<SampleBEnt> dataes;
        dataes = this.getEntityManager().createQuery(
                "select s from SAMPLE_B s").getResultList();
        Assert.assertEquals(30, dataes.size());
        dataes = this.getEntityManager().createQuery(
                "select s from SAMPLE_B s where s.name='mail'")
                .getResultList();
        Assert.assertEquals(1, dataes.size());
        Assert.assertEquals("value8", dataes.get(0).getValue());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testData2() {
        // assert the data in database.
        List<SampleBEnt> dataes;
        dataes = this.getEntityManager().createQuery(
                "select s from SAMPLE_B s").getResultList();
        Assert.assertEquals(30, dataes.size());
        dataes = this.getEntityManager().createQuery(
                "select s from SAMPLE_B s where s.name='mail'")
                .getResultList();
        Assert.assertEquals(1, dataes.size());
        Assert.assertEquals("value8", dataes.get(0).getValue());
    }

    @Test
    public void testJndi() {

    }
}
