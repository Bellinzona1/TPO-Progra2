import Modelo.ArbolPersonas;
import Modelo.Persona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbolPersonasGUI extends JFrame {
    private ArbolPersonas arbol;
    private JPanel panelArbol;
    private JTextArea areaRecorridos;
    private JComboBox<String> comboCriterio;

    public ArbolPersonasGUI() {
        setTitle("Árbol Binario de Personas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        arbol = new ArbolPersonas();
        initUI();
    }

    private void initUI() {
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new FlowLayout());

        JTextField txtNombre = new JTextField(8);
        JTextField txtApellido = new JTextField(8);
        JTextField txtEdad = new JTextField(4);
        JTextField txtDni = new JTextField(8);
        JButton btnInsertar = new JButton("Insertar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnRecorridos = new JButton("Mostrar Recorridos");
        comboCriterio = new JComboBox<>(new String[]{"DNI", "NOMBRE"});

        panelControles.add(new JLabel("Nombre:"));
        panelControles.add(txtNombre);
        panelControles.add(new JLabel("Apellido:"));
        panelControles.add(txtApellido);
        panelControles.add(new JLabel("Edad:"));
        panelControles.add(txtEdad);
        panelControles.add(new JLabel("DNI:"));
        panelControles.add(txtDni);
        panelControles.add(btnInsertar);
        panelControles.add(btnEliminar);
        panelControles.add(btnBuscar);
        panelControles.add(btnRecorridos);
        panelControles.add(new JLabel("Criterio:"));
        panelControles.add(comboCriterio);

        areaRecorridos = new JTextArea(8, 60);
        areaRecorridos.setEditable(false);
        JScrollPane scrollRecorridos = new JScrollPane(areaRecorridos);

        panelArbol = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarArbol(g, getWidth() / 2, 40, arbol, 0, getWidth() / 4);
            }
        };
        panelArbol.setPreferredSize(new Dimension(800, 350));
        JScrollPane scrollArbol = new JScrollPane(panelArbol);

        // Acciones
        btnInsertar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                int dni = Integer.parseInt(txtDni.getText());
                arbol.insertar(new Persona(nombre, apellido, edad, dni));
                panelArbol.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.");
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                String criterio = (String) comboCriterio.getSelectedItem();
                Persona p;
                if (criterio.equals("DNI")) {
                    int dni = Integer.parseInt(txtDni.getText());
                    p = new Persona("", "", 0, dni);
                } else {
                    String nombre = txtNombre.getText();
                    p = new Persona(nombre, "", 0, 0);
                }
                arbol.eliminar(p);
                panelArbol.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos para eliminar.");
            }
        });

        btnBuscar.addActionListener(e -> {
            String criterio = (String) comboCriterio.getSelectedItem();
            Persona p;
            if (criterio.equals("DNI")) {
                try {
                    int dni = Integer.parseInt(txtDni.getText());
                    p = new Persona("", "", 0, dni);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "DNI inválido.");
                    return;
                }
            } else {
                String nombre = txtNombre.getText();
                p = new Persona(nombre, "", 0, 0);
            }
            Persona encontrada = arbol.buscar(p);
            if (encontrada != null) {
                JOptionPane.showMessageDialog(this, "Encontrado: " + encontrada.getNombre() + " " + encontrada.getApellido());
            } else {
                JOptionPane.showMessageDialog(this, "No encontrado.");
            }
        });

        btnRecorridos.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Inorden:\n");
            arbol.mostrarInorden(sb);
            sb.append("\nPreorden:\n");
            arbol.mostrarPreorden(sb);
            sb.append("\nPostorden:\n");
            arbol.mostrarPostorden(sb);
            areaRecorridos.setText(sb.toString());
        });

        comboCriterio.addActionListener(e -> {
            String criterio = (String) comboCriterio.getSelectedItem();
            if (criterio.equals("DNI")) {
                arbol.setCriterio(ArbolPersonas.Criterio.DNI);
            } else {
                arbol.setCriterio(ArbolPersonas.Criterio.NOMBRE);
            }
            panelArbol.repaint();
        });

        setLayout(new BorderLayout());
        add(panelControles, BorderLayout.NORTH);
        add(scrollArbol, BorderLayout.CENTER);
        add(scrollRecorridos, BorderLayout.SOUTH);
    }

    // Dibuja el árbol recursivamente
    private void dibujarArbol(Graphics g, int x, int y, ArbolPersonas arbol, int nivel, int separacion) {
        try {
            java.lang.reflect.Field fieldRaiz = ArbolPersonas.class.getDeclaredField("raiz");
            fieldRaiz.setAccessible(true);
            Object nodo = fieldRaiz.get(arbol);
            dibujarNodo(g, x, y, nodo, nivel, separacion);
        } catch (Exception e) {
            // No dibuja nada si hay error
        }
    }

    private void dibujarNodo(Graphics g, int x, int y, Object nodo, int nivel, int separacion) {
        if (nodo == null) return;
        try {
            java.lang.reflect.Method getPersona = nodo.getClass().getMethod("getPersona");
            Object persona = getPersona.invoke(nodo);
            java.lang.reflect.Method getIzquierdo = nodo.getClass().getMethod("getIzquierdo");
            java.lang.reflect.Method getDerecho = nodo.getClass().getMethod("getDerecho");
            Object izq = getIzquierdo.invoke(nodo);
            Object der = getDerecho.invoke(nodo);

            String texto = obtenerTextoPersona(persona);
            int radio = 30;
            g.setColor(Color.CYAN);
            g.fillOval(x - radio / 2, y - radio / 2, radio, radio);
            g.setColor(Color.BLACK);
            g.drawOval(x - radio / 2, y - radio / 2, radio, radio);
            g.drawString(texto, x - radio / 2, y - radio / 2 - 4);

            if (izq != null) {
                g.drawLine(x, y + radio / 2, x - separacion, y + 60 - radio / 2);
                dibujarNodo(g, x - separacion, y + 60, izq, nivel + 1, separacion / 2);
            }
            if (der != null) {
                g.drawLine(x, y + radio / 2, x + separacion, y + 60 - radio / 2);
                dibujarNodo(g, x + separacion, y + 60, der, nivel + 1, separacion / 2);
            }
        } catch (Exception e) {
            // No dibuja nada si hay error
        }
    }

    private String obtenerTextoPersona(Object persona) {
        try {
            java.lang.reflect.Method getNombre = persona.getClass().getMethod("getNombre");
            java.lang.reflect.Method getDni = persona.getClass().getMethod("getDni");
            String nombre = (String) getNombre.invoke(persona);
            int dni = (int) getDni.invoke(persona);
            return nombre + "\n" + dni;
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ArbolPersonasGUI().setVisible(true);
        });
    }
}

