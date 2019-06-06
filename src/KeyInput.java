
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private boolean shiftPressed;

    public KeyInput() {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SHIFT)
            shiftPressed = true;

        switch (key) {
            case KeyEvent.VK_W:
                main.turn('w');
                break;
            case KeyEvent.VK_A:
                main.turn('a');
                break;
            case KeyEvent.VK_S:
                main.turn('s');
                break;
            case KeyEvent.VK_D:
                main.turn('d');
                break;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SHIFT)
            shiftPressed = false;
    }
}
