import javax.swing.*;
import java.awt.*;
public class gui extends JFrame{

    public gui(){
        JPanel e = new JPanel();
        add(e);
        addKeyListener(new KeyInput());

        e.addKeyListener(new KeyInput());
        setSize(400,400);
        setVisible(true);
    }
}
