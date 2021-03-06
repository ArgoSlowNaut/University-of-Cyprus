
/**
* Author: Iason Georgiou
* Written: 29/09/2021
* Last updated: 24/04/2022
*
* Compilation command: javac LongestDupSubstring.java
* Execution command: java LongestDupSubstring
* 
* This program is designed to find the longest duplicate substring in a given string.
* It works by brute forcing through all possible substrings of our input string.
* We have a nested for loop.
* In the header for loop, the counter i defines the length of the possible substrings we want to construct
* then, we go through all possible positions (starting from 0 to input.length()-i)
* and the third for loop is responsible for constructing another String object, which equals the String from each position k to k+i
* 
* Then we have a double nested for loop within the triple nested for loop for constructing all other possible substrings with the same length
* 
* variables places, placee and placesa, placeeb stand for each substring's ranges respectively, to make sure they do not overlap
* as per the exercise's rules
* 
* then we see if those substrings are equal, to see if it is a duplicate
*at the same time, we check if the length of this  duplicate string is larger than our current larger length
* 
*if we do find a larger duplicate string, we replace the ans object with that string where we store the answer 
*
*this solution always finds the first largest duplicate substring, since if there is a duplicate substring of equal length
*then it will not be replaced
**/

import java.util.Scanner;  //import scanner object in order to read from keyboard

public class LongestdupString {
	public static void main(String args[]) {
	Scanner sc = new Scanner (System.in); //define our scanner
		
	
		System.out.print("Give a string: ");  
		String input = sc.nextLine(); 
		
		String ans = "";
		int maxLength = 0; 
		
		for (int i=0; i<=input.length(); i++) {     //i goes through all possible lengths for our substring 
			for (int k=0; k<input.length()-i; k++) { 
				String substring = ""; 
				int places, placee;  
				places = k;  
				placee= k+i;
				for (int j=places; j<=placee; j++) {     
					substring += input.charAt(j);  
				}
				for (int k2=0; k2<input.length()-i; k2++) {  //start at all possible positions for length = k2
					String substringCon = ""; 
					int placesa = k2; 
					int placeeb = k2+i;
					for (int j=k2; j<=k2+i; j++) {   
						substringCon += input.charAt(j); //add to the substring the character at jE[k2, k2+i]
					}
					if (substringCon.equals(substring) && (places>placeeb || placesa>placee)) {  //check if the substrings are equal (duplicate), and check if the ranges do not overlap
						if (substring.length()>maxLength) { 
							maxLength = substring.length(); 
							ans = "" + substring;
						}
					}
				}
	}
}
		System.out.println("Longest substring: " + ans);  //print the longest substring
		System.out.println("Longest substring size: " + ans.length()); //print the size of the longest substring
}
}
