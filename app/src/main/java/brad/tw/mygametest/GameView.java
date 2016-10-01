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
import android.view.View;

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
    private Ball ball;
    private float ballW, ballH, ballX, ballY, dx, dy;   // 0.0f
    private Matrix matrix;
    private Timer timer;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        res = context.getResources();
        matrix = new Matrix();
        timer = new Timer();

        setBackgroundResource(R.drawable.bg);


    }

    private void init(){
        viewW = getWidth(); viewH = getHeight();
        ballW = viewW / 12f; ballH = ballW;

        dx = viewW / 240f; dy = viewH / 180f;

        ballBmp = BitmapFactory.decodeResource(res, R.drawable.ball);
        ballBmp = resizeBitmap(ballBmp, ballW, ballH);
        ball = new Ball(0,0,ballBmp);

        timer.schedule(new RefreshTask(), 0, 40);
        timer.schedule(ball, 0, 30);

        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        canvas.drawBitmap(ball.bmp,ball.x,ball.y,null);

    }

    private class RefreshTask extends TimerTask {
        @Override
        public void run() {
            postInvalidate();
        }
    }
    private class Ball extends TimerTask {
        float x, y;
        Bitmap bmp;
        Ball(float x, float y, Bitmap bmp){
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
