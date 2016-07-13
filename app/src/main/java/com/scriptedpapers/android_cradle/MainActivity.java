package com.scriptedpapers.android_cradle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.scriptedpapers.CradleLoader;

public class MainActivity extends AppCompatActivity {

    int ballCount = 4;
    int movingBallCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CradleLoader cradleLoader = (CradleLoader) findViewById(R.id.circleLoader);

        final EditText ballCountText = (EditText) findViewById(R.id.ballCountText);
        final EditText ballMovingCountText = (EditText) findViewById(R.id.ballMovingCountText);

        TextView decreaseBalls = (TextView) findViewById(R.id.decreaseBalls);
        TextView increaseBalls = (TextView) findViewById(R.id.increaseBalls);

        TextView decreaseMovingBalls = (TextView) findViewById(R.id.decreaseMovingBalls);
        TextView increaseMovingBalls = (TextView) findViewById(R.id.increaseMovingBalls);

        ballCountText.setText(String.valueOf(ballCount));
        ballMovingCountText.setText(String.valueOf(movingBallCount));


        decreaseBalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ballCount >= 3) {
                    ballCount--;
                    ballCountText.setText(String.valueOf(ballCount));
                    cradleLoader.startAnimation(ballCount, movingBallCount);
                }

            }
        });

        increaseBalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ballCount++;
                ballCountText.setText(String.valueOf(ballCount));
                cradleLoader.startAnimation(ballCount, movingBallCount);
            }
        });


        decreaseMovingBalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (movingBallCount >= 2) {
                    movingBallCount--;
                    ballMovingCountText.setText(String.valueOf(movingBallCount));
                    cradleLoader.startAnimation(ballCount, movingBallCount);
                }

            }
        });

        increaseMovingBalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movingBallCount++;
                ballMovingCountText.setText(String.valueOf(movingBallCount));
                cradleLoader.startAnimation(ballCount, movingBallCount);
            }
        });

        cradleLoader.startAnimation(ballCount, movingBallCount);

    }

}
