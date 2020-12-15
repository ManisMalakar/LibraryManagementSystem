
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.*;


public class AddBooks extends JFrame {
    private JPanel panel;

    private JLabel bookName;
    private JLabel bookPrice;
    private JLabel bookGenre;

    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField genreTextField;
    private JButton buttonAddUser;
    private JButton buttonBack;
    private GridBagConstraints constraints;

    public AddBooks() {
        this.setTitle("Add Book");
        this.setSize(1024, 1024);
        this.setResizable(false);
        initComponents();
        layout(panel, constraints);
    }

    public void initComponents() {
        panel = new JPanel();

        bookName = new JLabel("Book Name : ");
        bookPrice = new JLabel("Book Price : ");
        bookGenre = new JLabel("Book Genre : ");

        nameTextField = new JTextField(20);
        priceTextField = new JTextField(20);
        genreTextField = new JTextField(20);
        buttonAddUser = new JButton("Add");
        buttonBack = new JButton("<<Back");
    }

    private void layout(JPanel newPanel, GridBagConstraints constraints) {
        newPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);


        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(bookName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        newPanel.add(nameTextField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(bookPrice, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        newPanel.add(priceTextField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(bookGenre, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        newPanel.add(genreTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        newPanel.add(buttonBack,constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonAddUser, constraints);



        buttonAddUser.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                String bookName = nameTextField.getText();
                int bookPrice = Integer.parseInt(priceTextField.getText());
                String bookGenre = genreTextField.getText();


                if (bookName.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter the bookName"); //Display dialog box with the message
                } else if (bookGenre.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter the bookGenre"); //Display dialog box with the message
                } else {
                    Connection conn = Connect.connect();
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate("USE OurLibrary");
                        String st = ("INSERT INTO BooksList(BNAME,BPRICE,BGENRE) VALUES ('" + bookName + "','" + bookPrice + "','" + bookGenre + "')"); //Retreive username and passwords from users
                        stmt.executeUpdate(st);
                        JOptionPane.showMessageDialog(null, "Book Added Successfully!"); //Display Message

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }


        } );

        buttonBack.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new Admin().setVisible(true);


        } });




        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Add Books"));


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
                new AddBooks().setVisible(true);
            }
        });
    }
}