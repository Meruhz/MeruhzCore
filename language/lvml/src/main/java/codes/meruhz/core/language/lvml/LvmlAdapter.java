package codes.meruhz.core.language.lvml;

import codes.laivy.mlanguage.api.bungee.components.BaseComponentMessage;
import codes.laivy.mlanguage.api.bungee.components.BaseComponentMessageStorage;
import codes.meruhz.core.apis.language.LanguageAdapter;
import org.jetbrains.annotations.NotNull;

public class LvmlAdapter extends LanguageAdapter<BaseComponentMessageStorage<BaseComponentMessage>> {

    @Override
    public @NotNull BaseComponentMessageStorage<BaseComponentMessage> getStorage(@NotNull String name) {
        return super.getStorages().stream().filter(storage -> storage.getName().equals(name)).findFirst().orElseThrow(() -> new NullPointerException("Could not be found an LvMultiplesLanguages storage named '" + name + "'"));
    }
}