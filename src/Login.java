import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
   private  JPanel JPanelLogin;
    private JTextField textUsuario;
    private JPasswordField textPass;
    private JButton accesoButton;
    private JButton salirButton;
    private JLabel lblErrorUsuario;
    private JLabel lblErrorPass;
    private JLabel iconUser;
    private JLabel iconPass;

    public Login() {
        setTitle("LOGIN");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        FondoLogin panelFondo = new FondoLogin("src/imagenes/fondo-pantalla.jpg", 0.55f);
        panelFondo.setLayout(null);
        setContentPane(panelFondo);


        JLabel lblTitulo = new JLabel("BANCO EPN");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setBounds(150, 20, 220, 30);
        panelFondo.add(lblTitulo);


        iconUser = new JLabel();
        iconUser.setBounds(80, 80, 32, 32);
        add(iconUser);

        iconPass = new JLabel();
        iconPass.setBounds(80, 130, 32, 32);
        add(iconPass);

        ImageIcon userIcon = new ImageIcon("src/imagenes/user.png");
        userIcon = IconColorizer.toWhite(userIcon);
        userIcon = new ImageIcon(userIcon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH));
        iconUser.setIcon(userIcon);

        ImageIcon passIcon = new ImageIcon("src/imagenes/lock.png");
        passIcon = IconColorizer.toWhite(passIcon);
        passIcon = new ImageIcon(passIcon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH));
        iconPass.setIcon(passIcon);


        textUsuario = new JTextField();
        textUsuario.setBounds(120, 80, 280, 32);
        panelFondo.add(textUsuario);

        textPass = new JPasswordField();
        textPass.setBounds(120, 130, 280, 32);
        panelFondo.add(textPass);

        lblErrorUsuario = new JLabel();
        lblErrorUsuario.setForeground(Color.YELLOW);
        lblErrorUsuario.setBounds(120, 112, 280, 15);
        panelFondo.add(lblErrorUsuario);

        lblErrorPass = new JLabel();
        lblErrorPass.setForeground(Color.YELLOW);
        lblErrorPass.setBounds(120, 162, 280, 15);
        panelFondo.add(lblErrorPass);

        JCheckBox chkRecordar = new JCheckBox("Remember me");
        chkRecordar.setOpaque(false);
        chkRecordar.setForeground(Color.WHITE);
        chkRecordar.setBounds(120, 185, 130, 20);
        panelFondo.add(chkRecordar);

        JLabel lblForgot = new JLabel("Forgot password?");
        lblForgot.setForeground(Color.WHITE);
        lblForgot.setBounds(270, 185, 130, 20);
        panelFondo.add(lblForgot);

        accesoButton = new JButton("LOGIN");
        accesoButton.setBounds(170, 220, 170, 35);
        panelFondo.add(accesoButton);

        salirButton = new JButton("SALIR");
        salirButton.setBounds(200, 265, 110, 28);
        panelFondo.add(salirButton);

        accionesLogin();
    }

    private void accionesLogin() {
        salirButton.addActionListener(e -> dispose());

        accesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                lblErrorUsuario.setText("");
                lblErrorPass.setText("");

                String userCorrecto = "cliente123";
                String passCorrecta = "clave456";

                String usuarioIngresado = textUsuario.getText().trim();
                String passIngresada = new String(textPass.getPassword()).trim();

                boolean valido = true;

                if (usuarioIngresado.isEmpty()) {
                    lblErrorUsuario.setText("Ingrese el usuario");
                    valido = false;
                }

                if (passIngresada.isEmpty()) {
                    lblErrorPass.setText("Ingrese la contraseña");
                    valido = false;
                }

                if (!valido) return;

                if (usuarioIngresado.equals(userCorrecto) && passIngresada.equals(passCorrecta)) {

                    JOptionPane.showMessageDialog(Login.this, "Acceso permitido");

                    // Aquí llamas tu otra ventana
                    BancoForm banco = new BancoForm(usuarioIngresado);
                    banco.setVisible(true);
                     dispose();

                } else {
                    JOptionPane.showMessageDialog(Login.this,
                            "Usuario o contraseña incorrectos",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon icon = new ImageIcon(ruta);
        Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
