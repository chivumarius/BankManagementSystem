import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.*;




// ▬ "JFrame" Implementation for "GUI" ▬
public class Frame extends JFrame {

    // ▼ "Variables" ▼
    JLabel accNoLBL, ownerLBL, balanceLBL, cityLBL, genderLBL, amountLBL;
    JTextField accNoTXT, ownerTXT, balanceTXT, amountTXT;
    JComboBox<City> citiesCMB;

    JButton newBTN,saveBTN, showBTN, quitBTN, depositBTN, withdrawBTN;
    JRadioButton maleRDB, femaleRDB;
    ButtonGroup genderBTNGRP;


    JList<Account> accountsLST;
    JPanel p1,p2,p3,p4,p5;



    // ▼ Create a "Set<Account>" Object of "TreeSet" Type ▼
    Set<Account> accountSet = new TreeSet<>();
    Account acc, x;
    boolean newRec = true;



    // ▼ "Combobox" Data ▼
    DefaultComboBoxModel<City> citiesCMBMDL;
    DefaultListModel<Account> accountsLSTMDL;


    // ▼ "Table" Data ▼
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> transList = new ArrayList<>();





    //▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀ "Constructor" ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    public Frame() {
        // ▬ "JFrame" ▬
        // ▼ Call "JFrame" Constructor ▼
        super("Account Operations"); // ◄ Frame Title
        setLayout(null);
        setSize(600,400);



        // ════════════════════  Adding "Components" to the "Frame"  ══════════════════════

        // ═══════════════════ "Labels" ═══════════════════════
        accNoLBL = new JLabel("Account No.");
        ownerLBL  = new JLabel("Owner");
        balanceLBL= new JLabel("Balance");
        cityLBL   = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");


