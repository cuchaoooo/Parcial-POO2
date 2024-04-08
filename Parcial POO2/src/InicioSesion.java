import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class InicioSesion extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public InicioSesion() {
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(903, 800);

        JLabel loginTitleLabel = new JLabel("Log In");
        loginTitleLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        loginTitleLabel.setForeground(new Color(0, 0, 0));
        loginTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 30));
        emailField.setBorder(BorderFactory.createEmptyBorder());
        emailField.setBackground(new Color(255, 255, 255));
        emailField.setFont(new Font("Poppins", Font.PLAIN, 16));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 16));

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setPreferredSize(new Dimension(312, 62));
        loginButton.setBackground(new Color(0, 204, 118));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Poppins", Font.BOLD, 16));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String mensaje = validarCredenciales(email, password);
                JOptionPane.showMessageDialog(InicioSesion.this, mensaje);
            }
        });

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(30, 10, 10, 10); // Ajustar el margen superior del título
        loginPanel.add(loginTitleLabel, gbc); // Agregando el título al panel de inicio de sesión
        gbc.gridy++;
        loginPanel.add(emailField, gbc);
        gbc.gridy++;
        loginPanel.add(passwordField, gbc);
        gbc.gridy++;
        loginPanel.add(loginButton, gbc);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(132, 224, 117));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.add(loginPanel);

        loginPanel.setBounds(159, 55, 585, 400);

        add(backgroundPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String validarCredenciales(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("UsuarioSyC.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedEmail = parts[0].trim();
                    String storedPassword = parts[1].trim();
                    if (email.equals(storedEmail) && password.equals(storedPassword)) {
                        return "Inicio de sesión exitoso";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al leer el archivo de usuarios.";
        }
        return "Inicio de sesión fallido. Usuario o contraseña incorrectos.";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InicioSesion::new);
    }
}

