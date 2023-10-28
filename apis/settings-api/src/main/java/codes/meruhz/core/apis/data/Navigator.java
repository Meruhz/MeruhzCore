package codes.meruhz.core.apis.data;

import codes.meruhz.core.apis.AbstractConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Navigator {

    @NotNull AbstractConfiguration<?> getConfiguration();

    @Nullable Object get(@NotNull String location);
}
