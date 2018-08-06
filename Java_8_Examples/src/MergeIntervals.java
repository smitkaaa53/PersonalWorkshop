import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class MergeIntervals {
	
    public static List<Interval> mergeSimple(List<Interval> intervals) 
    {
    	List<Interval> mIntervals = new ArrayList<>();

    	for(Interval t : intervals)
    	{
    		boolean isMer = false;
    		for(Interval x : mIntervals)
        	{
				if(t.start > x.start && t.start < x.end)
				{
					x.end = t.end;
					isMer = true;
					break;
				}
        	}	
    		if(!isMer)
    			mIntervals.add(t);
    	}
    	return mIntervals;
    }
	
	public static List<Interval> mergeOptimum(List<Interval> intervals) 
	{
        Stack<Interval> stack = new Stack<>();
        intervals.sort((a,b) -> Integer.compare(a.start, b.start));
        
        intervals.forEach((it) -> {
        	
            if(stack.isEmpty() || it.start > stack.peek().end)
            {
            	stack.push(it);
            } 
            else
            {
                stack.peek().end = Math.max(it.end, stack.peek().end);
            }
        });
        return new ArrayList<Interval>(stack);
    }
	
    
    public static void main(String[] args) 
    {
		List req = Arrays.asList(new Interval[]{new Interval(1,3),new Interval(2,4),new Interval(5,7),new Interval(6,8)});
		
		((List<Interval>)mergeSimple(req)).forEach((t)-> System.out.println("["+t.start+","+t.end+"]"));
		
		((List<Interval>)mergeOptimum(req)).forEach((t)-> System.out.println("["+t.start+","+t.end+"]"));
		
	}
}

class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
}
