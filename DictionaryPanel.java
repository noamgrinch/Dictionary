import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * The GUI of the dictionary.
 * 
 */
public class DictionaryPanel extends JPanel{
	
	protected Dictionary _dic;
	private JButton SearchBtn,RemoveBtn,UpdateBtn,FileBtn,AddBtn,ClearBtn;
	private DefaultTableModel model;
	private JTable table;
	private String[] _columns = {"Word","Definition"};
	private Object[] _row = new Object[2];
	private MyOptionPane GetWordMeaningPane = new MyOptionPane();
	private MyOptionPaneSingle GetWordPane = new MyOptionPaneSingle();
	private static boolean search=false; //to present a searched word or not.
	
	public DictionaryPanel(){
		//controls
		SearchBtn = new JButton("Search");
		RemoveBtn = new JButton("Remove");
		UpdateBtn = new JButton("Update");
		FileBtn = new JButton("Load File");
		AddBtn = new JButton("Add");
		ClearBtn = new JButton("Clear");
		SearchBtn.addActionListener(new Action());
		RemoveBtn.addActionListener(new Action());
		UpdateBtn.addActionListener(new Action());
		FileBtn.addActionListener(new Action());
		AddBtn.addActionListener(new Action());
		ClearBtn.addActionListener(new Action());
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(2, 3, 10, 10));
		controls.add(SearchBtn);
		controls.add(RemoveBtn);
		controls.add(UpdateBtn);
		controls.add(FileBtn);
		controls.add(AddBtn);
		controls.add(ClearBtn);
		
		this.setLayout(new BorderLayout());
		
		this.add(controls,BorderLayout.SOUTH);
		//controls
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null); //so user cannot edit just like THAT.
		_dic = new Dictionary();
		JPanel tab = new JPanel(); //will contain the table with the words and meanings
		tab.setLayout(new FlowLayout());
		model = new DefaultTableModel(); //to manage the table.
		model.setColumnIdentifiers(_columns);
		table.setModel(model);
		tab.add(table);
		table.setFillsViewportHeight(true);
		tab.add(new JScrollPane(table));

		this.add(tab);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(search==false){
			for(Map.Entry<String,String> entry : _dic.getTree().entrySet()) {
				String word = entry.getKey();
				String meaning = entry.getValue();
				_row[0]= word;
				_row[1] = meaning;	  
				model.addRow(_row);
			}
		}
		else {
			model.addRow(_row);
			}
		search=false;
	}
	
	public void PaintSearchedWord(String word){
		search=true;
		_row[0]=word;
		_row[1]=_dic.getTree().get(word);
		
	}
	
	public void ClearTable(){
	    for (int i = model.getRowCount() - 1; i > -1; i--) {
	        model.removeRow(i);
	    }
	}

	

	private class Action implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ClearBtn){ //works
				_dic.getTree().clear();
			    ClearTable();
				repaint();
			}//Clear
			if(e.getSource()==AddBtn){
					
				int result = JOptionPane.showConfirmDialog(null,GetWordMeaningPane , 
						"Please Enter a word and its meaning Values", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if(!GetWordMeaningPane.GetWordPane().equals("")){ //empty strings are not allowed in the Dictionary fam.
						_dic.AddBlock(GetWordMeaningPane.GetWordPane(),GetWordMeaningPane.GetMeaningPane());
					}
					GetWordMeaningPane.Zerorize();
					ClearTable();
					repaint();
					}
			}//Add
			if(e.getSource()==RemoveBtn){
				int result = JOptionPane.showConfirmDialog(null,GetWordPane , 
							"Please Enter a word to remove.", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {		
					if(_dic.ContainsKey(GetWordPane.GetWordPane())){ 
						_dic.getTree().remove(GetWordPane.GetWordPane());
						GetWordPane.Zerorize();
						ClearTable();
						repaint();
					}
					else {
						GetWordPane.Zerorize();
						JOptionPane.showMessageDialog(null,"Cannot find word.","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
				}
		
			}//Remove
			if(e.getSource()==SearchBtn){ //keeps popping the joption wtf fam
				int result = JOptionPane.showConfirmDialog(null,GetWordPane , 
						"Please Enter a word to be searched:", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {		
					if(_dic.ContainsKey(GetWordPane.GetWordPane())){ 
						PaintSearchedWord(GetWordPane.GetWordPane());
						GetWordPane.Zerorize();
						ClearTable();
						repaint();
					}
								
					else {
						GetWordPane.Zerorize();
						JOptionPane.showMessageDialog(null,"Cannot find word.","Error!",JOptionPane.INFORMATION_MESSAGE);
						}
					}
			
			}//Search
			if(e.getSource()==FileBtn){
				int result = JOptionPane.showConfirmDialog(null,GetWordPane , 
						"Please enter the path of the file:", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION){
					_dic.AddFileDictionary(GetWordPane.GetWordPane());
					GetWordPane.Zerorize();
					ClearTable();
					repaint();
				}
			}//File
			if(e.getSource()==UpdateBtn){
				int result = JOptionPane.showConfirmDialog(null,GetWordPane , 
						"Please Enter a word to update:", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {		
					if(_dic.ContainsKey(GetWordPane.GetWordPane())){ 
						PaintSearchedWord(GetWordPane.GetWordPane());
						ClearTable();
						repaint();
						GetWordMeaningPane.SetWordPane(GetWordPane.GetWordPane(), _dic.getTree().get(GetWordPane.GetWordPane()));
						_dic.getTree().remove(GetWordPane.GetWordPane());
						int result1 = JOptionPane.showConfirmDialog(null,GetWordMeaningPane , 
								"Update:", JOptionPane.OK_CANCEL_OPTION);
						if (result1 == JOptionPane.OK_OPTION) {
							if(!GetWordMeaningPane.GetWordPane().equals("")){ //empty strings are not allowed in the Dictionary fam.
								_dic.AddBlock(GetWordMeaningPane.GetWordPane(),GetWordMeaningPane.GetMeaningPane());
							}
							GetWordMeaningPane.Zerorize();
							GetWordPane.Zerorize();
							ClearTable();
							repaint();
							}
						
					}
								
					else {
						GetWordPane.Zerorize();
						JOptionPane.showMessageDialog(null,"Cannot find word.","Error!",JOptionPane.INFORMATION_MESSAGE);
						}
					}
			}
				 //continue from here
		}//ActionPerformed
		
	}//ActionListener class
}
