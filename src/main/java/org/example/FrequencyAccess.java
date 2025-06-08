package org.example;

import java.util.*;

//Asked in LinkedIn interview
//https://leetcode.com/problems/all-oone-data-structure/description/
public class FrequencyAccess {
    private Map<String, Integer> freqMap = new HashMap<>();     // Maps number to its frequency
    private Map<Integer, Set<String>> freqBucket = new HashMap<>(); // Maps frequency to set of numbers with that frequency
    private int minFreq = Integer.MAX_VALUE;
    private int maxFreq = 0;

    public static void main(String[] args) {
        FrequencyAccess fa = new FrequencyAccess();
        fa.add("ab");
        fa.add("ab");
        fa.add("ab");
        fa.add("cv");
        fa.add("cv");
        //fa.add("za");

        System.out.println(fa.getMaxFrequency());
        System.out.println(fa.getMinFrequency());

        /*fa.remove("ab");
        fa.remove("ab");

        System.out.println(fa.getMaxFrequency());
        System.out.println(fa.getMinFrequency());*/

    }

    // Add a number to the counter
    public void add(String key) {
        int oldFreq = freqMap.getOrDefault(key, 0);
        int newFreq = oldFreq + 1;

        // Update the number -> frequency mapping
        freqMap.put(key, newFreq);

        // Remove from old frequency bucket
        if (oldFreq > 0) {
            freqBucket.get(oldFreq).remove(key);
            if (freqBucket.get(oldFreq).isEmpty()) {
                freqBucket.remove(oldFreq);
                // Update minFreq if needed
                if (minFreq == oldFreq) {
                    minFreq = newFreq;
                }
            }
        }

        // Add to new frequency bucket
        freqBucket.computeIfAbsent(newFreq, k -> new HashSet<>()).add(key);

        // Update maxFreq
        maxFreq = Math.max(maxFreq, newFreq);
        minFreq = Math.min(minFreq, newFreq);
    }

    // Remove a number from the counter
    public void remove(String key) {
        if (!freqMap.containsKey(key)) {
            return;
        }

        int oldFreq = freqMap.get(key);
        int newFreq = oldFreq - 1;

        // Update or remove from numToFreq
        if (newFreq == 0) {
            freqMap.remove(key);
        } else {
            freqMap.put(key, newFreq);
        }

        // Remove from old frequency bucket
        freqBucket.get(oldFreq).remove(key);
        if (freqBucket.get(oldFreq).isEmpty()) {
            freqBucket.remove(oldFreq);

            // Update maxFreq if needed
            if (oldFreq == maxFreq) {
                maxFreq = freqBucket.isEmpty() ? 0 : Collections.max(freqBucket.keySet());
            }
        }

        // Add to new frequency bucket if frequency > 0
        if (newFreq > 0) {
            freqBucket.computeIfAbsent(newFreq, k -> new HashSet<>()).add(key);
        }

        // Update minFreq
        if (freqBucket.isEmpty()) {
            minFreq = 0;
        } else if (oldFreq == minFreq) {
            minFreq = freqBucket.isEmpty() ? 0 : Collections.min(freqBucket.keySet());
        } else {
            minFreq = Math.min(minFreq, newFreq > 0 ? newFreq : minFreq);
        }
    }

    // Get numbers with minimum frequency
    public Set<String> getMinFrequencyElements() {
        return freqBucket.isEmpty() ? new HashSet<>() : freqBucket.get(minFreq);
    }

    // Get numbers with maximum frequency
    public Set<String> getMaxFrequencyElements() {
        return freqBucket.isEmpty() ? new HashSet<>() : freqBucket.get(maxFreq);
    }

    // Get the minimum frequency
    public String getMinFrequency() {
      return freqBucket.isEmpty() ? "" : freqBucket.get(minFreq).iterator().next();
    }

    // Get the maximum frequency
    public String getMaxFrequency() {
        return freqBucket.isEmpty() ? "" : freqBucket.get(maxFreq).iterator().next();
    }
}