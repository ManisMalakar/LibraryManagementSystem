
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class User extends JFrame {
    private JPanel panel;
    private JButton myBooks;
    private JButton rentBook;
    private JButton LogOut;

    private GridBagConstraints constraints;

    public User() {
        this.setTitle("User Functions");
        this.setSize(2056, 2056);
        this.setResizable(true);
        initComponents();
        addActionListeners();
        layout(panel,constraints);

    }
    public void initComponents(){
        panel=new JPanel();


        myBooks = new JButton("My Books");

        rentBook = new JButton("Rent book");

        LogOut = new JButton("Log Out");


    }
    private void layout(JPanel newPanel,GridBagConstraints constraints){
        newPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(20, 20, 20, 20);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(myBooks, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        newPanel.add(rentBook, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(LogOut, constraints);
        LogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Main().setVisible(true);
                setVisible(false);
            }});



        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "View Users"));


        add(newPanel);

        pack();
        setLocationRelativeTo(null);
    }


    private static void addActionListeners(){

    }
    public static void main(String[] args) {
        new User().setVisible(true);


    }
}