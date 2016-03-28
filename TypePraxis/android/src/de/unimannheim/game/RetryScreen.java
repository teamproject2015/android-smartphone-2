package de.unimannheim.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Saimadhav S on 28.03.2016.
 */
public class RetryScreen implements Screen {

    private TypePraxisGame game;
    protected OrthographicCamera camera;
    private Sprite splashSprite;

    Label oopsLabel;


    public RetryScreen(final TypePraxisGame typePraxisGame,GameScreen gameScreen) {
        this.game = typePraxisGame;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setOnscreenKeyboardVisible(false);
        game.stage = new Stage();
        TextButton exitButton = new TextButton("Exit",game.skin);
        exitButton.setSize(200,100);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.myGameCallback.startMainActivity();

            }
        });



        oopsLabel = new Label("OOPS!!!!!! Game Over", game.skin, "scoreLabel");

        final Table table = new Table(game.skin);
        table.setBounds(0, 0, TypePraxisGame.width, TypePraxisGame.height);
        //table.top();
        table.add(oopsLabel).expandX();
        table.row();
        table.add("Your Final " + game.scoreLabel.getText()).expandX();
        table.row();
        table.row();
        table.row();
        table.add(exitButton).width(200).height(100).expandX();
        table.setFillParent(true);

        game.stage.addActor(table);

        Gdx.input.setInputProcessor(game.stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 150 / 255f, 136 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.stage.draw();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
