package com.crankysupertoon.moddeobf.gui;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.crankysupertoon.moddeobf.ModDeobfGui;
import com.crankysupertoon.moddeobf.data.IErrorHandler;

public class GUIErrorHandler implements IErrorHandler {
    private Component parent;

    public GUIErrorHandler(Component parent) {
        this.parent = parent;
    }

    @Override
    public boolean handleError(String message, boolean warning) {
        JOptionPane.showMessageDialog(parent, message, ModDeobfGui.ERROR_DIALOG_TITLE, warning ? JOptionPane.WARNING_MESSAGE : JOptionPane.ERROR_MESSAGE);
        return true;
    }
}
