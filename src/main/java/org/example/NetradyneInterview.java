package org.example;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://www.geeksforgeeks.org/smallest-window-contains-characters-string/


Given a string Find the smallest subString which contains all the unique character present in the input string.
Optimize the solution for O(n) time complexity.

Example
InputString = aabbcddabaaaab
Output = bcdda or cddab, both these substring has length = 5 ,
there is no other substring in the given input which contains all unique 4 characters of the input string (a,b,c,d)
*/
public class NetradyneInterview {

    public int getMinSubString(String input){
        Set<Character> setStr = new HashSet<>();
        Map<Character, Integer> freqMap = new HashMap<>();

        int minLength = Integer.MAX_VALUE;

        for(int i = 0; i < input.length(); i++){
            setStr.add(input.charAt(i));
        }

        int left = 0;

        for(int right = 0; right < input.length(); right++){

            freqMap.put(input.charAt(right), freqMap.getOrDefault(input.charAt(right), 0) + 1);

            while(freqMap.size() == setStr.size()){
                minLength = Math.min(minLength, (right - left + 1));
                freqMap.put(input.charAt(left), freqMap.get(input.charAt(left)) - 1);

                if(freqMap.get(input.charAt(left)) == 0){
                    freqMap.remove(input.charAt(left));
                }
                left++;
            }
        }
        return minLength;
    }
}
