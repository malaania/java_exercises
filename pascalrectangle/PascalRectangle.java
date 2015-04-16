package com.capgemini.pascalrectangle;


/**
 * Created by ldrygala on 2015-01-23.
 * 0                     1
 * 1                   1   1
 * 2                 1   2   1
 * 3               1   3   3   1
 * 4             1   4   6   4   1
 * 5           1   5   10  10   5   1
 * 6         1   6   15  20  15   6   1
 * 7       1   7   21  35  35   21  7   1
 * 8     1   8   28  56  70  56   28  8   1
 * 9   1   9  36   84  126 126  84  36  9   1
 */
//TODO rekurencją bedzie łatwiej, użyj rozwiązania lepiej dopasowanego do problemu.
public class PascalRectangle {
   public static long pascal(int c, int r) {
	   	if( c<0 || c>r ){
	   		return 0;
	   	}
    	int array_size = (r/2) + 2;
    	long[] values = new long[array_size];
    	values[0] = 1;
    	for(int i = 0; i < r ; i++){    		
    		long[] new_values = new long[array_size];
			if (i%2 == 1) {				
				for(int k = 0; k < ((i/2) + 2); k++){     			
        			if( k == 0){
        				new_values[0] = 2*values[0];
        			} 
        			else{
        				new_values[k] = values[k-1] + values [k];
        			}    		
				} 				
			}		
			else {
				for(int m = 0; m < ((i/2) + 1); m++){
					new_values[m] = values[m] + values[m+1];  
				}
			}	
			values = new_values;	
    	}
    	if(r%2==1){
    		int polowa = (r+1)/2; 
    		
    		if(c >= polowa){
    			return values[c-polowa];
    		}
    		return values[polowa-c-1];
    	}
    	int polowa = r/2;
    	if(c >= polowa){
			return values[c-polowa];
		}
		return values[polowa-c];    	
    }	
}
