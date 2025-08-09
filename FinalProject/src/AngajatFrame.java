import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class AngajatFrame extends JFrame {

    private JLabel titleLabel;
    private JTextArea menuTextArea;
    private JTextArea orderTextArea;
    private JTextArea tablesTextArea;
    private JButton removeButton;
    private JButton saveButton;
    private JButton printChitantaButton;
    private JButton[] m;
    private HashMap<String, Integer> orderMap;
    private HashMap<String, Integer> orderMap1;
    private HashMap<String, Integer> orderMap2;
    private HashMap<String, Integer> orderMap3;
    private HashMap<String, Integer> orderMap4;
    private HashMap<String, Integer> orderMap5;
    private HashMap<String, Integer> orderMap6;
    private GestorEvenimenteAF ec;
    private BufferedReader br;
    private PrintWriter pw;
    private String l;
    private int nr_masa;
    private String[] comenzi;
    private StringBuilder menuText;
    private Map<String, Double> pretProduse;
    private String[] angajati; 

    public AngajatFrame(StringBuilder menuText, HashMap<String, Double> pretProduse, String[] angajati) {

        this.angajati = angajati;
        this.pretProduse = pretProduse;
        this.menuText = menuText;
        nr_masa = 0;
        orderMap1 = new HashMap<>();

        File f = new File("comenzi.txt");
        comenzi = new String[]{"", "", "", "", "", ""};
        /*if(f.exists())
        {
            try{
                br=new BufferedReader(new FileReader(f));
                while()
            }
        }
         */
        ec = new GestorEvenimenteAF();

        setTitle("Restaurant Menu");
        setSize(400, 400);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        menuTextArea = new JTextArea();
        menuTextArea.setEditable(false);
        menuTextArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane menuScrollPane = new JScrollPane(menuTextArea);

        orderTextArea = new JTextArea();
        orderTextArea.setEditable(false);
        orderTextArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane orderScrollPane = new JScrollPane(orderTextArea);

        saveButton = new JButton("Salveaza");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaComanda();
                salveaza();
                orderMap.clear();
            }
        });

        removeButton = new JButton("Sterge");
        removeButton.setEnabled(false);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOrderItem = orderTextArea.getSelectedText();
                if (selectedOrderItem != null && !selectedOrderItem.isEmpty()) {
                    removeFromOrder(selectedOrderItem);
                    
                }
            }
        });

        printChitantaButton = new JButton("Print Chitanta");
        printChitantaButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                print_chitanta();
                }  
                });

    JPanel tablePanel = new JPanel(new GridLayout(8, 1, 10, 10));
    String s[] = {"Masa 1", "Masa 2", "Masa 3", "Masa 4", "Masa 5", "Masa 6"};
    m  = new JButton[6];
    for(int i = 0;i<m.length ;i++)
        {
            m[i] = new JButton(s[i]);
        m[i].addActionListener(ec);
        tablePanel.add(m[i]);
    }

    tablePanel.add (saveButton);
    tablePanel.add (printChitantaButton);

    JPanel menuPanel = new JPanel(new BorderLayout());

    menuTextArea.setText (menuText.toString());
    menuTextArea.setVisible (true);
        
        
    menuPanel.add (titleLabel, BorderLayout.NORTH);

    menuPanel.add (menuScrollPane, BorderLayout.CENTER);

    JPanel orderPanel = new JPanel(new BorderLayout());

    orderPanel.add (new JLabel("Order"), BorderLayout.NORTH);
    orderPanel.add (orderScrollPane, BorderLayout.CENTER);

    orderPanel.add (removeButton, BorderLayout.SOUTH);

    JPanel contentPane = new JPanel(new GridLayout(1, 3));

    contentPane.add (menuPanel);

    contentPane.add (orderPanel);

    contentPane.add (tablePanel);

    setContentPane(contentPane);

    orderMap  = new HashMap<>();
    orderMap1  = new HashMap<>();
    orderMap2  = new HashMap<>();
    orderMap3  = new HashMap<>();
    orderMap4  = new HashMap<>();
    orderMap5  = new HashMap<>();
    orderMap6  = new HashMap<>();
    // Add ActionListener to menu items

    menuTextArea.addMouseListener ( 
        new java.awt.event.MouseAdapter() {
            @Override
        public void mouseClicked
        (java.awt.event.MouseEvent evt
        
            ) {
                String selectedMenuItem = menuTextArea.getSelectedText();
            if (selectedMenuItem != null && !selectedMenuItem.isEmpty() && exista_in_meniu(selectedMenuItem)) {
                addToOrder(selectedMenuItem);
            }
        }
    }

    );

        // Add ActionListener to order items
    orderTextArea.addMouseListener ( 
        new java.awt.event.MouseAdapter() {
            @Override
        public void mouseClicked
        (java.awt.event.MouseEvent evt) {
                String selectedOrderItem = orderTextArea.getSelectedText();
            if (selectedOrderItem != null && !selectedOrderItem.isEmpty() ) {
                removeButton.setEnabled(true);
            } else {
                removeButton.setEnabled(false);
            }
        }
    }

);
        
        
    }
    
    private boolean exista_in_meniu(String s)
    {
        for (Map.Entry<String, Double> entry : pretProduse.entrySet())
        {
            if(entry.getKey().equals(s))
                return true;
        }
        return false;
    }
    
    
    
        public void salveaza(){
    try{
      pw=new PrintWriter(new FileWriter("comenzi.txt"));
        for (String comanda : comenzi) {
            pw.println(comanda);
        }
      pw.close();	
     }catch(IOException e){e.printStackTrace();

}

}
        
        public void print_chitanta()
        {
              try{
              pw=new PrintWriter(new FileWriter("chitanta.txt"));
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
              LocalDateTime now = LocalDateTime.now(); 
              HashMap<String,Integer> order = get_comanda_masa();
              pw.println(dtf.format(now));
              pw.println("------------------------------------------------------------------------\r\n\r\n");
              pw.println("Lista produse:");
              pw.println(" ");
              for (Map.Entry<String, Integer> set :
              order.entrySet())
              {
                  pw.println(set.getValue() + " x " + set.getKey() 
                          + "                                                 " + set.getValue() * pretProduse.get(set.getKey()) + " lei");
              }
              pw.println("------------------------------------------------------------------------\r\n\r\n");
              
              pw.println("Suma totala:                                                    " + calculeaza_pret_comanda()+" lei");
              pw.println("------------------------------------------------------------------------");
              pw.println("------------------------------------------------------------------------");
              
              pw.println(JOptionPane.showInputDialog(null, "Alege un chelner:", 
                "Chelner", JOptionPane.QUESTION_MESSAGE, null, angajati, angajati[0]) + "               "
                        + "                                      Masa: " + (nr_masa+1));
              pw.close();	
             }catch(IOException e){e.printStackTrace();
        }
        }
        
        public HashMap<String,Integer> get_comanda_masa()
        {
            switch(nr_masa)
        {
            case 0:
                return orderMap1;
            case 1:
                return orderMap2;
            case 2:
                return orderMap3;
            case 3:
                return orderMap4;
            case 4:
                return orderMap5;
            default:
                return orderMap6;
            
                
        }
        }
        
        
    private class GestorEvenimenteAF implements ActionListener {

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == m[0]) {
            nr_masa = 0;
            orderMap.clear();
            orderMap.putAll(orderMap1);
            orderTextArea.setText(getComanda());
        } else if (e.getSource() == m[1]) {
            nr_masa = 1;
            orderMap.clear();
            orderMap.putAll(orderMap2);
            orderTextArea.setText(getComanda());

        } else if (e.getSource() == m[2]) {
            nr_masa = 2;
            orderMap.clear();
            orderMap.putAll(orderMap3);
            orderTextArea.setText(getComanda());

        } else if (e.getSource() == m[3]) {
            nr_masa = 3;
            orderMap.clear();
            orderMap.putAll(orderMap4);
            orderTextArea.setText(getComanda());
        } else if (e.getSource() == m[4]) {
            nr_masa = 4;
            orderMap.clear();
            orderMap.putAll(orderMap5);
            orderTextArea.setText(getComanda());
        } else if (e.getSource() == m[5]) {
            nr_masa = 5;
            orderMap.clear();
            orderMap.putAll(orderMap6);
            orderTextArea.setText(getComanda());
        }

    }
}

