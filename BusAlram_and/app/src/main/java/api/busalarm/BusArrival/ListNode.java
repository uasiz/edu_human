package api.busalarm.BusArrival;

import android.graphics.drawable.Drawable;

public class ListNode {
    private Drawable icon;
    private String busNum;
    private String arrTime;
    public boolean clickable=false;

    public Drawable getIcon() {
        return icon;
    }
    public void setIcon(Drawable icon) {
        this.icon = icon;
        this.clickable=true;
    }

    public String getBusNum() {
        return busNum;
    }
    public void setBusNum(String busNum) {
        this.busNum = busNum;
    }

    public String getArrTime() {
        return arrTime;
    }
    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }
}
