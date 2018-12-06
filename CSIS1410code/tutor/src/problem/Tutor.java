package problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.file.Path;

public class Tutor
{
    public enum statuses
    {
        ADD_NEW, ADD_PROBLEM, CHANGING_STUDENT, DEFAULT, EDIT, SHOW_PROBLEMS
    }

    public enum buttonPositions
    {
        DEFAULT, PROBLEMS
    }

    private final String nameRegex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    private final String solutionRegex = "^-?[0-9]+$";

    private Student currentStudent;
    private Problem currentProblem;
    private statuses currentStatus = statuses.ADD_NEW;
    private statuses mostRecentStatus;

    public static JFrame frame;
    private JComboBox<String> cmb;
    private JComboBox<String> cmbProblems;
    private JTextField txtName;
    private JTextField txtSolution;
    private JLabel lblTutoring;
    private JLabel lblStudents;
    private JLabel lblProblems;
    private JLabel lblName;
    private JLabel lblProblem;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    private JButton btnFour;
    private JButton btnFive;
    private JButton btnSolve;
    private String storageFileName;
    private boolean isFileSaved;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            try
            {
                Tutor app = new Tutor();
                app.frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application. No Args Constructor
     */
    public Tutor()
    {
        initialize();
        if (StudentsList.readStudentFile(storageFileName))
        {
            loadStudents();
            if (currentStudent != null && currentProblem != null)
            {
                updateStatus(statuses.SHOW_PROBLEMS);
            }
            else
            {
                updateStatus(statuses.DEFAULT);
            }
            isFileSaved = true;
        }
        else
        {
            updateStatus(statuses.ADD_NEW);
        }
    }

    private void addComponents()
    {
        // Add our components to our main frame.
        frame.getContentPane().add(lblTutoring);
        frame.getContentPane().add(lblStudents);
        frame.getContentPane().add(cmb);
        frame.getContentPane().add(lblProblems);
        frame.getContentPane().add(cmbProblems);
        frame.getContentPane().add(lblName);
        frame.getContentPane().add(txtName);
        frame.getContentPane().add(lblProblem);
        frame.getContentPane().add(txtSolution);
        frame.getContentPane().add(btnOne);
        frame.getContentPane().add(btnTwo);
        frame.getContentPane().add(btnThree);
        frame.getContentPane().add(btnFour);
        frame.getContentPane().add(btnFive);
        frame.getContentPane().add(btnSolve);
    }

    private void addProblem(Problem.problemTypes type)
    {
        // First we want to add the problem to our list.
        currentStudent.addProblem(type);
        // We know that our current problem is now at index 0 because that's where all new problems go.
        currentProblem = currentStudent.getProblems().get(0);
        // update our fields and update our combobox.
        updateFields(cmb.getSelectedIndex());
        // update the main status of our app.
        updateStatus(statuses.SHOW_PROBLEMS);
        // render the problem now that our environment is prepared.
        updateProblem();
    }

    private void addStudent(String name) throws InvalidStudentNameException
    {
        if (!name.matches(nameRegex))
        {
            throw new InvalidStudentNameException("Name is invalid.");
        }
        Student student = new Student(name);
        StudentsList.students.add(student);
        cmb.addItem(student.getName());
        cmb.setSelectedIndex(cmb.getItemCount() - 1);
    }

    void clear()
    {
        txtName.setText("");
        cmb.removeAllItems();
    }

    private void allowSubmitAnswer(boolean shouldAllow)
    {
        btnSolve.setEnabled(shouldAllow);
    }

    private void configureActionListeners()
    {
        cmb.addActionListener(e ->
        {
            updateStatus(statuses.CHANGING_STUDENT);
            if (cmb.getSelectedIndex() >= 0)
            {
                currentStudent = StudentsList.students.get(cmb.getSelectedIndex());
            }
            updateFields(cmb.getSelectedIndex());
            if (currentStudent.hasProblems())
            {
                updateStatus(statuses.SHOW_PROBLEMS);
                currentProblem = currentStudent.getProblems().get(currentStudent.getMostRecentProblemIndex());
            }
            else
            {
                updateStatus(statuses.DEFAULT);
            }
        });

        cmbProblems.addActionListener(e ->
        {
            if (currentStatus != statuses.ADD_PROBLEM &&
                currentStatus != statuses.CHANGING_STUDENT &&
                cmbProblems.getSelectedIndex() > -1
            )
            {
                // update current problem.
                currentProblem = currentStudent.getProblems().get(cmbProblems.getSelectedIndex());
                currentStudent.setMostRecentProblemIndex(cmbProblems.getSelectedIndex());

                // if the problem isn't solved, make sure we can edit it.
                enableSolutionEntry(!currentProblem.isSolved());

                updateProblem();
            }
        });

        btnOne.addActionListener(e ->
        {
            switch (this.currentStatus)
            {
                case EDIT:
                    try
                    {
                        addStudent(txtName.getText());
                    }
                    catch (InvalidStudentNameException ex)
                    {
                        JOptionPane.showMessageDialog(frame, ex.getMessage());
                    }
                    return;

                case ADD_PROBLEM:
                    addProblem(Problem.problemTypes.ADDITION);
                    return;

                default:
                    if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to clear your list and start over?",
                        "Remove All",
                        JOptionPane.CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE
                    ) == JOptionPane.YES_OPTION)
                    {
                        // Only do this if they confirm that they want to.
                        clear();
                        updateStatus(statuses.ADD_NEW);
                        txtName.requestFocusInWindow();
                    }
                    return;
            }
        });

