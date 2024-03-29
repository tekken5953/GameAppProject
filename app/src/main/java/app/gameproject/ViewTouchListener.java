package app.gameproject;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

@SuppressLint("ClickableViewAccessibility")
public class ViewTouchListener {

    //Image View
    public void onPressImageView(ImageView imageview) {
        imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v,  MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                    case MotionEvent.ACTION_MOVE : {
                        imageview.setScaleY(1.1f);
                        imageview.setScaleX(1.1f);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        imageview.setScaleY(1f);
                        imageview.setScaleX(1f);
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
                        button.setScaleY(1.05f);
                        button.setScaleX(1.05f);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        button.setScaleY(1f);
                        button.setScaleX(1f);
                        break;
                    }
                }
                return false;
            }
        });
    }
}
