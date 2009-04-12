/*
 * LogFrame.java
 *
 * Created on 25. leden 2008, 21:43
 */

package esmska.gui;

import esmska.data.Icons;
import esmska.data.Log;
import esmska.utils.L10N;
import esmska.utils.MiscUtils;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import org.openide.awt.Mnemonics;

/** Display log records
 *
 * @author  ripper
 */
public class LogFrame extends javax.swing.JFrame {
    private static final String RES = "/esmska/resources/";
    private static final Logger logger = Logger.getLogger(LogFrame.class.getName());
    private static final ResourceBundle l10n = L10N.l10nBundle;
    private static final DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM);
    private Log log = Log.getInstance();
    private LogListModel logModel = new LogListModel();
    

    /** Creates new form LogFrame */
    public LogFrame() {
        initComponents();
        closeButton.requestFocusInWindow();
        this.getRootPane().setDefaultButton(closeButton);

        //set window images
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new ImageIcon(getClass().getResource(RES + "log-16.png")).getImage());
        images.add(new ImageIcon(getClass().getResource(RES + "log-32.png")).getImage());
        images.add(new ImageIcon(getClass().getResource(RES + "log-48.png")).getImage());
        setIconImages(images);

        //close on Ctrl+W
        String command = "close";
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(
                KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), command);
        getRootPane().getActionMap().put(command, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeButtonActionPerformed(e);
            }
        });

        selectLastRecord();
    }
    
    /** select last log record in GUI */
    private void selectLastRecord() {
        logList.clearSelection();
        int index = logModel.getSize() - 1;
        logList.setSelectedIndex(index);
        logList.ensureIndexIsVisible(index);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        logList = new JList();
        closeButton = new JButton();
        clearButton = new JButton();
        copyButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(l10n.getString("LogFrame.title")); // NOI18N

        logList.setModel(logModel);
        logList.setCellRenderer(new LogRenderer());
        jScrollPane1.setViewportView(logList);

        closeButton.setIcon(new ImageIcon(getClass().getResource("/esmska/resources/close-22.png"))); // NOI18N
        Mnemonics.setLocalizedText(closeButton, l10n.getString("Close_")); // NOI18N
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        clearButton.setIcon(new ImageIcon(getClass().getResource("/esmska/resources/clear-22.png"))); // NOI18N
        Mnemonics.setLocalizedText(clearButton, l10n.getString("LogFrame.clearButton.text"));
        clearButton.setToolTipText(l10n.getString("LogFrame.clearButton.toolTipText")); // NOI18N
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        copyButton.setIcon(new ImageIcon(getClass().getResource("/esmska/resources/copy-22.png"))); // NOI18N
        Mnemonics.setLocalizedText(copyButton, l10n.getString("LogFrame.copyButton.text")); // NOI18N
        copyButton.setToolTipText(l10n.getString("LogFrame.copyButton.toolTipText")); // NOI18N
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                copyButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jScrollPane1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clearButton)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(copyButton)
                        .addPreferredGap(ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(closeButton)
                        .addComponent(clearButton))
                    .addComponent(copyButton))
                .addContainerGap())
        );

        layout.linkSize(SwingConstants.VERTICAL, new Component[] {clearButton, closeButton, copyButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void clearButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        log.clearRecords();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void copyButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_copyButtonActionPerformed
        try {
            logger.fine("Copying logs to clipboard");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringBuilder builder = new StringBuilder();
            for (Log.Record record : log.getRecords()) {
                builder.append("[");
                builder.append(timeFormat.format(record.getTime()));
                builder.append("] ");
                builder.append(record.getMessage().replaceAll("<.*?>",""));
                builder.append("\n");
            }
            Transferable text = new StringSelection(builder.toString());
            clipboard.setContents(text, null);
        } catch (IllegalStateException ex) {
            logger.log(Level.WARNING, "System clipboard not available", ex);
        }
    }//GEN-LAST:event_copyButtonActionPerformed

    /** Model for contact list */
    private class LogListModel extends AbstractListModel {
        private int oldSize = getSize();
        public LogListModel() {
            //listen for changes in log and fire events accordingly
            log.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (e.getID()) {
                        case Log.ACTION_ADD_RECORD:
                            fireContentsChanged(LogListModel.this, 0, getSize());
                            break;
                        case Log.ACTION_REMOVE_RECORD:
                        case Log.ACTION_CLEAR_RECORDS:
                            fireIntervalRemoved(LogListModel.this, 0, oldSize);
                            break;
                        default:
                            logger.warning("Unknown action event type");
                            assert false : "Unknown action event type";
                    }
                    oldSize = getSize();
                    selectLastRecord();
                }
            });
        }
        @Override
        public int getSize() {
            return log.size();
        }
        @Override
        public Log.Record getElementAt(int index) {
            return log.getRecords().get(index);
        }
    }

    /** Renderer for records in log list */
    private class LogRenderer implements ListCellRenderer {
        private final ListCellRenderer lafRenderer = new JList().getCellRenderer();
        
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = lafRenderer.getListCellRendererComponent(list, value, 
                    index, isSelected, cellHasFocus);
            Log.Record record = (Log.Record)value;
            //display message and time
            String text = "[" + timeFormat.format(record.getTime()) + "] " +
                    record.getMessage();
            ((JLabel)c).setText("<html>" + MiscUtils.escapeHtml(text) + "</html>");
            //add record icon
            ((JLabel)c).setIcon(record.getIcon() != null ? record.getIcon() :
                Icons.STATUS_BLANK);
            return c;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton clearButton;
    private JButton closeButton;
    private JButton copyButton;
    private JScrollPane jScrollPane1;
    private JList logList;
    // End of variables declaration//GEN-END:variables
    
}
