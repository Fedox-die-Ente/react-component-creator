package net.fedustria.reactcomponentcreator.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import net.fedustria.reactcomponentcreator.ui.test.TestDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * ───────────────────────────────────────────────
 * © 2025 Florian O. & Fabian W.
 * Created: 1/28/2025 8:02 PM
 * All rights reserved.
 * ───────────────────────────────────────────────
 */

public class CreateReactComponentAction extends AnAction {

    private final boolean isTypeScript = true;

    public CreateReactComponentAction() {
        super();
    }

    @SuppressWarnings("ActionPresentationInstantiatedInCtor")
    public CreateReactComponentAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        PsiDirectory directory = (PsiDirectory) e.getData(CommonDataKeys.PSI_ELEMENT);

        if (project == null || directory == null) {
            return;
        }

        String componentName = Messages.showInputDialog(project, "Enter component name", "Create React Component", Messages.getQuestionIcon());
        if (componentName == null || componentName.trim().isEmpty()) {
            return;
        }

        WriteCommandAction.runWriteCommandAction(project, () -> {
            try {
                PsiDirectory componentDir = directory.createSubdirectory(componentName);

                String componentType = isTypeScript ? "tsx" : "jsx";
                String indexContent = isTypeScript ? "index.ts" : "index.js";

                createFile(project, componentDir, componentName + "." + componentType, generateTsxContent(componentName));
                createFile(project, componentDir, componentName + ".module.css", generateCssContent());
                createFile(project, componentDir, indexContent, generateIndexContent(componentName));
            } catch (Exception ex) {
                Messages.showErrorDialog(project, "Error creating component: " + ex.getMessage(), "Error");
            }
        });
    }

    private void createFile(Project project, PsiDirectory directory, String fileName, String content) {
        PsiFileFactory fileFactory = PsiFileFactory.getInstance(project);

        FileTypeManager fileTypeManager = FileTypeManager.getInstance();
        FileType fileType = fileTypeManager.getFileTypeByExtension("tsx");

        PsiFile file = fileFactory.createFileFromText(fileName, fileType, content);

        directory.add(file);
    }

    private String generateTsxContent(String componentName) {
        return "import React from 'react';\n" +
                "import styles from './" + componentName + ".module.css';\n\n" +
                "export const " + componentName + " = () => {\n" +
                "  return (\n" +
                "    <div className={styles.container}>\n" +
                "      <p>" + componentName + "</p>\n" +
                "    </div>\n" +
                "  );\n" +
                "};\n";
    }

    private String generateCssContent() {
        return """
                .container {
                  /* Add your styles here */
                }
                """;
    }

    private String generateIndexContent(String componentName) {
        return "export { " + componentName + " } from './" + componentName + "';\n";
    }

    @Override
    public void update(AnActionEvent e) {
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

}
