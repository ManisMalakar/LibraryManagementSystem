
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.*;


public class IssueBooks extends JFrame {
    private JPanel panel;

    private JLabel labelUID;
    private JLabel labelBID;
    private JLabel labelPeriod;
    private JLabel labelIssueDate;

    private JTextField uidTextField;
    private JTextField bidTextField;
    private JTextField periodTextField;
    private JTextField dateTextField;

    private JButton buttonIssueBook;
    private JButton buttonBack;
    private GridBagConstraints constraints;

    public IssueBooks() {
        this.setTitle("Issue Books");
        this.setSize(1024, 1024);
        this.setResizable(false);
        initComponents();
        layout(panel,constraints);
    }

    public Integer getUID() { return Integer.parseInt(uidTextField.getText()); }

    public Integer getBID() { return Integer.parseInt(bidTextField.getText()); }

    public Integer getperiod() { return Integer.parseInt(periodTextField.getText()); }



    public void initComponents(){
        panel=new JPanel();

        labelUID = new JLabel("UID : ");
        labelBID = new JLabel("BID : ");
        labelPeriod = new JLabel(" Period : ");
        labelIssueDate = new JLabel("Issue Date (yyyy-MM-dd) : ");

        uidTextField = new JTextField(20);
        bidTextField = new JTextField(20);
        periodTextField = new JTextField(20);
        dateTextField = new JTextField(20);
        buttonIssueBook = new JButton("Issue Book");
        buttonBack = new JButton("<<Back");
    }

    private void layout(JPanel newPanel,GridBagConstraints constraints){
        newPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);


        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelUID, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        newPanel.add(uidTextField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelPeriod, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        newPanel.add(periodTextField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(labelIssueDate, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        newPanel.add(dateTextField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        newPanel.add(buttonBack,constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonIssueBook, constraints);


        buttonIssueBook.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){

                if(getUID().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please enter the UID");
                }
                else if(getperiod().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please enter the BID");
                }
                else if(dateTextField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please enter the issued date");
                }
                else {
                    Connection conn = Connect.connect();
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate("USE OurLibrary");
                        String st = ("INSERT INTO IssuedList(IID,UID,BID,Period,IssueDate) VALUES ('" + getUID() + "','" + getBID() + "','" +"','" + getperiod() + "','" + dateTextField.getText() + "')");
                        stmt.executeUpdate(st);
                        JOptionPane.showMessageDialog(null, "Book Issued Successfully!");

                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }


        });

        buttonBack.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new Admin().setVisible(true);


            } });

        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Issue Book"));


        add(newPanel);

        pack();
        setLocationRelativeTo(null);
    }



    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IssueBooks().setVisible(true);
            }
        });
    }
}