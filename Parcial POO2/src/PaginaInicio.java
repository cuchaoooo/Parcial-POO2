import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PaginaInicio extends JFrame {

    public PaginaInicio() {
        setTitle("Página de Entrada");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JLabel titleLabel = new JLabel("Página de Inicio");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.setPreferredSize(new Dimension(150, 50));
        registrarButton.setBackground(new Color(0, 204, 118));
        registrarButton.setForeground(Color.WHITE);
        registrarButton.setFont(new Font("Poppins", Font.BOLD, 16));

        JButton iniciarSesionButton = new JButton("Iniciar Sesión");
        iniciarSesionButton.setPreferredSize(new Dimension(150, 50));
        iniciarSesionButton.setBackground(new Color(0, 204, 118));
        iniciarSesionButton.setForeground(Color.WHITE);
        iniciarSesionButton.setFont(new Font("Poppins", Font.BOLD, 16));

        JButton listarUsuariosButton = new JButton("Listar Usuarios");
        listarUsuariosButton.setPreferredSize(new Dimension(150, 50));
        listarUsuariosButton.setBackground(new Color(0, 204, 118));
        listarUsuariosButton.setForeground(Color.WHITE);
        listarUsuariosButton.setFont(new Font("Poppins", Font.BOLD, 16));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        buttonsPanel.add(registrarButton);
        buttonsPanel.add(iniciarSesionButton);
        buttonsPanel.add(listarUsuariosButton);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 204, 118));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);
        backgroundPanel.add(buttonsPanel, BorderLayout.CENTER);

        add(backgroundPanel);

        setLocationRelativeTo(null);
        setVisible(true);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registros registros = new Registros();
                registros.setVisible(true);
            }
        });

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicioSesion inicioSesion = new InicioSesion();
                inicioSesion.setVisible(true);
            }
        });

        listarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarios();
            }
        });
    }

    private void listarUsuarios() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("UsuarioSyC.txt"));
            String line;
            StringBuilder userList = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                userList.append(line).append("\n");
            }
            reader.close();
            JOptionPane.showMessageDialog(PaginaInicio.this, userList.toString(), "Usuarios Registrados", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(PaginaInicio.this, "Error al leer el archivo de usuarios", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaginaInicio::new);
    }
}

class Registros extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public Registros() {
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(903, 800);

        JLabel loginTitleLabel = new JLabel("Registrese");
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

        JLabel forgotPasswordLabel = new JLabel("¿Olvidaste la contraseña?");
        forgotPasswordLabel.setFont(new Font("Poppins", Font.PLAIN, 16));

        JButton loginButton = new JButton("Registrar");
        loginButton.setPreferredSize(new Dimension(312, 62));
        loginButton.setBackground(new Color(0, 204, 118));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Poppins", Font.BOLD, 16));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                guardarEnArchivo(email + "," + password, "UsuarioSyC.txt");
                JOptionPane.showMessageDialog(Registros.this, "Datos guardados con éxito");
            }
        });

        JLabel createAccountLabel = new JLabel("Crear Cuenta");
        createAccountLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
        createAccountLabel.setForeground(new Color(83, 83, 83));

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(30, 10, 10, 10);
        loginPanel.add(loginTitleLabel, gbc);
        gbc.gridy++;
        loginPanel.add(emailField, gbc);
        gbc.gridy++;
        loginPanel.add(passwordField, gbc);
        gbc.gridy++;
        loginPanel.add(forgotPasswordLabel, gbc);
        gbc.gridy++;
        loginPanel.add(loginButton, gbc);
        gbc.gridy++;
        loginPanel.add(createAccountLabel, gbc);

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

        loginPanel.setBounds(159, 55, 585, 690);

        add(backgroundPanel);
    }

    private void guardarEnArchivo(String data, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(data + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


