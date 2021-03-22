package app.gameproject;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

@SuppressLint("ClickableViewAccessibility")
public class ViewTouchListener {

    //Image View
    public void onPressImageView(ImageView imageview, int false_color, int true_color) {
        imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v,  MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                    case MotionEvent.ACTION_MOVE : {
                        imageview.setColorFilter(true_color);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        imageview.setColorFilter(false_color);
                        break;
                    }
                }
                return false;
            }
        });
    }

    //Button
    public void onPressButton(Button button) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v,  MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                    case MotionEvent.ACTION_MOVE : {
                        v.setScaleX(1.2f);
                        v.setScaleY(1.2f);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.setScaleX(0);
                        v.setScaleY(0);
                        break;
                    }
                }
                return false;
            }
        });
    }
}
