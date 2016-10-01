package brad.tw.mygametest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by user on 2016/10/1.
 */
public class GameView extends View {
    private Resources res;
    private boolean isInit; // false
    private int viewW, viewH;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        res = context.getResources();
        setBackgroundResource(R.drawable.bg);


    }

    private void init(){
        viewW = getWidth(); viewH = getHeight();

        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        Bitmap ball = BitmapFactory.decodeResource(res, R.drawable.ball);
        canvas.drawBitmap(ball, 0,0,null);

    }
}
