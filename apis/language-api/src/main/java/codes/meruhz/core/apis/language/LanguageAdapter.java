package codes.meruhz.core.apis.language;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public abstract class LanguageAdapter<S> {

    private final @NotNull Set<@NotNull S> storages;

    public LanguageAdapter() {
        this(new HashSet<>());
    }

    public LanguageAdapter(@NotNull Set<@NotNull S> storages) {
        this.storages = storages;
    }

    public @NotNull Set<@NotNull S> getStorages() {
        return this.storages;
    }

    public abstract @NotNull S getStorage(@NotNull String name);
}