package ca.hudmod.config;

import net.minecraft.network.chat.Component;

public record ConfigOption(String key, boolean defaultValue, String translationKey) {

    public boolean get() {
        return HudmodConfig.get().getValue(this.key, this.defaultValue);
    }

    public void set(boolean value) {
        HudmodConfig.get().setValue(this.key, value);
    }

    public Component getLabel() {
        return Component.translatable(this.translationKey);
    }
}
