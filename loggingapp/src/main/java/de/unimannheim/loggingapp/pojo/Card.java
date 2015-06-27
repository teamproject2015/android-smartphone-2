package de.unimannheim.loggingapp.pojo;

import android.widget.Button;

/**
 * Created by suryadevara on 22-06-2015.
 */
public class Card {

    public int x;
    public int y;
    public Button button;

    public Card(Button button, int x, int y) {
        this.x = x;
        this.y = y;
        this.button = button;
    }

}
