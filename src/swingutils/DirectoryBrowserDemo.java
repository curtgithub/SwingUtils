/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingutils;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author owner
 */
public class DirectoryBrowserDemo extends JPanel
        implements ActionListener {

   static private final String NEWLINE = "\n";

   JButton fdButtton;
   JTextArea log;
   DirectoryBrowser db;

   public DirectoryBrowserDemo() {
      super(new BorderLayout());

      //Create the log first, because the action listeners
      //need to refer to it.
      log = new JTextArea(5, 20);
      log.setMargin(new Insets(5, 5, 5, 5));
      log.setEditable(false);
      JScrollPane logScrollPane = new JScrollPane(log);

      //Create a file chooser
      db = new DirectoryBrowser("I:\\media\\pics\\D90");

      //Uncomment one of the following lines to try a different
      //file selection mode.  The first allows just directories
      //to be selected (and, at least in the Java look and feel,
      //shown).  The second allows both files and directories
      //to be selected.  If you leave these lines commented out,
      //then the default mode (FILES_ONLY) will be used.
      //
      //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      //Create the open button.  We use the image from the JLF
      //Graphics Repository (but we extracted it from the jar).
      fdButtton = new JButton("From Direcoty");
      fdButtton.addActionListener(this);

      
      //For layout purposes, put the buttons in a separate panel
      JPanel buttonPanel = new JPanel(); //use FlowLayout
      buttonPanel.add(fdButtton);
   
      //Add the buttons and the log to this panel.
      add(buttonPanel, BorderLayout.PAGE_START);
      add(logScrollPane, BorderLayout.CENTER);
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      //Handle open button action.
      if (e.getSource() == fdButtton) {
         int returnVal = this.db.showOpenDialog(DirectoryBrowserDemo.this);

         if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = this.db.getSelectedFile();
            //This is where a real application would open the file.
            log.append("Opening: " + file.getName() + "." + NEWLINE);
         } else {
            log.append("Open command cancelled by user." + NEWLINE);
         }
         log.setCaretPosition(log.getDocument().getLength());

         //Handle save button action.
      } 
   }
   
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("DirectoryBrowserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new DirectoryBrowserDemo());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
   public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}