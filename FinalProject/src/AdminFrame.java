
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class AdminFrame extends JFrame {

    private JLabel angajatLabel;
    private JLabel meniuLabel;
    private JTextArea angajatiTextArea;
    private JTextArea meniuTextArea;
    private JButton[] b;
    private StringBuilder meniuText;
    private GestorEvenimenteAdF ec;
    private String itemName;
    private String tipProdus;
    private HashMap<String,Double> pretProduse;
    private HashMap<String, String> meniuProduse;
    private String[] angajati;
    private StringBuilder angajatiText;

    public AdminFrame(HashMap<String, String> meniuProduse,HashMap<String,Double>pretProduse,String[] angajati ){

        this.angajati = angajati;
        this.meniuProduse = meniuProduse;
        this.pretProduse = pretProduse;
        meniuText = new StringBuilder();
        salveaza_meniu();
        ec = new GestorEvenimenteAdF();
        
        angajatLabel = new JLabel("Lista Angajati");
        angajatLabel.setFont(new Font("Arial", Font.BOLD, 16));
        meniuLabel = new JLabel("Meniu");
        meniuLabel.setFont(new Font("Arial", Font.BOLD, 16));

        setTitle("Admin Menu");
        setSize(400, 400);
        setLocationRelativeTo(null);

        meniuTextArea = new JTextArea("");
        meniuTextArea.setEditable(false);
        meniuTextArea.setText(meniuText.toString());
        JScrollPane meniuScrollPane = new JScrollPane(meniuTextArea);

        angajatiText = new StringBuilder();
        angajatiTextArea = new JTextArea("");
        angajatiTextArea.setEditable(false);
        salveaza_lista_angajati();
        JScrollPane angajatiScrollPane = new JScrollPane(angajatiTextArea);
        


        JPanel butoanePanel = new JPanel(new GridLayout(5, 1, 10, 10));
        String s[] = {"Adauga produs", "Sterge Produs", "Modifica Produs", "Adauga Angajat", "Sterge Angajat"};
        b = new JButton[5];
        for (int i = 0; i < b.length; i++) {
            b[i] = new JButton(s[i]);
            b[i].addActionListener(ec);
            butoanePanel.add(b[i]);
        }

        meniuText = new StringBuilder();
        
        
        

        JPanel meniuPanel = new JPanel(new BorderLayout());
        meniuPanel.add (meniuLabel, BorderLayout.NORTH);
        meniuPanel.add(meniuScrollPane, BorderLayout.CENTER);

        JPanel angajatiPanel = new JPanel(new BorderLayout());
        angajatiPanel.add (angajatLabel, BorderLayout.NORTH);
        angajatiPanel.add(angajatiScrollPane, BorderLayout.CENTER);
        angajatiTextArea.setText(angajatiText.toString());
        angajatiTextArea.setVisible(true);

        

        JPanel contentPane = new JPanel(new GridLayout(1, 3));
        contentPane.add(angajatiPanel);
        contentPane.add(meniuPanel);
        contentPane.add(butoanePanel);

        setContentPane(contentPane);
        JFrame fr = new JFrame();
    }

    private class GestorEvenimenteAdF implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b[0]) {
                itemName = JOptionPane.showInputDialog("Introdu numele noului produs:");
                tipProdus = JOptionPane.showInputDialog("Specifica tipul produsului:");
                double pret = Double.parseDouble(JOptionPane.showInputDialog("Specifica pretul produsului:"));
                
                if (tipProdus.equals("aperitiv")) {
                    meniuProduse.put(itemName, "Aperitiv");
                    pretProduse.put(itemName, pret);
                    salveaza_meniu();
                } else if (tipProdus.equals("principal")) {
                    meniuProduse.put(itemName, "Principal");
                    pretProduse.put(itemName, pret);
                    salveaza_meniu();
                    

                } else if (tipProdus.equals("desert")) {
                    meniuProduse.put(itemName, "Desert");
                    pretProduse.put(itemName, pret);
                    salveaza_meniu();

                }
                else if (tipProdus.equals("racoritoare")){
                    meniuProduse.put(itemName, "Racoritoare");
                    pretProduse.put(itemName, pret);
                    salveaza_meniu();
                }
                meniuTextArea.setText(meniuText.toString());
            } else if (e.getSource() == b[1]) {
                itemName = JOptionPane.showInputDialog("Introdu produsul care trebuie sters:");
                meniuProduse.remove(itemName);
                pretProduse.remove(itemName);
                salveaza_meniu();
                meniuTextArea.setText(meniuText.toString());
            } else if (e.getSource() == b[2]) {
                itemName = JOptionPane.showInputDialog("Introdu produsul care trebuie modificat:");
                tipProdus = JOptionPane.showInputDialog("Specifica noul tip al produsului:");
                String newItemName = JOptionPane.showInputDialog("Introdu noua denumire a produsului:");
                double pretNou = Double.parseDouble(JOptionPane.showInputDialog("Specifica noul pret al produsului:"));
                
                for (Map.Entry<String, String> set
                        : meniuProduse.entrySet()) {
                    if (set.getKey().equals(itemName)) {
                        meniuProduse.remove(itemName);
                        pretProduse.remove(itemName);
                        if (tipProdus.equals("aperitiv")) {
                            meniuProduse.put(newItemName, "Aperitiv");
                            pretProduse.put(newItemName, pretNou);
                            salveaza_meniu();

                        } else if (tipProdus.equals("principal")) {
                            meniuProduse.put(newItemName, "Principal");
                            pretProduse.put(newItemName, pretNou);
                            salveaza_meniu();

                        } else if (tipProdus.equals("desert")) {
                            meniuProduse.put(newItemName, "Desert");
                            pretProduse.put(newItemName, pretNou);
                            salveaza_meniu();

                        }else if (tipProdus.equals("racoritoare")) {
                            meniuProduse.put(newItemName, "Racoritoare");
                            pretProduse.put(newItemName, pretNou);
                            salveaza_meniu();

                        }
                        meniuTextArea.setText(meniuText.toString());
                        break;
                    }
                }

            }
            else if(e.getSource() == b[3])
            {
                String numeAngajat = JOptionPane.showInputDialog("Introdu numele noului chelner:");
                for(int i=0;i<angajati.length;i++)
                    if(angajati[i].equals(""))
                    {
                        angajati[i] = numeAngajat;
                        salveaza_lista_angajati();
                        break;
                    }
            }
            else if(e.getSource() == b[4])
            {
                String numeAngajat = JOptionPane.showInputDialog("Introdu numele chelerului pe care vrei sa il elimini:");
                for(int i=0;i<angajati.length;i++)
                    if(angajati[i].equals(numeAngajat))
                    {
                        angajati[i] = "";
                        salveaza_lista_angajati();
                        break;
                    }
            }
        }
    }

    public void salveaza_meniu()
    {
        meniuText.setLength(0);
        meniuText.append("Aperitive:\n");
        for (Map.Entry<String, String> set :
             meniuProduse.entrySet())
        {
            if(set.getValue().equals("Aperitiv"))
                meniuText.append("- " + set.getKey()+ " - " + pretProduse.get(set.getKey()) +" lei\n");
        }
        meniuText.append("\nMese Principale:\n");
        for (Map.Entry<String, String> set :
             meniuProduse.entrySet())
        {
            if(set.getValue().equals("Principal"))
                meniuText.append("- " + set.getKey()+ " - " + pretProduse.get(set.getKey()) +" lei\n");
        }
        meniuText.append("\nDeserturi:\n");
        for (Map.Entry<String, String> set :
             meniuProduse.entrySet())
        {
            if(set.getValue().equals("Desert"))
                meniuText.append("- " + set.getKey()+ " - " + pretProduse.get(set.getKey()) +" lei\n");
        }
        meniuText.append("\nRacoritoare:\n");
        for (Map.Entry<String, String> set :
             meniuProduse.entrySet())
        {
            if(set.getValue().equals("Racoritoare"))
                meniuText.append("- " + set.getKey()+ " - " + pretProduse.get(set.getKey()) +" lei\n");
        }
    }
    
    private void salveaza_lista_angajati()
    {
        angajatiText.setLength(0);
        for(int i=0;i<angajati.length;i++)
            angajatiText.append(angajati[i] + "\n");
        angajatiTextArea.setText(angajatiText.toString());

    }
    
    
    public StringBuilder get_meniu() {
        return meniuText;
    }
}
