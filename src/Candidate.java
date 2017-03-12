public class Candidate implements Comparable{
    //Variables
    private String name;
    private Party party;
    private int votes = 0;
    private boolean isEliminated = false;

    /**
     * Constructor
     * @param name name of the candidate
     * @param party name of the party
     */
    public Candidate(String name, String party){
        this.name = name;
        this.party = Party.fromString(party);
    }

    /**
     * Getters and setters
     */
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Party getParty() {return party;}
    public void setParty(Party party) {this.party = party;}

    public int getVotes() {return votes;}
    public void setVotes(int votes) {this.votes = votes;}

    /**
     * Add a votes to the amount of votes
     */
    public void addVote(){
        this.votes += 1;
    }

    /**
     * Reset the votes to zero
     */
    public void resetVotes(){
        this.votes = 0;
    }

    /**
     * Return whether a candidate has been eliminated
     * @return a boolean value
     */
    public boolean isEliminated() {return isEliminated;}

    /**
     * Set the boolean value if a candidate has been eliminated
     * @param eliminated - true or false
     */
    public void setEliminated(boolean eliminated) {isEliminated = eliminated;}

    /**
     * Function to compare two candidate's votes
     *
     * @return the difference between the number of votes
     */
    @Override
    public int compareTo(Object o) {
        if(this.getClass() == o.getClass() && o != null){
            Candidate other = (Candidate) o;
            if(other.votes != this.votes)
                return other.votes - this.votes;
            else
                return other.getName().compareTo(this.getName());
        }
        return 0;
    }

    /**
     * Function to clone this Candidate.
     * @return a clone of the Candidate object
     */
    @Override
    protected Candidate clone() throws CloneNotSupportedException {
        return new Candidate(this.name, this.party.toString());
    }

    /**
     * Function to compare whether two Candidates are equal by using their name and party
     * @return whether the candidates are the same
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() == obj.getClass() && obj != null){
            Candidate other = (Candidate) obj;
            return this.name.equals(other.name) && this.party == other.party;
        }
        return false;
    }
}
