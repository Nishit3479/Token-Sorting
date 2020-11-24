package ir.token1.lab;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Token1 
{
	public static void main(String []args)
	{
		String data = ""; 
		try { 
			data = new String(Files.readAllBytes(Paths.get("test.txt")));
			} 	
		catch (IOException e) { 
			e.printStackTrace(); 
			} 
		System.out.println(data);
		String str = data.toLowerCase();
		String St="";
		for(int i=0; i<str.length(); i++)
		{
			char p = str.charAt(i);
			if(p=='.' || p==',' || p=='/' || p=='!' || p=='@' || p=='-' || p=='=' || p==';' || p==':' || p=='?' || p=='_' || p=='#' || p=='%' || p=='&' || p=='$' || 
					p=='^' || p=='*' || p=='(' || p==')' || p=='+' || p=='{' || p=='[' || p=='}' || p==']' || p=='|' || p=='<' || p=='>' || p=='~' || p=='`')
			{
				St +=" "+p;
			}
			else
			{
				St += p;
			}
		}
		String []token = St.split(" ");
		countFreq(token,token.length);
	}
	public static void countFreq(String arr[], int n) 
    { 
        Map<String, Integer> mp = new HashMap<>(); 
        for (int i = 0; i < n; i++) 
        { 
            if (mp.containsKey(arr[i]))  
            { 
                mp.put(arr[i], mp.get(arr[i]) + 1); 
            }  
            else
            { 
                mp.put(arr[i], 1); 
            } 
        } 
        for (Map.Entry<String, Integer> entry : mp.entrySet()) 
        { 
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }
        long startTime1 = System.currentTimeMillis();
        LinkedHashMap<String, Integer> SortedMap = new LinkedHashMap<>();
        mp.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> SortedMap.put(x.getKey(), x.getValue()));
        System.out.println("\nThe Tokens in the descending order of their frequencies using Sorting method 1 : ");
        for (Map.Entry<String, Integer> entry : SortedMap.entrySet()) 
        { 
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        } 
        long stopTime1 = System.currentTimeMillis();
        long elapsedTime1 = stopTime1 - startTime1;
        System.out.println("Time taken to Sort the Tokens using Method 1 : "+elapsedTime1+" Milliseconds");
        
        long startTime2 = System.currentTimeMillis();
        Map<String, Integer> sortedmap = sortByValue(mp, false);
        System.out.println("\nThe Tokens in the descending order of their frequencies using Sorting method 2 : ");
        for (Map.Entry<String, Integer> entry : sortedmap.entrySet()) 
        { 
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }
        long stopTime2 = System.currentTimeMillis();
        long elapsedTime2 = stopTime2 - startTime2;
        System.out.println("Time taken to Sort the Tokens using Method 2 : "+elapsedTime2+" Milliseconds");
    }
	private static Map<String, Integer> sortByValue(Map<String, Integer> mp, final boolean order)
    {
        List<Entry<String, Integer>> list = new LinkedList<>(mp.entrySet());
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
}
