//MrPyro 2019
package com.crankysupertoon.moddeobf.gui;

import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JTextField;

import com.crankysupertoon.moddeobf.ModDeobfGui;

public class LinuxBrowseListener extends MouseAdapter{
    private ModDeobfGui parent;
    private boolean isOpen;
    private JTextField field;
    private FileDialog fd;
    
    public LinuxBrowseListener(ModDeobfGui parent, boolean isOpen, JTextField field) {
      this.parent = parent;
      this.isOpen = isOpen;
      this.field = field;
      
      if (isOpen) {
          this.fd = new FileDialog(this.parent, "Please Select an Input File", FileDialog.LOAD);
      } else {
          this.fd = new FileDialog(this.parent, "Please Select an Output File", FileDialog.SAVE); 
      }
      
      fd.setFilenameFilter( (dir, name) -> name.toLowerCase().endsWith(".jar"));
      
      String key = isOpen ? ModDeobfGui.PREFS_KEY_OPEN_LOC : ModDeobfGui.PREFS_KEY_SAVE_LOC;
      String savedDir = parent.prefs.get(key, Paths.get("").toAbsolutePath().toString());
      File currentDir = new File(savedDir);
      
      if (!Paths.get(savedDir).getRoot().toFile().exists()) {
          currentDir = Paths.get("").toAbsolutePath().toFile();
      }
      
      while (!currentDir.isDirectory()) {
          currentDir = currentDir.getParentFile();
      }
      
      fd.setDirectory(currentDir.getPath());
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int returnState;
        
        fd.setVisible(true);
        
        returnState = fd.getFile() == null ? 0 : 1;
        
        if (returnState == 1) {
            File file = new File(fd.getDirectory() + fd.getFile());
            String path = fd.getDirectory() + fd.getFile();
            
            field.setText(path);
            
            String parentFolder = file.getParentFile().getAbsolutePath();
            
            if (isOpen) {
                parent.getOutputField().setText(path.replace(".jar", "-deobf.jar"));
                parent.prefs.put(ModDeobfGui.PREFS_KEY_OPEN_LOC, parentFolder);
            }
            
            parent.prefs.put(ModDeobfGui.PREFS_KEY_SAVE_LOC, parentFolder);
        }
    }
}
