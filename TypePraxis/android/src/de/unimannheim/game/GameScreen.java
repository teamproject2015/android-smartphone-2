package de.unimannheim.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Saimadhav S on 23.03.2016.
 */
public class GameScreen implements Screen {

    public static final int CIRCLE_WIDTH = 200;
    public static final int CIRCLE_HEIGHT = 200;

    private TypePraxisGame game;

    protected OrthographicCamera camera;
    private Texture wordImage;
    private Sound dropSound;
    private Music gameMusic;
    private Array<Circle> wordCircles,removeWordCircles;
    private long lastDropTime;
    private Pixmap pixmap;
    private Texture texture, progressTexture;
    int circleCount;
    private Array<String> randomText,removeRandomText;

    ImmediateModeRenderer renderer;

    private float[] positions;
    private float[] values;

    float thickness = 25f;

    //Random random = new Random();

    /**
     * Calling the constructor to set the typePraxisGame
     *
     * @param typePraxisGame
     */
    public GameScreen(final TypePraxisGame typePraxisGame) {
        this.game = typePraxisGame;
    }

    /**
     * Override the show method for GameScreen
     */
    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // super.create();
        wordImage = new Texture(Gdx.files.internal("images/circle.png"));
        // bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("music/drop.wav"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/water.mid"));

