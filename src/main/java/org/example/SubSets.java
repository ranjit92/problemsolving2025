package org.example;

import java.util.ArrayList;
import java.util.List;

public class SubSets {
    public static void main(String[] args) {
        SubSets obj = new SubSets();
        //obj.subset("", "abc");
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = new ArrayList<>();

        obj.subsets(result, new ArrayList<>(), nums, 0);
    }

    private void subset(String processed, String unProcessed){

        if(unProcessed.isEmpty()){
            System.out.println(processed);
            return;
        }
        char ch = unProcessed.charAt(0);
        subset(processed + ch, unProcessed.substring(1)); // take it
        subset(processed, unProcessed.substring(1)); // ignore it
    }


    private void subsets(List<List<Integer>> result, List<Integer> collector, int[] nums, int count){

        if(count == nums.length){
            result.add(collector);
            return;
        }

        List<Integer> list = new ArrayList<>(collector);
        list.add(nums[count]);
        subsets(result, list, nums, count + 1);
        subsets(result, collector, nums, count + 1);
    }
}
