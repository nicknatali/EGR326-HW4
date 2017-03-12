import org.junit.Assert;
import org.junit.Test;

/**
 * Created by NickNatali on 3/6/17.
 * Test the candidate class
 */
public class CandidateTest {

    /**
     * Test the constructor
     */
    @Test
    public void constructorTest() {
        //Arrange
        String candidateName = "Obama";
        String partyName = "DEM";

        //Act
        Candidate candidate = new Candidate(candidateName, partyName);
        Party party = Party.DEM;

        //Assert
        Assert.assertEquals("Incorrect candidate name", candidateName, candidate.getName());
        Assert.assertNotEquals("Correct candidate Name", "Trump", candidate.getName());

        Assert.assertEquals("Incorrect party name", party, candidate.getParty());
        Assert.assertNotEquals("Correct party name", "REP", candidate.getParty());
    }

    /**
     * Test getters
     */
    @Test
    public void gettersTest() {
        //Arrange
        String candidateName = "Obama";
        String partyName = "DEM";
        int votes = 0;

        //Act
        Candidate candidate = new Candidate(candidateName, partyName);
        Party party = Party.DEM;

        //Assert
        Assert.assertEquals("Incorrect candidate name", candidateName, candidate.getName());
        Assert.assertNotEquals("Correct candidate Name", "Trump", candidate.getName());

        Assert.assertEquals("Incorrect party name", party, candidate.getParty());
        Assert.assertNotEquals("Correct party name", "REP", candidate.getParty());

        Assert.assertEquals("Incorrect amount of votes", votes, candidate.getVotes());
        Assert.assertNotEquals("Correct amount of votes", 1, candidate.getVotes());
    }


    /**
     * Test setter methods
     */
    @Test
    public void setterTest() {
        //Arrange
        String candidateName = "Obama";
        String partyName = "DEM";
        int votes = 0;

        //Act
        Candidate candidate = new Candidate(candidateName, partyName);
        Party party = Party.REP;
        candidate.setEliminated(true);
        candidate.setName("Trump");
        candidate.setParty(party);
        candidate.setVotes(votes);


        //Assert
        Assert.assertEquals("Incorrect candidate name", "Trump", candidate.getName());
        Assert.assertNotEquals("Correct candidate Name", candidateName, candidate.getName());

        Assert.assertEquals("Incorrect party name", party, candidate.getParty());
        Assert.assertNotEquals("Correct party name", partyName, candidate.getParty());

        Assert.assertEquals("Incorrect amount of votes", votes, candidate.getVotes());
        Assert.assertNotEquals("Correct amount of votes", 1, candidate.getVotes());

        Assert.assertEquals("Elimination value is incorrect", true, candidate.isEliminated());
        Assert.assertNotEquals("Elimination value is correct", false, candidate.isEliminated());
    }

    /**
     * Test add and reset votes
     */
    @Test
    public void addAndResetVotesTest() {
        //Arrange
        String candidateName = "Obama";
        String partyName = "DEM";
        int votes = 1;

        //Act
        Candidate candidate = new Candidate(candidateName, partyName);
        candidate.setVotes(votes);
        candidate.resetVotes();

        //Assert
        Assert.assertEquals("Votes reset incorrectly",0, candidate.getVotes());
        Assert.assertNotEquals("Votes reset correctly", 1, candidate.getVotes());

        candidate.addVote();

        Assert.assertEquals("Votes added incorrectly",1, candidate.getVotes());
        Assert.assertNotEquals("Votes Added correctly", 0, candidate.getVotes());
    }

    /**
     * Test clone method
     */
    @Test
    public void cloneTest() throws CloneNotSupportedException  {
        //Arrange
        String candidateName = "Obama";
        String partyName = "DEM";

        //Act
        Candidate candidate = new Candidate(candidateName, partyName);
        Candidate candidate2 = candidate.clone();

        //Assert
        Assert.assertEquals(candidate, candidate2);
        Assert.assertNotSame(candidate, candidate2);
    }

    /**
     * Test the compare to method
     */
    @Test
    public void compareToTest() {
        //Arrange
        String candidateName = "Obama";
        String partyName = "DEM";

        //Act
        Candidate candidate = new Candidate(candidateName, partyName);
        Candidate candidate2 = new Candidate("Trump", "REP");
        candidate.setVotes(1);
        candidate2.setVotes(2);

        //Assert
        Assert.assertEquals("Incorrect candidate is deemed larger", 1, candidate.compareTo(candidate2));

        //!Assert
        Assert.assertNotEquals("Objects are comparable", -1, candidate.compareTo(candidate2));
    }

    /**
     * Test equals and hashcode test
     */
    @Test
    public void equalsTest() {
        //Arrange
        String candidateName = "Obama";
        String partyName = "DEM";
        boolean isEqual = true;

        //Act
        Candidate candidate = new Candidate(candidateName, partyName);
        Candidate candidate2 = new Candidate("Obama", "DEM");

        //Assert
        Assert.assertEquals("The two objects are equal", isEqual, candidate.equals(candidate2));
        Assert.assertEquals("The two objects are equal", isEqual, candidate2.equals(candidate));
    }

}
