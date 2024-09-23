import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by (type 'x' to quit):", actionChoices);

            if (actionChoice == null) {
                break;
            } else if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    // BONUS MISSION - Alphabetically sorts the strings in ArrayList 'results'
                    results.sort(null);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term:");

                // ORIGINAL CODE
//                String searchTerm = in.nextLine();


                // Removed Original code - Added a new String variable for Input search term
                String strSearchTermInput = in.nextLine();




                /* CT NOTES - Current Idea for Task 3 - Implementing Case-Insensitive 'searchTerm'
                 * Split the String 'searchTermInput' by spaces " " & will be stored into array
                 * Edit individual string in the Array to make first letter UPPERCASE and rest LOWERCASE
                 * Then combined concat() back into a SINGLE string and assign as searchTerm
                 */

                // Splits the input string by spaces " " delimeter & stored into an Array
                String[] strArraySearchTerm = strSearchTermInput.split(" ");
                ArrayList<String> strArrayList = new ArrayList<>();

                // Uses For-Each Loop to iterate each string in the String Array
                for (String strWord : strArraySearchTerm) {

                    // Capitalize first letter in the String & make the rest LOWERCASE
                    strArrayList.add(strWord.substring(0,1).toUpperCase() + strWord.substring(1).toLowerCase());
                }

                // Then combine the string with a space delimiter
                String searchTerm = String.join(" ", strArrayList);



                // ORIGINAL CODE

                // IF - "all" is selected as searchField
                if (searchField.equals("all")) {
//                    System.out.println("Search all fields not implemented yet.");


                    // THEN - Uses printJobs() to format the result of matched searchTerm by using findByValue() in JobData
                    printJobs(JobData.findByValue(searchTerm));


                    // ELSE - Uses printJobs() to print out results based on 'searchField' & 'searchTerm'
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        int choiceIdx = -1;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        int i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (int j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            if (in.hasNextInt()) {
                choiceIdx = in.nextInt();
                in.nextLine();
            } else {
                String line = in.nextLine();
                boolean shouldQuit = line.equals("x");
                if (shouldQuit) {
                    return null;
                }
            }

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

//        System.out.println("printJobs is not implemented yet");


        // Using IF-ELSE statement to print results - IF 'someJobs' ArrayList contains a match then the size would be > 0
        if (someJobs.size() > 0) {

            // Added Nested For-Each Loops to print each entry in the Hashmap
            for (HashMap<String, String> hashMapJob : someJobs) {

                System.out.println("\n*****");

                for (Map.Entry<String, String> jobEntry : hashMapJob.entrySet()) {
                    System.out.println(jobEntry.getKey() + ": " + jobEntry.getValue());
                }

                System.out.println("*****");
            }

            // If 'someJobs' ArrayList has a size of 0
        } else {
            System.out.print("No Results");
        }

    }
}
