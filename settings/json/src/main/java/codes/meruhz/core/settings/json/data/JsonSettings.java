package codes.meruhz.core.settings.json.data;

import codes.meruhz.core.apis.data.Settings;
import codes.meruhz.core.settings.json.JsonConfiguration;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public class JsonSettings extends Settings {

    public JsonSettings(@NotNull JsonConfiguration configuration) {
        super(configuration);
    }

    @Override
    public @NotNull JsonElement get(@NotNull String location) {
        @NotNull JsonElement result = (JsonElement) this.getConfiguration().getContent();
        @NotNull String[] directories = location.split("/");

        for(String directory : directories) {

            if(result == null) {
                throw new NullPointerException("Could not find a property named '" + directory + "' at plugin settings");

            } else if(result.isJsonObject()) {
                @NotNull JsonObject obj = result.getAsJsonObject();

                if(obj.has(directory)) {
                    result = obj.get(directory);

                } else {
                    throw new NullPointerException("Could not find a property named '" + directory + "' at plugin settings");
                }

            } else if(result.isJsonArray()) {
                return result;

            } else {
                throw new IllegalArgumentException("Unsupported Json type at '" + directory + "'");
            }
        }

        return result;
    }
}