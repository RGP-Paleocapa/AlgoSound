package it.example.util.alert;

import javafx.concurrent.Task;

/**
 * Abstract task that shows an error alert if the task fails.
 *
 * @param <T> the result type of the task
 */
public abstract class AlertingTask<T> extends Task<T> {

    /**
     * Called when the task fails. Shows an error alert with the exception message.
     */
    @Override
    protected void failed() {
        super.failed();
        Throwable exception = getException();
        AlertUtil.showErrorAlert("Error", exception.getMessage());
    }
}
