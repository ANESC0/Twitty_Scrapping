package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Results extends JPanel implements ActionListener {

    String recherche;
    JPanel panel;

    public Results(String r){
        this.recherche = r;

        this.panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(this.recherche);

        panel.add(label);

        this.add(panel);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
