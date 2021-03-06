// Homework 4 (Election)
// Instructor-provided code.
// You SHOULD modify this file to make it interface with your own classes.

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents the text user interface (UI) for the restaurant
 * program, allowing the user to view and manage the restaurant and its objects.
 * 
 * @author Han
 * @version Spring 2017
 */
public final class ElectionTextUI {
	Election election;
	/**
	 * Constructs a new text user interface for managing a restaurant.
	 */
	public ElectionTextUI() {
		System.out.println("Election Vote Counter");
		election = new Election();
	}
	
	/**
	 * Displays the main menu of choices and prompts the user to enter a choice.
	 * Once a valid choice is made, initiates other code to handle that choice.
	 */
	public void mainMenu() {
		// main menu
		displayOptions();
		while (true) {
			String choice = ValidInputReader.getValidString(
					"Main menu, enter your choice:",
					"^[aAcCrRpPeEqQ?]$").toUpperCase();
			if (choice.equals("A")) {
				addPollingPlace();					// DONE! :)
			} else if (choice.equals("C")) {
				closeElection();					// DONE! :)
			} else if (choice.equals("R")) {
				results();	// DONE! :)
			} else if (choice.equals("P")) {
				perPollingPlaceResults();			// TODO:
			} else if (choice.equals("E")) {
				eliminate();						// DONE :)
			} else if (choice.equals("?")) {
				displayOptions();
			} else if (choice.equals("Q")) {
				System.out.println("Goodbye.");
				break;
			}
			System.out.println();
		}
	}
	
	// Displays the list of key commands the user can use.
	private void displayOptions() {
		System.out.println();
		System.out.println("Main System Menu");
		System.out.println("----------------");
		System.out.println("A)dd polling place");
		System.out.println("C)lose the polls");
		System.out.println("R)esults");
		System.out.println("P)er-polling-place results");
		System.out.println("E)liminate lowest candidate");
		System.out.println("?) display this menu of choices again");
		System.out.println("Q)uit");
		System.out.println();
	}
	
	// Called when P key is pressed from main menu.
	// Reads data from a new polling place.
	private void addPollingPlace() {
		if(election.isClosed()) {
			// when the election is not open,
			System.out.println("The election is closed.");
			System.out.println("No more polling places may be added.");
		}
		//Prompt user for polling location name
		String pollingPlaceInput = ValidInputReader.getValidString("Name of polling place:", "^[a-zA-Z0-9 ]+$");
		//Convert to file name format
		String pollingPlaceFileName = "./ballots-" + pollingPlaceInput.toLowerCase().replace(" ", "-") + ".txt";
		//Try to construct a file from the input name
		File f = new File(pollingPlaceFileName);
		if(!f.exists()) {
			// when the polling place is not found,
			System.out.println("No such polling place was found.");
			return;
		}
		//Add polling place to the election
		election.addPollingPlace(pollingPlaceFileName);
		//Prompt user of the added polling place
		System.out.println("Added " + pollingPlaceInput + ".");
		//add polling place's data to election totals
		election.accountForVotes(pollingPlaceFileName);
	}
	
	// Called when C key is pressed from main menu.
	// Closes the election so that no more voting can take place.
	private void closeElection() {
		System.out.println("Closing the election.");
		election.closeElection();
	}
	
	// Called when R key is pressed from main menu.
	// Shows the current results of the election.
	private void results() {
		if(!election.isClosed()) {
			// when the election is not closed,
			System.out.println("The election is still open for votes.");
			System.out.println("You must close the election before viewing results.");
			return;
		}
		// when the election is closed,
		System.out.println("Current election results for all polling places.");
		// show the current results
		System.out.println("NAME                          PARTY   VOTES     %");
		//loop through candidates in election
		for(Candidate each : election.getCandidates()){
            if(!each.isEliminated()) {
                System.out.printf("%-30s%-8s%5d%9.1f\n",
                        each.getName(),
                        each.getParty().toString(),
                        each.getVotes(),
                        (each.getVotes() / ((double) election.getTotalVotes())) * 100);
            }
		}
	}
	
	// Called when R key is pressed from main menu.
	// Shows the current results of the election.
	private void perPollingPlaceResults() {
		if(!election.isClosed()) {
			// when the election is not closed,
			System.out.println("The election is still open for votes.");
			System.out.println("You must close the election before viewing results.");
			return;
		}
		//Get user input
		String pollingPlaceInput = ValidInputReader.getValidString("Name of polling place:", "^[a-zA-Z0-9 ]+$");
		String pollingPlaceFileName = "./ballots-" + pollingPlaceInput.toLowerCase().replace(" ", "-") + ".txt";
		//If the poll exists within the election
		if(election.getPolls().contains(new Poll(pollingPlaceFileName))) {
			// when the polling place exists,
			System.out.println("Current election results for " + pollingPlaceInput + ".");
			// show the current results
			System.out.println("NAME                          PARTY   VOTES     %");
			//loop through candidates in election
			Election localElection = election.perPollingPlaceResults(pollingPlaceFileName);
			for(Candidate eachCandidate : localElection.getCandidates()){
				if(!eachCandidate.isEliminated()) {
					System.out.printf("%-30s%-8s%5d%9.1f\n",
							eachCandidate.getName(),
							eachCandidate.getParty().toString(),
							eachCandidate.getVotes(),
							(eachCandidate.getVotes() / ((double) localElection.getTotalVotes())) * 100);
				}
			}
		} else {
			// when the polling place doesn't exist,
			System.out.println("No such polling place was found.");
		}
	}

	// Called when E key is pressed from main menu.
	// Removes the candidate who has the fewest votes, and reallocates his/her
	// votes to the next available choice for those ballots.
	private void eliminate() {
        if(!election.isClosed()) {
            // when the election is not closed,
            System.out.println("The election is still open for votes.");
            System.out.println("You must close the election before eliminating candidates.");
            return;
        }

        if(election.isThereMajority()) {
            // when the election already has a winner,
            System.out.println("A candidate already has a majority of the votes.");
            System.out.println("You cannot remove any more candidates.");
            return;
        }

		// when we can eliminate a candidate,
		System.out.println("Eliminating the lowest-ranked candidate.");

		System.out.println("Eliminated " + election.eliminate().getName() + ".");
	}
	
	// This helper is just put into the text UI code to mark places where you
	// will need to add or modify this file.  Crashes with a runtime exception.
	private void crash(String message) {
		// Math.random() < 10 will always be true;  so why is it there?
		// I can't just throw because Eclipse will then warn about dead code
		// for any code that occurs after a call to crash().
		// So I wrap the exception throw in an "opaque predicate" to fool it.
		if (Math.random() < 10) {
			throw new RuntimeException("Not yet implemented: " + message);
		}
	}
}
