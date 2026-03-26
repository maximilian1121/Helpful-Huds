package ca.maximilian.hudmod.client;

public class Utils {
    public static final long DAY_LENGTH = 24000L;
    public static final String MOD_ID = "hudmod";

    public static String formatTime(long ticks) {
        long totalHours = (ticks / 1000) + 6;

        int hour24 = (int) (totalHours % 24);
        int minutes = (int) ((ticks % 1000) * 60 / 1000);

        String period = (hour24 < 12) ? "AM" : "PM";

        int hour12 = hour24 % 12;
        if (hour12 == 0) hour12 = 12;

        return String.format("%02d:%02d %s", hour12, minutes, period);
    }

}
