package api.busalarm.BusLine;

import android.graphics.drawable.Drawable;

public class ListNode {
    private Drawable icon;
    private String number;
    private String direction;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
