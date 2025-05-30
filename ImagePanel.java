import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image backgroundImage;
    private float alpha;  // saydamlık değeri (0.0f = tam şeffaf, 1.0f = tam opak)

    public ImagePanel(String imagePath, float alpha) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        this.alpha = alpha;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Alpha değeri ayarla
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // Resmi çiz
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        g2d.dispose();
    }
}
