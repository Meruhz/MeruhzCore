package codes.meruhz.core.apis.settings.data;

import codes.meruhz.core.apis.settings.AbstractConfiguration;
import org.jetbrains.annotations.NotNull;

public abstract class Settings implements Navigator {

    private final @NotNull AbstractConfiguration<?> configuration;

    public Settings(@NotNull AbstractConfiguration<?> configuration) {
        this.configuration = configuration;
    }

    @Override
    public @NotNull AbstractConfiguration<?> getConfiguration() {
        return this.configuration;
    }
}
