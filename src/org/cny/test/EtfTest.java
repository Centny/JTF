package org.cny.test;

import java.io.InputStream;
import java.util.List;

import org.cny.jtf.Etf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Centny.
 */
public class EtfTest extends Etf<SampleFacade> {

    public static final Class<?>[] ENTITES = {SampleEnt.class, SampleBEnt.class};

    public EtfTest() {
        super(SampleFacade.class, ENTITES);
    }

    @Before
    public void importTestData() throws Exception {
        InputStream input = this.getClass().getResourceAsStream("sample2.xls");
        super.importData(input);
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
}
