package command;

import com.google.gson.JsonElement;

public class GetCommand implements Command {
    private final Action action;
    private final JsonElement idx;
    private JsonElement res;
    public GetCommand(Action action, JsonElement idx){
        this.idx = idx;
        this.action =action;
    }
    public JsonElement GetResult(){
        return this.res;
    }
    @Override
    public void execute() throws Exception {
        this.res = action.get(idx);
    }
}
