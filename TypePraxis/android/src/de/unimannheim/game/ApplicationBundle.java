package de.unimannheim.game;

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
