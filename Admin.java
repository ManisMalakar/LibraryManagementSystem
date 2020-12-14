
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Admin extends JFrame {
    private JPanel panel;
    private JButton viewBooks;
    private JButton viewUsers;
    private JButton viewIssued;
    private JButton issueBook;
    private JButton addUser;
    private JButton addBook;
    private JButton returnBook;
    private JButton createReset;
    private GridBagConstraints constraints;

    public Admin() {
        this.setTitle("Library Management System");
        this.setSize(600, 700);
        this.setResizable(false);
        initComponents();
        layout(panel, constraints);

    }

    public void initComponents() {
        panel = new JPanel();

        viewBooks = new JButton("View Books");

        viewUsers = new JButton("View Users");

        viewIssued = new JButton("View Issued Books");

        issueBook = new JButton("Issue Book");

        addUser = new JButton("Add User");

        addBook = new JButton("Add Book");

        returnBook = new JButton("Return Book");

        createReset = new JButton("Create/Reset");


    }

    private void layout(JPanel newPanel, GridBagConstraints constraints) {
        newPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(20, 20, 20, 20);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(viewBooks, constraints);
        viewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewBooks().displayBooks();
            }});

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(viewUsers, constraints);
        viewUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewUsers().displayUsers();
                setVisible(false);
            }});

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(viewIssued, constraints);
        viewIssued.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewIssuedBooks().displayIssuedBooks();
                setVisible(false);
            }});

        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(issueBook, constraints);
        issueBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IssueBooks().setVisible(true);
                setVisible(false);
            }});

        constraints.gridx = 1;
        constraints.gridy = 0;
        newPanel.add(addUser, constraints);
        addUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddUsers().setVisible(true);
                setVisible(false);
            }});

        constraints.gridx = 1;
        constraints.gridy = 1;
        newPanel.add(addBook, constraints);
        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddBooks().setVisible(true);
                setVisible(false);
            }});

        constraints.gridx = 1;
        constraints.gridy = 2;
        newPanel.add(returnBook, constraints);
        returnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }});

        constraints.gridx = 1;
        constraints.gridy = 3;
        newPanel.add(createReset, constraints);
        createReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateReset().setVisible(true);
                setVisible(false);
            }});

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;



        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Admin Functions"));

        add(newPanel);

        pack();
        setLocationRelativeTo(null);
    }

}
