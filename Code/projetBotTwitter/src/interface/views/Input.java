package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input extends JPanel implements ActionListener {

    private String pseudo;
    private JPanel panel;
    private JPanel panelbarrecherche;
    private JTextField tf;

    public Input(String text) {

        this.panel = new JPanel(new BorderLayout());
        this.pseudo = text;

        JLabel jlabelvide = new JLabel();
        tf = new JTextField("SELECT ... WHERE ...",15);

        JLabel label01= new JLabel("Entrez la commande souhaitée",SwingConstants.CENTER);
        label01.setFont(new Font("Serif", Font.BOLD,20));
        label01.setBorder(new EmptyBorder(0,0,20,0));

        JLabel label02 = new JLabel("Cliquer ici pour voir la table relationnelle",SwingConstants.CENTER);
        label02.setBorder(new EmptyBorder(20,0,0,0));
        label02.setFont(new Font("Serif", Font.ITALIC,15));

        JButton btn1 = new JButton("Rechercher");
        btn1.addActionListener(this);

        panelbarrecherche = new JPanel();
        panelbarrecherche.add(tf);

        panel.add(label01,BorderLayout.NORTH);
        panel.add(jlabelvide);
        panel.add(panelbarrecherche);
        panel.add(btn1,BorderLayout.EAST);
        panel.add(label02,BorderLayout.SOUTH);

        panel.setBorder(new EmptyBorder(200,400,200,400));

        this.add(panel);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.remove(this.panel);
        Results r = new Results(tf.getText());
        this.add(r);
        this.validate();
        this.repaint();
    }
}