public String getComanda()
    {
        return comenzi[nr_masa];
        
    }
    
    private void addToOrder(String item) {
        if (orderMap.containsKey(item)) {
            int quantity = orderMap.get(item);
            orderMap.put(item, quantity + 1);
        } else {
            orderMap.put(item, 1);
        }
        updateOrderText();
    }

    private void removeFromOrder(String item) {
        if (orderMap.containsKey(item)) {
            int quantity = orderMap.get(item);
            if (quantity > 1) {
                orderMap.put(item, quantity - 1);
            } else {
                orderMap.remove(item);
            }
            updateOrderText();
        }
    }

    private void adaugaComanda()
    {
        comenzi[nr_masa] = orderTextArea.getText();
        
        switch(nr_masa)
        {
            case 0:
                orderMap1.clear();
                orderMap1.putAll(orderMap);
                break;
            case 1:
                orderMap2.clear();
                orderMap2.putAll(orderMap);
                break;
            case 2:
                orderMap3.clear();
                orderMap3.putAll(orderMap);
                break;
            case 3:
                orderMap4.clear();
                orderMap4.putAll(orderMap);
                break;
            case 4:
                orderMap5.clear();
                orderMap5.putAll(orderMap);    
                break;
            case 5:
                orderMap6.clear();
                orderMap6.putAll(orderMap);    
                break;
        }
        orderMap.clear();
    }
    
    private void updateOrderText() {
        orderTextArea.setText("");
        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            orderTextArea.append(item + " (Cantitate: " + quantity + ")\n");
        }
    }

    public void setMenuText(String menuText) {
        menuTextArea.setText(menuText);
    }
    
    
    private double calculeaza_pret_comanda()
    {
        switch(nr_masa)
        {
            case 0:
                return calculeaza_total(orderMap1);
            case 1:
                return calculeaza_total(orderMap2);
            case 2:
                return calculeaza_total(orderMap3);
            case 3:
                return calculeaza_total(orderMap4);
            case 4:
                return calculeaza_total(orderMap5);
            case 5:
                return calculeaza_total(orderMap6);
            default:
                return 0;
        }
    }
    
    
    private double calculeaza_total(Map<String,Integer> order)
    {
        double total=0;
        for (Map.Entry<String, Integer> set :
             order.entrySet())
                total += pretProduse.get(set.getKey()) * set.getValue();
            
        return total;
        
    }

 
}
