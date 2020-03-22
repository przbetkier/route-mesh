package integration.commons.helpers;

import java.util.concurrent.ThreadLocalRandom;

public class LatitudeHelper {

    private static final Double MIN_LATITUDE = 49.00238;
    private static final Double MAX_LATITUDE = 54.833333;
    private static final Double MIN_LONGITUDE = 14.12298;
    private static final Double MAX_LONGITUDE = 24.14585;

    public static Double randomPolishLatitude() {
        return ThreadLocalRandom.current().nextDouble(MIN_LATITUDE, MAX_LATITUDE);
    }

    public static Double randomPolishLongitude() {
        return ThreadLocalRandom.current().nextDouble(MIN_LONGITUDE, MAX_LONGITUDE);
    }
}
