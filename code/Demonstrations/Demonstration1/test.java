import javax.swing.JFrame;
import javax.swing.JLabel;


public class test extends JFrame
{
  public test()
    {
        super("test GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Empty JFrame"));
        pack();
        setVisible(true);
   }
}
