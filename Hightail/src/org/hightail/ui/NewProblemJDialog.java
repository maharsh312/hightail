package org.hightail.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.hightail.Problem;
import org.hightail.parsers.task.TaskParser;
import org.hightail.parsers.task.TaskParserGetter;
import org.htmlparser.util.ParserException;

public class NewProblemJDialog extends javax.swing.JDialog {
    
    protected boolean returnValue = false;
    protected Problem problem;
    
    /**
     * Creates new form NewProblemJDialog
     */
    public NewProblemJDialog(java.awt.Frame parent) {
        super(parent, true); // makes it modal
        initComponents();
        
        setTitle("New problem");
        
        makeShortcuts();
        
        // sets cursor in problem name field
        nameField.requestFocus();
        
        setLocationRelativeTo(parent);
    }
    
    private void makeShortcuts() {
        // escape key will close the dialog
        getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "close");
        getRootPane().getActionMap().put("close", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        // hitting enter will perform the same action as clicking create button
        nameField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        nameField.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
            }
        });
        // hitting enter will perform the same action as clicking parse button
        urlField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        urlField.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parse();
            }
        });
    }
    
    public Problem getProblem() {
        if (returnValue == false) {
            return null;
        }
        
        if (problem == null) {
            problem = new Problem(nameField.getText());
        }
        
        return problem;
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        problemNameLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        parsingPanel = new javax.swing.JPanel();
        problemURLLabel = new javax.swing.JLabel();
        urlField = new javax.swing.JTextField();
        parseButton = new javax.swing.JButton();
        abortParsingButton = new javax.swing.JButton();
        parsingStatusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        problemNameLabel.setText("Problem name:");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        parsingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Problem statement parsing (optional)"));

        problemURLLabel.setText("URL of problem statement:");

        parseButton.setText("Parse");
        parseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parseButtonActionPerformed(evt);
            }
        });

        abortParsingButton.setText("Abort");
        abortParsingButton.setEnabled(false);
        abortParsingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abortParsingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout parsingPanelLayout = new javax.swing.GroupLayout(parsingPanel);
        parsingPanel.setLayout(parsingPanelLayout);
        parsingPanelLayout.setHorizontalGroup(
            parsingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parsingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parsingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(parsingPanelLayout.createSequentialGroup()
                        .addComponent(problemURLLabel)
                        .addGap(45, 45, 45)
                        .addComponent(urlField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parsingPanelLayout.createSequentialGroup()
                        .addComponent(parseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(parsingStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(abortParsingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(0, 0, 0))
        );
        parsingPanelLayout.setVerticalGroup(
            parsingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parsingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parsingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(problemURLLabel)
                    .addComponent(urlField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parsingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parseButton)
                    .addComponent(abortParsingButton)
                    .addComponent(parsingStatusLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(parsingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(problemNameLabel)
                                .addGap(107, 107, 107)
                                .addComponent(nameField))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(problemNameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(parsingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void cancel() {
        returnValue = false;
        this.dispose();
    }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancel();
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    private void create() {
        String name = nameField.getText();
        if (name.isEmpty() || name.length() > Problem.PROBLEM_NAME_MAX_LENGTH) {
            JOptionPane.showMessageDialog(this, "Problem name empty or too long.", "Wrong name", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!urlField.getText().isEmpty() && problem == null) {
            // it seems that the user didn't press Parse
            // TODO: do something here
        }
        
        returnValue = true;
        this.dispose(); // TODO: is this okay?
    }
    
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        create();
    }//GEN-LAST:event_createButtonActionPerformed
    
    private void parse() {
        String URL = urlField.getText();
        if (URL.equals("")) {
            JOptionPane.showMessageDialog(this, "Enter the URL.", "No URL", JOptionPane.ERROR_MESSAGE);
            return;
        }
        parseButton.setEnabled(false);
        createButton.setEnabled(false);
        abortParsingButton.setEnabled(true);
        // TODO: what about the cancel button?
        startParsing(URL);
    }
    
    private void parseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parseButtonActionPerformed
        parse();
    }//GEN-LAST:event_parseButtonActionPerformed
    
    private void abortParsingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abortParsingButtonActionPerformed
        abortParsing();
    }//GEN-LAST:event_abortParsingButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abortParsingButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton createButton;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton parseButton;
    private javax.swing.JPanel parsingPanel;
    private javax.swing.JLabel parsingStatusLabel;
    private javax.swing.JLabel problemNameLabel;
    private javax.swing.JLabel problemURLLabel;
    private javax.swing.JTextField urlField;
    // End of variables declaration//GEN-END:variables
    
    protected Thread thread;
    
    private void startParsing(final String URL) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    parsingStatusLabel.setText("Parsing...");
                    parsingStatusLabel.setToolTipText("");
                    TaskParser parser = TaskParserGetter.getTaskParser(URL);
                    problem = parser.parse(URL);
                    // TODO: check if problem is correct (non empty testcaseSet etc)
                    parsingStatusLabel.setText("Parsing ok");
                    if(nameField.getText().isEmpty()) {
                        nameField.setText(problem.getName());
                    }
                } catch (ParserException ex) {
                    parsingStatusLabel.setText("Parsing failed");
                    parsingStatusLabel.setToolTipText(ex.getMessage());
                    problem = null;
                }
                setButtonStateForAfterParsing();
            }
        });
        thread.start();
    }
    
    private void abortParsing() {
        thread.interrupt();
        problem = null;
        parsingStatusLabel.setText("Parsing aborted");
        setButtonStateForAfterParsing();
    }
    
    private void setButtonStateForAfterParsing() {
        abortParsingButton.setEnabled(false);
        parseButton.setEnabled(true);
        createButton.setEnabled(true);
    }
}
