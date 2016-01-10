package trie;


public class ElementType1 extends Element {

	
	public ElementType1(){}
	
	
	public ElementType1(String word){
		super(word);
	}
	
	
	public char[] toCharArray(){
		return word.toLowerCase().toCharArray();
	}
	
}
