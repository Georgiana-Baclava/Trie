package trie;

import test.Command;
import test.TestReader;
import test.TestWriter;

public class Main {

	
	public static void main(String[] args){
		
		TestReader r = new TestReader("/home/bgl/workspace/Trie/src/trie.in");
		TestWriter w = new TestWriter("/home/bgl/workspace/Trie/src/trie.out");
		
		Trie t1 = new Trie();
		Trie t2 = new Trie();
		
		
		//citim prima linie si o adaugam in ambele trie-uri
		String[] str = r.getWords();
		int n = str.length;
		for(int i = 0; i < n; i++){
			//adaug fiecare cuvant din propozitie
			TrieElement e1 = new ElementType1(str[i]);
			t1.add(e1);
			
			TrieElement e2 = new ElementType2(str[i]);
			t2.add(e2);
		}
		
		
		
		//operatii pe primul trie
		Command[] fc = r.getFirstCommands();
		for(int i = 0; i < fc.length; i++){
			TrieElement e = new ElementType1(fc[i].getWord());
			int type = fc[i].getType();
			switch(type){
				case Command.ADD:
					t1.add(e);
					break;
				case Command.REMOVE:
					t1.remove(e);
					break;
				case Command.COUNT:
					w.printCount(t1.count(e));
					break;
				case Command.LIST:
					w.printSortedWords(t1.getSortedElements(e));
					break;
			}
		}
		
		
		
		//operatii pe al doilea trie
		Command[] sc = r.getSecondCommands();
		for(int i = 0; i < sc.length; i++){
			TrieElement e = new ElementType2(sc[i].getWord());
			int type = sc[i].getType();
			switch(type){
				case Command.ADD:
					t2.add(e);
					break;
				case Command.REMOVE:
					t2.remove(e);
					break;
				case Command.COUNT:
					w.printCount(t2.count(e));
					break;
				case Command.LIST:
					w.printSortedWords(t2.getSortedElements(e));
					break;
			}
		}
		
		w.close();
	}
}
