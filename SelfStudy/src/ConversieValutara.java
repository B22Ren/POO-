import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConversieValutara extends JFrame {
    private JLabel SumaLabel, DinLabel, InLabel, resultatLabel;
    private JTextField SumaField;
    private JComboBox<String> DinComboBox, InComboBox;
    private JButton conversieButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final String[] Valute = {"USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY","INR"};
    private double[] Rata = {1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47,87.14};

    public ConversieValutara() {
        setTitle("Conversie Valutara");
        setLayout(new GridLayout(4, 2));

        SumaLabel = new JLabel("Suma:");
        add(SumaLabel);

        SumaField = new JTextField();
        add(SumaField);

        DinLabel = new JLabel("Din:");
        add(DinLabel);

        DinComboBox = new JComboBox<>(Valute);
        add(DinComboBox);

        InLabel = new JLabel("In:");
        add(InLabel);

        InComboBox = new JComboBox<>(Valute);
        add(InComboBox);

        conversieButton = new JButton("Conversie");
        add(conversieButton);

        resultatLabel = new JLabel();
        add(resultatLabel);

        conversieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(SumaField.getText());
                    String DinValute = (String) DinComboBox.getSelectedItem();
                    String InValute = (String) InComboBox.getSelectedItem();
                    double SchimbRata = Rata[getIndex(DinValute)] / Rata[getIndex(InValute)];
                    double resultat = amount * SchimbRata;
                    resultatLabel.setText(decimalFormat.format(resultat) + " " + InValute);
                } catch (Exception ex) {
                    resultatLabel.setText("Invalid input");
                }
            }
        });

        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int getIndex(String valute) {
        for (int i = 0; i < Valute.length; i++) {
            if (valute.equals(Valute[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new ConversieValutara();
    }
}