package trie;


public class ElementType2 extends Element {
		
	public ElementType2(){}
	
	
	public ElementType2(String word){
		super(word);
	}
	
	
	public char[] toCharArray(){
		
		String result = word.replaceAll("[\\-\\_\\(\\)]", "");
		return result.toCharArray();
	}
}
