import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Election {
    //Variables
    ArrayList<Candidate> candidates = new ArrayList<Candidate>() {
        public boolean add(Candidate x) {
            super.add(x);
            Collections.sort(candidates);
            return true;
        }
    };
    ArrayList<Poll> polls = new ArrayList<>();
    private boolean isClosed = false;

    /**
     * Constructor
     */
    public Election(){
        //Once election is created, find the given candidates
        try {
            //String to store the line from the file
            List<String> textData = Files.readAllLines(Paths.get("./candidates.txt"));
            //Iterate of the broken up text data and create new tables from it
            for (String each : textData) {
                String[] attributes = each.split(",");
                candidates.add(new Candidate(attributes[0], attributes[1]));
            }
        }
        catch(java.io.IOException e){
            System.out.println("Candidates list not found! Exiting program!");
            System.exit(0);
        }
    }


    /**
     * Returns candidates from an arraylist.
     * @return candidates
     */
    public ArrayList<Candidate> getCandidates() {
        Collections.sort(candidates);
        return candidates;
    }

    /**
     * Sets the candidates
     * @param candidates - the candidates to set
     */
    public void setCandidates(ArrayList<Candidate> candidates) {this.candidates = candidates;}

    /**
     * Get the polls from an arraylist
     * @return polls
     */
    public ArrayList<Poll> getPolls() {return polls;}

    /**
     * Sets the polls
     * @param polls - the polls that were set.
     */
    public void setPolls(ArrayList<Poll> polls) {this.polls = polls;}

    /**
     * Returns if an election was closed.
     * @return a boolean
     */
    public boolean isClosed() {return isClosed;}


    /**
     * @param pollName - the polling place to be added
     * @return if the polling place was added or not
     */
    public boolean addPollingPlace(String pollName){
        Poll poll = new Poll(pollName);
        return polls.add(poll);
    }

    /**
     * @param poll - the polling place to be added
     * @return if the polling place was added or not
     */
    public boolean addPollingPlace(Poll poll){
        return polls.add(poll);
    }

    /**
     * Eliminate a candidate from an election
     * @return a candidate that was eliminated.
     */
    public Candidate eliminate(){
        //Temp variables to store the least popular attributes
        Candidate leastPopular = candidates.get(0);
        int leastPopularVotes = candidates.get(0).getVotes();
        int leastPopularIndex = 0;
        //Loop through the candidates and find the least popular
        for(int i = 1; i < candidates.size(); i++){
            if(candidates.get(i).getVotes() < leastPopularVotes && !candidates.get(i).isEliminated()){
                leastPopular = candidates.get(i);
                leastPopularVotes = candidates.get(i).getVotes();
                leastPopularIndex = i;
            }
        }
        //Set candidate as eliminated
        candidates.get(leastPopularIndex).setEliminated(true);
        //Reset candidate votes
        for(Candidate each : candidates)
            each.resetVotes();
        for(Poll each : polls)
            accountForVotes(each.getFILENAME());
        return leastPopular;
    }

    /**
     * Close an election
     */
    public void closeElection(){
        isClosed = true;
    }

    /**
     * Results for specific polling places.
     * @param pollName - the specific polling place
     * @return the local election containing the results
     */
    public Election perPollingPlaceResults(String pollName){
        //Loop to find the correct poll
        for(Poll eachPoll : polls){
            if(eachPoll.equals(new Poll(pollName))){
                //Create a local election
                Election localElection = new Election();
                //Loop through candidates to change the values of the eliminated ones
                for(Candidate eachCandidate : candidates){
                    if(eachCandidate.isEliminated()){
                        for(Candidate eachLocalCandidate : localElection.getCandidates()){
                            if(eachCandidate.equals(eachLocalCandidate)){
                                eachLocalCandidate.setEliminated(true);
                            }
                        }
                    }
                }
                //Set candidate
                localElection.addPollingPlace(pollName);
                localElection.accountForVotes(pollName);
                return localElection;
            }
        }
        return null;
    }

    //Extra functionality

    /**
     * Loops through polls and distributes votes to candidates.
     * @param fileName
     */
    public void accountForVotes(String fileName){
        //Loop through polls and check for file name match
        Collections.sort(candidates);
        for(Poll each : polls) {
            if(each.getFILENAME().equals(fileName))
                each.distributeVotes(candidates);
        }
    }

    /**
     * Checks whether there is a majority for a certain candidate
     * @return a boolean value
     */
    public boolean isThereMajority(){
        for(Candidate each : candidates){
            if(each.getVotes()*2 > getTotalVotes()){
                return true;
            }
        }
        return false;
    }

    /**
     * Return the total votes
     * @return the amount of votes
     */
    public int getTotalVotes(){
        int total = 0;
        for(Candidate each : candidates){
            if(!each.isEliminated())
                total += each.getVotes();
        }
        return total;
    }

}
