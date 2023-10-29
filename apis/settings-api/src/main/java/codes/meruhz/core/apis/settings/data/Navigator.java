package codes.meruhz.core.apis.settings.data;

import codes.meruhz.core.apis.settings.AbstractConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Navigator {

    @NotNull AbstractConfiguration<?> getConfiguration();

    @Nullable Object get(@NotNull String location);
}
