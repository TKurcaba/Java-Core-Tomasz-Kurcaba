package command;

import com.google.gson.JsonElement;

public class DeleteCommand implements Command{
    Action action;
    JsonElement idx;
    public DeleteCommand(Action action,JsonElement idx) {
        this.action = action;
        this.idx = idx;
    }

    @Override
    public void execute() throws Exception {
        action.delete(idx);
    }
}
