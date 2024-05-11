import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PaginaInicio extends JFrame {
    private InicioSesion inicioSesion;
    private String usuario;
    private String contrasena;
    private ActionListener actionListener;

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void addActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    public PaginaInicio(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        setTitle("Página de Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        mostrarInicioSesion();
    }

    private void mostrarInicioSesion() {
        inicioSesion = new InicioSesion();
        inicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inicioSesion.esInicioSesionExitoso()) {
                    String usuario = inicioSesion.getUsuario();
                    String contrasena = inicioSesion.getContrasena();
                    inicioSesion.dispose();
                    mostrarPaginaInicio(usuario, contrasena);
                }
            }
        });
        inicioSesion.setVisible(true);
    }

    private void mostrarPaginaInicio(String usuario, String contrasena) {
        JLabel titleLabel = new JLabel("Página de Inicio");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.setPreferredSize(new Dimension(150, 50));
        registrarButton.setBackground(new Color(0, 204, 118));
        registrarButton.setForeground(Color.WHITE);
        registrarButton.setFont(new Font("Poppins", Font.BOLD, 16));

        JButton gestionCuentaButton = new JButton("Gestionar");
        gestionCuentaButton.setPreferredSize(new Dimension(150, 50));
        gestionCuentaButton.setBackground(new Color(0, 204, 118));
        gestionCuentaButton.setForeground(Color.WHITE);
        gestionCuentaButton.setFont(new Font("Poppins", Font.BOLD, 16));

        JButton listarUsuariosButton = new JButton("Listar Usuarios");
        listarUsuariosButton.setPreferredSize(new Dimension(150, 50));
        listarUsuariosButton.setBackground(new Color(0, 204, 118));
        listarUsuariosButton.setForeground(Color.WHITE);
        listarUsuariosButton.setFont(new Font("Poppins", Font.BOLD, 16));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        buttonsPanel.add(registrarButton);
        buttonsPanel.add(gestionCuentaButton);
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
                registros.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (registros.esRegistroExitoso()) {
                            registros.dispose();
                            mostrarPaginaInicio(usuario, contrasena);
                        }
                    }
                });
                registros.setVisible(true);
            }
        });

        gestionCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionCuenta gestionCuenta = new GestionCuenta(usuario, contrasena);
                gestionCuenta.setVisible(true);
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
            String url = "jdbc:mysql://127.0.0.1:3306/login?user=root&password=root";
            Connection conn = DriverManager.getConnection(url);
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(PaginaInicio.this, "Error al listar usuarios", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String usuario = "usuario_obtenido_del_formulario";
        String contrasena = "contrasena_obtenida_del_formulario";
        SwingUtilities.invokeLater(() -> {
            PaginaInicio paginaInicio = new PaginaInicio(usuario, contrasena);
        });
    }
}

class Registros extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private boolean registroExitoso;
    private ActionListener actionListener;

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
                // Aquí puedes guardar los datos en la base de datos en lugar de un archivo
                // Por ejemplo:
                // 1. Conectar a la base de datos
                // 2. Ejecutar una consulta SQL para insertar los datos en la tabla correspondiente
                registroExitoso = true;
                JOptionPane.showMessageDialog(Registros.this, "Datos guardados con éxito");
                if (actionListener != null) {
                    actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });

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
        loginPanel.add(loginButton, gbc);
        gbc.gridy++;

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

    public boolean esRegistroExitoso() {
        return registroExitoso;
    }

    public void addActionListener(ActionListener listener) {
        actionListener = listener;
    }
}

class InicioSesion extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private boolean inicioSesionExitoso;
    private ActionListener actionListener;
    private String usuario;
    private String contrasena;

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
                if (verificarEnBaseDeDatos(email, password)) {
                    JOptionPane.showMessageDialog(InicioSesion.this, "Inicio de sesión exitoso");
                    usuario = email;
                    contrasena = password;
                    inicioSesionExitoso = true;
                    if (actionListener != null) {
                        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                    }
                } else {
                    JOptionPane.showMessageDialog(InicioSesion.this, "Inicio de sesión fallido. Usuario o contraseña incorrectos.");
                }
            }
        });

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

    private boolean verificarEnBaseDeDatos(String usuario, String contraseña) {
        String url = "jdbc:mysql://127.0.0.1:3306/login?user=root&password=root";
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            pstmt.setString(2, contraseña);
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean hasResult = rs.next();
                System.out.println("INICIO DE SESION EXITOSO");
                return hasResult;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean esInicioSesionExitoso() {
        return inicioSesionExitoso;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void addActionListener(ActionListener listener) {
        actionListener = listener;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InicioSesion::new);
    }
}
