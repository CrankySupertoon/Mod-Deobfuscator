package com.crankysupertoon.moddeobf.cli;

import com.crankysupertoon.moddeobf.data.IErrorHandler;

public class CLIErrorHandler implements IErrorHandler {

    @Override
    public boolean handleError(String message, boolean warning) {
        System.err.println(message);
        return true;
    }
}
