package command;

import com.google.gson.JsonElement;

public class SetCommand implements Command {
    private final Action action ;
    private final JsonElement idx;
    private final JsonElement msg;
    public SetCommand(Action action, JsonElement idx, JsonElement msg) {
        this.action = action;
        this.idx = idx;
        this.msg = msg;
    }

    @Override
    public void execute() {
        action.set(idx,msg);
    }
}
