package codes.meruhz.core.apis.language.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileUtils {

    public static @NotNull List<InputStream> getAllInputStreamsFromResources(@NotNull String specificFolder) {
        @NotNull List<InputStream> inputStreams = new ArrayList<>();
        @NotNull ClassLoader classLoader = FileUtils.class.getClassLoader();

        try {
            @NotNull Enumeration<URL> resources = classLoader.getResources(specificFolder);

            while(resources.hasMoreElements()) {
                @NotNull URL resource = resources.nextElement();

                if(resource.getProtocol().equals("jar")) {
                    @NotNull String jar = resource.getFile();

                    @NotNull String path = jar.substring(5, jar.indexOf("!"));
                    @NotNull String folder = jar.substring(jar.indexOf("!") + 2);

                    try(@NotNull JarFile jarFile = new JarFile(path)) {
                        @NotNull Enumeration<JarEntry> entries = jarFile.entries();

                        while(entries.hasMoreElements()) {
                            @NotNull JarEntry entry = entries.nextElement();

                            if(!entry.isDirectory() && entry.getName().startsWith(folder)) {
                                @Nullable InputStream inputStream = classLoader.getResourceAsStream(entry.getName());

                                if(inputStream != null) {
                                    inputStreams.add(inputStream);
                                }
                            }
                        }
                    }

                } else if(resource.getProtocol().equals("file")) {
                    @NotNull File folder = new File(resource.getFile());
                    FileUtils.processFolder(folder, specificFolder, inputStreams);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return inputStreams;
    }

    private static void processFolder(@NotNull File folder, @NotNull String specificFolder, @NotNull List<InputStream> inputStreams) {
        @NotNull File @Nullable [] files = folder.listFiles();

        if(files != null) {

            for(File file : files) {

                if(file.isDirectory()) {

                    if(file.getName().equals(specificFolder)) {
                        FileUtils.processFolder(file, specificFolder, inputStreams);
                    }

                } else try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    inputStreams.add(fileInputStream);

                } catch (IOException e) {
                    throw new RuntimeException("An error occurred while reading file: " + file.getAbsolutePath(), e);
                }
            }
        }
    }
}
