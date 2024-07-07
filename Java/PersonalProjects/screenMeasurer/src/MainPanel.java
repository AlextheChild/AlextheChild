import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    JLabel coordLabel;
    int width = 1440, height = 900;
    int rightMargin = 70, bottomMargin = 30;

    public MainPanel() {
        coordLabel = new JLabel("", SwingConstants.CENTER);
        coordLabel.setForeground(new Color(255, 255, 255, 200));
        coordLabel.setFont(new Font("Monospace", Font.BOLD, 12));

        this.setLayout(null);
        this.add(coordLabel);
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        this.setOpaque(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
    }

    // ————— screenshot ————— //
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // ————— coord display ————— //

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        coordLabel.setText(x + ", " + y);

        // marginalization
        if (x > width - rightMargin) {
            x -= rightMargin;
        }
        if (y > height - bottomMargin) {
            y -= bottomMargin;
        }
        coordLabel.setBounds(x, y, 70, 30);
    }

    // ————— exiting ————— //

    @Override
    public void mouseClicked(MouseEvent e) {
        System.exit(0);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) {
            System.exit(0);
        }
    }

    // ————— hell ————— //
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}