package de.unimannheim.loggingapp.touchlogger;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import de.unimannheim.loggingapp.R;
import de.unimannheim.loggingapp.pojo.Card;

public class IconGridActivity extends SensorManagerActivity {

    private static int ROW_COUNT = 4;
    private static int COL_COUNT = 3;
    private static Object lock = new Object();
    int turns;
    private GridView gridView;
    private int lastClicked;
    private List<Drawable> images;
    private Context context;
    private Drawable backImage;
    private int[][] cards;
    private Card firstCard;
    private Card secondCard;
    private TableLayout mainTable;
    private UpdateCardsHandler handler;
    private ButtonListener buttonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadImages();
        handler = new UpdateCardsHandler();
        setContentView(R.layout.activity_touchlogger_icongrid);
        backImage = getResources().getDrawable(R.drawable.android_icon);
        mainTable = (TableLayout) findViewById(R.id.tableLayout_iconMatch);
        context = mainTable.getContext();
        /*TableRow tr = ((TableRow)findViewById(R.id.tableRow_matchicons));
        tr.removeAllViews();
        tr.addView(mainTable);*/

        cards = new int[COL_COUNT][ROW_COUNT];

        for (int y = 0; y < ROW_COUNT; y++) {
            mainTable.addView(createRow(y));
        }

        firstCard = null;
        loadCards();
        turns = 0;
        ((TextView) findViewById(R.id.textView_icongrid_tries)).setText("Tries: " + turns);
    }

    private TableRow createRow(int y) {
        TableRow row = new TableRow(context);
        row.setHorizontalGravity(Gravity.CENTER);
        for (int x = 0; x < COL_COUNT; x++) {
            row.addView(createImageButton(x, y));
        }
        return row;
    }

    private View createImageButton(int x, int y) {
        Button button = new Button(context);
        button.setBackground(backImage);
        button.setId(100 * x + y);
        button.setOnClickListener(buttonListener);
        return button;
    }

    private void loadCards() {
        try {
            int size = ROW_COUNT * COL_COUNT;
            Log.i("loadCards()", "size=" + size);
            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < size; i++) {
                list.add(i);
            }

            Random r = new Random();
            for (int i = size - 1; i >= 0; i--) {
                int t = 0;
                if (i > 0) {
                    t = r.nextInt(i);
                }
                t = list.remove(t);
                cards[i % COL_COUNT][i / COL_COUNT] = t % (size / 2);
                Log.i("loadCards()", "card[" + (i % COL_COUNT) +
                        "][" + (i / COL_COUNT) + "]=" + cards[i % COL_COUNT][i / COL_COUNT]);
            }
        } catch (Exception e) {
            Log.e("loadCards()", e + "");
        }
    }

    private void loadImages() {
        images = new ArrayList<Drawable>();
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_1));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_2));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_3));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_4));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_5));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_6));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_7));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_8));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_9));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_10));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_11));
        images.add(getResources().getDrawable(R.drawable.ic_icongrid_12));
    }

    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            synchronized (lock) {
                if (firstCard != null && secondCard != null) {
                    return;
                }
                int id = v.getId();
                int x = id / 100;
                int y = id % 100;
                turnCard((Button) v, x, y);
            }
        }

        private void turnCard(Button button, int x, int y) {
            button.setBackground(images.get(cards[x][y]));

            if (firstCard == null) {
                firstCard = new Card(button, x, y);
            } else {
                if (firstCard.x == x && firstCard.y == y) {
                    return; //the user pressed the same card
                }

                secondCard = new Card(button, x, y);
                turns++;
                ((TextView) findViewById(R.id.textView_icongrid_tries)).setText("Tries: " + turns);

                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            synchronized (lock) {
                                handler.sendEmptyMessage(0);
                            }
                        } catch (Exception e) {
                            Log.e("E1", e.getMessage());
                        }
                    }
                };
                Timer t = new Timer(false);
                t.schedule(tt, 1300);
            }
        }
    }


    class UpdateCardsHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            synchronized (lock) {
                checkCards();
            }
        }

        public void checkCards() {
            if (cards[secondCard.x][secondCard.y] == cards[firstCard.x][firstCard.y]) {
                firstCard.button.setVisibility(View.INVISIBLE);
                secondCard.button.setVisibility(View.INVISIBLE);
            } else {
                secondCard.button.setBackground(backImage);
                firstCard.button.setBackground(backImage);
            }
            firstCard = null;
            secondCard = null;
        }
    }
}
