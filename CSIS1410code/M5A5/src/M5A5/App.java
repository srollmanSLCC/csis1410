package M5A5;

// Much of the code for the layout is based on the example application.

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;

public class App
{
    public static JFrame frame;
    private JComboBox<String> cmb;
    private JTextField txtFirst;
    private JTextField txtLast;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtCompany;
    private JButton btnNew;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private boolean gotoNextField;
    private String value;
    private String storageFileName;
    private boolean isFileSaved;

    /**
     * Main class for our App.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            try
            {
                App app = new App();
                app.frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public App()
    {
        initialize();
        if (BusinessContactList.readContactFile(storageFileName))
        {
            loadContacts();
            setDefaultState();
            isFileSaved = true;
        }
        else
        {
            setNewState();
        }
    }

    /**
     * Initialize the values for our app.
     */
    private void initialize()
    {
        // Initialize variables.
        isFileSaved = false;
        storageFileName = "contacts.ser";
        frame = new JFrame();
        cmb = new JComboBox<>();
        txtFirst = new JTextField();
        txtLast = new JTextField();
        txtPhone = new JTextField();
        txtEmail = new JTextField();
        txtCompany = new JTextField();
        btnNew = new JButton("New");
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");

        // Create labels for our components and set their positions.
        JLabel lblRolodex = new JLabel("My Rolodex");
        lblRolodex.setBounds(10, 11, 414, 20);
        lblRolodex.setHorizontalAlignment(SwingConstants.LEFT);
        JLabel lblContacts = new JLabel("Contacts");
        lblContacts.setBounds(10, 39, 110, 14);
        JLabel lblFirst = new JLabel("First Name");
        lblFirst.setBounds(168, 39, 64, 14);
        JLabel lblLast = new JLabel("Last Name");
        lblLast.setBounds(279, 39, 97, 14);
        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(168, 95, 46, 14);
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(279, 95, 69, 14);
        JLabel lblCompany = new JLabel("Company");
        lblCompany.setBounds(168, 151, 64, 14);

        // Set up contacts combo box
        cmb.setModel(new DefaultComboBoxModel<>());
        cmb.setBounds(10, 56, 132, 20);
        cmb.setMaximumRowCount(4);

        // Set up text fields.
        txtFirst.setBounds(168, 56, 91, 20);
        txtFirst.setColumns(10);
        txtLast.setBounds(279, 56, 149, 20);
        txtLast.setColumns(10);
        txtPhone.setBounds(168, 111, 91, 20);
        txtPhone.setColumns(10);
        txtPhone.setToolTipText("Enter Contact's Phone");
        txtEmail.setBounds(279, 111, 149, 20);
        txtEmail.setColumns(10);
        txtEmail.setToolTipText("Enter the contact's email");
        txtCompany.setBounds(168, 168, 260, 20);
        txtCompany.setColumns(10);
        txtCompany.setToolTipText("Enter Contact's Company");

        // Set up our buttons
        btnNew.setBounds(15, 207, 89, 23);
        btnNew.setEnabled(false);
        btnAdd.setBounds(119, 207, 89, 23);
        btnAdd.setEnabled(false);
        btnUpdate.setBounds(223, 207, 89, 23);
        btnUpdate.setEnabled(false);


        // Set up the main frame, use dimensions of example for simplicity.
        frame.setBounds(650, 450, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        // Add our components to our main frame.
        frame.getContentPane().add(lblRolodex);
        frame.getContentPane().add(lblContacts);
        frame.getContentPane().add(cmb);
        frame.getContentPane().add(lblFirst);
        frame.getContentPane().add(txtFirst);
        frame.getContentPane().add(lblLast);
        frame.getContentPane().add(txtLast);
        frame.getContentPane().add(lblPhone);
        frame.getContentPane().add(txtPhone);
        frame.getContentPane().add(lblEmail);
        frame.getContentPane().add(txtEmail);
        frame.getContentPane().add(lblCompany);
        frame.getContentPane().add(txtCompany);
        frame.getContentPane().add(btnNew);
        frame.getContentPane().add(btnAdd);
        frame.getContentPane().add(btnUpdate);

        // Configure action listeners for each of components.
        txtFirst.addActionListener(arg0 -> txtLast.requestFocusInWindow());
        txtLast.addActionListener(arg0 -> txtPhone.requestFocusInWindow());
        txtCompany.addActionListener(e -> txtFirst.requestFocusInWindow());

        cmb.addActionListener(e ->
        {
            updateFields(cmb.getSelectedIndex());
            setDefaultState();
        });

        // Borrow the functionality from the example for the validation functionality
        txtPhone.addActionListener(e ->
        {
            gotoNextField = true;
            value = txtPhone.getText();
            if (!BusinessContact.BusinessContactValidation.isValidPhone(value))
            {
                JOptionPane.showMessageDialog(frame,
                    String.format("'%s' is not a valid phone number!", value), "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
                txtPhone.setText("");
                gotoNextField = false;
            }
            if (gotoNextField)
            {
                txtEmail.requestFocusInWindow();
            }
        });

        // Borrow the functionality from the example for the validation functionality
        txtEmail.addActionListener(e ->
        {
            gotoNextField = true;
            value = txtEmail.getText();
            if (!BusinessContact.BusinessContactValidation.isValidEmail(value))
            {
                JOptionPane.showMessageDialog(frame,
                    String.format("%s is not a valid email address!", value), "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
                txtEmail.setText("");
                gotoNextField = false;
            }
            if (gotoNextField)
            {
                txtCompany.requestFocusInWindow();
            }
        });

        btnNew.addActionListener(e ->
        {
            clear();
            setNewState();
            txtFirst.requestFocusInWindow();
        });
        btnAdd.addActionListener(e ->
        {
            BusinessContact contact = new BusinessContact();
            refresh(contact);
            BusinessContactList.contacts.add(contact);
            cmb.addItem(contact.getFullName());
            cmb.setSelectedIndex(cmb.getItemCount() - 1);
            // update button state
            setDefaultState();
        });


        btnUpdate.addActionListener(e ->
        {
            // get the selected contact and update its fields
            int idx = cmb.getSelectedIndex();
            BusinessContact contact = BusinessContactList.contacts.get(idx);
            refresh(contact);
            // update the combo box
            cmb.insertItemAt(contact.getFullName(), idx);
            cmb.removeItemAt(idx + 1);
            cmb.setSelectedIndex(idx);
        });

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(327, 207, 89, 23);
        btnDelete.setEnabled(false);
        frame.getContentPane().add(btnDelete);


        btnDelete.addActionListener(e ->
        {
            if (JOptionPane.showConfirmDialog(frame,
                String.format("Delete %s?",
                    BusinessContactList.contacts.get(cmb.getSelectedIndex()).getFullName()),
                "Delete", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
            {
                int idx = cmb.getSelectedIndex();
                cmb.removeItemAt(idx);
                BusinessContactList.contacts.remove(idx);
            }

        });

        setupMenu();
    }

    private void setupMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mnuOpen = new JMenuItem("Open...");
        mnuOpen.addActionListener(arg0 ->
        {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            int result = fc.showOpenDialog(frame);

            // if user clicked Cancel button on dialog, return
            if (result == JFileChooser.CANCEL_OPTION)
            {
                return;// System.exit(1);

            }

            // return Path representing the selected file
            Path filePath = fc.getSelectedFile().toPath();
            storageFileName = filePath.toString();
            //clear comboBox and list
            if (!BusinessContactList.contacts.isEmpty())
            {
                cmb.setModel(new DefaultComboBoxModel<>());
                BusinessContactList.contacts.clear();
            }

            // read the file and load the comboBox
            if (BusinessContactList.readContactFile(storageFileName))
            {
                loadContacts();
                setDefaultState();
                isFileSaved = true;
            }
            else
            {
                setNewState();
            }
        });
        mnFile.add(mnuOpen);

        JSeparator separator = new JSeparator();
        mnFile.add(separator);

        JMenuItem mnuSave = new JMenuItem("Save");
        mnuSave.addActionListener(e ->
        {
            if (BusinessContactList.contacts.size() == 0)
            {
                JOptionPane.showMessageDialog(
                    frame,
                    "There are not contacts to save!",
                    "Can't save contacts...",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isFileSaved)
            {
                openFileDialog();
            }
            BusinessContactList.writeContactFile(storageFileName);
        });
        mnuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        mnFile.add(mnuSave);

        JMenuItem mnuSaveAs = new JMenuItem("Save As...");
        mnuSaveAs.addActionListener(arg0 ->
        {
            if (BusinessContactList.contacts.size() == 0)
            {
                JOptionPane.showMessageDialog(
                    frame,
                    "There are not contacts to save!",
                    "Can't save contacts...",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (openFileDialog())
            {
                BusinessContactList.writeContactFile(storageFileName);
            }
        });

        mnFile.add(mnuSaveAs);

        JSeparator separator_1 = new JSeparator();
        mnFile.add(separator_1);

        JMenuItem mnuExit = new JMenuItem("Exit");
        mnuExit.addActionListener(e -> System.exit(0));
        mnuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        mnFile.add(mnuExit);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        JMenuItem mnuAbout = new JMenuItem("About");
        mnuAbout.addActionListener(arg0 -> About.main(null));
        mnHelp.add(mnuAbout);
    }

    private boolean openFileDialog()
    {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        int result = fc.showSaveDialog(null);

        // if user clicked Cancel button on dialog, return
        if (result == JFileChooser.CANCEL_OPTION)
        {
            return false;
        }
        storageFileName = fc.getSelectedFile().getName();
        return true;
    }

    private void loadContacts()
    {
        for (BusinessContact item : BusinessContactList.contacts)
        {
            cmb.addItem(item.getFullName());
            // System.out.println(item);
        }
        cmb.setSelectedIndex(0);
        updateFields(0);
    }

    private void setDefaultState()
    {
        btnNew.setEnabled(true);
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void setNewState()
    {
        btnNew.setEnabled(true);
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    private void updateFields(int selectedIndex)
    {
        BusinessContact contact = BusinessContactList.contacts.get(selectedIndex);
        txtFirst.setText(contact.getFirstName());
        txtLast.setText(contact.getLastName());
        txtPhone.setText(contact.getPhoneNumber());
        txtEmail.setText(contact.getEmailAddress());
        txtCompany.setText(contact.getCompany());
    }

    void refresh(BusinessContact contact)
    {
        contact.setFirstName(txtFirst.getText());
        contact.setLastName(txtLast.getText());
        contact.setPhoneNumber(txtPhone.getText());
        contact.setEmailAddress(txtEmail.getText());
        contact.setCompany(txtCompany.getText());
    }

    void clear()
    {
        txtFirst.setText("");
        txtLast.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtCompany.setText("");
    }

}
