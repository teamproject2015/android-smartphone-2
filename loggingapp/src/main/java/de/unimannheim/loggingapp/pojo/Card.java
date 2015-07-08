package de.unimannheim.loggingapp.pojo;

import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by suryadevara on 22-06-2015.
 */
public class Card {

    public int x;
    public int y;
    public ImageButton button;

    public Card(ImageButton button, int x, int y) {
        this.x = x;
        this.y = y;
        this.button = button;
    }

}
