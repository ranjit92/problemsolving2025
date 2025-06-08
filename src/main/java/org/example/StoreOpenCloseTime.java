package org.example;

import java.util.*;

/*
* Question 2

*You are given two arrays which represent open and close timings of a store.
* You have to print the schedule.
* if any of the timing is greater than 2400 ,you will have to print closed
* and if the timing for open and close are same you have to print Open 24 hours
* Example: Input //Open [900,1000,1000,1000,2700,1200,800]
* // Close - [1700,1600,1600,1600,1600,1200,800]
* // Output: // Mon: 900-1700 // Tue-Thur: 1000-1600 // Fri: Closed // Sat-Sun: Open 24 Hours
*
* */
public class StoreOpenCloseTime {

    public static void main(String[] args) {

        StoreOpenCloseTime st = new StoreOpenCloseTime();
        int[] open = new int[]{900,1000,1000,1000,2700,1200,800};
        int[] close = new int[]{1700,1600,1600,1600,1600,1200,800};
        st.openCloseTime(open, close);

    }

    //Output: // Mon: 900-1700 // Tue-Thur: 1000-1600 // Fri: Closed // Sat-Sun: Open 24 Hours
    public void openCloseTime(int[] open, int[] close){

        String[] days = new String[]{"Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};
        Map<String, List<String>> map = new HashMap<>();

        for(int i = 0; i < 7; i++){
            String key = "";
            int open_ = open[i];
            int close_ = close[i];

            if(open_ == close_){
                key = "Open 24 hours";
            }
            else if(open_ > 2400 || close_ > 2400){
                key = "Closed";
            }
            else{
                key = open[i] + "-" + close[i];
            }

            if(map.containsKey(key)){
                List<String> list = map.get(key);
                list.add(days[i]);
                map.put(key, list);
            }
            else{
                List<String> list = new ArrayList<>();
                list.add(days[i]);
                map.put(key, list);
            }
        }

        for(String key : map.keySet()){
            List<String> val = map.get(key);

            if(val.size() > 1){
                System.out.println(val.get(0) + "-" + val.get(val.size()-1) + ": " + key);
            }
            if(val.size() == 1){
                System.out.println(val.get(0) + ": " + key);
            }
        }
    }
}
