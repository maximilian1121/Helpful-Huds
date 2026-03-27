package ca.hudmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class HudmodConfig {
    private static final HudmodConfig INSTANCE = new HudmodConfig();
    private static final File CONFIG_FILE = new File(Minecraft.getInstance().gameDirectory, "config/hudmod.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type MAP_TYPE = new TypeToken<Map<String, Boolean>>(){}.getType();

    public static final ConfigOption BEDROCK_COORDS = new ConfigOption("bedrockCoordinateCounter", true, "gui.hudmod.bedrock_coords");
    public static final ConfigOption BEDROCK_DAYS = new ConfigOption("bedrockDaysPlayedCounter", true, "gui.hudmod.bedrock_days");
    public static final ConfigOption BEDROCK_TIME = new ConfigOption("bedrockTimeOfDayCounter", false, "gui.hudmod.bedrock_time");
    public static final ConfigOption BEDROCK_FPS = new ConfigOption("bedrockFpsCounter", false, "gui.hudmod.bedrock_fps");
    public static final ConfigOption HOTBAR_COORDS = new ConfigOption("hotbarCoordinateCounter", false, "gui.hudmod.hotbar_coords");
    public static final ConfigOption COMPASS_HUD = new ConfigOption("compassHud", false, "gui.hudmod.compass");
    public static final ConfigOption COMPASS_SMOOTH = new ConfigOption("compassHudSmoothed", true, "gui.hudmod.compass_smooth");

    public static final List<ConfigOption> OPTIONS = List.of(
            BEDROCK_COORDS, BEDROCK_DAYS, BEDROCK_TIME, BEDROCK_FPS,
            HOTBAR_COORDS, COMPASS_HUD, COMPASS_SMOOTH
    );

    private Map<String, Boolean> values = new HashMap<>();

    private HudmodConfig() {}

    public static HudmodConfig get() { return INSTANCE; }

    protected boolean getValue(String key, boolean defaultValue) {
        return values.getOrDefault(key, defaultValue);
    }

    protected void setValue(String key, boolean value) {
        values.put(key, value);
    }

    public static void save() {
        try {
            if (!CONFIG_FILE.getParentFile().exists()) {
                CONFIG_FILE.getParentFile().mkdirs();
            }
            try (Writer writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(INSTANCE.values, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }
        try (Reader reader = new FileReader(CONFIG_FILE)) {
            Map<String, Boolean> loaded = GSON.fromJson(reader, MAP_TYPE);
            if (loaded != null) {
                INSTANCE.values = loaded;
            }
        } catch (Exception e) {
            save();
        }
    }

    public static void resetToDefaults() {
        INSTANCE.values.clear();
        save();
    }
}
