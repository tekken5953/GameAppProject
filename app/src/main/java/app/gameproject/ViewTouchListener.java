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
                        imageview.setColorFilter(false_color);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        imageview.setColorFilter(true_color);
                        break;
                    }
                }
                return false;
            }
        });
    }

    //Button
    public void onPressButton(Button button, int false_color, int true_color) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v,  MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                    case MotionEvent.ACTION_MOVE : {
                        v.setBackgroundColor(false_color);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.setBackgroundColor(true_color);
                        break;
                    }
                }
                return false;
            }
        });
    }
}
