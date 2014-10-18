
import Display.Display_make;
import javax.swing.JFrame;

public class Main
{
    public static void main(String[] args)
    {
        Display_make dmake = new Display_make();
        dmake.setTitle("Test Interface");
        dmake.setSize(300,200);
        dmake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dmake.setVisible(true);
    }
}
