package org.example;
/*You are running a classroom and suspect that some of your students are passing around the answer to a multiple-choice question disguised as a random note.

Your task is to write a function that, given a list of words and a note, finds and returns the word in the list that is scrambled inside the note, if any exists. If none exist, it returns the result ""-"" as a string. There will be at most one matching word. The letters don't need to be in order or next to each other. The letters cannot be reused.

Example:
words = [""baby"", ""referee"", ""cat"", ""dada"", ""dog"", ""bird"", ""ax"", ""baz""]
note1 = ""ctay""
find(words, note1) => ""cat""   (the letters do not have to be in order)

note2 = ""bcanihjsrrrferet""
find(words, note2) => ""cat""   (the letters do not have to be together)

note3 = ""tbaykkjlga""
find(words, note3) => ""-""     (the letters cannot be reused)

note4 = ""bbbblkkjbaby""
find(words, note4) => ""baby""

note5 = ""dad""
find(words, note5) => ""-""

note6 = ""breadmaking""
find(words, note6) => ""bird""

note7 = ""dadaa""
find(words, note7) => ""dada""

All Test Cases:
find(words, note1) -> ""cat""
find(words, note2) -> ""cat""
find(words, note3) -> ""-""
find(words, note4) -> ""baby""
find(words, note5) -> ""-""
find(words, note6) -> ""bird""
find(words, note7) -> ""dada""

Complexity analysis variables:

W = number of words in `words`
S = maximal length of each word or of the note  "*/
public class karatAtalasian {

    public static void main(String[] args) {
        String[] words = new String[] {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};
        String note7 = "dadaa";
        String note3 = "tbaykkjlga";
        String note1 = "ctay";
        System.out.println(find(words, note7));
        System.out.println(find(words, note3));
        System.out.println(find(words, note1));

    }

    public static String find(String[] words, String note){
        int[] charNote = new int[26];
        for(int i = 0; i < note.length(); i++){
            charNote[note.charAt(i) - 'a']++;
        }

        for(String word : words){
            int[] wordArr = new int[26];
            for(int i = 0; i < word.length(); i++){
                wordArr[word.charAt(i) - 'a']++;
            }
            if(isMatch(charNote, wordArr, word.length())){
                return word;
            }
        }

        return "-";
    }

    public static boolean isMatch(int[] charNote, int[] wordArr, int wordLen){
        int count = 0;
        for(int i = 0; i < 26; i++){
            if(charNote[i] !=0 && wordArr[i] !=0 && charNote[i] >= wordArr[i]){
                count += wordArr[i];
            }
        }
        return count == wordLen;
    }
}
