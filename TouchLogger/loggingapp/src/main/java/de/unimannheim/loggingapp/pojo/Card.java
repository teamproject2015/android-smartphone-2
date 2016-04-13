package de.unimannheim.loggingapp.pojo;

import android.widget.ImageButton;

/**
 * Created by suryadevara on 22-06-2015.
 *
 * Card is POJO class with different variable assigned for Icongrid game
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
