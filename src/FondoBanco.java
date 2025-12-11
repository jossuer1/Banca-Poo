import javax.swing.*;
import java.awt.*;

public class FondoBanco extends JPanel {

    private Image imagen;
    private float opacidad;

    public FondoBanco(String ruta, float opacidadOscuro) {
        imagen = new ImageIcon(ruta).getImage();
        opacidad = opacidadOscuro;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.BLACK);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}
