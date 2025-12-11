import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BancoForm extends JFrame {

    private JPanel panelBanco;
    private JLabel labelUsuario;
    private JLabel labelSaldo;
    private JButton btnDeposito;
    private JButton btnRetiro;
    private JButton btnTransferencia;
    private JButton btnSalir;
    private JTextArea historialArea;

    private double saldo = 1000.00;

    public BancoForm(String usuario) {

        setTitle("Banco - Bienvenido");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(panelBanco);

        // Mostrar datos iniciales
        labelUsuario.setText("Cliente: " + usuario);
        labelSaldo.setText("Saldo: $" + saldo);

        // BOTÓN DEPOSITO
        btnDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String valor = JOptionPane.showInputDialog("Ingrese cantidad a depositar:");
                if (valor == null || valor.isEmpty()) return;

                double monto = Double.parseDouble(valor);
                saldo += monto;

                actualizarSaldo();
                agregarHistorial("Depósito de $" + monto);
            }
        });

        // BOTÓN RETIRO
        btnRetiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String valor = JOptionPane.showInputDialog("Ingrese cantidad a retirar:");
                if (valor == null || valor.isEmpty()) return;

                double monto = Double.parseDouble(valor);

                if (monto > saldo) {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                    return;
                }

                saldo -= monto;

                actualizarSaldo();
                agregarHistorial("Retiro de $" + monto);
            }
        });

        // BOTÓN TRANSFERENCIA
        btnTransferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String destinatario = JOptionPane.showInputDialog("Nombre del destinatario:");
                if (destinatario == null || destinatario.isEmpty()) return;

                String valor = JOptionPane.showInputDialog("Monto a transferir:");
                if (valor == null || valor.isEmpty()) return;

                double monto = Double.parseDouble(valor);

                if (monto > saldo) {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                    return;
                }

                saldo -= monto;

                actualizarSaldo();
                agregarHistorial("Transferencia de $" + monto + " a " + destinatario);
                JOptionPane.showMessageDialog(null, "Transferencia exitosa a " + destinatario);
            }
        });

        // BOTÓN SALIR
        btnSalir.addActionListener(e -> System.exit(0));

    }

    private void actualizarSaldo() {
        labelSaldo.setText("Saldo: $" + saldo);
    }

    private void agregarHistorial(String mensaje) {
        historialArea.append(mensaje + "\n");
    }
}
