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

    public GestionCuenta(){
        setTitle("Gestion de Cuenta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);

        //Se coloca un limite invisible en mitad de la ventana para poder tener los implementos de cambiar usuario y contraseña en el otro lado
        setLayout(new BorderLayout());
        JPanel panelPerfil = new JPanel();
        panelPerfil.setLayout(new BorderLayout());

        //Se define la foto por defecto desde el principio
        lblFotodePerfil= new JLabel(new ImageIcon("DEFAULT_PROFILE_IMAGES/img.png"));
        lblFotodePerfil.setHorizontalAlignment(JLabel.CENTER);
        lblFotodePerfil.setVerticalAlignment(JLabel.CENTER);

        btnActualizarFoto = new JButton("Cargar Imagen");
        btnActualizarFoto.setBackground(new Color(0,204,118));
        btnActualizarFoto.setForeground(Color.WHITE);
        btnActualizarFoto.setFont(new Font("Poppins",Font.BOLD,16));

        /* Se crea un ActionListener para que pueda cargar la imagen y ademas esta misma se ajuste a las dimensiones de la
        foto por default*/
        btnActualizarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser= new JFileChooser();
                int resultado= fileChooser.showOpenDialog(GestionCuenta.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivoseleccionado= fileChooser.getSelectedFile();
                    try {
                        Image imagen = ImageIO.read(archivoseleccionado);
                        ImageIcon imagenpordefecto=(ImageIcon) lblFotodePerfil.getIcon();

                        int anchopordefecto= imagenpordefecto.getIconWidth();
                        int alturapordefecto= imagenpordefecto.getIconHeight();

                        int ancho= imagen.getWidth(null);
                        int alto= imagen.getHeight(null);

                        if (alto > alturapordefecto || ancho > anchopordefecto) {
                            imagen = imagen.getScaledInstance(alturapordefecto,anchopordefecto, Image.SCALE_SMOOTH);
                        }

                        lblFotodePerfil.setIcon(new ImageIcon(imagen));

                        //lblFotodePerfil.setIcon(new ImageIcon(imagen));. NOTA: Este fue el primer metodo para cargar la imagen pero mantenia las dimensiones originales de la imagen cargada
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }

                }
            }
        });

        btnQuitarFoto=new JButton("Eliminar Imagen");
        btnQuitarFoto.setBackground(new Color(0,204,118));
        btnQuitarFoto.setForeground(Color.WHITE);
        btnQuitarFoto.setFont(new Font("Poppins",Font.BOLD,16));

        //Metodo para eliminar imagen y regresar a la foto original
        btnQuitarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFotodePerfil.setIcon(new ImageIcon("DEFAULT_PROFILE_IMAGES/img.png"));
            }

        });

        panelPerfil.add(lblFotodePerfil, BorderLayout.CENTER);
        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new FlowLayout());
        btnpanel.add(btnActualizarFoto);
        btnpanel.add(btnQuitarFoto);
        panelPerfil.add(btnpanel, BorderLayout.SOUTH);

        add(panelPerfil,BorderLayout.WEST);

        JLabel Perfiltitlelbl= new JLabel("Perfil");
        Perfiltitlelbl.setFont(new Font("Poppins",Font.BOLD,30));
        Perfiltitlelbl.setForeground(new Color(0,0,0));
        Perfiltitlelbl.setHorizontalAlignment(SwingUtilities.CENTER);
        add(Perfiltitlelbl, BorderLayout.NORTH);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionCuenta().setVisible(true);
            }
        });
    }

    
}
