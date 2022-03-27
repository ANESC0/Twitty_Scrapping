package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JPanel implements ActionListener{

    private JPanel panel;
    private JPanel panelglobal;
    private TextField tf;

    public Accueil(){

        panel = new JPanel(new BorderLayout());

        JLabel label01= new JLabel("Commencez par entrer le pseudo d'un utilisateur",SwingConstants.CENTER);
        JLabel vide = new JLabel();
        JLabel label02 = new JLabel("Puis lancez la recherche !",SwingConstants.CENTER);

        tf = new TextField("",15);

        JButton btn1 = new JButton("Rechercher");
        btn1.addActionListener(this);

        panel.add(label01,BorderLayout.NORTH);
        panel.add(vide);
        panel.add(tf);
        panel.add(btn1,BorderLayout.EAST);
        panel.add(label02,BorderLayout.SOUTH);

        label01.setFont(new Font("Serif", Font.BOLD,20));
        label01.setBorder(new EmptyBorder(0,0,20,0));

        label02.setBorder(new EmptyBorder(20,0,0,0));
        label02.setFont(new Font("Serif", Font.ITALIC,15));

        panel.setBorder(new EmptyBorder(300,400,200,400));
        this.panelglobal = panel;
        this.add(this.panelglobal);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.remove(this.panelglobal);
        Input i = new Input(tf.getText());
        this.add(i);
        this.validate();
        this.repaint();
    }
}
