package de.unimannheim.utils;

/**
 * Created by Saimadhav S on 25.03.2016.
 */

public class DimensionUtils {
    private static boolean isInitialised = false;
    private static float pixelsPerOneDp;

    private static void initialise(android.view.View view) {
        pixelsPerOneDp = view.getResources().getDisplayMetrics().densityDpi / 160f;
        isInitialised = true;
    }

    public static float pxToDp(android.view.View view, float px) {
        if (!isInitialised) {
            initialise(view);
        }

        return px / pixelsPerOneDp;
    }

    public static float dpToPx(android.view.View view, float dp) {
        if (!isInitialised) {
            initialise(view);
        }

        return dp * pixelsPerOneDp;
    }
}
