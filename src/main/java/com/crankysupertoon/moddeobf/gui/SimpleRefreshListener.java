package com.crankysupertoon.moddeobf.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleRefreshListener extends MouseAdapter {
    private final Runnable callback;
    public SimpleRefreshListener(Runnable callback) {
        this.callback = callback;
    }

    public void mouseClicked(MouseEvent e) {
        this.callback.run();
    }
}
