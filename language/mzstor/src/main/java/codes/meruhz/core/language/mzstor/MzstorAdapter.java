package codes.meruhz.core.language.mzstor;

import codes.meruhz.core.apis.language.LanguageAdapter;
import codes.meruhz.storages.core.data.Storage;
import org.jetbrains.annotations.NotNull;

public class MzstorAdapter extends LanguageAdapter<Storage<?, ?>> {

    @Override
    public @NotNull Storage<?, ?> getStorage(@NotNull String name) {
        return super.getStorages().stream().filter(storage -> storage.getName().equals(name)).findFirst().orElseThrow(() -> new NullPointerException("Could not be found an storage named '" + name + "'"));
    }
}