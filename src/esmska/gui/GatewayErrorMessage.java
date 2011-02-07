
/*
 * GatewayErrorMessage.java
 *
 * Created on 8.1.2011, 16:01:32
 */

package esmska.gui;

import esmska.data.Config;
import esmska.data.Icons;
import esmska.data.SMS;
import esmska.gui.GatewayMessageDialog.TaskPane;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import org.openide.awt.Mnemonics;

/** Error message from gateway displayed to a user
 *
 * @author ripper
 */
public class GatewayErrorMessage extends GatewayMessage {
    private static final Config config = Config.getInstance();

    /** Creates new form GatewayErrorMessage */
    public GatewayErrorMessage() {
        initComponents();

        //if not Substance LaF, add clipboard popup menu to text components
        if (!config.getLookAndFeel().equals(ThemeManager.LAF.SUBSTANCE)) {
            ClipboardPopupMenu.register(smsTextArea);
        }
    }

    /** Initialize this message to show an SMS error
     * @param sms sms that failed
     */
    public TaskPane showErrorMsg(SMS sms) {
        String cause = (sms.getErrMsg() != null ? sms.getErrMsg().trim() : "");
        String recipient = extractRecipient(sms);
        String title = MessageFormat.format(l10n.getString("GatewayErrorMessage.smsFailed"), recipient);
        Icon icon = Icons.STATUS_WARNING;

        msgLabel.setText("<html>" + cause + "</html>");
        smsTextArea.setText(sms.getText());

        return wrapAsTaskPane(this, title, icon);
    }

    /** Focus the best component in this panel */
    @Override
    public void setBestFocus() {
        okButton.requestFocusInWindow();
    }

    /** Cancel this message, do what's most appropriate */
    @Override
    public void cancel() {
        okButton.doClick(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new JButton();
        smsTextScrollPane = new JScrollPane();
        smsTextArea = new JTextArea();
        smsTextLabel = new JLabel();
        msgLabel = new JHtmlLabel();
        Mnemonics.setLocalizedText(okButton, l10n.getString("OK_"));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        smsTextScrollPane.setVisible(false);

        smsTextArea.setColumns(20);
        smsTextArea.setLineWrap(true);
        smsTextArea.setRows(5);
        smsTextArea.setWrapStyleWord(true);
        smsTextScrollPane.setViewportView(smsTextArea);

        smsTextLabel.setIcon(new ImageIcon(getClass().getResource("/esmska/resources/expand-off-12.png"))); // NOI18N
        Mnemonics.setLocalizedText(smsTextLabel, l10n.getString("GatewayErrorMessage.smsTextLabel.text"));
        smsTextLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        smsTextLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                smsTextLabelMouseClicked(evt);
            }
        });
        Mnemonics.setLocalizedText(msgLabel, "<<Some text>>\t");
        msgLabel.setVerticalAlignment(SwingConstants.TOP);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(msgLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(smsTextScrollPane)
                    .addComponent(okButton)
                    .addComponent(smsTextLabel, Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(msgLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(smsTextLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(smsTextScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        actionSupport.fireActionPerformed(CLOSE_ME, null);
    }//GEN-LAST:event_okButtonActionPerformed

    private void smsTextLabelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_smsTextLabelMouseClicked
        smsTextScrollPane.setVisible(!smsTextScrollPane.isVisible());
        if (smsTextScrollPane.isVisible()) {
            smsTextLabel.setIcon(Icons.get("expand-on-12.png"));
        } else {
            smsTextLabel.setIcon(Icons.get("expand-off-12.png"));
        }
        this.revalidate();
    }//GEN-LAST:event_smsTextLabelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JHtmlLabel msgLabel;
    private JButton okButton;
    private JTextArea smsTextArea;
    private JLabel smsTextLabel;
    private JScrollPane smsTextScrollPane;
    // End of variables declaration//GEN-END:variables
    
}
