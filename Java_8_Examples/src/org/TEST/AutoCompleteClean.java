package org.TEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AutoCompleteClean 
{
	
	public static void main(String[] args) 
	{
		
		Tries tries = new Tries();
		
		tries.insert("AMAZON");
		tries.insert("AMAZON PRIME");
		tries.insert("AMAZING");
		tries.insert("AMAZON NOW");
		
		tries.insert("WALMART");
		tries.insert("WALMART LABS");
		
		List<String> search = tries.autoComplete("W");
		
		//search = tries.autoComplete("A");
		//search = tries.autoComplete("AMA");
		//search = tries.autoComplete("AMAZON");
		
		
		
		search.forEach((t)->System.out.println(t.toString()));
		
	}
	
	
}

class Tries
{
	TriesN root;
	
	public Tries()
	{
		root = new TriesN(' ');
	}
	
	public void insert(String word)
	{
		if(search(word))
			return;
		
		TriesN curr = root;
		TriesN pre;
		
		for(char ch : word.toCharArray())
		{
			pre=curr;
			TriesN child= curr.getChild(ch);
			if(child==null)
			{
				child = new TriesN(ch);
				curr.children.add(child);
			}
			curr = child;
			curr.parent = pre;
		}
		curr.isEnd = true;
	}
	
	public boolean search(String word)
	{
		TriesN curr = root;
		for(char ch : word.toCharArray())
		{
			TriesN child = curr.getChild(ch);
			if(child==null)
				return false;
			else
				curr = child;
		}
		
		return curr.isEnd;
	}
	
	public List<String> autoComplete(String prefix)
	{
		TriesN lastNode = root;
		for(int i=0;i<prefix.length();i++)
		{
			lastNode = lastNode.getChild(prefix.charAt(i));
			if(lastNode ==null)
				return new ArrayList<>();
		}
		return lastNode.getWords();
	}
	
}
class TriesN
{
	char data;
	List<TriesN> children;
	TriesN parent;
	boolean isEnd;
	
	public TriesN(char ch)
	{
		data=ch;
		children = new LinkedList<>();
		isEnd = false;
	}

	public TriesN getChild(char ch)
	{
		if(children!=null)
		{
			for(TriesN eachChild : children)
			{
				if(eachChild.data == ch)
				{
					return eachChild;
				}
			}
		}
		return null;
	}

	public List<String> getWords()
	{
		List<String> words = new ArrayList<>();
		
		if(isEnd)
		{
			words.add(toString());
		}
		
		if(children!=null)
		{
			for(int i=0;i<children.size();i++)
			{
				if(children.get(i)!=null)
				{
					words.addAll(children.get(i).getWords());
				}
			}
		}
		return words;
	}

	@Override
	public String toString() 
	{
		if(parent==null)
			return "";
		return parent.toString()+String.valueOf(data);
	}
    
}
