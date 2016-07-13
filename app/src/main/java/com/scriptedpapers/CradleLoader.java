package com.scriptedpapers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by mahes on 7/5/16.
 */
public class CradleLoader extends View {

    Paint circlePaint = new Paint();

    public static final int ANIM_DURATION = 750;

    public static final int[] BG_COLOR = {0xFF01A3F9, 0xFF9E00EE, 0xFFEF2E59, 0xFFFF931C, 0xFFFCE511, 0xFF1EC248};
    public static final int[] CIRCLE_COLOR = {0xFFFFEA00, 0xFF13FB2D, 0xFF00C0FF, 0xFF6527C8, 0xFFEF2E59, 0xFFFFAF18};

    int radius;
    int radiusY;

    int start;
    int limit;

    int circleRadius;

    int screenWidth;

    int firstXValue;

    private int count = 5;
    private int patch = 2;

    int bgCount = 0;

    private boolean IS_LEFT = true;

    ObjectAnimator firstUpAnimator;
    ObjectAnimator firstDownAnimator;

    ObjectAnimator lastUpAnimator;
    ObjectAnimator lastDownAnimator;

    public CradleLoader(Context context) {
        super(context);
        initView();
    }

    public CradleLoader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CradleLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    void initView() {
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(CIRCLE_COLOR[bgCount]);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (IS_LEFT) {
            start = 0;
            limit = patch - 1;
        } else {
            limit = count - 1;
            start = limit - patch + 1;
        }

        for (int i = 0; i < count; i++) {

            int extraTmpValue = 0;

            if((i >= start) && (i <= limit)) {
                extraTmpValue = firstXValue;
            }

            if (count % 2 == 0) {

                int xValue = 0;
                int yValue = 0;

                if (i < count / 2) {
                    xValue = extraTmpValue + getWidth() / 2 - (((count - 1) - (i * 2)) * circleRadius);
                } else if (i == count / 2 || i > count / 2) {
                    xValue = extraTmpValue + getWidth() / 2 + ((((i - count / 2) * 2) + 1) * circleRadius);
                }

                yValue = (int) (Math.sqrt((radius * radius) - (extraTmpValue * extraTmpValue)) + radiusY);

                if(extraTmpValue == 0)
                    canvas.drawCircle(xValue, yValue, circleRadius, circlePaint);
                else
                    canvas.drawCircle(xValue, yValue, circleRadius, circlePaint);

            } else {

                int xValue = 0;
                int yValue = 0;

                if (count / 2 - i == 0) {
                    xValue = extraTmpValue + getWidth() / 2;

                } else if (i < count / 2) {
                    xValue = extraTmpValue + getWidth() / 2 - (((count - 1) - (i * 2)) * circleRadius);

                } else if (i > count / 2) {
                    xValue = extraTmpValue + getWidth() / 2 + ((i - count / 2) * 2 * circleRadius);
                }

                yValue = (int) (Math.sqrt((radius * radius) - (extraTmpValue * extraTmpValue)) + radiusY);

                if(extraTmpValue == 0)
                    canvas.drawCircle(xValue, yValue, circleRadius, circlePaint);
                else
                    canvas.drawCircle(xValue, yValue, circleRadius, circlePaint);
            }

        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        StopAnimation();
        startAnimation(count, patch);
    }

    public void StopAnimation() {

        if (firstUpAnimator != null) {
            firstUpAnimator.end();
        }

        if (firstDownAnimator != null) {
            firstDownAnimator.end();
        }
        if (lastUpAnimator != null) {
            lastUpAnimator.end();
        }

        if (lastDownAnimator != null) {
            lastDownAnimator.removeAllListeners();
            lastDownAnimator.end();
        }

        setStartXValue(0);
        setEndXValue(0);

        invalidate();
    }

    public void startAnimation(int count, int patch) {

        this.count = count;
        this.patch = patch;

        circleRadius = (int) (getWidth() / (count * 4));
        setBackgroundColor(BG_COLOR[bgCount]);

        screenWidth = getWidth();

        radius = getHeight() / 4;
        radiusY = getHeight() / 2;

        StopAnimation();

        firstUpAnimator = ObjectAnimator.ofInt(CradleLoader.this, "startXValue", 0, -radius * 3 / 4);
        firstUpAnimator.setDuration(ANIM_DURATION);
        firstUpAnimator.setInterpolator(new DecelerateInterpolator(1.5F));

        firstDownAnimator = ObjectAnimator.ofInt(CradleLoader.this, "startXValue", -radius * 3 / 4, 0);
        firstDownAnimator.setDuration(ANIM_DURATION);
        firstDownAnimator.setInterpolator(new AccelerateInterpolator(1.5F));
        firstDownAnimator.setStartDelay(ANIM_DURATION);

        firstDownAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                bgCount++;

                if(bgCount >= BG_COLOR.length)
                    bgCount = 0;

                circlePaint.setColor(CIRCLE_COLOR[bgCount]);
                setBackgroundColor(BG_COLOR[bgCount]);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        lastUpAnimator = ObjectAnimator.ofInt(CradleLoader.this, "endXValue", 0, radius * 3 / 4);
        lastUpAnimator.setDuration(ANIM_DURATION);
        lastUpAnimator.setInterpolator(new DecelerateInterpolator(1.5F));
        lastUpAnimator.setStartDelay(2 * ANIM_DURATION);

        lastDownAnimator = ObjectAnimator.ofInt(CradleLoader.this, "endXValue", radius * 3 / 4, 0);
        lastDownAnimator.setDuration(ANIM_DURATION);
        lastDownAnimator.setInterpolator(new AccelerateInterpolator(1.5F));
        lastDownAnimator.setStartDelay(3 * ANIM_DURATION);

        lastDownAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                firstUpAnimator.start();
                firstDownAnimator.start();
                lastUpAnimator.start();
                lastDownAnimator.start();

                bgCount++;

                if(bgCount >= BG_COLOR.length)
                    bgCount = 0;

                circlePaint.setColor(CIRCLE_COLOR[bgCount]);
                setBackgroundColor(BG_COLOR[bgCount]);

            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        firstUpAnimator.start();
        firstDownAnimator.start();
        lastUpAnimator.start();
        lastDownAnimator.start();

    }

    public void setStartXValue(int firstXValue) {

        IS_LEFT = true;
        this.firstXValue = firstXValue;
        invalidate();
    }


    public void setEndXValue(int lastXValue) {

        IS_LEFT = false;
        this.firstXValue = lastXValue;
        invalidate();
    }

}
