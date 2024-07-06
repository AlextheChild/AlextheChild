import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelThing extends JPanel implements MouseMotionListener, KeyListener {
    JLabel coordLabel;
    int width = 1440, height = 900;
    int rightMargin = 70, bottomMargin = 30;

    public PanelThing() {
        coordLabel = new JLabel("", SwingConstants.CENTER);
        coordLabel.setForeground(new Color(255, 255, 255, 200));
        coordLabel.setFont(new Font("Monospace", Font.BOLD, 12));

        this.setLayout(null);
        this.add(coordLabel);
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        this.setOpaque(false);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        coordLabel.setText(x + ", " + y);

        // marginalization
        if (x > width - rightMargin) {
            x -= 70;
        }
        if (y > height - bottomMargin) {
            y -= 30;
        }
        coordLabel.setBounds(x, y, 70, 30);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}