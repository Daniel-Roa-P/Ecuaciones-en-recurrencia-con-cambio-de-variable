
package recurrenciacambio;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class RecurrenciaCambio extends JFrame implements ActionListener {

    JLabel texto1 = new JLabel("Escoja el tipo de ecuación: ");
    JLabel texto2 = new JLabel("A = ");
    JLabel texto3 = new JLabel("B = ");
    JLabel texto4 = new JLabel("Procedimiento:");
    JLabel solucion = new JLabel();
    JTextArea procedimiento = new JTextArea();
    
    JTextField tf1 = new JTextField("3");
    JTextField tf2 = new JTextField("13");
    
    ButtonGroup  bg = new ButtonGroup();
    JButton boton = new JButton("Calcular Ecuación");
    
    JRadioButton rb1 = new JRadioButton("F(n) = A + B*F(n/2)");
    JRadioButton rb2 = new JRadioButton("F(n) = A^n + B*F(n/2)");
    
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane1 = new JScrollPane();
    JScrollPane scrollPane2 = new JScrollPane();
    JScrollPane scrollPane3 = new JScrollPane();
    
    Casos caso;
    
    double a,b,f;
    
    public static void main(String[] args) {
        
       RecurrenciaCambio rc = new RecurrenciaCambio();
       rc.setBounds(500, 0, 350, 700);
       rc.setTitle("Recurrencias con cambio de variable");
       rc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       rc.setVisible(true);
        
    }

    RecurrenciaCambio(){
        
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(texto1);
        c.add(texto2);
        c.add(texto3);
        c.add(texto4);
        
        c.add(tf1);
        c.add(tf2);
        
        bg.add(rb1);
        bg.add(rb2);
        
        c.add(rb1);
        c.add(rb2);
        
        c.add(boton);
        
        c.add(scrollPane1);
        c.add(scrollPane3);
        
        texto1.setBounds(10, 10, 300, 30);
        texto2.setBounds(10, 150, 300, 30);
        texto3.setBounds(10, 180, 300, 30);
        texto4.setBounds(10, 330, 500, 30);
        
        tf1.setBounds(50, 155, 100, 20);
        tf2.setBounds(50, 185, 100, 20);
        
        rb1.setBounds(10, 50, 300, 30);
        rb1.setBackground(Color.LIGHT_GRAY);
        rb2.setBounds(10, 80, 300, 30);
        rb2.setBackground(Color.LIGHT_GRAY);
        
        boton.setBounds(10, 250, 310, 20);
        boton.addActionListener(this);
        boton.setBackground(Color.GREEN);
        
        scrollPane.setBounds(10, 280, 1000, 300);
        scrollPane.setPreferredSize(new Dimension(1000, 300));  
        scrollPane.setBackground(Color.LIGHT_GRAY);
        
        scrollPane1.setBounds(10, 280, 310, 50);
        scrollPane1.setPreferredSize(new Dimension(310, 50));
        scrollPane1.setBackground(Color.BLUE);
        
        scrollPane2.setBounds(10, 280, 500, 1000);
        scrollPane2.setPreferredSize(new Dimension(500, 1000));  
        scrollPane2.setBackground(Color.LIGHT_GRAY);
        
        scrollPane3.setBounds(10, 360, 310, 290);
        scrollPane3.setPreferredSize(new Dimension(310, 330));
        scrollPane3.setBackground(Color.BLUE);
        
    }
    
    public void mostrarResultado(){
        
        scrollPane.removeAll();
         
        a = Double.parseDouble(tf1.getText());
        b = Double.parseDouble(tf2.getText());

        solucion.setText(caso.obtenerFormula(a, b));
        solucion.setBounds(2, 2, 500, 30);

        scrollPane.add(solucion);
        scrollPane.repaint();

        scrollPane1.setViewportView(scrollPane);
            
    }
    
    public void mostrarProcedimiento(){
        
        scrollPane2.removeAll();

        procedimiento.setText(caso.obtenerProcedimiento(a, b));
        procedimiento.setBounds(0, 0, 350, 1000);

        scrollPane2.add(procedimiento);
        scrollPane2.repaint();

        scrollPane3.setViewportView(scrollPane2);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == boton && rb1.isSelected()){

            System.out.println("primer caso");
            
            caso = new CasoUno();
            mostrarResultado();
            mostrarProcedimiento();
            
        } else if(e.getSource() == boton && rb2.isSelected()){
        
           System.out.println("segundo caso");
            
           caso = new CasoDos();
           mostrarResultado();
           mostrarProcedimiento();    
           
        } 
        
    }
    
}
