import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Poll implements Comparable{
    //Variables
    private final String FILENAME;
    private ArrayList<String> votesFromLocalPoll = new ArrayList<>();

    /**
     * Constructor
     * @param FILENAME name of the file that's supplying information
     */
    public Poll(String FILENAME){
        this.FILENAME = FILENAME;
        //Read in votes from file
        try {
            //String to store the line from the file
            List<String> textData = Files.readAllLines(Paths.get(FILENAME));
            //Iterate over each line of candidate preferences from the file
            for (String line : textData) {
                this.votesFromLocalPoll.add(line);
            }
        }
        catch(java.io.IOException e){
            System.out.println("404 Poll not found ;)");
        }
    }

    /**
     * Gets FILENAME
     */
    public String getFILENAME() {return FILENAME;}
    /**
     * Gets votes from local poll
     */
    public ArrayList<String> getvotesFromLocalPoll() {return votesFromLocalPoll;}
    /**
     * Sets vote from local poll
     * @param votesFromLocalPoll new list of votes from a local poll
     */
    public void setvotesFromLocalPoll(ArrayList<String> votesFromLocalPoll) {
        this.votesFromLocalPoll = votesFromLocalPoll;
    }

    /**
     * Core functionality of the Poll class - distributes votes to candidates
     * @param candidates used to add votes to candidates
     *                   and store candidates from Election class
     */
    public void distributeVotes(ArrayList<Candidate> candidates) {
        //Gets array of candidates from Election class and adds votes to candidates
        //and can create a new candidate if they are not on the list
        //Once election is created, find the given candidates
        for (String line : this.votesFromLocalPoll) {
            //Iterate over each candidate a person listed in order of preference
            for(String candidatePref : line.split(",")){
                boolean voteHasBeenCast = false;
                //Set a null candidate in the event that a fill in is added
                Candidate fillIn = null;
                //Iterate over each candidate running and apply vote where applicable
                for(Candidate candidate : candidates){
                    if(candidate.getName().equals(candidatePref) && !candidate.isEliminated()){
                        //If candidates name matches the vote cast, then add a vote to them
                        candidate.addVote();
                        voteHasBeenCast = true;
                        break;
                    } else if(!candidate.isEliminated() && isSpeltIncorrectly(candidate.getName(), candidatePref)){
                        //If candidates name is close enough, then add a vote to them
                        candidate.addVote();
                        voteHasBeenCast = true;
                        break;
                    } else if(candidate.getName().equals(candidatePref) && candidate.isEliminated()){
                        break;
                    } else {
                        //Loop through all candidates if there is a new match and see if it is a fill in
                        boolean candidateExists = false;
                        for(Candidate eachCand : candidates){
                            if(eachCand.getName().equals(candidatePref)) {
                                candidateExists = true;
                                break;
                            }else if(isSpeltIncorrectly(eachCand.getName(),candidatePref)) {
                                candidateExists = true;
                                break;
                            }
                        }
                        //If it is a fill in, create a new candidate
                        if(!candidateExists) {
                            fillIn = new Candidate(candidatePref, "UNK");
                            fillIn.addVote();
                            voteHasBeenCast = true;
                            break;
                        }
                    }
                }
                //If fill in has been set then add it to the candidates list
                if(fillIn != null){
                    candidates.add(fillIn);
                }
                //Check if vote has been cast and stop loop if it has been
                if(voteHasBeenCast)
                    break;
            }
        }
    }

    /**
     * Checks to see whether a given word is spelt incorrectly
     * @param a - used to compare strings
     * @param b - used to compare strings
     * @return true or false depending on if a word is spelt correctly
     */
    public boolean isSpeltIncorrectly(String a, String b){
        //Based on the definition for Levenshtein distance at : http://rosettacode.org/wiki/Levenshtein_distance#Java
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        //Return result of whether string is misspelled
        if(costs[b.length()] <= 3)
            return true;
        else
            return false;
    }

    /**
     * Function to compare two votes from local polling places
     * @return the difference between the number of votes
     */
    @Override
    public int compareTo(Object o) {
        if(this.getClass() == o.getClass() && o != null){
            Poll other = (Poll) o;
            return other.votesFromLocalPoll.size() - this.votesFromLocalPoll.size();
        }
        return 0;
    }

    /**
     * Function to clone this Poll
     * @return a clone of the Poll object
     */
    @Override
    protected Poll clone() throws CloneNotSupportedException {
        return new Poll(this.FILENAME);
    }

    /**
     * Function to compare whether two Polls are equal by using their filenames
     *
     * @return whether the filename is equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() == obj.getClass() && obj != null){
            Poll other = (Poll) obj;
            return this.FILENAME.equals(other.FILENAME);
        }
        return false;
    }
}
