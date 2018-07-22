package api.busalarm.UserRoute;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import api.busalarm.R;
import api.busalarm.dto.RouteorderDto;

public class UserRoute extends AppCompatActivity{

    Button reset,stopLocation;
    private ListView Route;

    ListAdapter adapter;
    ArrayList<RouteorderDto> ObjectList = new ArrayList<>();

    String BusNumber;   //처음에 받아와서 서버에 전달해줄 값
    String StartStation;  //클릭된 리스트값을 다음 액티비티에 전달
    int StartStationNum;    //현재 클릭한 리스트뷰의 아이템이 출발 정류장보다 뒤의 정류장인지 확인하기 위한 출발 정류장 번호
    double Destinationlatitude;
    double Destinationlongitude;

    OutputStream sendMsg;
    InputStream reMsg;
    private Socket socket;  //소켓생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_line); //새로 만든 xml레이아웃 파일
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setTitle("UserRoute");

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    0 );
        }

        final Intent intent = getIntent();
        BusNumber = intent.getStringExtra("SelectedBus");
        StartStation = intent.getStringExtra("StartStation");
        reset = (Button) findViewById(R.id.reset);
        Route = (ListView) findViewById(R.id.BusStop);
        stopLocation = (Button) findViewById(R.id.stopLocation);

        adapter = new ListAdapter();
        Route.setAdapter(adapter);

        //새로고침 버튼클릭 이벤트
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Route.setAdapter(adapter);
            }
        });
        //위치정보중지 이벤트
        stopLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceintent = new Intent(getApplicationContext(), MyService.class);
                stopService(serviceintent);
            }
        });
        //리스트 클릭 이벤트
        Route.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(StartStationNum<position){
                    setDestinationDlg(ObjectList.get(position).nodeid, ObjectList.get(position).nodenm, ObjectList.get(position).routeid,  position);

                }else{
                    falseDestinationDlg();
                }

            }
        });


        Thread ListSet = new Thread() {
            public void run() {
                try {
                    socket = new Socket("58.74.90.3", 6666);
                    sendMsg = socket.getOutputStream(); //메시지 송신 자원
                    reMsg = socket.getInputStream();    //메시지 수신 자원

                    //메시지 송신
                    String msg = "RouteRoute/" + BusNumber;
                    sendMsg.write(msg.getBytes());

                    //메시지 수신
                    ObjectInputStream inS = new ObjectInputStream(reMsg);
                    for (; ; ) {
                        Object a = inS.readObject();
                        if (a instanceof RouteorderDto) {
                            RouteorderDto ord1 = (RouteorderDto) a;
                        } else if (a instanceof ArrayList) {
                            ObjectList = (ArrayList) a;
                            break;
                        }
                    }
                    for (int i = 0; i < ObjectList.size(); i++) {
                        if (ObjectList.get(i).nodeid.equals(StartStation)) {
                            adapter.addList(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ride), ObjectList.get(i).nodenm);
                            StartStationNum=i;
                        } else {
                            adapter.addList(ObjectList.get(i).nodenm);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ListSet.start();


    }//OnCreate 끝

    private void  setDestinationDlg(final String nodeid, final String nodenm, final String routeid, final int position) {
        AlertDialog.Builder setDestination = new AlertDialog.Builder(UserRoute.this);
        setDestination.setMessage("'"+nodenm+"'에서 하차하시겠습니까?").setCancelable(
                false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // '예' 선택시
                        setDestination("GiveMeStationTest/"+nodeid); //목적지 정류장 위치 받아오기

                        //서비스 시작
                        Intent serviceintent = new Intent(getApplicationContext(), MyService.class);
                        serviceintent.putExtra("nodeid", nodeid);
                        serviceintent.putExtra("routeid",routeid);
                        serviceintent.putExtra("Destinationlatitude",Destinationlatitude);
                        serviceintent.putExtra("Destinationlongitude",Destinationlongitude);
                        serviceintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startService(serviceintent);

                        adapter.getItem(position).setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.busicon));
                        Route.setAdapter(adapter);
                    }
                }).setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //'아니오' 선택시 다이얼로그 취소
                        dialog.cancel();
                    }
                });
        AlertDialog alert = setDestination.create();

        alert.setTitle("Title");
        alert.setIcon(R.drawable.busicon);
        alert.show();

    }

    private void falseDestinationDlg(){
        AlertDialog.Builder reSelect = new AlertDialog.Builder(UserRoute.this);
        reSelect.setTitle("출발지 이후의 정류장을 선택해주세요"); // AlertDialog.builder를 통해 Title text를 입력
        reSelect.setNegativeButton("OK", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Negative Button을 생성
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.dismiss(); "아니오" button이 받은 DialogInterface를 dismiss 하여 MainView로 돌아감
                dialog.cancel();
            }
        });
        AlertDialog dialog = reSelect.create(); // 위의 builder를 생성할 AlertDialog 객체 생성
        dialog.show(); // dialog를 화면에 뿌려 줌
    }

    //내리고자 하는 버스정류장 위치 요청
    public void setDestination(final String msg){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("58.74.90.3", 6666);
                    sendMsg = socket.getOutputStream(); //메시지 송신 자원
                    reMsg = socket.getInputStream();    //메시지 수신 자원

                    //메시지 송신
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
                    Destinationlatitude = Double.valueOf(parts[1]);
                    Destinationlongitude = Double.valueOf(parts[2]);
                    Log.d("longitude", Double.toString(Destinationlongitude));
                    Log.d("latitude", Double.toString(Destinationlatitude));

                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        try {
            t1.join();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    @Override
    protected void onStop() {  //앱 종료시 소켓 닫음
        super.onStop();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}