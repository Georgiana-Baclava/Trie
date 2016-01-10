package trie;

import java.util.ArrayList;

public class Trie implements AbstractTrie {

	public static final int ALPH_SIZE = 69;
	
	private int nrKids = 0;
	private int nrOcc = 0;
	private Element value = null;
	private Trie[] children = new Trie[ALPH_SIZE];
	
	
	@Override
	public void add(TrieElement element) {
		
		char[] key = element.toCharArray();
		
		int length = key.length;
		int index;
		
		Trie node = this;
		
		
		for(int level = 0; level < length; ++level){
			index = Element.CharToIndex(key[level]);
			if(node.children[index] == null){
				node.children[index] = new Trie();
				node.nrKids++;
			}
			
			node = node.children[index];
		}
		
		if(node.value == null)
			node.value = (Element) element;
		else if(node.value.compareTo(element) > 0)
			node.value = (Element) element;
		++node.nrOcc;	
		
		
	}

	@Override
	public int count(TrieElement element) {
		
		char[] key = element.toCharArray();
		
		int length = key.length;
		int index;
		
		Trie node = this;
		
		for(int level = 0; level < length; ++level){
			index = Element.CharToIndex(key[level]);
			if(node.children[index] == null)
				return 0;
			
			node = node.children[index];
		}
		
		return node.nrOcc;
	}

	
	public boolean deleteKey(Trie node, char[] key, int level, int length){
		
		if(length <= 0 || node == null)
			return false;
		
		if(level == length){
			
			if(node.nrOcc > 0){
			
				--node.nrOcc;
				if(node.nrOcc == 0) node.value = null;
			
				if(node.nrOcc == 0 && node.nrKids == 0)
					return true;
				
				return false;
			}
		}
		
		else{
			int index = Element.CharToIndex(key[level]);
			if(deleteKey(node.children[index], key, level+1, length)){
				node.children[index] = null;
				--node.nrKids;
			}
			
			if (node.nrOcc == 0 && node.nrKids == 0) return true;
			return false;
		}
		
		return false;
	}
	
	
	
	@Override
	public void remove(TrieElement element) {
		
		char[] key = element.toCharArray();
		int length = key.length;
		
		Trie node = this;

		deleteKey(node, key, 0, length);
	}

	
	
	
	public boolean searchElements(Trie node, ArrayList<Element> v){
		
		if(node == null){
			return false;
		}
		
		if(node.nrOcc > 0){
			v.add(node.value);
		}

		
		
			for(Trie t : node.children){
				searchElements(t, v);
				
			}
		
		
		return true;
	}
	
	
	
	@Override
	public TrieElement[] getSortedElements(TrieElement prefix) {
		ArrayList<Element> v = new ArrayList<>();
		
		char[] key = prefix.toCharArray();
		int length = key.length;
		int index;
		
		Trie node = this;
		
		for(int level = 0; level < length && node != null; ++level){
			index = Element.CharToIndex(key[level]);
			
			node = node.children[index];
		}
		

		if(node == null)
			return new Element[0];

		
		searchElements(node, v);
		
		
		return v.toArray(new Element[v.size()]);
		
	}

}
