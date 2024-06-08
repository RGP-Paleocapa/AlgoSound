package it.example.util.alert;

import javafx.concurrent.Task;

public abstract class AlertingTask<T> extends Task<T> {

    @Override
    protected void failed() {
        super.failed();
        Throwable exception = getException();
        AlertUtil.showErrorAlert("Error", exception.getMessage());
    }
}
