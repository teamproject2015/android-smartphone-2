package de.unimannheim.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import de.unimannheim.interfaces.SizeChangeListener;
import de.unimannheim.interfaces.View;

/**
 * Created by Saimadhav S on 23.03.2016.
 */
public class TypePraxisGame extends Game {


    public SpriteBatch batch;

    public Skin skin;
    public Stage stage;
    public BitmapFont circleFont;
    public static int width, height;
    public TextButton startButton;
    Label scoreLabel,failedAttemptsLabel;
    public int score;
    public int failedAttempts;
    public int noOfAttempts;
    public TextField nameField;


    private View view;

    // Define an interface for your various callbacks to the android launcher
    public interface MyGameCallback {
        public void startMainActivity();
    }
    // Local variable to hold the callback implementation
    public MyGameCallback myGameCallback;

    // Setter for the callback
    public void setMyGameCallback(MyGameCallback callback) {
        myGameCallback = callback;
    }

    public TypePraxisGame(ApplicationBundle applicationBundle,MyGameCallback myGameCallback) {
        view = applicationBundle.getView();
        this.myGameCallback = myGameCallback;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        circleFont = new BitmapFont();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        //skin.addRegions(atlas);

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        circleFont = skin.getFont("calibri-font");
        circleFont.setColor(Color.BLACK);
        batch = new SpriteBatch();
        noOfAttempts = 3;

        scoreLabel = new Label("Score: " + score, skin, "scoreLabel");
        failedAttemptsLabel = new Label("Failed: " +  failedAttempts + "/"+ noOfAttempts, skin, "scoreLabel");

        nameField = new TextField("", skin);
        nameField.setSize(400, 100);

        final Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), 150);
        //table.top();
        table.add(scoreLabel).expandX();
        //table.row();
        table.add(failedAttemptsLabel);
        table.add(nameField).height(100).expandX();
/*
        nameField.setWidth(view.getWidth() * 0.6f);
        nameField.setHeight( view.getHeight() * 0.05f);*/
        view.addListener(new SizeChangeListener() {
            @Override
            public void onSizeChange(float width, float height) {
                //Gdx.app.log("INFO", "Visible area: " + width + "   " + height);
                //Gdx.app.log("INFO", "Stage area: " + stage.getWidth() + "   " + stage.getHeight());
                float keyboardHeight = getKeyboardHeight();
                //nameField.addAction(Actions.moveTo(width / 2 - nameField.getWidth() / 2.0f, keyboardHeight + (6 * (height / 8)), 1, Interpolation.sineOut));
                if(height == stage.getHeight()){
                    table.setBounds(0, 0, Gdx.graphics.getWidth(), 150);
                } else {
                    table.setBounds(0, 0, Gdx.graphics.getWidth(), (stage.getHeight() + 200) - keyboardHeight);
                }
            }
        });


        //table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        this.setScreen(new Splash(this));
    }

    @Override
    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        circleFont.dispose();
        stage.dispose();
        skin.dispose();
    }

    private float getKeyboardHeight() {
        return stage.getHeight() - view.getHeight();
    }

}
