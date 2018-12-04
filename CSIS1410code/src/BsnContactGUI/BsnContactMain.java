//
////CSIS1410
////BsnConactMain
//
//package BsnContactGUI;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JMenuBar;
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.JSeparator;
//import javax.swing.ComboBoxModel;
//import javax.swing.ImageIcon;
//import javax.swing.SwingConstants;
//import javax.swing.KeyStroke;
//
//import java.awt.event.KeyEvent;
//import java.io.File;
//import java.nio.file.Path;
//import java.awt.event.InputEvent;
//
//import javax.swing.JComboBox;
//import javax.swing.JFileChooser;
//import javax.swing.JLabel;
//
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//import javax.swing.JTextField;
//
//import java.awt.Font;
//import java.awt.Point;
//
//import javax.swing.JButton;
////import java.awt.event.FocusAdapter;
////import java.awt.event.FocusEvent;
//import java.awt.Toolkit;
//import javax.swing.DefaultComboBoxModel;
//
//public class BsnContactMain
//{
//	public static JFrame frame;
//	private JComboBox<String> comboBox;
//	private JTextField txtFirstName;
//	private JTextField txtLastName;
//	private JTextField txtPhone;
//	private JTextField txtEmail;
//	private JTextField txtCompany;
//	private JButton btnNewContact;
//	private JButton btnAddContact;
//	private JButton btnUpdateContact;
//	private JButton btnDeleteContact;
//	private boolean gotoNextField;
//	// private boolean lostFocus;
//	private String txtFieldValue;
//	private String fileName;
//	private boolean fileSaved;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args)
//	{
//		EventQueue.invokeLater(() ->
//		{
//			try
//			{
//				BsnContactMain window = new BsnContactMain();
//				window.frame.setVisible(true);
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public BsnContactMain()
//	{
//		initialize();
//		if (ContactRecordList.readContactFile(fileName))
//		{
//			loadContactComboBox();
//			setDefaultButtonState();
//			fileSaved = true;
//		} else
//		{// no records read - set buttons
//			setNewButtonState();
//		}
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize()
//	{
//		frame = new JFrame();
//		frame.setIconImage(
//				Toolkit.getDefaultToolkit().getImage(BsnContactMain.class.getResource("/images/slcclogo.png")));
//		frame.setBounds(650, 450, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		frame.setResizable(false);
//
//		JLabel lblNewLabel = new JLabel("My Really Cool Contact Management System");
//		lblNewLabel.setBounds(10, 11, 414, 20);
//		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		frame.getContentPane().add(lblNewLabel);
//
//		fileSaved = false;
//		fileName = "contacts.ser";
//
//		/*
//		 * Combo Box label and box
//		 */
//		JLabel lblContacts = new JLabel("Contacts");
//		lblContacts.setBounds(10, 39, 110, 14);
//		frame.getContentPane().add(lblContacts);
//
//		comboBox = new JComboBox<String>();
//		comboBox.setModel(new DefaultComboBoxModel<String>());
//		comboBox.setBounds(10, 56, 132, 20);
//		comboBox.setMaximumRowCount(4);
//		frame.getContentPane().add(comboBox);
//
//		comboBox.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				updateTextFields(comboBox.getSelectedIndex());
//				setDefaultButtonState();
//			}
//		});
//
//		/*
//		 * First Name label and field
//		 */
//		JLabel lblFirstName = new JLabel("First Name");
//		lblFirstName.setBounds(168, 39, 64, 14);
//		frame.getContentPane().add(lblFirstName);
//
//		txtFirstName = new JTextField();
//		txtFirstName.setBounds(168, 56, 91, 20);
//		txtFirstName.setColumns(10);
//		txtFirstName.setToolTipText("Enter Contact's First Name");
//		frame.getContentPane().add(txtFirstName);
//
//		/*
//		 * txtFirstName.addFocusListener(new FocusAdapter() {
//		 *
//		 * @Override public void focusLost(FocusEvent e) {
//		 *
//		 * a text field loses focus whenever the tab key or the enter key is
//		 * pressed if lostFocus is true, the enter key has been pressed so don't
//		 * execute this code because the field's ActionListener does this when
//		 * the enter key is pressed :)
//		 *
//		 * if(lostFocus || txtFirstName.getText()==null) { gotoNextField = true;
//		 * txtFieldValue = txtFirstName.getText();
//		 * if(!BC_Validate.isValidFirstName(txtFirstName.getText())) {
//		 * JOptionPane.showMessageDialog(frame, String.format(
//		 * "%s is not a valid first name!", txtFieldValue), "Invalid Input",
//		 * JOptionPane.ERROR_MESSAGE); txtFirstName.setText("");
//		 * txtFirstName.requestFocusInWindow(); gotoNextField = false; }
//		 * if(gotoNextField) { txtLastName.requestFocusInWindow(); } } } });
//		 */
//		txtFirstName.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent arg0)
//			{
//				// lostFocus = false;
//				gotoNextField = true;
//				txtFieldValue = txtFirstName.getText();
//				if (!BC_Validate.isValidFirstName(txtFieldValue))
//				{
//					JOptionPane.showMessageDialog(frame, String.format("%s is not a valid first name!", txtFieldValue),
//							"Invalid Input", JOptionPane.ERROR_MESSAGE);
//					txtFirstName.setText("");
//					gotoNextField = false;
//					// lostFocus=true;
//				}
//				if (gotoNextField)
//				{
//					txtLastName.requestFocusInWindow();
//					// lostFocus = false;
//				}
//			}
//		});
//
//		/*
//		 * Last Name label and field
//		 */
//		JLabel lblLastName = new JLabel("Last Name");
//		lblLastName.setBounds(279, 39, 97, 14);
//		frame.getContentPane().add(lblLastName);
//
//		txtLastName = new JTextField();
//		txtLastName.setBounds(279, 56, 149, 20);
//		txtLastName.setColumns(10);
//		txtLastName.setToolTipText("Enter Contact's Last Name");
//		frame.getContentPane().add(txtLastName);
//
//		/*
//		 * txtLastName.addFocusListener(new FocusAdapter() {
//		 *
//		 * @Override public void focusLost(FocusEvent arg0) { if(lostFocus) {
//		 * gotoNextField = true; txtFieldValue = txtLastName.getText();
//		 * if(!BC_Validate.isValidLastName(txtLastName.getText())) {
//		 * JOptionPane.showMessageDialog(frame, String.format(
//		 * "%s is not a valid last name!", txtFieldValue), "Invalid Input",
//		 * JOptionPane.ERROR_MESSAGE); txtLastName.setText("");
//		 * txtLastName.requestFocusInWindow(); gotoNextField = false; }
//		 * if(gotoNextField) txtPhone.requestFocusInWindow(); } } });
//		 */
//		txtLastName.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent arg0)
//			{
//				// lostFocus = false;
//				gotoNextField = true;
//				txtFieldValue = txtLastName.getText();
//				if (!BC_Validate.isValidLastName(txtFieldValue))
//				{
//					JOptionPane.showMessageDialog(frame, String.format("%s is not a valid last name!", txtFieldValue),
//							"Invalid Input", JOptionPane.ERROR_MESSAGE);
//					txtLastName.setText("");
//					gotoNextField = false;
//					// lostFocus=true;
//				}
//				if (gotoNextField)
//				{
//					txtPhone.requestFocusInWindow();
//					// lostFocus = false;
//				}
//
//			}
//		});
//
//		/*
//		 * Phone label and field
//		 */
//		JLabel lblPhone = new JLabel("Phone");
//		lblPhone.setBounds(168, 95, 46, 14);
//		frame.getContentPane().add(lblPhone);
//
//		txtPhone = new JTextField();
//		txtPhone.setBounds(168, 111, 91, 20);
//		txtPhone.setColumns(10);
//		txtPhone.setToolTipText("Enter Contat's Phone");
//		frame.getContentPane().add(txtPhone);
//
//		/*
//		 * txtPhone.addFocusListener(new FocusAdapter() {
//		 *
//		 * @Override public void focusLost(FocusEvent e) { gotoNextField = true;
//		 * txtFieldValue = txtPhone.getText();
//		 * if(!BC_Validate.isValidPhone(txtPhone.getText())) {
//		 * JOptionPane.showMessageDialog(frame, String.format(
//		 * "%s is not a valid phone number!", txtFieldValue), "Invalid Input",
//		 * JOptionPane.ERROR_MESSAGE); txtPhone.setText("");
//		 * txtPhone.requestFocusInWindow(); gotoNextField = false; }
//		 * if(gotoNextField) txtEmail.requestFocusInWindow(); } });
//		 */
//		txtPhone.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				// lostFocus = false;
//				gotoNextField = true;
//				txtFieldValue = txtPhone.getText();
//				if (!BC_Validate.isValidPhone(txtFieldValue))
//				{
//					JOptionPane.showMessageDialog(frame,
//							String.format("%s is not a valid phone number!", txtFieldValue), "Invalid Input",
//							JOptionPane.ERROR_MESSAGE);
//					txtPhone.setText("");
//					gotoNextField = false;
//					// lostFocus=true;
//				}
//				if (gotoNextField)
//				{
//					txtEmail.requestFocusInWindow();
//					// lostFocus = false;
//				}
//			}
//		});
//
//		/*
//		 * Email field
//		 */
//		JLabel lblEmail = new JLabel("eMail");
//		lblEmail.setBounds(279, 95, 69, 14);
//		frame.getContentPane().add(lblEmail);
//
//		txtEmail = new JTextField();
//		txtEmail.setBounds(279, 111, 149, 20);
//		txtEmail.setColumns(10);
//		txtEmail.setToolTipText("Enter Contact's eMail Address");
//		frame.getContentPane().add(txtEmail);
//
//		/*
//		 * txtEmail.addFocusListener(new FocusAdapter() {
//		 *
//		 * @Override public void focusLost(FocusEvent e) { gotoNextField = true;
//		 * txtFieldValue = txtEmail.getText();
//		 * if(!BC_Validate.isValidEmail(txtEmail.getText())) {
//		 * JOptionPane.showMessageDialog(frame, String.format(
//		 * "%s is not a valid email address!", txtFieldValue), "Invalid Input",
//		 * JOptionPane.ERROR_MESSAGE); txtEmail.setText("");
//		 * txtEmail.requestFocusInWindow(); gotoNextField = false; }
//		 * if(gotoNextField) txtCompany.requestFocusInWindow(); } });
//		 */
//		txtEmail.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				// lostFocus = false;
//				gotoNextField = true;
//				txtFieldValue = txtEmail.getText();
//				if (!BC_Validate.isValidEmail(txtFieldValue))
//				{
//					JOptionPane.showMessageDialog(frame,
//							String.format("%s is not a valid email address!", txtFieldValue), "Invalid Input",
//							JOptionPane.ERROR_MESSAGE);
//					txtEmail.setText("");
//					gotoNextField = false;
//					// lostFocus=true;
//				}
//				if (gotoNextField)
//				{
//					txtCompany.requestFocusInWindow();
//					// lostFocus = false;
//				}
//			}
//		});
//
//		/*
//		 * Company label and field
//		 */
//		JLabel lblCompany = new JLabel("Company");
//		lblCompany.setBounds(168, 151, 64, 14);
//		frame.getContentPane().add(lblCompany);
//
//		txtCompany = new JTextField();
//		txtCompany.setBounds(168, 168, 260, 20);
//		txtCompany.setColumns(10);
//		txtCompany.setToolTipText("Enter Contact's Company");
//		frame.getContentPane().add(txtCompany);
//
//		/*
//		 * txtCompany.addFocusListener(new FocusAdapter() {
//		 *
//		 * @Override public void focusLost(FocusEvent e) { gotoNextField = true;
//		 * txtFieldValue = txtCompany.getText();
//		 * if(!BC_Validate.isValidCompanyName(txtCompany.getText())) {
//		 * JOptionPane.showMessageDialog(frame, String.format(
//		 * "%s is not a valid company name!", txtFieldValue), "Invalid Input",
//		 * JOptionPane.ERROR_MESSAGE); txtCompany.setText("");
//		 * txtCompany.requestFocusInWindow(); gotoNextField = false; }
//		 * if(gotoNextField) txtFirstName.requestFocusInWindow(); } });
//		 */
//		txtCompany.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				// lostFocus = false;
//				gotoNextField = true;
//				txtFieldValue = txtCompany.getText();
//				if (!BC_Validate.isValidCompanyName(txtFieldValue))
//				{
//					JOptionPane.showMessageDialog(frame,
//							String.format("%s is not a valid company name!", txtFieldValue), "Invalid Input",
//							JOptionPane.ERROR_MESSAGE);
//					txtCompany.setText("");
//					gotoNextField = false;
//					// lostFocus=true;
//				}
//				if (gotoNextField)
//				{
//					txtFirstName.requestFocusInWindow();
//					// lostFocus = false;
//				}
//			}
//		});
//
//		/*
//		 * New button
//		 */
//		btnNewContact = new JButton("New");
//		btnNewContact.setBounds(15, 207, 89, 23);
//		btnNewContact.setEnabled(false);
//		frame.getContentPane().add(btnNewContact);
//
//		btnNewContact.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				clearTextFields();
//				setNewButtonState();
//				txtFirstName.requestFocusInWindow();
//			}
//		});
//
//		/*
//		 * Save button
//		 */
//		btnAddContact = new JButton("Add");
//		btnAddContact.setBounds(119, 207, 89, 23);
//		btnAddContact.setEnabled(false);
//		frame.getContentPane().add(btnAddContact);
//
//		btnAddContact.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				// create new client
//				ContactRecord contact = new ContactRecord();
//				updateClientFields(contact);
//				// add the client to contact list
//				ContactRecordList.cList.add(contact);
//				// add client to combobox
//				comboBox.addItem(contact.getFLName());
//				// update selected item in combobox
//				comboBox.setSelectedIndex(comboBox.getItemCount() - 1);
//				// update button state
//				setDefaultButtonState();
//			}
//		});
//
//		/*
//		 * Update button
//		 */
//		btnUpdateContact = new JButton("Update");
//		btnUpdateContact.setBounds(223, 207, 89, 23);
//		btnUpdateContact.setEnabled(false);
//		frame.getContentPane().add(btnUpdateContact);
//
//		btnUpdateContact.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				// get the selected contact and update its fields
//				int idx = comboBox.getSelectedIndex();
//				ContactRecord contact = ContactRecordList.cList.get(idx);
//				updateClientFields(contact);
//				// update the combo box
//				comboBox.insertItemAt(contact.getFLName(), idx);
//				comboBox.removeItemAt(idx+1);
//				comboBox.setSelectedIndex(idx);
//			}
//		});
//
//		/*
//		 * Delete button
//		 */
//		btnDeleteContact = new JButton("Delete");
//		btnDeleteContact.setBounds(327, 207, 89, 23);
//		btnDeleteContact.setEnabled(false);
//		frame.getContentPane().add(btnDeleteContact);
//
//		JLabel lblNewLabel_1 = new JLabel("");
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setIcon(new ImageIcon(BsnContactMain.class.getResource("/images/Secretary_.png")));
//		lblNewLabel_1.setBounds(10, 80, 110, 126);
//		frame.getContentPane().add(lblNewLabel_1);
//		btnDeleteContact.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				if (JOptionPane.showConfirmDialog(frame,
//						String.format("Delete %s?",
//								ContactRecordList.cList.get(comboBox.getSelectedIndex()).getFLName()),
//						"Delete", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
//				{
//					int idx = comboBox.getSelectedIndex();
//					comboBox.removeItemAt(idx);
//					ContactRecordList.cList.remove(idx);
//				}
//
//			}
//		});
//
//		/*
//		 * Menu bar
//		 */
//		JMenuBar menuBar = new JMenuBar();
//		frame.setJMenuBar(menuBar);
//
//		JMenu mnFile = new JMenu("File");
//		menuBar.add(mnFile);
//
//		JMenuItem mntmOpen = new JMenuItem("Open...");
//		mntmOpen.setIcon(new ImageIcon(BsnContactMain.class.getResource("/images/Open_v2.png")));
//		mntmOpen.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent arg0)
//			{
//				// configure dialog allowing selection of a file or directory
//				JFileChooser fileChooser = new JFileChooser();
//				fileChooser.setCurrentDirectory(new File("."));
//				int result = fileChooser.showOpenDialog(frame);
//
//				// if user clicked Cancel button on dialog, return
//				if (result == JFileChooser.CANCEL_OPTION)
//					return;// System.exit(1);
//
//				// return Path representing the selected file
//				Path filePath = fileChooser.getSelectedFile().toPath();
//				fileName = filePath.toString();
//				//clear comboBox and list
//				if(!ContactRecordList.cList.isEmpty()){
//					comboBox.setModel(new DefaultComboBoxModel<String>());
//					ContactRecordList.cList.clear();
//				}
//				//read the file and load the comboBox
//				if (ContactRecordList.readContactFile(fileName))
//				{
//					loadContactComboBox();
//					setDefaultButtonState();
//					fileSaved = true;
//				} else
//				{// no records read - set buttons
//					setNewButtonState();
//				}
//			}
//		});
//		mnFile.add(mntmOpen);
//
//		JSeparator separator = new JSeparator();
//		mnFile.add(separator);
//
//		JMenuItem mntmSave = new JMenuItem("Save");
//		mntmSave.setIcon(new ImageIcon(BsnContactMain.class.getResource("/images/Save.png")));
//		mntmSave.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				if (ContactRecordList.cList.size() == 0)
//				{
//					JOptionPane.showMessageDialog(
//						frame,
//						"There are not contacts to save!",
//						"Woops...",
//						JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				if (!fileSaved)
//				{
//					openFileSaverDialog();
//				}
//				ContactRecordList.writeContactFile(fileName);
//			}
//		});
//		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
//		mnFile.add(mntmSave);
//
//		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
//		mntmSaveAs.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				if (ContactRecordList.cList.size() == 0)
//				{
//					JOptionPane.showMessageDialog(
//						frame,
//						"There are not contacts to save!",
//						"Woops...",
//						JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//
//				if(openFileSaverDialog())
//					ContactRecordList.writeContactFile(fileName);
//			}
//		});
//		mntmSaveAs.setIcon(new ImageIcon(BsnContactMain.class.getResource("/images/Save-as.png")));
//
//		mnFile.add(mntmSaveAs);
//
//		JSeparator separator_1 = new JSeparator();
//		mnFile.add(separator_1);
//
//		JMenuItem mntmExit = new JMenuItem("Exit");
////		mntmExit.setIcon(
////				new ImageIcon(BsnContactMain.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
////		mntmExit.setSelectedIcon(
////				new ImageIcon(BsnContactMain.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
//		mntmExit.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				System.exit(0);
//			}
//		});
//		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
//		mnFile.add(mntmExit);
//
//		JMenu mnHelp = new JMenu("Help");
//		menuBar.add(mnHelp);
//
//		JMenuItem mntmAbout = new JMenuItem("About");
//		mntmAbout.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent arg0)
//			{
//				About. main(null);
//			}
//		});
//		mntmAbout.setIcon(new ImageIcon(BsnContactMain.class.getResource("/images/slcclogo_iconpng.png")));
//		mnHelp.add(mntmAbout);
//	}// end initialize()
//
//	private boolean openFileSaverDialog()
//	{
//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setCurrentDirectory(new File("."));
//		int result = fileChooser.showSaveDialog(null);
//
//		// if user clicked Cancel button on dialog, return
//		if (result == JFileChooser.CANCEL_OPTION)
//			return false;// System.exit(1);
//
//		// return Path representing the selected file
//		fileName = fileChooser.getSelectedFile().getName();
//		return true;
//	}
//
//	void loadContactComboBox()
//	{
//		for (ContactRecord item : ContactRecordList.cList)
//		{
//			comboBox.addItem(item.getFLName());
//			// System.out.println(item);
//		}
//		comboBox.setSelectedIndex(0);
//		updateTextFields(0);
//	}
//
//	void setDefaultButtonState()
//	{// if file loaded successfully | new contact added
//		btnNewContact.setEnabled(true);
//		btnAddContact.setEnabled(false);
//		btnUpdateContact.setEnabled(true);
//		btnDeleteContact.setEnabled(true);
//	}
//
//	void setNewButtonState()
//	{ // if file not found/loaded | new button clicked
//		btnNewContact.setEnabled(true);
//		btnAddContact.setEnabled(true);
//		btnUpdateContact.setEnabled(false);
//		btnDeleteContact.setEnabled(false);
//	}
//
//	void updateTextFields(int selectedIndex)
//	{
//		ContactRecord contact = ContactRecordList.cList.get(selectedIndex);
//		txtFirstName.setText(contact.getFirstName());
//		txtLastName.setText(contact.getLastName());
//		txtPhone.setText(contact.getPhoneNumber());
//		txtEmail.setText(contact.getEmailAddress());
//		txtCompany.setText(contact.getCompany());
//	}
//
//}// end class
