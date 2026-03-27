package ca.hudmod;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class Utils {
    public static String formatTime(long ticks) {
        long totalHours = (ticks / 1000) + 6;

        int hour24 = (int) (totalHours % 24);
        int minutes = (int) ((ticks % 1000) * 60 / 1000);

        String period = (hour24 < 12) ? "AM" : "PM";

        int hour12 = hour24 % 12;
        if (hour12 == 0) hour12 = 12;

        return String.format("%02d:%02d %s", hour12, minutes, period);
    }

    public static @NotNull ResourceLocation getConfigImage(String translationKey) {
        String imageName = translationKey.substring(translationKey.lastIndexOf('.') + 1);

        String path = "images/" + imageName + ".png";

        return getResourceLocation("hudmod", path);
    }

    public static ResourceLocation getResourceLocation(String namespace, String path) {
        //? if <1.21 {
        return new ResourceLocation(namespace, path);
         //?} else
        //return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
}
