/*
 * Used to capture a word and a meaning to manipulate.
 */
import javax.swing.*;
public class MyOptionPane extends JPanel{


	private static final long serialVersionUID = 1L;

		private JTextField _word,_meaning;
		
		public MyOptionPane(){
			 _word = new JTextField(15);
		     _meaning = new JTextField(30);

		    add(new JLabel("Word"));
		    add(_word);
		    add(Box.createHorizontalStrut(15)); // a spacer
		    add(new JLabel("Meaning"));
		    add(_meaning );
		    
		}
		
		public String GetWordPane(){
			return _word.getText();
		}
		
		public String GetMeaningPane(){
			return _meaning.getText();
		}
		
		public JTextField GetWordField(){
			return _word;
		}

		public JTextField GetMeaningField(){
			return _meaning;
		}
		
		public void SetWordPane(String word,String meaning){
			_word.setText(word);
			_meaning.setText(meaning);
		}
		
		public void Zerorize(){
			_word.setText("");
			_meaning.setText("");
		}
}
