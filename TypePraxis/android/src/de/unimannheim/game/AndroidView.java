package de.unimannheim.game;

import java.util.ArrayList;

import de.unimannheim.interfaces.SizeChangeListener;
import de.unimannheim.interfaces.View;

/**
 * Created by Saimadhav S on 25.03.2016.
 */
public class AndroidView implements View {
    private ArrayList<SizeChangeListener> listeners = new ArrayList<SizeChangeListener>();
    private float width, height;
    public AndroidView(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addListener(SizeChangeListener listener) {
        listeners.add(listener);
    }

    public void onSizeChange(float width, float height) {
        this.width = width;
        this.height = height;
        for(SizeChangeListener listener : listeners) {
            listener.onSizeChange(width, height);
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
