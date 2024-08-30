package Meeting;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class button extends JButton {
    public button(String text) {
        super(text);
        setOpaque(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setBackground(new Color(0x2E3B55));
        setFont(new Font("Arial", Font.BOLD, 13));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape border = new RoundRectangle2D.Double(0, 0, getWidth() -2, getHeight() -3, 20, 20);
        g2.setStroke(new BasicStroke(3f));
        g2.setColor(getBackground());
        g2.fill(border);
        g2.setColor(getForeground());
        g2.draw(border);
        g2.dispose();
        super.paintComponent(g);
    }


}
