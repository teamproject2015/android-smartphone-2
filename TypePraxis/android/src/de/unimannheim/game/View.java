package de.unimannheim.game;

/**
 * Created by Saimadhav S on 25.03.2016.
 */
public interface View {
    public void onSizeChange(float width, float height);
    public void addListener(SizeChangeListener sizeChangeListener);
    public float getWidth();
    public float getHeight();
}
