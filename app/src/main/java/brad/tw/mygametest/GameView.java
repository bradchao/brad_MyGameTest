package brad.tw.mygametest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2016/10/1.
 */
public class GameView extends View {

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.YELLOW);
    }
}
