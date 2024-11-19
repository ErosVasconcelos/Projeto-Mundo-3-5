package view;

import javax.swing.*;
import java.awt.*;

public class SaidaFrame extends JDialog {
    public JTextArea texto;

    public SaidaFrame(Frame owner) {
        super(owner, "Saída do Cliente", false);
        setBounds(100, 100, 400, 300);

        texto = new JTextArea();
        texto.setEditable(false);

        add(new JScrollPane(texto), BorderLayout.CENTER);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 

        setLocationRelativeTo(null); 
        setAlwaysOnTop(true); 
        setVisible(true);

    }

    public void fechar() {
        this.dispose();
    }
}
