package org.leetcode;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Description of Question

// During a day, there are millions of transactions happening between banks. Because there are so many transactions, those are sorted only at the end of each day, when the balances between each banks are calculated. This is called “settlement”. The process of calculating the end of day balances between banks, and moving the money from one to another. Because this process is so complex, banks work with “clearing houses”. Those are third party financial institutions to which banks send all their transactions and that processes the “settlement” for them.

// We want to build the software for a clearing house.

// First step is to simply make a software to allows to sort a list of transactions with a payer, payee and amount and calculate all balances between each pair of banks.

// Optimization consists of trying to reduce the amount of transaction by settling all banks together. with "->" means "owes" if A -> 300 B, and B -> 300 C, then you can reduce the amount of transactions from 2 to 1 by having A paying C 300 directly.

// transactions = [
//   {"payee": "BoA", "amount": 132, "payer": "Chase"},
//   {"payee": "BoA", "amount": 827, "payer": "Chase"},
//   {"payee": "Well Fargo", "amount": 751, "payer": "BoA"},
//   {"payee": "BoA", "amount": 585, "payer": "Chase"},
//   {"payee": "Chase", "amount": 877, "payer": "Well Fargo"},
//   {"payee": "Well Fargo", "amount": 157, "payer": "Chase"},
//   {"payee": "Well Fargo", "amount": 904, "payer": "Chase"},
//   {"payee": "Chase", "amount": 548, "payer": "Well Fargo"},
//   {"payee": "Chase", "amount": 976, "payer": "BoA"},
//   {"payee": "BoA", "amount": 872, "payer": "Well Fargo"},
//   {"payee": "Well Fargo", "amount": 571, "payer": "Chase"}
// ]

//Lvl1
//   {"payee": "BoA", "amount": 132, "payer": "Chase"},
//   {"payee": "BoA", "amount": 827, "payer": "Chase"},
//   {"payee": "BoA", "amount": 585, "payer": "Chase"},
//   {"payee": "Chase", "amount": 976, "payer": "BoA"},

//   {"payee": "BoA", "amount": 872, "payer": "Well Fargo"},
//   {"payee": "Well Fargo", "amount": 751, "payer": "BoA"},


//   {"payee": "Chase", "amount": 877, "payer": "Well Fargo"},
//   {"payee": "Chase", "amount": 548, "payer": "Well Fargo"},
//   {"payee": "Well Fargo", "amount": 571, "payer": "Chase"}
//   {"payee": "Well Fargo", "amount": 157, "payer": "Chase"},
//   {"payee": "Well Fargo", "amount": 904, "payer": "Chase"},
// ]

//Lvl2
//   {"payee": "BoA", "amount": 568, "payer": "Chase"},
//   {"payee": "BoA", "amount": 121, "payer": "Well Fargo"},
//   {"payee": "Well Fargo", "amount": 207, "payer": "Chase"},


public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        List<Trans> list = new ArrayList<>();
        
        list.add(new Trans("A","B",100));
        list.add(new Trans("A","B",400));

        list.add(new Trans("B","C",200));
        list.add(new Trans("B","C",100));
        
        list.add(new Trans("B","A",100));
        list.add(new Trans("C","D",100));
        
        List<Trans> newList = consolidateTransLv1(list);
        
        //System.out.ptinln(newList);
        
        newList.forEach((t1)->System.out.println(t1.payeeName+" "+ t1.payerName+" "+t1.amount));
        
        
    }
     /*   A->B +sum
        B->A  -Subscttra
        
        A/B-B/A 
        
        A-B
        
        B->C - Amount SAME
        
        
        A->B300
        B->C300
        C->300
        
        A->C300 */
        
        /*
        * null List OR Empty List;
        *  All Single transations - no Duplicate between A->B
        *  payee/payer are not NULL ? EMPTY.
        *   Double.NAN 
        * //Adding Double = Validate Double Precisions.
        * // O(n2) - n no of transaction - Latency - conplexity is high
        * // Space eficient - meomry Comsumption
        * // Sum may get Outpof range
        */
        public static List<Trans> consolidateTransLv1(List<Trans> trans)
        {
            
            if(trans==null || trans.isEmpty())
            {
                return trans;
            }
             
            Set<String> visisted = new HashSet<>();
            List<Trans> newList = new ArrayList<>();
            
            Trans temp = null;
            for(int i=0;i<trans.size();i++)
            {
                temp=trans.get(i);
                double sum = temp.amount;
                if(!visisted.contains(temp.toString()))
                {
                    for(int j=i+1;j<trans.size();j++)
                    {
                      Trans  subTemp=trans.get(j);
                      
                      if(temp.payeeName.equals(subTemp.payeeName) && temp.payerName.equals(subTemp.payerName))
                      {
                          
                          sum +=subTemp.amount;
                          visisted.add(subTemp.toString());
                      }
                    }
                    visisted.add(temp.toString());
                    temp.amount=sum;
                   newList.add(temp);  

                }
              
            }
            
            return newList;
        }
        
}
        class Trans
        {
            String payeeName;
            String payerName;
            double amount;
            
            Trans(String payeeName,            String payerName,            double amount)
            {
                this.payeeName=payeeName;
                this.payerName=payerName;
                this.amount = amount;
            }
            
           /* public int hashCode()
            {
             return new String(payeeName+payerName+amount).hashCode();
            }*/
            
            public String toString()
            {
                return new String(payeeName+payerName+amount);
            }
        }
            
        
        