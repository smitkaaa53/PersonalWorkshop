import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    Solution.testGIS();
  }
  
  public static void testGIS() {
    List<Integer> input = new ArrayList<Integer>();
    
    input.add(Integer.valueOf(-1));
    input.add(Integer.valueOf(-100));
    input.add(Integer.valueOf(5));
    
//    [1, -100, 5]
    
    //input null || empty value
    
    //single value list
    //all -Ve
    //start -ve
    //end -ve
    // in between -ve 
    //duplicate imputs
    //
    /** create test input here! **/
    
    Long answer = Solution.gis(input);
    
   System.out.println(answer);
    assert(answer.equals(Long.valueOf(7)));
  }
  
  public static long gis(List<Integer> input)
  {
    if(input==null || input.isEmpty())
      return Integer.MIN_VALUE;
    
    //int currIndex=0;
    Integer[] inputArr = input.toArray(new Integer[]{});
//    Map<List<Integer>,Long> sumMap = new HashMap<>();
    Long maxSum = Long.MIN_VALUE;
    for(int i=0;i<inputArr.length;i++)
    {
  //    List<Integer> list = new ArrayList<>();
    //  list.add(inputArr[i]);
      long sum = inputArr[i];
      //sumMap.put(new ArrayList<>(list), new Long(sum) );
      maxSum = Math.max(maxSum,sum);
      
      for(int j=i+1;j<inputArr.length;j++)
      {
        //  list.add(inputArr[j]);
          sum+=inputArr[j];
      //    sumMap.put(new ArrayList<>(list), new Long(sum));
          maxSum = Math.max(maxSum,sum);
      
      } 
    }
    return maxSum;
  }
}


/* 
Your previous JavaScript content is preserved below:

const assert = require('assert');

/**
* Given a list of integers, return the sum of the largest subsequence of 
* integers. By "subsequence" we mean any subarray, for example: example in 
* the following list of integers:
* [1, 2, 3, 4] some examples (NON-EXHAUSTIVE) of subsequences are [ [3], [2, 3, 4], [1, 2, 3, 4],... ]

* Tests: 
* gis([1, 2, 3, 4]) === 10
* gis([1, -100, 5]) === 5
*/
      /*
const gis = (args) => {
 // code me!
};


const gisTests = () => {
  gisTest([1,2,3,4], 10);
}

const gisTest = (arg, expected) => {
  assert(gis(arg) == expected, `FAILED: output is ${gis(arg)}`);
}

// run the tests
gisTests();

 */