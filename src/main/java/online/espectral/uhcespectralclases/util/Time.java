package online.espectral.uhcespectralclases.util;

public class Time {
    int days;
    public static long minutes(long minutes) {
        return minutes * 60 * 1000;
    }
    public static long seconds(long seconds) {
        return seconds * 1000;
    }
    public static String getRemainTime(long timeElapsed, long finishTime) {
        String remainTime = String.valueOf(finishTime - timeElapsed);
        return remainTime.substring(0, remainTime.length() - 3);
    }
    public static int ticksToSeconds(int ticks) {
        return ticks / 20;
    }
    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }
    public static int ticksToMinutes(int ticks) {
        return ticks / 20 * 60;
    }
    public static int minutesToTicks(int minutes) {
        return minutes * 20 * 60;
    }
    public static boolean hasFinished(long timeElapsed, long finishedTime) {
        return finishedTime - timeElapsed <= 0;
    }



}
