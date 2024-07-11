import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class MainPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    final int width = 1440, height = 900;
    final int rightMargin = 70, bottomMargin = 30;
    final String saveFolderPath = "C:/Users/Alex/Desktop/";

    JLabel coordLabel;

    boolean dragging = false;
    int beforeDraggedX, beforeDraggedY;
    int dragOffsetX, dragOffsetY;

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (dragging) {
            // ! negatives
            g.setColor(new Color(255, 255, 255, 150));
            g.fillRect(beforeDraggedX, beforeDraggedY, dragOffsetX, dragOffsetY);
            g.setColor(new Color(255, 255, 255, 200));
            g.drawRect(beforeDraggedX, beforeDraggedY, dragOffsetX, dragOffsetY);
        }
    }

    // ————— screenshot ————— //
    @Override
    public void mousePressed(MouseEvent e) {
        dragging = true;
        beforeDraggedX = e.getX();
        beforeDraggedY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragOffsetX = e.getX() - beforeDraggedX;
        dragOffsetY = e.getY() - beforeDraggedY;

        displayNums(dragOffsetX, dragOffsetY);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        repaint();

        try {
            takeScreenshot(beforeDraggedX, beforeDraggedY, dragOffsetX, dragOffsetY);
        } catch (Exception ex) {
            System.out.println("Error taking screenshot");
        }

        System.exit(0);
    }

    public void takeScreenshot(int x, int y, int w, int h) throws AWTException, IOException {
        // ! opacity
        // ! copy to clipboard
        // take screenshot
        Robot r = new Robot();
        BufferedImage image = r.createScreenCapture(new Rectangle(x, y, w, h));

        // save
        ImageIO.write(image, "png", new File(saveFolderPath + "screenshot" + System.currentTimeMillis() + ".png"));

        // copy to clipboard
        new ImageCopier(image);
    }

    // ————— coord display ————— //

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        displayNums(x, y);
    }

    public void displayNums(int num1, int num2) {
        coordLabel.setText(num1 + ", " + num2);

        // marginalization
        if (num1 > width - rightMargin) {
            num1 -= rightMargin;
        }
        if (num2 > height - bottomMargin) {
            num2 -= bottomMargin;
        }
        coordLabel.setBounds(num1, num2, 70, 30);
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