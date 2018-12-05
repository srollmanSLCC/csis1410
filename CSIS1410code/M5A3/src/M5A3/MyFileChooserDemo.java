/********************************************************
 *
 *  Project :  M5A3
 *  File    :  MyFileChooserDemo.java
 *  Name    :  Steven Rollman
 *
 *  Description : Runs an application that you can enter text into, save it to disk as a file.
 *                Clicking file -> save when no filepath is set, will pop up a JFileChooser and
 *                allow you to select where to save. If a file has already been loaded, file -> save
 *                will just save the file to the current file.
 *
 ********************************************************/

package M5A3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * MyFileChooserDemo Class
 */
public class MyFileChooserDemo
{

	private JFrame frame;
	private static Scanner input;
	private Formatter output; // outputs text to a file       
	private String fileName;
	private JTextField txtFileName;
	private JTextArea textBody;
	private boolean fileSaved;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
			try
			{
				MyFileChooserDemo window = new MyFileChooserDemo();
				window.frame.setVisible(true);
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
	public MyFileChooserDemo()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		fileSaved = false;
		
		JLabel lbl = new JLabel("File Name: ");
		lbl.setBounds(10, 11, 62, 14);
		frame.getContentPane().add(lbl);
		
		txtFileName = new JTextField();
		txtFileName.setEditable(false);
		txtFileName.setBounds(70, 8, 354, 20);
		frame.getContentPane().add(txtFileName);
		txtFileName.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 36, 414, 194);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textBody = new JTextArea();
		textBody.setBounds(10, 10, 394, 174);
		panel.add(textBody);
		textBody.setLineWrap(true);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open File...");
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	      // configure dialog allowing selection of a file or directory
	      JFileChooser fileChooser = new JFileChooser();
	      fileChooser.setFileSelectionMode(
	         JFileChooser.FILES_AND_DIRECTORIES);
	      int result = fileChooser.showOpenDialog(null);

	      // if user clicked Cancel button on dialog, return
	      if (result == JFileChooser.CANCEL_OPTION)
	         return;//System.exit(1);

	      // return Path representing the selected file
	      Path filePath = fileChooser.getSelectedFile().toPath();
	      fileName = filePath.toString();
				
	      try
	      {
	         input = new Scanner(Paths.get(fileName));
	    	  //input = new Scanner(fileName);
	         textBody.setText("");
	         while (input.hasNext()) // while there is more to read
	         {
	            // read file line by line
	        	 textBody.append( input.nextLine() );
	        	 textBody.append("\n");
	         }
	      } 
	      catch (IOException ioException)
	      {
	         System.err.println("Error opening file. Terminating.");
	         System.exit(1);
	      } 
	      catch (NoSuchElementException elementException)
	      {
	         System.err.println("File improperly formed. Terminating.");
	      } 
	      catch (IllegalStateException stateException)
	      {
	         System.err.println("Error reading from file. Terminating.");
	      } 
	      finally
	      {
	        if (input != null)
	          input.close();
	      }
			}
		});
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileSaved = false;
				txtFileName.setText("");
				textBody.setText("");
				textBody.requestFocusInWindow();
			}
		});
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFile.add(mntmNew);
		mnFile.add(mntmOpenFile);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(e ->
		{
			if(!fileSaved)
			{
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(
                    JFileChooser.FILES_AND_DIRECTORIES);
                int result = fileChooser.showSaveDialog(null);

                // if user clicked Cancel button on dialog, return
                if (result == JFileChooser.CANCEL_OPTION)
                    return;//System.exit(1);
                Path filePath = fileChooser.getSelectedFile().toPath();
                fileName = filePath.toString();
                txtFileName.setText(fileName);
			}
			saveMyFile();
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.setIcon(new ImageIcon(MyFileChooserDemo.class.getResource("floppy.gif")));
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.addActionListener(e ->
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(
				JFileChooser.FILES_AND_DIRECTORIES);
			int result = fileChooser.showSaveDialog(null);

			// if user clicked Cancel button on dialog, return
            if (result == JFileChooser.CANCEL_OPTION)
                return;//System.exit(1);

            // return Path representing the selected file
            Path filePath = fileChooser.getSelectedFile().toPath();
		  	fileName = filePath.toString();
			txtFileName.setText(fileName);
			saveMyFile();
		});
		mnFile.add(mntmSaveAs);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);
	}
	
	private void saveMyFile()
	{
	  try
	  {
	  	//if(output == null) return;
	  	output = new Formatter(fileName); // open the file
	  	output.format("%s", textBody.getText());
	  	fileSaved = true;
	  }
	  catch (SecurityException securityException)
	  {
	     System.err.println("Write permission denied. Terminating.");
	     System.exit(1); // terminate the program
	  } 
	  catch (FileNotFoundException fileNotFoundException)
	  {
	     System.err.println("Error opening file. Terminating.");
	     System.exit(1); // terminate the program
	  } 
	  catch (FormatterClosedException formatterClosedException)
	  {
	     System.err.println("Error writing to file. Terminating.");
	     System.exit(1); // terminate the program
	  } 
	  finally
	  {
	  	if(output != null)
	  		output.close();
	  }
	}
}
