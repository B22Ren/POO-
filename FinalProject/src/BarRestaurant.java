import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class BarRestaurant extends JFrame {
   
    private Scanner scanner;
    private JPanel p;
    private JButton b_admin,b_angajat;
    private GestorEvenimenteBR ec;
    private StringBuilder meniuText;
    private HashMap<String,String> meniuProduse;
    private HashMap<String,Double> pretProduse;
    private String[] angajati;
            
    public BarRestaurant() {
        super ("Bine ati venit!");
        angajati = new String[] {"Marian Sefir","Andrei Stir",""};
        scanner=new Scanner(System.in);
        ec = new GestorEvenimenteBR();
        p = new JPanel();
        p.setLayout(new GridLayout(1,2,10,10));
        b_admin = new JButton("Admin");
        b_admin.addActionListener(ec);
        b_angajat = new JButton("Angajat");
        b_angajat.addActionListener(ec);
        p.add(b_admin);
        p.add(b_angajat);
        add(p);
        
        meniuProduse = new HashMap();
        pretProduse = new HashMap();
        meniuProduse.put("Inele_de_Ceapa","Aperitiv");
        pretProduse.put("Inele_de_Ceapa",17.00);
        meniuProduse.put("Caprese_Salad","Aperitiv");
        pretProduse.put("Caprese_Salad",10.0);
        meniuProduse.put("Bruschetta","Aperitiv");
        pretProduse.put("Bruschetta",14.0);

        meniuProduse.put("Somon_Grilled","Principal");
        pretProduse.put("Somon_Grilled",30.0);
        meniuProduse.put("Filet_Mignon","Principal");
        pretProduse.put("Filet_Mignon",17.0);
        meniuProduse.put("Alfredo_de_pui","Principal");
        pretProduse.put("Alfredo_de_pui",21.0);
        meniuProduse.put("Pizza","Principal");
        pretProduse.put("Pizza",28.0);
        
        meniuProduse.put("Tiramisu","Desert");
        pretProduse.put("Tiramisu",15.0);
        meniuProduse.put("Chocolate_Lava_Cake","Desert");
        pretProduse.put("Chocolate_Lava_Cake",15.0);
        meniuProduse.put("Cheesecake","Desert");
        pretProduse.put("Cheesecake",15.0);
        meniuProduse.put("Clatite","Desert");
        pretProduse.put("Clatite",15.0);
        meniuProduse.put("Papanasi","Desert");
        pretProduse.put("Papanasi",12.0);
        
        meniuProduse.put("Apa_Plata","Racoritoare");
        pretProduse.put("Apa_Plata",6.0);
        meniuProduse.put("Ceai","Racoritoare");
        pretProduse.put("Ceai",7.0);
        meniuProduse.put("Cola","Racoritoare");
        pretProduse.put("Cola", 8.0);   
        meniuProduse.put("Bere","Racoritoare");
        pretProduse.put("Bere",10.0);
        meniuProduse.put("Limonada","Racoritoare");
        pretProduse.put("Limonada",10.0);
        
        meniuText = new StringBuilder();
        
        
                
    }
    
    private class GestorEvenimenteBR implements ActionListener{
		private JFrame f;
		
	 public void actionPerformed(ActionEvent e){
            if(e.getSource()==b_angajat)
            {
                salveaza_meniu();
                f = new AngajatFrame(meniuText,pretProduse,angajati);
	  	f.setVisible(true);
                
            }
            else
            {
                f = new AdminFrame(meniuProduse,pretProduse,angajati);
                f.setVisible(true);
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
    
    public static void main(String args[])
    {
        BarRestaurant t = new BarRestaurant();
        t.setSize(375, 150);
        t.setLocation(300,300);
        t.setVisible(true); 	
    }
}


