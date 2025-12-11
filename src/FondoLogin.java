import javax.swing.*;
import java.awt.*;

public class FondoLogin extends JPanel {

    private Image imagen;
    private float opacidad;

    public FondoLogin(String rutaImagen, float opacidadOscuro) {
        imagen = new ImageIcon(rutaImagen).getImage();
        this.opacidad = opacidadOscuro; // 0 = nada oscuro, 1 = totalmente negro
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.black);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}

