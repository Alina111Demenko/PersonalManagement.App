import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        /*Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(myCompany);
        System.out.println(json);*/

        //Fenster
        Unternehmen myCompany = new Unternehmen("MK");
        JFrame frame = new JFrame("Personalmanagement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,900);
        frame.getContentPane().setBackground(new Color(24,24,24));

        //icon
        ImageIcon icon = new ImageIcon(Main.class.getResource("/resources/logo.png"));
        frame.setIconImage(icon.getImage());

        //alles
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(24,24,24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //Buttons, Text
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(33,33,33));
        inputPanel.setBorder(BorderFactory.createLineBorder(new Color(187,134,252), 1));
        BorderFactory.createEmptyBorder(15,15,15,15);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;

        JTextField tfVorname = new JTextField(15);
        JTextField tfNachname = new JTextField(15);
        JTextField tfGeburtstag = new JTextField(15);
        JTextField tfTelefon = new JTextField(15);
        JTextField tfEmail = new JTextField(15);

        for(JTextField tf : new JTextField[]{tfVorname, tfNachname, tfGeburtstag, tfTelefon, tfEmail}){
            tf.setBackground(new Color(45,45,45));
            tf.setForeground(new Color(230,230,230));
            tf.setCaretColor(new Color(187,134,252));
            tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            tf.setBorder(BorderFactory.createEmptyBorder(5, 7, 5, 7));
        }

        JButton bEintragen = styledButton("Eintragen");
        JButton bSuchen = styledButton("Suchen");
        JButton bAbteilung = styledButton("Abteilung einfügen");
        JButton bAbteilungLoschen = styledButton("Abteilung löschen");
        JButton bLoschen= styledButton("Mitarbeiterdaten löschen");
        JButton bSave = styledButton("Save");
        JButton bLoad = styledButton("Load");

        c.gridx = 0; c.gridy = 0;
        inputPanel.add(styledLabel("Vorname:"),c);
        c.gridx = 1;
        inputPanel.add(tfVorname, c);
        c.gridx = 2;
        inputPanel.add(bEintragen,c);

        c.gridx = 0; c.gridy = 1;
        inputPanel.add(styledLabel("Nachname:"), c);
        c.gridx = 1;
        inputPanel.add(tfNachname, c);
        c.gridx = 2;
        inputPanel.add(bSuchen,c);

        c.gridx = 0; c.gridy = 2;
        inputPanel.add(styledLabel("Geburtstag:"), c);
        c.gridx = 1;
        inputPanel.add(tfGeburtstag, c);
        c.gridx = 2;
        inputPanel.add(bSave, c);
        c.gridx = 3;
        inputPanel.add(bLoad, c);


        c.gridx = 0; c.gridy = 3;
        inputPanel.add(styledLabel("Abteilung:"), c);
        c.gridx = 1;
        String[] abteilungen = {"Software", "IT", "Marketing", "HR", "Sales"};
        JComboBox<String> combo = new JComboBox<>(abteilungen);

        for(String name : abteilungen){
            myCompany.AbteilungEinfugen(new Abteilung(name));

        }
        combo.setBackground(new Color(33,33,33));
        combo.setForeground(new Color(230,230,230));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        inputPanel.add(combo, c);


        c.gridx = 2;
        inputPanel.add(bAbteilung,c);

        c.gridx = 0; c.gridy = 4;
        inputPanel.add(styledLabel("Telefonnummer:"), c);
        c.gridx = 1;
        inputPanel.add(tfTelefon,c);
        c.gridx = 2;
        inputPanel.add(bAbteilungLoschen,c);

        c.gridx = 0; c.gridy = 5;
        inputPanel.add(styledLabel("E-Mail:"), c);
        c.gridx = 1;
        inputPanel.add(tfEmail,c);
        c.gridx = 2;
        inputPanel.add(bLoschen,c);

        //hinfügen inputPanel in mainPanel
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1.0; gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(inputPanel, gbc);

        //tabelle
        String[] columns = {"Vorname", "Nachname", "Geburtstag", "Abteilung", "Telefonnummer", "E-Mail"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        model.addRow(new Object[]{"Vorname", "Name", "01.01.2000", "IT", "123", "name@test"});
        JTable table = new JTable(model);
        table.setBackground(new Color(33, 33, 33));
        table.setForeground(new Color(220, 220, 220));
        table.setGridColor(new Color(187, 134, 252));
        table.setShowGrid(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(187, 134, 252));
        table.getTableHeader().setForeground(new Color(24, 24, 24));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));



        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(33, 33, 33));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(187, 134, 252), 1));

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);

        //nehmen alle daten und hibzufugen zu Person(Mitarbeiter)
        bEintragen.addActionListener(e -> {
            String vorname = tfVorname.getText().trim();
            String nachname = tfNachname.getText().trim();
            String geburtstag = tfGeburtstag.getText().trim();
            String telefon = tfTelefon.getText().trim();
            String email = tfEmail.getText().trim();
            String abtName = (String)combo.getSelectedItem();

            if(vorname.isEmpty() || nachname.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Vorname und Nachname sind Pflichtfelder.");
                return;
            }

            Person p = new Person(nachname, vorname, geburtstag, telefon, email);
            Abteilung abt = myCompany.getAbteilung(abtName);
            abt.Personeinfugen(p);
            model.addRow(new Object[]{vorname, nachname, geburtstag, abtName, telefon, email});

        });
        bLoad.addActionListener(e -> {
            Unternehmen geladen = DataManager.load();
            if (geladen == null) {
                JOptionPane.showMessageDialog(frame, "Keine Daten gefunden.");
                return;
            }

            // myCompany обновляем
            myCompany.abteilungs = geladen.abteilungs;

            // таблицу очищаем и заново заполняем
            model.setRowCount(0);
            for (Abteilung a : myCompany.abteilungs) {
                for (Person p : a.personList) {
                    model.addRow(new Object[]{p.vorname, p.nachname, p.geburtstag, a.name, p.telefon, p.email});
                }
            }
            JOptionPane.showMessageDialog(frame, "Geladen!");
        });

        bAbteilung.addActionListener(e -> {
            String neuerName = JOptionPane.showInputDialog(frame, "Name der neuen Abteilung:");
            if(neuerName != null && !neuerName.trim().isEmpty()){
                myCompany.AbteilungEinfugen(new Abteilung(neuerName.trim()));
                combo.addItem(neuerName.trim());
            }
        });

        bAbteilungLoschen.addActionListener(e -> {
            String abtName = (String)combo.getSelectedItem();
            if(abtName != null){
                myCompany.AbteilungLochen(abtName);
                combo.removeItem(abtName);
                for(int i =model.getRowCount() -1; i >= 0; i--){
                    if(model.getValueAt(i, 3).equals(abtName)){
                        model.removeRow(i);
                    }
                }
            }
        });

        bSuchen.addActionListener(e -> {
            String email = tfEmail.getText().trim();
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Email eingeben zum Suchen.");
                return;
            }
            model.setRowCount(0);
            for(Abteilung a : myCompany.abteilungs){
                for(Person p : a.personList){
                    if(p.email.equalsIgnoreCase(email)){
                        model.addRow(new Object[]{p.vorname, p.nachname, p.geburtstag, a.name, p.telefon, p.email});
                    }
                }
            }
            if(model.getRowCount() == 0){
                JOptionPane.showMessageDialog(frame, "Niemand gefunden.");
            }
        });

        bSave.addActionListener(e -> {
            DataManager.save(myCompany);
            JOptionPane.showMessageDialog(frame, "Gespeichert!");
        });

        bLoschen.addActionListener(e -> {
            String email = tfEmail.getText().trim();
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Email eingeben zum Löschen." );
                return;
            }
            

            for(Abteilung a : myCompany.abteilungs){
                a.PersonLochen(email);
            }


            for(int i = model.getRowCount() -1; i >= 0; i--){
                if(model.getValueAt(i, 5).equals(email)){
                    model.removeRow(i);
                }
            }

        });
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);//fenster in center
        frame.setVisible(true);
    }

    static JLabel styledLabel(String text){
        JLabel label = new JLabel(text);
        label.setForeground(new Color(150,150,150));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return label;



    }

    static JButton styledButton(String text){
        JButton button = new JButton(text);
        button.setBackground(new Color(187, 134, 252));
        button.setForeground(new Color(24, 24, 24));
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
}