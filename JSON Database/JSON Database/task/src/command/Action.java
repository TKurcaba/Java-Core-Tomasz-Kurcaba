package command;

import com.google.gson.JsonElement;
import server.Database;

public class Action {
    private Database db;

    public Action(Database db){
        this.db = db;
    }

    public JsonElement get(JsonElement idx) throws Exception {

        return db.getValue(idx);
    }
    public void set(JsonElement idx, JsonElement msg){
        db.setValue(idx, msg);
    }
    public void delete(JsonElement idx) throws Exception {
        db.deleteValue(idx);
    }
}
