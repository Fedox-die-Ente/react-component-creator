package net.fedustria.reactcomponentcreator.ui.test;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * ───────────────────────────────────────────────
 *        © 2025 Florian O. & Fabian W.        
 *          Created: 1/28/2025 10:39 PM              
 *            All rights reserved.                
 * ───────────────────────────────────────────────
 */

public class TestDialog extends DialogWrapper {

    private final Test panel;

    public TestDialog(@Nullable Project project) {
        super(project);
        panel = new Test();
        init();
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel.getMainPanel();
    }

    public String getComponentName() {
        return panel.getComponentName();
    }

    public boolean isTypeScript() {
        return panel.isTypeScript();
    }


}
