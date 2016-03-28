package de.unimannheim.game;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Saimadhav S on 24.03.2016.
 */
public class CustomFont {

        private static CustomFont font;
        public Typeface CHOKO_FONT;

        private CustomFont() {

        }

        public static CustomFont getInstance(Context context) {
            if (font == null) {
                font = new CustomFont();
                font.init(context);
            }
            return font;
        }

        public void init(Context context) {
            CHOKO_FONT = Typeface.createFromAsset(context.getAssets(), "ui/Choko.ttf");
        }


}
