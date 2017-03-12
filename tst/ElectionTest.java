import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by NickNatali on 3/6/17.
 * Test the election class
 */
public class ElectionTest {

    @Test
    public void eliminateTest(){
        //Arrange
        Election election = new Election();

        //Act
        election.addPollingPlace("ballots-kent.txt");
        election.accountForVotes("ballots-kent.txt");
        election.closeElection();
        election.eliminate();

        //Assert
        Assert.assertEquals("Candidate not eliminated", true, election.getCandidates().get(3).isEliminated());
        Assert.assertNotEquals("Candidate not eliminated", false, election.getCandidates().get(3).isEliminated());
    }

    @Test
    public void testGettersAndSetters() {
        //Arrange
        String FILENAME = "file.txt";
        String partyName = "DEM";
        String candidateName = "John";
        ArrayList<Candidate> candidates = new ArrayList<>();
        ArrayList<Poll> polls = new ArrayList<>();

        //Act
        Election election = new Election();
        Poll poll = new Poll(FILENAME);
        polls.add(poll);
        Candidate candidate = new Candidate(candidateName, partyName);
        candidates.add(candidate);
        election.setCandidates(candidates);
        election.setPolls(polls);


        //Assert
        Assert.assertEquals("Incorrect candidates", candidates, election.getCandidates());
        Assert.assertEquals("Incorrect poll", polls, election.getPolls());

        //!Assert
        Assert.assertNotEquals("Correct candidates", "John", election.getCandidates());
        Assert.assertNotEquals("Correct poll", "John", election.getPolls());
    }

    @Test
    public void isThereMajorityTest(){
        //Arrange
        Election election = new Election();

        //Act
        election.addPollingPlace("ballots-kent.txt");
        election.accountForVotes("ballots-kent.txt");
        election.closeElection();
        election.eliminate();

        //Assert
        Assert.assertEquals("Not correct amount of votes", false, election.isThereMajority());
        Assert.assertNotEquals("Not correct amount of votes", true, election.isThereMajority());
        election.closeElection();
        Assert.assertEquals("Not correct amount of votes", false, election.isThereMajority());
        Assert.assertNotEquals("Not correct amount of votes", true, election.isThereMajority());
    }
}
