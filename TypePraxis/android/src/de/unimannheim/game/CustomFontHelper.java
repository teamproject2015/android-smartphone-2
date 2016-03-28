package de.unimannheim.game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Saimadhav S on 24.03.2016.
 */
public class CustomFontHelper {

        private static CustomFont font;

        public static void applyFont(View parentView, Context context) {

            font = CustomFont.getInstance(context);
            apply((ViewGroup)parentView);

        }

        private static void apply(ViewGroup parentView) {
            for (int i = 0; i < parentView.getChildCount(); i++) {

                View view = parentView.getChildAt(i);

//You can add any view element here on which you want to apply font

                if (view instanceof EditText) {
                    ((EditText) view).setTypeface(font.CHOKO_FONT);

                }
                if (view instanceof TextView) {
                    ((TextView) view).setTypeface(font.CHOKO_FONT);
                }

                else if (view instanceof ViewGroup
                        && ((ViewGroup) view).getChildCount() > 0) {
                    apply((ViewGroup) view);
                }
            }

        }


}
