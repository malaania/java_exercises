package com.capgemini.placeToSplit;

/**
 * Given a non-empty array, return true if there is a place to split the array so that the sum of the numbers on one side is equal to the sum of the numbers on the other side.
 * Example:
 * {{{
 * canBalance({1, 1, 1, 2, 1}) → true
 * canBalance({2, 1, 1, 2, 1}) → false
 * canBalance({10, 10}) → true
 * }}}
 */
public final class PlaceToSplit {
    private PlaceToSplit() {
    }
    //TODO można zrobić optymalniej ale nie jest źle. Niestety znów nieczytalne.
    public static boolean canBalance(int[] nums) {
        int sum = 0;
        int size = nums.length;
        for (int i = 0; i<size; i++){
        	sum += nums[i];
        }
        if(sum%2==1){
        	return false;
        }
        int half = sum/2;
        int partial_sum = 0;
        for(int j = 0; j < size ; j++){
        	partial_sum += nums[j];
        	if(partial_sum == half){
        		return true;
        	}
        	if(partial_sum > half){
        		return false;
        	}
        }
		return false;        
    }
}
