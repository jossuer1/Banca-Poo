import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
    private JTextField textUsuario;
    private JPasswordField textPass;
    private JButton accesoButton;
    private JPanel JPanelLogin;
    private JButton salirButton;

    public login() {
        setTitle("LOGIN");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(JPanelLogin);
        setLocationRelativeTo(null); // centrar
        setVisible(true);

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        accesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Credenciales reales de la tarea
                String userCorrecto = "cliente123";
                String passCorrecta = "clave456";

                String usuarioIngresado = textUsuario.getText();
                String passIngresada = new String(textPass.getPassword());

                if (userCorrecto.equals(usuarioIngresado) &&
                        passCorrecta.equals(passIngresada)) {

                    JOptionPane.showMessageDialog(null, "Acceso permitido");

                    // Abrir la ventana principal del banco
                    BancoForm banco = new BancoForm(usuarioIngresado);
                    banco.setVisible(true);

                    dispose(); // cerrar esta ventana

                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
    }
}
