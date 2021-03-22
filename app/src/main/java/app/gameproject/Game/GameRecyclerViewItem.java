package app.gameproject.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class GameRecyclerViewItem {

    private String id;
    private String name;
    private Drawable img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public GameRecyclerViewItem(String id, String name, Drawable img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }
}