package org.example;

import java.util.*;

//[(25,579),(218,918),(1281,1307),(623,1320),(685,1353),(1308,1358)]
public class MinDayForAllMeeting {

    public static void main(String[] args) {
        MinDayForAllMeeting ob = new MinDayForAllMeeting();
        List<Interval> intervals= List.of(new Interval(25,579),new Interval(218,918),new Interval(1281,1307),new Interval(623,1320),new Interval(685,1353),new Interval(1308,1358));
        ob.minMeetingRooms(intervals);
    }
    public int minMeetingRooms(List<Interval> list) {

        List<Interval> intervals = new ArrayList<>(list);
        int days = 0;
        Queue<Interval> queue = new LinkedList<>();
        intervals.sort((a,b) -> Integer.compare(a.end, b.end));

        for(int i = 0; i < intervals.size(); i++){
            queue.add(intervals.get(i));
        }

        while(!queue.isEmpty()){
            int size = queue.size();
            Interval previous = null;
            for(int i = 0; i < size; i++){
                Interval current = queue.poll();

                if(previous != null){
                    if(current.start >= previous.end){
                        previous = current;
                    }
                    else{
                        queue.add(current);
                    }
                }
                else{
                    previous = current;
                }
            }
            days++;
        }
        return days;
    }
}

class Interval {
    public int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