        btnTwo.addActionListener(e ->
        {
            switch (this.currentStatus)
            {
                case DEFAULT:
                case SHOW_PROBLEMS:
                    updateStatus(statuses.ADD_NEW);
                    return;

                case ADD_PROBLEM:
                    addProblem(Problem.problemTypes.SUBTRACTION);
                    return;

                default:
                    try
                    {
                        addStudent(txtName.getText());
                        // update button state
                        updateStatus(statuses.DEFAULT);
                    }
                    catch (InvalidStudentNameException ex)
                    {
                        JOptionPane.showMessageDialog(frame, ex.getMessage());
                    }
                    return;
            }
        });


        btnThree.addActionListener(e ->
        {
            switch (this.currentStatus)
            {
                case EDIT:
                    // get the selected contact and update its fields
                    int idx = cmb.getSelectedIndex();
                    String newName = txtName.getText();
                    Student student = StudentsList.students.get(idx);
                    student.setName(newName);
                    // update the combo box
                    cmb.insertItemAt(student.getName(), idx);
                    cmb.removeItemAt(idx + 1);
                    cmb.setSelectedIndex(idx);
                    return;

                case ADD_PROBLEM:
                    addProblem(Problem.problemTypes.MULTIPLICATION);
                    return;

                default:
                    updateStatus(statuses.EDIT);
                    return;
            }
        });

