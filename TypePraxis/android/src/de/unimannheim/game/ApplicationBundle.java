package de.unimannheim.game;

import de.unimannheim.interfaces.View;

/**
 * Created by Saimadhav S on 25.03.2016.
 */
public class ApplicationBundle {
    private final View view;

    public ApplicationBundle(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
}
