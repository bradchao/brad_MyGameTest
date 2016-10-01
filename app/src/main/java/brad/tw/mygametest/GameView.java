package brad.tw.mygametest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2016/10/1.
 */
public class GameView extends View {
    private Resources res;
    private boolean isInit; // false
    private int viewW, viewH;   // 0
    private Bitmap ballBmp;
    private float ballW, ballH;   // 0.0f
    private Matrix matrix;
    private Timer timer;
    private LinkedList<Ball> balls;
    private int[] ballImgs = {R.drawable.ball, R.drawable.ball1,
    R.drawable.ball2, R.drawable.ball3};
    private Bitmap[] bmps = new Bitmap[ballImgs.length];

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        res = context.getResources();
        matrix = new Matrix();
        timer = new Timer();
        balls = new LinkedList<>();

        setBackgroundResource(R.drawable.bg);


    }

    private void init(){
        viewW = getWidth(); viewH = getHeight();
        ballW = viewW / 12f; ballH = ballW;

        for (int i=0; i<ballImgs.length; i++) {
            Bitmap tempBmp = BitmapFactory.decodeResource(res, ballImgs[i]);
            tempBmp = resizeBitmap(tempBmp, ballW, ballH);
            bmps[i] = tempBmp;
        }

        timer.schedule(new RefreshTask(), 0, 40);

        isInit = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Ball ball = new Ball(
                event.getX()-ballW/2, event.getY()-ballH/2,
                bmps[(int)(Math.random()*bmps.length)]);
        timer.schedule(ball, 0, 30 + (int)(Math.random()*70));
        balls.add(ball);
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        for (Ball ball : balls) {
            canvas.drawBitmap(ball.bmp, ball.x, ball.y, null);
        }

    }

    private class RefreshTask extends TimerTask {
        @Override
        public void run() {
            postInvalidate();
        }
    }
    private class Ball extends TimerTask {
        float x, y, dx, dy;
        Bitmap bmp;
        Ball(float x, float y, Bitmap bmp){
            dx = viewW / 120f; dy = viewH / 90f;
            this.x = x; this.y = y;
            this.bmp = bmp;
        }

        @Override
        public void run() {
            if(x<0 || x+ballW>viewW){
                dx *= -1;
            }
            if (y<0 || y+ballH>viewH){
                dy *= -1;
            }
            x += dx;
            y += dy;
        }
    }


    private Bitmap resizeBitmap(Bitmap src, float newW, float newH){
        matrix.reset();
        matrix.postScale(newW/src.getWidth(),newH/src.getHeight());
        Bitmap newBmp = Bitmap.createBitmap(src,
                0,0,src.getWidth(),src.getHeight(),
                matrix,false);
        return newBmp;

    }
}