        btnFour.addActionListener(e ->
        {
            switch (this.currentStatus)
            {
                case EDIT:
                    updateStatus(statuses.DEFAULT);
                    return;

                case ADD_PROBLEM:
                    addProblem(Problem.problemTypes.DIVISION);
                    return;

                default:
                    if (JOptionPane.showConfirmDialog(frame,
                        String.format("Delete %s?",
                            StudentsList.students.get(cmb.getSelectedIndex()).getName()),
                        "Delete", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                        int idx = cmb.getSelectedIndex();
                        StudentsList.students.remove(idx);
                        if (cmb.getItemCount() > 1)
                        {
                            cmb.removeItemAt(idx);
                        }
                        else
                        {
                            cmb.removeAllItems();
                        }
                        updateStatus(statuses.DEFAULT);
                    }
                    break;
            }
        });

        btnFive.addActionListener(e ->
        {
            switch (this.currentStatus)
            {
                case ADD_PROBLEM:
                    updateStatus(mostRecentStatus);
                    break;
                default:
                    updateStatus(statuses.ADD_PROBLEM);
                    return;
            }
        });

        btnSolve.addActionListener(e ->
        {
            // should only be enabled when a valid potential solution is entered.
            trySolveProblem();
        });

        txtName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e)
            {
                // we don't really care about keyTyped at the moment.
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                // we don't really care about keyPressed at the moment.
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                // first we want to make sure that what is entered is valid.
                if (txtName.getText().matches(nameRegex))
                {
                    // If the key that was pressed was enter, let's submit.
                    if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        btnTwo.doClick();
                        return;
                    }
                    return;
                }
            }
        });

        txtSolution.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e)
            {
                // we don't really care about keyTyped at the moment.
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                // we don't really care about keyPressed at the moment.
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                // first we want to make sure that what is entered is valid.
                if (txtSolution.getText().matches(solutionRegex))
                {
                    // If the key that was pressed was enter, let's submit.
                    if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        btnSolve.doClick();
                        return;
                    }
                    // If we have a potentially valid solution,
                    // then we want to enable the submit answer button
                    // but only if the problem isn't solved.
                    allowSubmitAnswer(!currentProblem.isSolved());
                    return;
                }

                // don't allow them to submit an answer.
                allowSubmitAnswer(false);
            }
        });
    }

    private void configureButtons()
    {
        int buttonLineY = 40;
        int buttonWidth = 89;
        int buttonHeight = 23;
        int problemFontSize = 20;

        // Set up our buttons
        btnOne.setEnabled(false);
        btnTwo.setEnabled(false);
        btnThree.setEnabled(false);
        btnFour.setEnabled(false);
        btnFive.setEnabled(false);
        btnSolve.setEnabled(false);
        btnOne.setBounds(15, buttonLineY, buttonWidth, buttonHeight);
        btnTwo.setBounds(119, buttonLineY, buttonWidth, buttonHeight);
        btnThree.setBounds(223, buttonLineY, buttonWidth, buttonHeight);
        btnFour.setBounds(327, buttonLineY, buttonWidth, buttonHeight);
        btnFive.setBounds(450, buttonLineY, 120, buttonHeight);
        btnSolve.setBounds(380, 80, 200, buttonHeight*2);
        btnSolve.setFont(new Font("ProblemButton", Font.BOLD, problemFontSize));
    }

    private void configureComboboxes()
    {
        // Set up students combo box
        cmb.setModel(new DefaultComboBoxModel<>());
        cmb.setBounds(230, 11, 132, 20);
        cmb.setMaximumRowCount(4);

        // Set up problems combo box
        cmbProblems.setModel(new DefaultComboBoxModel<>());
        cmbProblems.setBounds(450, 11, 150, 20);
        cmbProblems.setMaximumRowCount(4);
    }

    private void configureFrame()
    {
        // Set up the main frame, use dimensions of example for simplicity.
        frame.setBounds(650, 450, 640, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
    }

    private void configureLabels()
    {
        // Create labels for our components and set their positions.
        lblTutoring = new JLabel("Elementary Tutoring");
        lblTutoring.setBounds(10, 11, 150, 20);
        lblTutoring.setHorizontalAlignment(SwingConstants.LEFT);
        lblStudents = new JLabel("Student");
        lblStudents.setBounds(180, 13, 45, 14);

        // Problems combobox and student name entry will go in the same place.
        lblProblems = new JLabel("Problems");
        lblProblems.setBounds(390, 13, 60, 14);
        lblName = new JLabel("Name");
        lblName.setBounds(400, 13, 64, 14);
    }

    private void configureProblemArea()
    {
        // Set up problem solving area.
        int problemFontSize = 20;
        Font font = new Font("Problem", Font.PLAIN, problemFontSize);

        lblProblem = new JLabel();
        lblProblem.setBounds(10, 82, 250, 46);
        lblProblem.setFont(font);
        lblProblem.setHorizontalAlignment(SwingConstants.RIGHT);
        lblProblem.setHorizontalTextPosition(SwingConstants.RIGHT);
        txtSolution.setBounds(277, 80, 90,46);
        txtSolution.setColumns(10);
        txtSolution.setFont(font);
    }

    private void configureTextFields()
    {
        // Set up text field.
        txtName.setBounds(450, 11, 150, 20);
        txtName.setColumns(10);
    }

    private void enableSolutionEntry(boolean shouldEnable)
    {
        if (shouldEnable)
        {
            txtSolution.setEnabled(true);
            txtSolution.setText("");
        }
        else
        {
            txtSolution.setText(currentProblem.solution + "");
            txtSolution.setEnabled(false);
            btnSolve.setEnabled(false);
        }
    }

    /**
     * Initialize the values for our app.
     */
    private void initialize()
    {
        // Initialize variables.
        isFileSaved = false;
        storageFileName = "students.ser";
        frame = new JFrame();
        cmb = new JComboBox<>();
        cmbProblems = new JComboBox<>();
        txtName = new JTextField();
        txtSolution = new JTextField();
        btnOne = new JButton("New");
        btnTwo = new JButton("Add");
        btnThree = new JButton("Update");
        btnFour = new JButton("Delete");
        btnFive = new JButton("New Problem");
        btnSolve = new JButton("Submit Answer");

        configureFrame();
        configureLabels();
        configureComboboxes();
        configureTextFields();
        configureProblemArea();
        configureButtons();
        addComponents();
        configureActionListeners();
        setupMenu();
    }

    private void loadStudents()
    {
        for (Student item : StudentsList.students)
        {
            cmb.addItem(item.getName());
        }
        cmb.setSelectedIndex(0);
        if (StudentsList.students.size() > 0)
        {
            currentStudent = StudentsList.students.get(cmb.getSelectedIndex());
            if (currentStudent.hasProblems())
            {
                currentProblem = currentStudent.getProblems().get(currentStudent.getMostRecentProblemIndex());
            }
        }
        updateFields(0);
    }

    private void migrateButtons(buttonPositions position)
    {
        int buttonLineX;
        int buttonLineY;
        int buttonWidth;
        int buttonHeight;

        switch (position)
        {
            case PROBLEMS:
                buttonLineX = 450;
                buttonLineY = 40;
                buttonWidth = 120;
                buttonHeight = 23;
                btnOne.setBounds(buttonLineX, buttonLineY + 30, buttonWidth, buttonHeight);
                btnTwo.setBounds(buttonLineX, buttonLineY + 60, buttonWidth, buttonHeight);
                btnThree.setBounds(buttonLineX, buttonLineY + 90, buttonWidth, buttonHeight);
                btnFour.setBounds(buttonLineX, buttonLineY + 120, buttonWidth, buttonHeight);
                break;
            case DEFAULT:
            default:
                buttonLineY = 40;
                buttonWidth = 89;
                buttonHeight = 23;
                btnOne.setBounds(15, buttonLineY, buttonWidth, buttonHeight);
                btnTwo.setBounds(119, buttonLineY, buttonWidth, buttonHeight);
                btnThree.setBounds(223, buttonLineY, buttonWidth, buttonHeight);
                btnFour.setBounds(327, buttonLineY, buttonWidth, buttonHeight);
                break;
        }
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

    private void renderAddNewLayout()
    {
        btnOne.setEnabled(true);
        btnTwo.setEnabled(true);
        btnThree.setEnabled(false);
        btnFour.setEnabled(false);
        btnFive.setVisible(false);
        btnSolve.setVisible(false);
        btnOne.setText("New");
        btnTwo.setText("Add");
        btnThree.setText("Update");
        btnFour.setText("Delete");
        btnFive.setText("New Problem");
        lblProblems.setVisible(false);
        cmbProblems.setVisible(false);
        lblName.setVisible(true);
        txtName.setVisible(true);
        lblProblem.setVisible(false);
        txtSolution.setVisible(false);
        migrateButtons(buttonPositions.DEFAULT);
        txtName.setText("");
        txtName.requestFocusInWindow();
    }

    private void renderAddNewProblemLayout()
    {
        btnOne.setEnabled(true);
        btnTwo.setEnabled(true);
        btnThree.setEnabled(true);
        btnFour.setEnabled(true);
        btnFive.setEnabled(true);
        btnSolve.setVisible(false);
        btnOne.setText("Addition");
        btnTwo.setText("Subtraction");
        btnThree.setText("Multiplication");
        btnFour.setText("Division");
        btnFive.setText("Cancel");
        lblProblems.setVisible(true);
        cmbProblems.setVisible(true);
        lblName.setVisible(false);
        txtName.setVisible(false);
        lblProblem.setVisible(false);
        txtSolution.setVisible(false);
        migrateButtons(buttonPositions.PROBLEMS);
    }

    private void renderDefaultLayout()
    {
        btnOne.setEnabled(true);
        btnTwo.setEnabled(true);
        btnThree.setEnabled(true);
        btnFour.setEnabled(true);
        btnFive.setEnabled(true);
        btnFive.setVisible(true);
        btnSolve.setVisible(false);
        btnOne.setText("New");
        btnTwo.setText("Add");
        btnThree.setText("Update");
        btnFour.setText("Delete");
        btnFive.setText("New Problem");
        lblProblems.setVisible(true);
        cmbProblems.setVisible(true);
        lblName.setVisible(false);
        txtName.setVisible(false);
        lblProblem.setVisible(false);
        txtSolution.setVisible(false);
        migrateButtons(buttonPositions.DEFAULT);
    }

    private void renderEditLayout()
    {
        btnOne.setEnabled(true);
        btnTwo.setEnabled(false);
        btnThree.setEnabled(true);
        btnFour.setEnabled(true);
        btnFive.setVisible(false);
        btnSolve.setVisible(false);
        btnOne.setText("Add New");
        btnTwo.setText("Add");
        btnThree.setText("Submit");
        btnFour.setText("Cancel");
        btnFive.setText("New Problem");
        lblProblems.setVisible(false);
        cmbProblems.setVisible(false);
        lblName.setVisible(true);
        txtName.setVisible(true);
        lblProblem.setVisible(false);
        txtSolution.setVisible(false);
        migrateButtons(buttonPositions.DEFAULT);
    }

    private void renderProblemLayout()
    {

        btnOne.setEnabled(true);
        btnTwo.setEnabled(true);
        btnThree.setEnabled(true);
        btnFour.setEnabled(true);
        btnFive.setEnabled(true);
        btnFive.setVisible(true);
        btnSolve.setVisible(true);
        btnOne.setText("New");
        btnTwo.setText("Add");
        btnThree.setText("Update");
        btnFour.setText("Delete");
        btnFive.setText("New Problem");
        lblProblems.setVisible(true);
        cmbProblems.setVisible(true);
        lblName.setVisible(false);
        txtName.setVisible(false);
        lblProblem.setVisible(true);
        txtSolution.setVisible(true);
        migrateButtons(buttonPositions.DEFAULT);
        if (currentProblem != null)
        {
            enableSolutionEntry(!currentProblem.isSolved());
        }
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
            if (!StudentsList.students.isEmpty())
            {
                cmb.setModel(new DefaultComboBoxModel<>());
                StudentsList.students.clear();
            }

            //read the file and load the comboBox
            if (StudentsList.readStudentFile(storageFileName))
            {
                loadStudents();
                if (currentStudent != null && currentProblem != null)
                {
                    updateStatus(statuses.SHOW_PROBLEMS);
                }
                else
                {
                    updateStatus(statuses.DEFAULT);
                }
                isFileSaved = true;
            }
            else
            {
                updateStatus(statuses.ADD_NEW);
            }
        });
        mnFile.add(mnuOpen);

        JSeparator separator = new JSeparator();
        mnFile.add(separator);

        JMenuItem mnuSave = new JMenuItem("Save");
        mnuSave.addActionListener(e ->
        {
            if (StudentsList.students.size() == 0)
            {
                JOptionPane.showMessageDialog(
                    frame,
                    "There are not students to save!",
                    "Can't save students...",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isFileSaved)
            {
                openFileDialog();
            }
            StudentsList.writeStudentsFile(storageFileName);
        });
        mnuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        mnFile.add(mnuSave);

        JMenuItem mnuSaveAs = new JMenuItem("Save As...");
        mnuSaveAs.addActionListener(arg0 ->
        {
            if (StudentsList.students.size() == 0)
            {
                JOptionPane.showMessageDialog(
                    frame,
                    "There are no students to save!",
                    "Can't save students...",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (openFileDialog())
            {
                StudentsList.writeStudentsFile(storageFileName);
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

    private void trySolveProblem()
    {
        boolean isSolved = currentStudent.trySolveProblem(cmbProblems.getSelectedIndex(), Integer.parseInt(txtSolution.getText()));
        if (isSolved)
        {
            // If they got it right, let's let them know that they did.
            String message = "You got it right!";
            JOptionPane.showMessageDialog(frame, message, "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            // on a correct answer, a refresh of the arraylist is triggered. Let's react accordingly.
            cmb.setSelectedIndex(cmb.getSelectedIndex());
        }
    }

    private void updateFields(int selectedIndex)
    {
        // We don't want to do anything if we don't have a valid index.
        if (selectedIndex < 0)
        {
            return;
        }
        txtName.setText(currentStudent.getName());

        // clear out our list.
        cmbProblems.removeAllItems();

        if (currentStudent.hasProblems())
        {
            for (Problem p : currentStudent.getProblems())
            {
                // This is where we would have liked to be able to insert items to the cmb at a specific index.
                // Unfortunately, the JComboBox component doesn't support adding items at a specific index so
                // we have to rebuild our list every time we update it if we want our item at the top (which we do).
                String toAdd = String.format("%-15s\t%-8s", p.toString(), p.isSolved() ? "Solved" : "Unsolved");
                cmbProblems.addItem(toAdd);
            }

            cmbProblems.setSelectedIndex(currentStudent.getMostRecentProblemIndex());
        }
    }

    private void updateProblem()
    {
        if (currentProblem == null)
        {
            return;
        }
        String[] words = currentProblem.toString().split(" ");
        if (currentProblem.isSolved())
        {
            txtSolution.setText(currentProblem.solution + "");
            lblProblem.setText(currentProblem.toString().replace(words[words.length-1], ""));
        }
        else
        {
            lblProblem.setText(currentProblem.toString().replace(words[words.length-1], ""));
        }
    }

    private void updateStatus(statuses status)
    {
        switch (status)
        {
            case ADD_NEW:
                renderAddNewLayout();
                break;
            case ADD_PROBLEM:
                renderAddNewProblemLayout();
                break;
            case EDIT:
                renderEditLayout();
                break;
            case SHOW_PROBLEMS:
                renderProblemLayout();
                break;
            case CHANGING_STUDENT:
                // don't do anything, just update the status.
                break;
            case DEFAULT:
            default:
                renderDefaultLayout();
                break;
        }
        this.mostRecentStatus = currentStatus;
        this.currentStatus = status;
    }
}