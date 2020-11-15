package com.crankysupertoon.moddeobf.cli;

import com.crankysupertoon.moddeobf.data.IProgressListener;

public class CLIProgressListener implements IProgressListener {

    @Override
    public void start(int max, String label) {
        System.out.println(label);
    }

    @Override
    public void startWithoutProgress(String label) {
        System.out.println(label);
    }

    @Override
    public void setProgress(int value) {
        //NO-OP
    }

    @Override
    public void setMax(int max) {
        //NO-OP
    }
    
    @Override
    public void setLabel(String label) {
        System.out.println(label);
    }
}
