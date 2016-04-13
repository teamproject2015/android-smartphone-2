package de.unimannheim.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import de.unimannheim.utils.SpriteAccessor;

/**
 * Created by Saimadhav S on 27.03.2016.
 */
public class Splash implements Screen {

    private Sprite splashSprite;
    private TweenManager tweenManager;
    private TypePraxisGame game;

    public Splash(TypePraxisGame typePraxisGame) {
        this.game = typePraxisGame;
    }

    @Override
    public void show() {
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class,new SpriteAccessor());

        Texture texture = new Texture(Gdx.files.internal("images/splash.png"));
        splashSprite = new Sprite(texture);
        splashSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Tween.set(splashSprite, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(splashSprite, SpriteAccessor.ALPHA, 4).target(1).start(tweenManager);
        Tween.to(splashSprite,SpriteAccessor.ALPHA,2).target(0).delay(2).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game));
            }
            }).start(tweenManager);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        game.batch.begin();
        splashSprite.draw(game.batch);
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
