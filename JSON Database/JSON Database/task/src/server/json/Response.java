package server.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.LinkedHashMap;
import java.util.Map;

public class Response {
    public static final String STATUS_OK = "OK";
    public static final String STATUS_ERROR = "ERROR";

    private String msg;
    private String reason;
    private String value;



    public void setMsg(String response) {
        this.msg = response;
    }



    public void setReason(String reason) {
        this.reason = reason;
    }



    public void setValue(String value) {
        this.value = value;
    }

    public String toJSON() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("\"response\"", msg);
        if (value != null) {

            map.put("\"value\"", value);
        }
        if (reason != null) {
            map.put("reason", reason);
        }
        return map.toString().replaceAll("=",":");

    }
}
