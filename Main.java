import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DictionaryPanel p = new DictionaryPanel();
        frame.add(p);
        frame.setSize(500, 500);
        frame.setVisible(true);
    	System.out.println(p._dic.toString());
    }
}
