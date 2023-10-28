package codes.meruhz.core.settings.yaml.data;

import codes.meruhz.core.apis.data.Settings;
import codes.meruhz.core.settings.yaml.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class YamlSettings extends Settings {

    public YamlSettings(@NotNull YamlConfiguration configuration) {
        super(configuration);
    }

    @Override
    public @Nullable Object get(@NotNull String location) {
        @NotNull Map<String, Object> current = (Map<String, Object>) this.getConfiguration().getContent();
        @NotNull String[] keys = location.split("/");

        for(String key : keys) {

            if(!current.containsKey(key)) {
                throw new NullPointerException("Could not find an property named '" + key + "'");
            }

            @NotNull Object value = current.get(key);

            if(value instanceof Map) {
                current = (Map<String, Object>) value;

            } else {
                return value;
            }
        }

        throw new NullPointerException("Could not find an object at location '" + location + "'");
    }
}