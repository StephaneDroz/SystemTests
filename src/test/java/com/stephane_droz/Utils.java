package com.stephane_droz;

import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String prettyPrint(JsonObject json) {
        StringWriter sw = new StringWriter();

        try {
            Map<String, Object> properties = new HashMap<>(1);
            properties.put(JsonGenerator.PRETTY_PRINTING, true);


            JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
            JsonWriter jsonWriter = writerFactory.createWriter(sw);

            jsonWriter.writeObject(json);
            jsonWriter.close();

        } catch (Exception ignored) {
        }

        String prettyPrinted = sw.toString();

        return prettyPrinted;
    }

    public static JsonObject toJson(String jsonString){
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        return jsonObject;
    }
}
