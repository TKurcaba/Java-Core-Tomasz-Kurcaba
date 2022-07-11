package server.cla;

import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandLineArgument {
    @Expose
    @Parameter(names = {"-t", "--type"}, description = "Type of the request",  order = 0)
    private String type;

    @Expose
    @Parameter( names = {"-k", "--key"},description = "Key of record",order = 1)
    private String key;

    @Expose
    @Parameter(names = {"-v", "--value"},description = "Value to add database",order = 2)
    private String value;

    @Parameter( names = {"-in", "--input-file"}, description = "File containing the request as json record",order = 3)
    private String filename;


    private String readFromFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public String toJson() {
        if (filename != null) {
            try {
                return readFromFile("/Users/tkurczaba/Documents/JSON Database/JSON Database/task/src/client/data/" + this.filename);
            } catch (IOException e) {
                System.out.println("Cannot read file: " + e.getMessage());
                System.exit(1);
            }
        }
        Map<String, String> map = new LinkedHashMap<>();
        map.put("type", type);
        map.put("key", key);
        map.put("value", value);
        return new Gson().toJson(map);
    }
}
