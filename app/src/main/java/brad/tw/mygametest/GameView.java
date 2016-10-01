package brad.tw.mygametest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2016/10/1.
 */
public class GameView extends View {
    private Resources res;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        res = context.getResources();
        setBackgroundResource(R.drawable.bg);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bg = BitmapFactory.decodeResource(res, R.drawable.bg);
        canvas.drawBitmap(bg,-200,0,null);

    }
}
