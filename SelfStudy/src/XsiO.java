/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Renata
 */ import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class XsiO implements ActionListener {
    private JFrame frame;
    private JPanel Panou;
    private JButton[] butoane = new JButton[9];
    private boolean RandulX = true;

    public XsiO() {
        frame = new JFrame("X si O");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panou = new JPanel();
        Panou.setLayout(new GridLayout(3, 3));
        Panou.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            butoane[i] = new JButton();
            butoane[i].setFont(new Font("Arial", Font.PLAIN, 40));
            butoane[i].addActionListener(this);
            Panou.add(butoane[i]);
        }

        frame.add(Panou, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (RandulX) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        button.setEnabled(false);
        RandulX= !RandulX;

        checkForWinner();
    }

    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (butoane[i].getText().equals(butoane[i+1].getText()) && butoane[i].getText().equals(butoane[i+2].getText()) && !butoane[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, butoane[i].getText() + " a castigat!");
                reseteazaGame();
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (butoane[i].getText().equals(butoane[i+3].getText()) && butoane[i].getText().equals(butoane[i+6].getText()) && !butoane[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, butoane[i].getText() + " a castigat!");
                reseteazaGame();
                return;
            }
        }

        // Check diagonals
        if (butoane[0].getText().equals(butoane[4].getText()) && butoane[0].getText().equals(butoane[8].getText()) && !butoane[0].isEnabled()) {
            JOptionPane.showMessageDialog(frame, butoane[0].getText() + " a castigat!");
            reseteazaGame();
            return;
        }
        if (butoane[2].getText().equals(butoane[4].getText()) && butoane[2].getText().equals(butoane[6].getText()) && !butoane[2].isEnabled()) {
            JOptionPane.showMessageDialog(frame, butoane[2].getText() + " a castigat!");
            reseteazaGame();
            return;
        }

       //In caz de egalitate
        boolean egalitate= true;
        for (int i = 0; i < 9; i++) {
            if (butoane[i].isEnabled()) {
                egalitate= false;
                break;
            }
        }
        if (egalitate) {
            JOptionPane.showMessageDialog(frame, "Egalitate!");
            reseteazaGame();
        }
    }

    public void reseteazaGame() {
        for (int i = 0; i < 9; i++) {
            butoane[i].setText("");
            butoane[i].setEnabled(true);
        }
        RandulX = true;
    }

    public static void main(String[] args) {
        new XsiO();
    }
} 

