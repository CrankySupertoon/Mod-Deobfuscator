package com.crankysupertoon.moddeobf.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.crankysupertoon.moddeobf.ModDeobfGui;
import com.crankysupertoon.moddeobf.ModDeobfImpl;
import com.crankysupertoon.moddeobf.util.MCPVersions;
import com.crankysupertoon.moddeobf.util.MCPVersions.MCPVersion;
import com.crankysupertoon.moddeobf.util.MappingVersions.MappingVersion;

import net.minecraftforge.srgutils.MinecraftVersion;

public class StartListener extends MouseAdapter {
    private ModDeobfGui parent;
    private Thread run = null;
    private JTextField input;
    private JTextField output;
    private JComboBox<MinecraftVersion> mcVer;
    private JComboBox<MappingVersion> mappingVer;
    private JLabel progressLabel;
    private JProgressBar progressBar;

    public StartListener(ModDeobfGui parent, JTextField input, JTextField output, JComboBox<MinecraftVersion> mcVer, JComboBox<MappingVersion> mappingVer, JLabel progressLabel, JProgressBar progressBar) {
        this.parent = parent;
        this.input = input;
        this.output = output;
        this.mcVer = mcVer;
        this.mappingVer = mappingVer;
        this.progressLabel = progressLabel;
        this.progressBar = progressBar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!input.getText().endsWith(".jar") && !input.getText().equals("") || !output.getText().endsWith(".jar") && !output.getText().equals("")) {
            JOptionPane.showMessageDialog(parent, "Mod needs to be a .JAR file.", ModDeobfGui.ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
        }
        if(input.getText().equals("")) {
            JOptionPane.showMessageDialog(parent, "No Obfuscated Mod Defined.", ModDeobfGui.ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
        }
        if(output.getText().equals("")) {
            JOptionPane.showMessageDialog(parent, "No Output Location Defined.", ModDeobfGui.ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
        }
        if(run != null && run.isAlive()) {
            return;
        }
        run = new Thread("ModDeobf Remapping Thread") {
            @Override
            public void run() {
                try {
                    MCPVersion mcp = MCPVersions.get((MinecraftVersion)mcVer.getSelectedItem());
                    MappingVersion map = (MappingVersion)mappingVer.getSelectedItem();

                    ModDeobfImpl.remap(new File(input.getText()), new File(output.getText()), mcp, map, new GUIErrorHandler(parent), new GUIProgressListener(progressLabel, progressBar));
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(parent, "There was an error.\n" + ex.toString() + "\n" + getFormattedStackTrace(ex.getStackTrace()), ModDeobfGui.ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        run.start();
    }

    private String getFormattedStackTrace(StackTraceElement[] stacktrace) {
        StringBuilder sb = new StringBuilder();
        for(StackTraceElement element : stacktrace) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
