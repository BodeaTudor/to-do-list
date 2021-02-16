package org.example.transfer;

public class UpdateItemRequest {

    private boolean done;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "UpdateToDoItemRequest{" +
                "done=" + done +
                '}';
    }
}
