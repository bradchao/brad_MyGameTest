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
    private Bitmap ball;
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

        ball = BitmapFactory.decodeResource(res, R.drawable.ball);
        ball = resizeBitmap(ball, ballW, ballH);

        timer.schedule(new BallTask(), 1*1000, 70);

        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        canvas.drawBitmap(ball,ballX,ballY,null);

    }

    private class BallTask extends TimerTask {
        @Override
        public void run() {
            ballX += dx;
            ballY += dy;
            postInvalidate();
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
