package sinpaleta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Ventana extends JFrame implements ActionListener {
    private JPanel panel1, panel2;
    private JButton btn1, btn2, btn3;
    private JTextField username, newUser;
    private JPasswordField pass;
    private JList<String> list;
    private JTextArea text;

    private static void panelAddAll(JPanel panel, Component... components) {
        for (Component component : components) {
            panel.add(component);
        }
    }

    void mostrarVentana() {

        setSize(400, 300);
        panel1 = logInPanel();
        panel2 = listPanel();
        add(panel1);

        setVisible(true);
        setDefaultCloseOperation(3);
    }

    private JPanel logInPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usrLbl = new JLabel("Usuario (fran)");
        usrLbl.setBounds(50, 50, 100, 25);

        username = new JTextField();
        username.setBounds(205, 50, 145, 25);

        JLabel passLbl = new JLabel("Clave (clave)");
        passLbl.setBounds(50, 100, 100, 25);

        pass = new JPasswordField();
        pass.setBounds(205, 100, 145, 25);

        btn1 = new JButton("ENTRAR");
        btn1.setBounds(50, 200, 145, 25);
        btn1.addActionListener(this);

        btn2 = new JButton("LIMPIAR");
        btn2.setBounds(205, 200, 145, 25);
        btn2.addActionListener(this);

        panelAddAll(panel, usrLbl, username, passLbl, pass, btn1, btn2);

        return panel;
    }

    private JPanel listPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        String[] cursos = {"DAM 1", "DAM 2", "ASIR 1", "ASIR 2"};
        list = new JList<>(cursos);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setBounds(25, 25, 75, 200);


        newUser = new JTextField();
        newUser.setBounds(145, (getHeight() / 2) - 50, 80, 25);

        btn3 = new JButton("Agregar");
        btn3.setBounds(145, getHeight() / 2, 80, 25);
        btn3.addActionListener(this);


        text = new JTextArea();
        JScrollPane textScroller = new JScrollPane(text);
        textScroller.setBounds(getWidth() - 135, 25, 100, 200);

        panelAddAll(panel, newUser, listScroller, btn3, textScroller);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object btn = actionEvent.getSource();
        if (btn == btn1) {
            if ("fran".equals(username.getText()) && Arrays.equals("clave".toCharArray(), pass.getPassword())) {
                remove(panel1);
                add(panel2);
                setVisible(true);
            } else JOptionPane.showMessageDialog(this, "Acceso Denegado");
        } else if (btn == btn2) {
            username.setText("");
            pass.setText("");
        } else if (btn == btn3) {
            if (list.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes elegir 1 curso");
            } else if (newUser.getText().length() < 2) {
                JOptionPane.showMessageDialog(this, "Nombre invalido");
            } else {
                text.append(newUser.getText() + " " + list.getSelectedValue() + "\n");
            }
        }
    }
}
