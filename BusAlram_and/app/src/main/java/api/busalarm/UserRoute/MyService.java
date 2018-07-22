package api.busalarm.UserRoute;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyService extends Service
{
    String nodeid;
    String routeid;

    double Destinationlatitude;
    double Destinationlongitude;
    int check;

    double myLatitude;
    double myLongitude;

    OutputStream sendMsg;
    InputStream reMsg;
    private Socket socket;

    Vibrator mVibe;
    long[] pattern = { 3000, 3000 ,3000, 3000, 3000, 3000, 3000, 3000, 3000, 3000, 3000, 3000};
    Intent Serviceintent ;

    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;


    private class LocationListener implements android.location.LocationListener{
        Location mLastLocation;
        public LocationListener(String provider)
        {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }
        @Override
        public void onLocationChanged(Location location)
        {
            myLongitude = location.getLongitude();
            myLatitude = location.getLatitude();
            double distance;
            Location locationS = new Location("station point");
            locationS.setLatitude(Destinationlatitude);
            locationS.setLongitude(Destinationlongitude);
            //distance = location.distanceTo(locationS);
            //Toast.makeText(getBaseContext(), "정류장까지"+Double.toString(distance)+"m", Toast.LENGTH_LONG).show();
            if(myLongitude-Destinationlongitude<=0.01){
                if(myLatitude-Destinationlatitude<=0.01){
                    Toast.makeText(getBaseContext(), "도착할꺼야아아아ㅏㅏ아아아아ㅏㅇ앙!!!!!!!!!!!!!!!!!!", Toast.LENGTH_LONG).show();
                    sending();
                }else{
                }
            }else{
            }
            mLastLocation.set(location);
        }
        public void sending(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        socket = new Socket("58.74.90.3", 6666);
                        sendMsg = socket.getOutputStream(); //메시지 송신 자원
                        reMsg = socket.getInputStream();    //메시지 수신 자원

                        //메시지 송신
                        String msg = "EndBusCheck/" + nodeid +","+ routeid;
                        sendMsg.write(msg.getBytes());
                        Log.d("보낸 메시지", msg);

                        //메시지 수신
                        ObjectInputStream in = new ObjectInputStream(reMsg);
                        String data="";
                        reMsg = socket.getInputStream();
                        byte[] reBuffer = new byte[100];
                        reMsg.read(reBuffer);
                        data = new String(reBuffer);
                        data = data.trim();
                        Log.d("받아온 메시지", data);
                        String[] parts = data.split(","); //split (토큰라이저, char 보다 단순하게 자르기 편한 메서드라서)
                        String temp = parts[0];
                        check = Integer.parseInt(parts[1]); // check=0 이면 false, 1이면 true
                        if(check==1){
                            mVibe.vibrate(pattern,0);
                            /*Intent intent = new Intent(MyService.this, RestartService.class);
                            intent.setAction(RestartService. ACTION_RESTART_PERSISTENTSERVICE);
                            PendingIntent sender = PendingIntent.getBroadcast(MyService.this, 0, intent, 0);

                            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 0, 10*1000, sender);*/

                        }else{
                            Log.d("아직ㄴㄴ", Integer.toString(check));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }
        @Override
        public void onProviderEnabled(String provider)
        {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }//메인 CLASS 끝


    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };
    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Serviceintent = intent;
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        //UserRoute에서 받아온값
        nodeid = intent.getStringExtra("nodeid");
        routeid = intent.getStringExtra("routeid");
        Destinationlatitude = intent.getDoubleExtra("Destinationlatitude",0);
        Destinationlongitude= intent.getDoubleExtra("Destinationlongitude",0);
        Log.d("onStartCommand", nodeid+","+routeid+","+Destinationlatitude+","+Destinationlongitude);
        Toast.makeText(getBaseContext(),  nodeid+","+routeid+","+Destinationlatitude+","+Destinationlongitude, Toast.LENGTH_LONG).show();


        initializeLocationManager();
        Toast.makeText(getBaseContext(), "목적 정류장 지정 완료", Toast.LENGTH_LONG).show();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
        return START_STICKY;
    }
    @Override
    public void onCreate()
    {
        mVibe = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        Log.e(TAG, "onCreate");
    }
    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }
    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }
}