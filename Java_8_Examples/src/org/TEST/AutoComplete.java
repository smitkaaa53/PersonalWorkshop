package org.TEST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AutoComplete 
{
	
	public static void main(String[] args) 
	{
		
		Trie trie = new Trie();
		
		trie.insert("AMAZON");
		trie.insert("AMAZON PRIME");
		trie.insert("AMAZING");
		trie.insert("AMAZON NOW");
		
		trie.insert("WALMART");
		trie.insert("WALMART LABS");
		
		List<String> words = trie.autoComplete("W");
		
		words = trie.autoComplete("A");
		words = trie.autoComplete("AMA");
		words = trie.autoComplete("AMAZON");
		
		
		
		words.forEach((t)->System.out.println(t.toString()));
		
	}

}

class Trie
{
	TriesNode root = new TriesNode(' ');

	public void insert(String dataStr)
	{
		TriesNode current = root;
		for(char ch : dataStr.toCharArray())
		{
			current = current.add(ch);
		}
		current.isEnd = true;
		current.dataStr = dataStr;
	}
	
	public List<String> autoComplete(String dataStr)
	{
		TriesNode lastNode = root;
		for(char ch : dataStr.toCharArray())
		{
			lastNode = lastNode.getChild(ch);
			if(lastNode == null)
			{
				return new ArrayList<>();
			}
		}
		return lastNode.getCompleteWords();
	}
	
	
}

class TriesNode
{
	char data;
	List<TriesNode> children;
	boolean isEnd;
	String dataStr;
	
	public TriesNode(char ch)
	{
		data=ch;
		children = new LinkedList<>();
		isEnd = false;
	}
	
	public TriesNode add(char ch)
	{
		TriesNode chNode = 	getChild(ch);
		if(chNode==null)
		{
			chNode = new TriesNode(ch);
			children.add(chNode);
		}
		return chNode;
	}
	
	public TriesNode getChild(char ch)
	{
		if(children!=null)
		{
			for(TriesNode child : children)
			{
				if(child.data == ch)
				{
					return child;
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString() 
	{
		return dataStr;
	}
	
	public List<String> getCompleteWords()
	{
		List<String> result = new ArrayList<>();
		if(isEnd)
		{
			result.add(toString());
		}

		if(children!=null)
		{
			for(TriesNode child : children)
			{
				result.addAll(child.getCompleteWords());
			}
		}
		
		return result;
	}

    
}