        // ═══════════════════ "TextFields" ═══════════════════════
        accNoTXT = new JTextField(); accNoTXT.setEnabled(false);
        ownerTXT = new JTextField();
        balanceTXT = new JTextField(); balanceTXT.setEnabled(false);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150,25));



        // ═══════════════════ "ComboBox" ═══════════════════════
        citiesCMBMDL = new DefaultComboBoxModel<>();
        citiesCMBMDL.addElement(null);

        citiesCMBMDL.addElement(new City("Bucharest", "Bucharest"));
        citiesCMBMDL.addElement(new City("Cluj", "Cluj-Napoca"));
        citiesCMBMDL.addElement(new City("Brasov","Brasov"));
        citiesCMBMDL.addElement(new City("Timisoara", "Timis"));



        // ═══════════════════ Adding "Data" to "JComboBox" ═══════════════════════
        citiesCMB = new JComboBox<>(citiesCMBMDL);



        // ═══════════════════ "Radio Buttons" ═══════════════════════
        maleRDB = new JRadioButton("Male", true);
        femaleRDB = new JRadioButton("Female");
        genderBTNGRP = new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);


        // ═══════════════════ "Buttons" ═══════════════════════
        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("WithDraw");



        // ═══════════════════ "Table" ═══════════════════════
        accountsLSTMDL = new DefaultListModel<>();
        accountsLST    = new JList<>(accountsLSTMDL);


        // ═══════════════════ "Panels" ═══════════════════════
        p1 = new JPanel(); p1.setBounds(5,5,300,150);
        p1.setLayout(new GridLayout(5,2));

        p2 = new JPanel(); p2.setBounds(5,155,300,40);
        p2.setLayout(new FlowLayout());

        p3 = new JPanel(); p3.setBounds(5,195,600,40);
        p3.setLayout(new FlowLayout());

        p4 = new JPanel(); p4.setBounds(305,5,300,190);
        p4.setLayout(new BorderLayout());

        p5 = new JPanel(); p5.setBounds(5,240,580,120);
        p5.setLayout(new BorderLayout());


        // ═══════════════════ Adding "Components" to "Panel" ═══════════════════════
        p1.add(accNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balanceLBL);
        p1.add(balanceTXT);
        p1.add(cityLBL);
        p1.add(citiesCMB);
        p1.add(maleRDB);
        p1.add(femaleRDB);


        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);

        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);

        p4.add(accountsLST);




        // ═══════════════════ Adding "Panels" to "Frame" ═══════════════════════
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);


        // ═══════════════════ "Table" creation ═══════════════════════
        tableModel = new DefaultTableModel();

        table = new JTable(tableModel);
        tableModel.addColumn("TrsNO");
        tableModel.addColumn("TrsDate");
        tableModel.addColumn("TrsType");
        tableModel.addColumn("TrsAmount");

        JScrollPane scrollPane = new JScrollPane(table);
        p5.add(scrollPane);






        // ══════════════════════════════════════════
        // "Functionality"
        // ══════════════════════════════════════════

        // ═══════════════════ 1. "newBTN" Action Listener ═══════════════════════
        newBTN.addActionListener(e -> {
            accNoTXT.setText("");
            ownerTXT.setText("");
            citiesCMB.setSelectedIndex(0);

            maleRDB.setSelected(true);

            balanceTXT.setText("");
            amountTXT.setText("");
            newRec = true;
        });





        // ═══════════════════ 2. "saveBTN" Action Listener ═══════════════════════
        saveBTN.addActionListener(e -> {

            // ▼ If there is a "New Record" ▼
            if (newRec){
                // Getting the "Inserted Text"
                if (ownerTXT.getText().length() != 0){

                    // ▼ "Creating" a new "Account" Object
                    //      → with the "Inserted Text" Get it from
                    //      → the "TextFields" ▼
                    acc = new Account(
                            ownerTXT.getText(),
                            (City) citiesCMB.getSelectedItem(),
                            maleRDB.isSelected()? 'M':'F'
                    );

                    // ▼ Set the "Account No." in the "TextFields"
                    accNoTXT.setText(String.valueOf(acc.accNo));
                    accountSet.add(acc);
                    accountsLSTMDL.addElement(acc);
                    newRec = false;

                } else {
                    // ▼ Showing an Error Message ▼
                    JOptionPane.showMessageDialog(null,"Please Fill All Fields");
                }
            } else {
                // ▼ If there is not a "New Record" ▼
                // ▼ "Updating" the "Account" Object by "Removing Account" ▼
                accountSet.remove(acc);

                // ▼ "Creating" a new "Account" Objects
                //      → and Getting the "Inserted Texts" ▼
                int a  = Integer.parseInt(accNoTXT.getText());
                String o = ownerTXT.getText();
                City c = (City) citiesCMB.getSelectedItem();


                char g = maleRDB.isSelected()?'M':'F';
                double b = Double.parseDouble(balanceTXT.getText());


                acc = new Account(a,o,c,g,b);
                accountSet.add(acc);
                accountsLSTMDL.setElementAt(acc,accountsLST.getSelectedIndex());
                newRec = false;
            }

        });




        // ═══════════════════ 3. "showBTN" Action Listener ═══════════════════════
        showBTN.addActionListener(e -> {

            // ▼ Creating a "String" Variable ▼
            String str = "";

            // ▼ Iterating through the "Account Set"by using "Iterator" ▼
            for (Account account : accountSet) {
                str += account.toString() + "\n";

                // ▼ "Showing" the "Message Dialog" ▼
                JOptionPane.showMessageDialog(null, str);
            }
        });




        // ═══════════════════ 4. "depositBTN" Action Listener ═══════════════════════
        depositBTN.addActionListener(e -> {

            // ▼ If there is "Not" a "New Record" ▼
            if (!newRec && amountTXT.getText().length() !=0){

                // ▼ "Adding Transaction" to "Table" ▼
                Transaction transaction = new Transaction(
                        acc,
                        LocalDate.now(),
                        'D',
                        Double.parseDouble(amountTXT.getText())
                );


                // ▼ Calling the "Method" ▼
                DisplayTransactionsInTable(transaction);

                // ▼ "Perform Deposit" from "Account" ▼
                acc.deposit(Double.parseDouble(amountTXT.getText()));
                balanceTXT.setText(String.valueOf(acc.balance));
            }
        });






        // ═══════════════════ 5. "withdrawBTN" Action Listener ═══════════════════════
        withdrawBTN.addActionListener(e -> {

            // ▼ If there is "Not" a "New Record" ▼
            if (!newRec && amountTXT.getText().length() != 0){

                // ▼ Adding "Transaction" to "Table" ▼
                Transaction transaction = new Transaction(
                        acc, LocalDate.now(),
                        'W',
                        Double.parseDouble(amountTXT.getText())
                );


                // ▼ Calling the "Method" ▼
                DisplayTransactionsInTable(transaction);


                // ▼ "Perform Withdrawal" on "Account" ▼
                acc.withdraw(Double.parseDouble(amountTXT.getText()));
                balanceTXT.setText(String.valueOf(acc.balance));
            }
        });






        // ═══════════════════ 6. "accountsLST" Action Listener ═══════════════════════
        accountsLST.addListSelectionListener(e -> {
            acc = x = accountsLST.getSelectedValue();

            accNoTXT.setText(String.valueOf(acc.accNo));
            ownerTXT.setText(acc.owner);
            citiesCMB.setSelectedItem(acc.city);

            // ▼ Set the "Gender" in the "RadioButtons" ▼
            if (acc.gender == 'M')
                maleRDB.setSelected(true);
            else
                femaleRDB.setSelected(true);


            // ▼ "Set" the "TextField" ▼
            balanceTXT.setText(String.valueOf(acc.balance));
            amountTXT.setEnabled(true);
            depositBTN.setEnabled(true);
            newRec = false;


            // ▼ "Clear Table" ▼
            for(int i= tableModel.getRowCount() -1; i>=0; i--){
                tableModel.removeRow(i);
            }


            // ▼ "Get Transactions" to "Selected Account"
            //      → by Calling the "Method" ▼
            SearchTransactionList(acc.accNo);
        });

    }




    //▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀ "SearchTransactionList()" Method ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    // ► "Get Transactions" to "Selected Account" ◄
    private void SearchTransactionList(int accNo) {

        // ▼ Creating a "List<Transaction>" of "ArrayList" Type ▼
        List<Transaction> filteredList = new ArrayList<>();

        // ▼ "Iterate" through the "List" ▼
        for (Transaction transaction: transList){

            // ▼The "Filter Values" that Contains "Transaction Number" ▼
            if (transaction.getAcc().accNo == accNo){
                // ▼ Adding "Transaction" to "List" ▼
                filteredList.add(transaction);
            }
        }




        // ▼ "Display" the "Filtered List" ▼
        for (Transaction transaction : filteredList) {

            // ▼ Displaying "Data" into "Table" ▼
            tableModel.addRow(new Object[]{
                    transaction.getTrsNo(),
                    transaction.getDate(),
                    transaction.getOperation(),
                    transaction.getAmount()
            });
        }
    }






    //▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀ "DisplayTransactionsInTable()" Method ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    public void DisplayTransactionsInTable(Transaction transaction){

        // ▼ Displaying "Data" into "Table" ▼
        tableModel.addRow(new Object[]{
                transaction.getTrsNo(),
                transaction.getDate(),
                transaction.getOperation(),
                transaction.getAmount()
        });


        // ▼ Adding "Object" to "Array List" ▼
        transList.add(transaction);
    }

}