        // start the playback of the background music immediately
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);

        // create the raindrops array and spawn the first raindrop
        wordCircles = new Array<>();
        removeWordCircles = new Array<>();
        randomText = new Array<>();
        removeRandomText = new Array<>();

        positions = new float[1];
        values = new float[1];

        pixmap = new Pixmap(640, 640, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2 - 1);
        pixmap.setColor(Color.BLACK);
        pixmap.drawCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2 - 1);
        texture = new Texture(pixmap);

        spawnRaindrop();

        progressTexture = new Texture(Gdx.files.internal("images/progress.png"));
        progressTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        renderer = new ImmediateModeRenderer20(false, true, 1);

        game.stage.setKeyboardFocus(game.nameField);
        //show the keyboard
        Gdx.input.setOnscreenKeyboardVisible(true);


        game.nameField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char key) {
                //Gdx.app.log("keyboarb", "kepressed: " + key);
                if (key == '\n') {
                    String text = game.nameField.getText();
                    boolean isKeyMatched = false;
                    int j = 0;
                    for (String randText : randomText) {
                        if (randText.equalsIgnoreCase(text)) {
                            removeWordCircles.add(wordCircles.get(j));
                            removeRandomText.add(randomText.get(j));
                            removeWords();
                            game.score++;
                            dropSound.play();
                            isKeyMatched = true;
                            break;
                        }
                        j++;
                    }
                    if (!isKeyMatched) {
                        Gdx.input.vibrate(200);
                    }
                    game.nameField.setText("");
                    Gdx.input.setOnscreenKeyboardVisible(true);
                }
            }
        });


        gameMusic.play();

    }

    private void spawnRaindrop() {

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.
        Circle wordCircle = myCircleOverlapCheckMethod();
        //MathUtils.random(0,TestText.TEST_TEXT.length);
        randomText.add(randomText());
        wordCircles.add(wordCircle);

        positions = createNewArray(positions, wordCircles);
        values = createNewArray(values, wordCircles);

        lastDropTime = TimeUtils.nanoTime();
        circleCount++;
    }

    private static String randomText(){
        String text = TestText.THREE_CHARS[MathUtils.random(0, TestText.THREE_CHARS.length)];
        //random.nextInt(TestText.TEST_TEXT.length);
        //Gdx.app.log("INFO", "Random text " + text);
        if (!"".equals(text)){
            return text;
        }
        return randomText();
    }

    /**
     * create a new Array based on the existing count of words available in wordcircle
     *
     * @param oldArray
     * @param wordCircles
     * @return
     */
    private static float[] createNewArray(float[] oldArray, Array<Circle> wordCircles) {
        float[] newArray = new float[wordCircles.size];

        System.arraycopy(oldArray,0,newArray,0,oldArray.length);

        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] == 0) {
                newArray[i] = 1f;
            }
        }
        return newArray;
    }


    private Circle myCircleOverlapCheckMethod() {
        Circle wordCircle = new Circle();
        wordCircle.x = MathUtils.random(0, TypePraxisGame.width - 228);
        wordCircle.y = MathUtils.random(600, TypePraxisGame.height - 228);
        wordCircle.radius = 100;

        for (Circle circle : wordCircles) {
            if (!wordCircle.overlaps(circle)) {
                return wordCircle;
            } else {
                return myCircleOverlapCheckMethod();
            }
        }
        return wordCircle;
    }

    private void removeWords(){
        if (removeRandomText.size>0){
            randomText.removeAll(removeRandomText,false);
        }

        if (removeWordCircles.size>0){
            wordCircles.removeAll(removeWordCircles,false);
            circleCount = wordCircles.size;
        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 150 / 255f, 136 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // tell the camera to update its matrices.
        camera.update();

        for (int j = 0; j < positions.length; j++) {

            positions[j] += values[j] * 0.10f * delta;

            if (positions[j] > 1f) {
                positions[j] = 1f;
                values[j] *= -1f;

            } else if (positions[j] < 0f) {
                positions[j] = 0f;
                values[j] *= -1f;
                Gdx.input.vibrate(100);
                game.failedAttempts++;
                //Gdx.app.log("INFO", "wordCircles size: " + wordCircles.size);
                removeWordCircles.add(wordCircles.get(j));
                removeRandomText.add(randomText.get(j));
            }
        }

        if (game.failedAttempts==3) {
            game.failedAttempts=0;
            gameMusic.pause();
            game.setScreen(new RetryScreen(game,this));
          //  dispose();
        }

        removeWords();
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops

        game.stage.draw();
        game.batch.begin();
        game.scoreLabel.setText("Score: " + game.score);
        game.failedAttemptsLabel.setText("Failed: " +  game.failedAttempts + "/"+ game.noOfAttempts);
        int i = 0;
        for (Circle circle : wordCircles) {
            String text = randomText.get(i);
            game.batch.draw(texture, circle.x, circle.y, CIRCLE_WIDTH, CIRCLE_HEIGHT);
            game.circleFont.draw(game.batch, text, circle.x + 80, circle.y + 100);
            i++;
        }
        game.batch.end();

        if ((TimeUtils.nanoTime() - lastDropTime > 2000000000) && circleCount < 2) {
            spawnRaindrop();
        }

        progressTexture.bind();
        // check if we need to create a new raindrop
        int k = 0;
        for (Circle circle: wordCircles) {
            progress(circle.x + 100, circle.y + 100, circle.radius, thickness, 1f, Color.RED, progressTexture);
            progress(circle.x + 100, circle.y + 100, circle.radius, thickness, positions[k], Color.GREEN, progressTexture);
            k++;
        }


    }


    public void progress(float cx, float cy, float r, float thickness, float amt, Color c, Texture progressTexture) {
        //start and end angles
        float start = 0f;
        float end = amt * 360f;

        renderer.begin(camera.combined, GL20.GL_TRIANGLE_STRIP);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        int segs = (int) (12 * Math.cbrt(r));
        end += 90f;
        start += 90f;
        float halfThick = thickness / 2f;
        float step = 360f / segs;
        for (float angle = start; angle < (end + step); angle += step) {
            float tc = 0.5f;
            if (angle == start)
                tc = 0f;
            else if (angle >= end)
                tc = 1f;

            float fx = MathUtils.cosDeg(angle);
            float fy = MathUtils.sinDeg(angle);

            float z = 0f;
            renderer.color(c.r, c.g, c.b, c.a);
            renderer.texCoord(tc, 1f);
            renderer.vertex(cx + fx * (r + halfThick), cy + fy * (r + halfThick), z);

            renderer.color(c.r, c.g, c.b, c.a);
            renderer.texCoord(tc, 0f);
            renderer.vertex(cx + fx * (r + -halfThick), cy + fy * (r + -halfThick), z);
        }
        renderer.end();
    }


    @Override
    public void dispose() {
        // dispose of all the native resources
        wordImage.dispose();
        //bucketImage.dispose();
        dropSound.dispose();
        gameMusic.dispose();
        pixmap.dispose();
        texture.dispose();
        progressTexture.dispose();
        wordCircles.clear();
        removeWordCircles.clear();
        randomText.clear();
        removeRandomText.clear();
        renderer.dispose();
        progressTexture.dispose();
        circleCount = 0;
        randomText.clear();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        gameMusic.pause();
    }

    @Override
    public void resume() {

    }
}
