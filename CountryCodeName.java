//Assignment 01 :- CountryCodeName 
// Author :- Pavitra Thakkar 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

 //Main Method 
public class CountryCodeName {
    private String[][] countryData;
    
    public static void main(String[] args) {
        CountryCodeName ccn = new CountryCodeName();
        ccn.init();
        Scanner scanner = new Scanner(System.in);
        String input;
        
        System.out.println("Welcome to Country Code Name. Enter \"bye\" anytime to end.");
        
        while (true) {
            System.out.print("Please enter a country code or a country name: ");
            input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye");
                break;
            }
            
            if (input.length() == 2) {
            	//Assume it is a country code
            	
                String countryName = ccn.getCountryName(input);
                if (countryName != null) {
                    System.out.println("The country name for the country code entered " + input.toUpperCase() + " is " + countryName);
                } else {
                    System.out.println("Matching string cannot be less than two letters, please enter another matching string: ");
                }
            } else {
            	//Assume it is a country name
            	
                String countryCode = ccn.getCountryCode(input);
                if (countryCode != null) {
                    System.out.println("The country code for the country name entered " + input + " is " + countryCode);
                } else {
                    System.out.println("Matching string cannot be less than two letters, please enter another matching string: ");
                }
            }
            
            while (true) {
                System.out.print("Please enter a matching string for the country names: ");
                input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye");
                    return;
                }
                
                if (input.length() < 2) {
                    System.out.println("Matching string cannot be less than two letters, please enter another matching string: ");
                    continue;
                }
                
                String[] matchedCountries = ccn.getMatchedCountries(input);
                if (matchedCountries != null && matchedCountries.length > 0) {
                    System.out.println("The list of countries for the matching string \"" + input + "\" is the following:");
                    System.out.println("Results count: " + matchedCountries.length);
                    for (String country : matchedCountries) {
                        System.out.println("\t" + country);
                    }
                } else {
                    System.out.println("No match for matching string entered");
                }
                break;
            }
        }
        
        scanner.close();
    }
   
    // Method to initialize the country data array
    public void init() {
    String[] countryCodes = Locale.getISOCountries();
    countryData = new String[countryCodes.length][2];
        
        for (int i = 0; i < countryCodes.length; i++) {
            Locale locale = new Locale("", countryCodes[i]);
            countryData[i][0] = countryCodes[i];
            countryData[i][1] = locale.getDisplayCountry();
        }
    }
    
    //Method to get country name from country code
    public String getCountryName(String code) {
        if (code == null || code.length() != 2) {
            return null;
        }
        code = code.toUpperCase();
        
        for (String[] entry : countryData) {
            if (entry[0].equalsIgnoreCase(code)) {
                return entry[1];
            }
        }
        return null;
    }
    
    //Method to get country code from country name
    public String getCountryCode(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        name = name.toLowerCase();
        
        for (String[] entry : countryData) {
            if (entry[1].toLowerCase().equals(name)) {
                return entry[0];
            }
        }
        return null;
    }
    
    //Method to get method countries by a substring
    public String[] getMatchedCountries(String matchString) {
        if (matchString == null || matchString.length() < 2) {
            return null;
        }
        matchString = matchString.toLowerCase();
        
        ArrayList<String> matchedCountries = new ArrayList<>();
        
        for (String[] entry : countryData) {
            if (entry[1].toLowerCase().contains(matchString) || entry[0].toLowerCase().contains(matchString)) {
                String result = entry[1] + " (" + entry[0] + ")";
                if (!matchedCountries.contains(result)) {
                    matchedCountries.add(result);
                }
            }
        }
        
        if (matchedCountries.isEmpty()) {
            return null;
        }
        
        String[] resultArray = new String[matchedCountries.size()];
        return matchedCountries.toArray(resultArray);
    }
}
