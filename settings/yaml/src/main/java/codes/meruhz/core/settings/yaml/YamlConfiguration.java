package codes.meruhz.core.settings.yaml;

import codes.meruhz.core.apis.AbstractConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class YamlConfiguration extends AbstractConfiguration<Map<String, Object>> {

    private final @NotNull DumperOptions dumperOptions;

    public YamlConfiguration(@NotNull File folder, @NotNull String name, @Nullable DumperOptions dumperOptions) {
        super(folder, name.endsWith(".yml") ? name : name + ".yml");
        super.content = new LinkedHashMap<>();
        this.dumperOptions = dumperOptions == null ? this.getDefaultDumperOptions() : dumperOptions;
    }

    protected @NotNull DumperOptions getDefaultDumperOptions() {
        @NotNull DumperOptions dumperOptions = new DumperOptions();

        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.DOUBLE_QUOTED);
        dumperOptions.setNonPrintableStyle(DumperOptions.NonPrintableStyle.BINARY);
        dumperOptions.setLineBreak(DumperOptions.LineBreak.UNIX);
        dumperOptions.setSplitLines(true);
        dumperOptions.setIndent(2);

        return dumperOptions;
    }

    public @NotNull DumperOptions getDumperOptions() {
        return this.dumperOptions;
    }

    @Override
    public void load() {
        super.load();

        if(super.getFile().length() != 0) {
            @NotNull Map<@NotNull String, @Nullable Object> content;

            try {
                content = new Yaml(this.getDumperOptions()).load(AbstractConfiguration.getFileContent(super.getFile()));

            } catch (IOException e) {
                throw new RuntimeException("An error occurred while loading yaml content from file: " + super.getFile().getAbsolutePath(), e);
            }

            super.setContent(content);

            if(!super.getContent().equals(content)) {
                this.load();
            }

        } else {
            super.save(new Yaml(this.getDumperOptions()).dump(super.getContent()));
        }
    }
}
