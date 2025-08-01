package org.example;

import java.util.*;

public class SplunkKarataCommonCourse {


    /*

You are a developer for a university. Your current project is to develop a system for students to find courses they share with friends. The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs.

Write a function that takes in a collection of (student ID number, course name) pairs and returns, for every pair of students, a collection of all courses they share.

Sample Input:

enrollments1 = [
  ["58", "Linear Algebra"],
  ["94", "Art History"],
  ["94", "Operating Systems"],
  ["17", "Software Design"],
  ["58", "Mechanics"],
  ["58", "Economics"],
  ["17", "Linear Algebra"],
  ["17", "Political Science"],
  ["94", "Economics"],
  ["25", "Economics"],
  ["58", "Software Design"],
]

Sample Output (pseudocode, in any order):

find_pairs(enrollments1) =>
{
  "58,17": ["Software Design", "Linear Algebra"]
  "58,94": ["Economics"]
  "58,25": ["Economics"]
  "94,25": ["Economics"]
  "17,94": []
  "17,25": []
}

Additional test cases:

Sample Input:

enrollments2 = [
  ["0", "Advanced Mechanics"],
  ["0", "Art History"],
  ["1", "Course 1"],
  ["1", "Course 2"],
  ["2", "Computer Architecture"],
  ["3", "Course 1"],
  ["3", "Course 2"],
  ["4", "Algorithms"]
]

Sample output:

find_pairs(enrollments2) =>
{
  "1,0":[]
  "2,0":[]
  "2,1":[]
  "3,0":[]
  "3,1":["Course 1", "Course 2"]
  "3,2":[]
  "4,0":[]
  "4,1":[]
  "4,2":[]
  "4,3":[]
}

Sample Input:
enrollments3 = [
  ["23", "Software Design"],
  ["3", "Advanced Mechanics"],
  ["2", "Art History"],
  ["33", "Another"],
]

Sample output:

find_pairs(enrollments3) =>
{
  "23,3": []
  "23,2": []
  "23,33":[]
  "3,2":  []
  "3,33": []
  "2,33": []
}

All Test Cases:
find_pairs(enrollments1)
find_pairs(enrollments2)
find_pairs(enrollments3)

Complexity analysis variables:

n: number of student,course pairs in the input
s: number of students
c: total number of courses being offered (note: The number of courses any student can take is bounded by a small constant)

*/
        public static void main(String[] argv) {
            String[][] enrollments1 = {
                    {"58", "Linear Algebra"},
                    {"94", "Art History"},
                    {"94", "Operating Systems"},
                    {"17", "Software Design"},
                    {"58", "Mechanics"},
                    {"58", "Economics"},
                    {"17", "Linear Algebra"},
                    {"17", "Political Science"},
                    {"94", "Economics"},
                    {"25", "Economics"},
                    {"58", "Software Design"}
            };

            String[][] enrollments2 = {
                    {"0", "Advanced Mechanics"},
                    {"0", "Art History"},
                    {"1", "Course 1"},
                    {"1", "Course 2"},
                    {"2", "Computer Architecture"},
                    {"3", "Course 1"},
                    {"3", "Course 2"},
                    {"4", "Algorithms"}
            };

            String[][] enrollments3 = {
                    {"23", "Software Design"},
                    {"3",  "Advanced Mechanics"},
                    {"2",  "Art History"},
                    {"33", "Another"},
            };

            SplunkKarataCommonCourse ob = new SplunkKarataCommonCourse();
            Map<String, Set<String>> result = ob.getCourceList(enrollments1);

            for(String key : result.keySet()){
                System.out.println(key);

                for(String ss : result.get(key)){
                    System.out.print(ss + ", ");
                }

                System.out.println("-----------------");
            }
        }

        public Map<String, Set<String>> getCourceList(String[][] enrollments){

            Map<String, Set<String>> enmMap = new HashMap<>();
            List<String> students = new ArrayList<>();
            Map<String, Set<String>> result = new HashMap<>();

            for(String[] enrollment : enrollments){
                Set<String> courseSet = enmMap.getOrDefault(enrollment[0], new HashSet<>());
                courseSet.add(enrollment[1]);
                enmMap.put(enrollment[0], courseSet);
            }

            for(String student : enmMap.keySet()){
                students.add(student);
            }

            for(int i = 0; i < students.size(); i++){

                for(int j = i + 1; j < students.size(); j++){
                    String pair = students.get(i) + ", "+ students.get(j);
                    Set<String> courseSet = result.getOrDefault(pair, new HashSet<>());

                    Set<String> common = getCommonCourses(enmMap.get(students.get(i)), enmMap.get(students.get(j)));
                    courseSet.addAll(common);
                    result.put(pair, courseSet);
                }


            }

            return result;

        }


        public Set<String> getCommonCourses(Set<String> course1, Set<String> course2){
            Set<String> result = new HashSet<>();

            if(course1 != null && !course1.isEmpty() && course2 != null && !course2.isEmpty()){
                for(String course : course1){
                    if(course2.contains(course)){
                        result.add(course);
                    }
                }
            }

            return result;
        }


    }

