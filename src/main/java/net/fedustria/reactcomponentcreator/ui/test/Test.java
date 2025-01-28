package net.fedustria.reactcomponentcreator.ui.test;

import javax.swing.*;

/**
 * ───────────────────────────────────────────────
 *        © 2025 Florian O. & Fabian W.        
 *          Created: 1/28/2025 11:05 PM              
 *            All rights reserved.                
 * ───────────────────────────────────────────────
 */

public class Test {
    private JPanel contentPane;
    private JPanel contentPanel;
    private JPanel componentName;
    private JPanel useTypescript;
    private JLabel labelComponentName;
    private JTextField textFieldComponentName;
    private JLabel labelUseTypescript;
    private JCheckBox checkboxUseTypescript;
    private JPanel interfaceTablePanel;
    private JTable tableInterface;

    public JPanel getMainPanel() {
        return contentPane;
    }

    public String getComponentName() {
        return textFieldComponentName.getText();
    }

    public boolean isTypeScript() {
        return checkboxUseTypescript.isSelected();
    }
}
