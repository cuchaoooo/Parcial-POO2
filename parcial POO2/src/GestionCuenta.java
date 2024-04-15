import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GestionCuenta extends JFrame {
    private JLabel lblFotodePerfil;
    private JButton btnActualizarFoto;
    private JButton btnQuitarFoto;
    private JButton cambiarContrasenaButton;
    private JButton eliminarCuentaButton;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private String usuario;
    private String contrasena;

    public GestionCuenta(String usuario, final String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;

        setTitle("Gestion de Cuenta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        setLayout(new BorderLayout());
        JPanel panelPerfil = new JPanel();
        panelPerfil.setLayout(new BorderLayout());

        lblFotodePerfil = new JLabel(new ImageIcon("DEFAULT_PROFILE_IMAGES/img.png"));
        lblFotodePerfil.setHorizontalAlignment(JLabel.CENTER);
        lblFotodePerfil.setVerticalAlignment(JLabel.CENTER);

        btnActualizarFoto = new JButton("Cargar Imagen");
        btnActualizarFoto.setBackground(new Color(0, 204, 118));
        btnActualizarFoto.setForeground(Color.WHITE);
        btnActualizarFoto.setFont(new Font("Poppins", Font.BOLD, 16));

        btnActualizarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int resultado = fileChooser.showOpenDialog(GestionCuenta.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivoseleccionado = fileChooser.getSelectedFile();
                    try {
                        Image imagen = ImageIO.read(archivoseleccionado);
                        ImageIcon imagenpordefecto = (ImageIcon) lblFotodePerfil.getIcon();

                        int anchopordefecto = imagenpordefecto.getIconWidth();
                        int alturapordefecto = imagenpordefecto.getIconHeight();

                        int ancho = imagen.getWidth(null);
                        int alto = imagen.getHeight(null);

                        if (alto > alturapordefecto || ancho > anchopordefecto) {
                            imagen = imagen.getScaledInstance(alturapordefecto, anchopordefecto, Image.SCALE_SMOOTH);
                        }

                        lblFotodePerfil.setIcon(new ImageIcon(imagen));

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        btnQuitarFoto = new JButton("Eliminar Imagen");
        btnQuitarFoto.setBackground(new Color(0, 204, 118));
        btnQuitarFoto.setForeground(Color.WHITE);
        btnQuitarFoto.setFont(new Font("Poppins", Font.BOLD, 16));

        btnQuitarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFotodePerfil.setIcon(new ImageIcon("DEFAULT_PROFILE_IMAGES/img.png"));
            }

        });

        cambiarContrasenaButton = new JButton("Cambiar Contraseña");
        cambiarContrasenaButton.setBackground(new Color(0,204,118));
        cambiarContrasenaButton.setForeground(Color.WHITE);
        cambiarContrasenaButton.setFont(new Font("Poppins", Font.BOLD, 16));
        final String[] contrasenaArray = {contrasena};
        cambiarContrasenaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevaContrasena = JOptionPane.showInputDialog(GestionCuenta.this, "Ingrese la nueva contraseña:");
                if (nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
                    contrasenaArray[0] = nuevaContrasena;
                    txtContrasena.setText(contrasenaArray[0]);
                    JOptionPane.showMessageDialog(GestionCuenta.this, "¡La contraseña ha sido cambiada con éxito!");
                } else {
                    JOptionPane.showMessageDialog(GestionCuenta.this, "No se ha ingresado una nueva contraseña.");
                }
            }
        });

        eliminarCuentaButton = new JButton("Eliminar Cuenta");
        eliminarCuentaButton.setBackground(new Color(0,204,118));
        eliminarCuentaButton.setForeground(Color.WHITE);
        eliminarCuentaButton.setFont(new Font("Poppins", Font.BOLD, 16));
        eliminarCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioTemp = usuario;
                String contrasenaTemp = contrasena;
                usuarioTemp = "";
                contrasenaTemp = "";
                txtUsuario.setText("");
                txtContrasena.setText("");
                JOptionPane.showMessageDialog(GestionCuenta.this, "¡La cuenta ha sido eliminada!");
            }
        });

        panelPerfil.add(lblFotodePerfil, BorderLayout.CENTER);
        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new FlowLayout());
        btnpanel.add(btnActualizarFoto);
        btnpanel.add(btnQuitarFoto);
        panelPerfil.add(btnpanel, BorderLayout.SOUTH);

        add(panelPerfil, BorderLayout.WEST);
        JPanel datosPanel = new JPanel(new BorderLayout());
        datosPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JPanel centerPanel = new JPanel(new GridLayout(10, 10, 50, 2));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(160, 0, 0, 0));

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        txtUsuario = new JTextField(usuario);
        txtUsuario.setEditable(false);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        txtContrasena = new JPasswordField(contrasena);
        txtContrasena.setEditable(false);

        centerPanel.add(usuarioLabel);
        centerPanel.add(txtUsuario);
        centerPanel.add(contrasenaLabel);
        centerPanel.add(txtContrasena);

        datosPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(cambiarContrasenaButton, BorderLayout.WEST);
        buttonsPanel.add(eliminarCuentaButton, BorderLayout.EAST);
        datosPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(datosPanel, BorderLayout.CENTER);
        cambiarContrasenaButton.setPreferredSize(btnActualizarFoto.getPreferredSize());

        JLabel Perfiltitlelbl = new JLabel("Perfil");
        Perfiltitlelbl.setFont(new Font("Poppins", Font.BOLD, 30));
        Perfiltitlelbl.setForeground(new Color(0, 0, 0));
        Perfiltitlelbl.setHorizontalAlignment(SwingUtilities.CENTER);
        add(Perfiltitlelbl, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String usuario = "USERBOT";
                String contrasena = "----------";
                new GestionCuenta(usuario, contrasena).setVisible(true);
            }
        });
    }
}