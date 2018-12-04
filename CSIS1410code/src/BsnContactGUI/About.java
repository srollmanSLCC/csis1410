//package BsnContactGUI;
//
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.Toolkit;
//import javax.swing.JLabel;
//import javax.swing.ImageIcon;
//import java.awt.Color;
//import javax.swing.SwingConstants;
//import java.awt.Font;
//
//public class About extends JDialog
//{
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args)
//	{
//		try
//		{
//			About dialog = new About();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setLocation(BsnContactMain.frame.getLocation());
//			dialog.setVisible(true);
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Create the dialog.
//	 */
//	public About()
//	{
//		getContentPane().setBackground(Color.WHITE);
//		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/images/slcclogo.png")));
//		setBounds(100, 100, 400, 231);
//		//this.setLocation(getLocation());
//		getContentPane().setLayout(null);
//
//		JLabel lblNewLabel = new JLabel("");
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/images/SLCCLogo1.png")));
//		lblNewLabel.setBounds(12, 0, 358, 68);
//		getContentPane().add(lblNewLabel);
//
//		JLabel lblNewLabel_1 = new JLabel("CSIS 1410 Object Orented Programming");
//		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setBounds(12, 68, 358, 22);
//		getContentPane().add(lblNewLabel_1);
//
//		JLabel lblProjectBusinessContact = new JLabel("Project: M5-A5 - Business Contacts");
//		lblProjectBusinessContact.setHorizontalAlignment(SwingConstants.CENTER);
//		lblProjectBusinessContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblProjectBusinessContact.setBounds(12, 96, 358, 16);
//		getContentPane().add(lblProjectBusinessContact);
//
//		JLabel lblProgrammerRobertBaird = new JLabel("Programmer: Robert Baird");
//		lblProgrammerRobertBaird.setHorizontalAlignment(SwingConstants.CENTER);
//		lblProgrammerRobertBaird.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblProgrammerRobertBaird.setBounds(12, 114, 358, 16);
//		getContentPane().add(lblProgrammerRobertBaird);
//
//		JLabel lblthereIsNot = new JLabel("\"There is no try...only do or do not\" ~Yoda");
//		lblthereIsNot.setForeground(new Color(128, 0, 0));
//		lblthereIsNot.setHorizontalAlignment(SwingConstants.CENTER);
//		lblthereIsNot.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
//		lblthereIsNot.setBounds(12, 154, 358, 16);
//		getContentPane().add(lblthereIsNot);
//	}
//}
