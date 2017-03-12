import org.junit.Assert;
import org.junit.Test;

/**
 * Created by NickNatali on 3/6/17.
 * Test the Party Class
 */
public class PartyTest {

    /**
     * Test the toShortName method
     */
    @Test
    public void fromStringTest() {
        Assert.assertEquals(Party.REP, Party.fromString("REP"));
        Assert.assertEquals(Party.DEM, Party.fromString("DEM"));
        Assert.assertEquals(Party.LIB, Party.fromString("LIB"));
        Assert.assertEquals(Party.GRN, Party.fromString("GRN"));
        Assert.assertEquals(Party.CNS, Party.fromString("CNS"));
        Assert.assertEquals(Party.TEA, Party.fromString("TEA"));
        Assert.assertEquals(Party.ARR, Party.fromString("ARR"));
        Assert.assertEquals(Party.UNK, Party.fromString("UNK"));

        //Negative tests
        Assert.assertNotEquals("Not correct Party", Party.DEM, Party.fromString("REP"));
        Assert.assertNotEquals("Not correct Party", Party.REP, Party.fromString("DEM"));
        Assert.assertNotEquals("Not correct Party", Party.LIB, Party.fromString("GRN"));
        Assert.assertNotEquals("Not correct Party", Party.TEA, Party.fromString("LIB"));
        Assert.assertNotEquals("Not correct Party", Party.LIB, Party.fromString("TEA"));
        Assert.assertNotEquals("Not correct Party", Party.TEA, Party.fromString("CNS"));
        Assert.assertNotEquals("Not correct Party", Party.CNS, Party.fromString("UNK"));
        Assert.assertNotEquals("Not correct Party", Party.UNK, Party.fromString("ARR"));
    }

    /**
     * Test the to String method
     */
    @Test
    public void toStringTest(){
        //Asserts
        Assert.assertEquals("REP", Party.REP.toString());
        Assert.assertEquals("DEM", Party.DEM.toString());
        Assert.assertEquals("LIB", Party.LIB.toString());
        Assert.assertEquals("GRN", Party.GRN.toString());
        Assert.assertEquals("CNS", Party.CNS.toString());
        Assert.assertEquals("TEA", Party.TEA.toString());
        Assert.assertEquals("ARR", Party.ARR.toString());
        Assert.assertEquals("UNK", Party.UNK.toString());

        //Negative tests
        Assert.assertNotEquals("Not correct Party","DEM", Party.REP.toString());
        Assert.assertNotEquals("Not correct Party","REP", Party.DEM.toString());
        Assert.assertNotEquals("Not correct Party","LIB", Party.GRN.toString());
        Assert.assertNotEquals("Not correct Party","GRN", Party.LIB.toString());
        Assert.assertNotEquals("Not correct Party","CNS", Party.TEA.toString());
        Assert.assertNotEquals("Not correct Party","TEA", Party.CNS.toString());
        Assert.assertNotEquals("Not correct Party","ARR", Party.UNK.toString());
        Assert.assertNotEquals("Not correct Party","", Party.ARR.toString());
    }
}
