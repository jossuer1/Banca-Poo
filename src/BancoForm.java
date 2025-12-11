import javax.swing.*;
import java.awt.*;

public class BancoForm extends JFrame {
    private JPanel panelBanco;
    private JLabel labelUsuario;
    private JLabel labelSaldo;
    private JButton btnDeposito;
    private JButton btnRetiro;
    private JButton btnTransferencia;
    private JButton btnSalir;
    private JTextArea historialArea;
    private JScrollPane scroll;

    private double saldo = 1000.00;

    public BancoForm(String usuario) {

        setTitle("Banco - Operaciones");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        labelUsuario = new JLabel("Cliente: " + usuario);
        labelUsuario.setBounds(20, 20, 300, 30);
        add(labelUsuario);

        labelSaldo = new JLabel("Saldo actual: $" + saldo);
        labelSaldo.setBounds(20, 50, 300, 30);
        add(labelSaldo);

        btnDeposito = new JButton("Depósito");
        btnDeposito.setBounds(20, 100, 150, 40);
        add(btnDeposito);

        btnRetiro = new JButton("Retiro");
        btnRetiro.setBounds(200, 100, 150, 40);
        add(btnRetiro);

        btnTransferencia = new JButton("Transferencia");
        btnTransferencia.setBounds(20, 160, 150, 40);
        add(btnTransferencia);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 160, 150, 40);
        add(btnSalir);

        historialArea = new JTextArea();
        historialArea.setEditable(false);

        scroll = new JScrollPane(historialArea);
        scroll.setBounds(20, 220, 430, 150);
        add(scroll);

        acciones();
    }

    private void acciones() {

        btnDeposito.addActionListener(e -> {
            String valor = JOptionPane.showInputDialog("Cantidad a depositar:");
            if (!entradaValida(valor)) return;

            double monto = Double.parseDouble(valor);
            if (monto <= 0) {
                mensaje("Ingrese un monto mayor a 0");
                return;
            }

            saldo += monto;
            actualizarSaldo();
            historialArea.append("Depósito de $" + monto + "\n");
            mensaje("Depósito exitoso");
        });

        btnRetiro.addActionListener(e -> {
            String valor = JOptionPane.showInputDialog("Cantidad a retirar:");
            if (!entradaValida(valor)) return;

            double monto = Double.parseDouble(valor);
            if (monto <= 0) {
                mensaje("Ingrese un monto mayor a 0");
                return;
            }

            if (monto > saldo) {
                mensaje("Saldo insuficiente");
                return;
            }

            saldo -= monto;
            actualizarSaldo();
            historialArea.append("Retiro de $" + monto + "\n");
            mensaje("Retiro exitoso");
        });

        btnTransferencia.addActionListener(e -> {

            String destinatario = JOptionPane.showInputDialog("Nombre del destinatario:");
            if (destinatario == null || destinatario.isBlank()) {
                mensaje("Ingrese un nombre válido");
                return;
            }

            String valor = JOptionPane.showInputDialog("Monto a transferir:");
            if (!entradaValida(valor)) return;

            double monto = Double.parseDouble(valor);
            if (monto <= 0) {
                mensaje("Ingrese un monto mayor a 0");
                return;
            }

            if (monto > saldo) {
                mensaje("Saldo insuficiente");
                return;
            }

            saldo -= monto;
            actualizarSaldo();
            historialArea.append("Transferencia de $" + monto + " a " + destinatario + "\n");
            mensaje("Transferencia exitosa a " + destinatario + " por $" + monto);
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }

    private boolean entradaValida(String valor) {

        if (valor == null) return false;
        if (valor.isBlank()) {
            mensaje("Ingrese un valor");
            return false;
        }
        if (!valor.matches("[0-9]+(\\.[0-9]+)?")) {
            mensaje("Ingrese solo números");
            return false;
        }
        return true;
    }

    private void mensaje(String m) {
        JOptionPane.showMessageDialog(this, m);
    }

    private void actualizarSaldo() {
        labelSaldo.setText("Saldo actual: $" + saldo);
    }
}
