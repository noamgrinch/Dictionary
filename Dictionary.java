import java.io.File;
import java.util.*;
import java.io.*;

import javax.swing.JOptionPane;
/* this class is a representation of a dictionary implemented by a TreeMap(RB tree).
 * AddFileDictioary method is programmed to work only by one format of files where the odd line is the word and the even line below is the meaning.
 * If the file is not written that way(odd number of lines for a example) it catches the exception and notifies by a JOptionPane.
 */
public class Dictionary { 
	
	private TreeMap<String,String> _DB;

	public Dictionary(){
		_DB = new TreeMap<String,String>();
	}
	
	public void AddFileDictionary(String file){
		String meaning;
		String word;
		try {
			Scanner input = new Scanner(new File(file));

			while(input.hasNext()){
				word=input.nextLine();
				meaning = input.nextLine();
				_DB.put(word.toLowerCase(),meaning.toLowerCase());		
			}
			input.close();
		}
		catch(IOException e){//file not found.
			JOptionPane.showMessageDialog(null,"Cannot open file. Please try again","Error!",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(NoSuchElementException e){ // odd number of lines.
			JOptionPane.showMessageDialog(null,"File is not written in the correct format!","Error!",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e){//General error.
			JOptionPane.showMessageDialog(null,"There was a problem loading the file. Please try again.","Error!",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public String toString(){ //overrides
		for(Map.Entry<String,String> entry : _DB.entrySet()) {
			  String word = entry.getKey();
			  String meaning = entry.getValue();

			  System.out.println(word + " -- " + meaning);
			}
		return "";
	}
	
	public TreeMap<String,String> getTree(){
		return _DB;
	}
	
	public void AddBlock(String word, String meaning){
		_DB.put(word.toLowerCase(),meaning.toLowerCase());
	}
	
	public boolean ContainsKey(String key){
		return _DB.containsKey(key);
	}
}
