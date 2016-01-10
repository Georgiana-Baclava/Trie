package trie;


public abstract class Element implements TrieElement, Comparable {

	protected String word;
	

	public Element(){}

	public Element(String word){
		this.word = word;
	}
	
	public static int CharToIndex(char c){
		
		switch(c){
			case '!': return 0;
			case '(': return 1;
			case ')': return 2;
			case '?': return 3;
			case '-': return 4;
			case '_': return 15;
		}
		
		if(Character.isDigit(c))
			return 5+(c-'0');
		if(Character.isUpperCase(c))
			return 16+(c-'A');
		else if(Character.isLowerCase(c))
			return 43+(c-'a');
		
		return -1;
	}
	
	public String toString(){
		return word;
	}
	
	public int compareTo(Object obj){
		return word.compareTo(((Element)obj).word);
	}
}
