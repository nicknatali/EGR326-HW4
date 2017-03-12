import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NickNatali on 3/6/17.
 * Test the Poll Class
 */
public class PollTest {

    //Test the constructor
    @Test
    public void constructorTest() {
        //Arrange
        String FILENAME = "ballots-kent.txt";
        ArrayList<String> votesFromLocalPoll = new ArrayList<>();

        //Act
        Poll poll = new Poll(FILENAME);

        //Assert
        Assert.assertEquals("Incorrect FILENAME", FILENAME, poll.getFILENAME());
        Assert.assertNotEquals("Correct candidate Name", "candidate", poll.getFILENAME());

        //Should probably test adding it to the array list
        Assert.assertEquals("Incorrect file size", 6, poll.getvotesFromLocalPoll().size());
    }

    //Test getters and setters
    @Test
    public void gettersAndSettersTest() {
        //Arrange
        String FILENAME = "ballots-kent.txt";
        ArrayList<String> votesFromLocalPoll = new ArrayList<>();

        //Act
        Poll poll = new Poll(FILENAME);
        votesFromLocalPoll.add(FILENAME);
        poll.setvotesFromLocalPoll(votesFromLocalPoll);

        //Assert
        Assert.assertEquals("Incorrect value from votesFromLocalPolls", votesFromLocalPoll, poll.getvotesFromLocalPoll());
        Assert.assertNotEquals("Correct removal of table", "Irrelevant", poll.getvotesFromLocalPoll());
    }

    //Test distributeVotes method
    @Test
    public void distributeVotesTest() {
        //Arrange
        String FILENAME = "ballots-kent.txt";
        ArrayList<String> votesFromLocalPoll = new ArrayList<>();
        Election election = new Election();
        Poll poll = new Poll(FILENAME);

        //Act
        election.addPollingPlace(poll);
        election.accountForVotes(FILENAME);

        //Assert
        Assert.assertEquals("Incorrect FILENAME", FILENAME, poll.getFILENAME());
        Assert.assertNotEquals("Correct candidate Name", "candidate", poll.getFILENAME());

        //Should probably test adding it to the array list
        Assert.assertEquals("Incorrect amount of votes", 2, election.getCandidates().get(0).getVotes());
        Assert.assertEquals("Incorrect amount of votes", 2, election.getCandidates().get(1).getVotes());
        Assert.assertEquals("Incorrect amount of votes", 1, election.getCandidates().get(2).getVotes());
        Assert.assertNotEquals("Incorrect amount of votes", 0, election.getCandidates().get(0).getVotes());
        Assert.assertNotEquals("Incorrect amount of votes", 0, election.getCandidates().get(1).getVotes());
        Assert.assertNotEquals("Incorrect amount of votes", 0, election.getCandidates().get(2).getVotes());
    }

    //Test compareTo method
    @Test
    public void compareToTest() {
        //Arrange
        String fileName = "ballots-kent.txt";
        String fileName2 = "candidates.txt";
        ArrayList<String> votesFromLocalPoll = new ArrayList<>();
        ArrayList<String> votesFromLocalPoll2 = new ArrayList<>();
        votesFromLocalPoll.add("John");
        votesFromLocalPoll.add("John");
        votesFromLocalPoll.add("John");
        votesFromLocalPoll2.add("James");
        votesFromLocalPoll2.add("James");

        //Act
        Poll poll = new Poll(fileName);
        Poll poll2 = new Poll(fileName2);
        poll.setvotesFromLocalPoll(votesFromLocalPoll);
        poll2.setvotesFromLocalPoll(votesFromLocalPoll2);

        //Assert
        Assert.assertEquals("Incorrect poll is deemed larger", 1, poll2.compareTo(poll));

        //!Assert
        Assert.assertNotEquals("Objects are comparable", -1, poll2.compareTo(poll));
    }


    //Test equals and hashcode test
    @Test
    public void equalsTest() {
        //Arrange
        String fileName = "ballots-kent.txt";
        boolean isEqual = true;

        //Act
        Poll poll = new Poll(fileName);
        Poll poll2 = new Poll(fileName);


        //Assert
        Assert.assertEquals("The two objects are equal", isEqual, poll.equals(poll2));
        Assert.assertEquals("The two objects are equal", isEqual, poll2.equals(poll));
    }


    /**
     * Test clone method
     */
    @Test
    public void cloneTest() throws CloneNotSupportedException  {
        //Arrange
        String fileName = "ballots-kent.txt";

        //Act
        Poll poll = new Poll(fileName);
        Poll poll2 = poll.clone();

        //Assert
        Assert.assertEquals(poll, poll2);
        Assert.assertNotSame(poll, poll2);
    }

}
