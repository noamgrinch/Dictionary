
/*
 * Used to capture single words by JOption to manipulate.
 */
import javax.swing.*;
public class MyOptionPaneSingle extends JPanel{


	private static final long serialVersionUID = 2L;

		private JTextField _word;
		
		public MyOptionPaneSingle(){
			 _word = new JTextField(15);

		    add(new JLabel("Word"));
		    add(_word);

		}
		
		public String GetWordPane(){
			return _word.getText();
		}
		

		
		public JTextField GetWordField(){
			return _word;
		}

		public void Zerorize(){
			_word.setText("");
		}
}
