package api.busalarm.BusArrival;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import api.busalarm.R;
import api.busalarm.UserRoute.UserRoute;
import api.busalarm.dto.RouteorderDto;
import api.busalarm.dto.XmlStationsBusListDto;


public class BusArrival extends AppCompatActivity {

    Button reset;
    private ListView InfoList;

    ListAdapter adapter;
    ArrayList<XmlStationsBusListDto> ObjectList = new ArrayList<>();

    String selStation;  //처음에 받아와서 서버에 전달해줄 값

    OutputStream sendMsg;
    InputStream reMsg;
    private Socket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_arrival); //새로 만든 xml레이아웃 파일
        setTitle("BusArrival");

        reset = (Button) findViewById(R.id.reset);
        InfoList = (ListView)findViewById(R.id.ina);

        adapter = new ListAdapter();

        //새로고침 버튼클릭 이벤트
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(BusArrival.this, BusArrival.this.getClass());
                intent.putExtra("selStation",selStation);
                BusArrival.this.finish();
                BusArrival.this.startActivity(intent);
            }
        });


        //리스트 클릭 이벤트
        InfoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if(adapter.mItems.get(position).clickable){
                    String SelectedBus = ObjectList.get(position).routeid;
                    String StationName = ObjectList.get(position).nodenm;

                    Intent intent = new Intent(getApplicationContext(), UserRoute.class);
                    intent.putExtra("SelectedBus",SelectedBus);
                    intent.putExtra("StartStation",selStation);
                    intent.putExtra("StartStationName",StationName);
                    startActivity(intent);
                    onStop();
                }
            }
        });
    }   //OnCreate 끝

    @Override
    protected  void onStart(){
        super.onStart();
        Intent intent = getIntent();
        selStation = intent.getStringExtra("selStation");
        ListSet();
        InfoList.setAdapter(adapter);
    }

    public void ListSet(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("58.74.90.3", 6666);
                    sendMsg = socket.getOutputStream(); //메시지 송신 자원
                    reMsg = socket.getInputStream();    //메시지 수신 자원

                    //메시지 송신
                    String msg = "GiveMeBus/"+selStation;
                    sendMsg.write(msg.getBytes());
                    Log.d("전달한 버스정류장",selStation);

                    //메시지 수신
                    ObjectInputStream inS = new ObjectInputStream(reMsg);
                    for(;;) {
                        Object a = inS.readObject();
                        if(a instanceof XmlStationsBusListDto) {
                            XmlStationsBusListDto ord1 = (XmlStationsBusListDto) a;
                        }else if(a instanceof ArrayList) {
                            ObjectList = (ArrayList) a;
                            break;
                        }
                    }

                    Log.d("true",Integer.toString(ObjectList.size()));  //1~41
                    for(int i=0; i<ObjectList.size(); i++){
                        //if(ObjectList.get(i).check){
                        int time=Integer.parseInt(ObjectList.get(i).arrtime);
                        //60초보다 작을경우
                        if(time<60){
                            adapter.addList(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ride), ObjectList.get(i).routeno,Integer.toString(time)+"초");
                            //60초보다 클 경우
                        }else{
                            time=time/60;
                            //2분 이하일 경우
                            if(time<=2){
                                adapter.addList(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ride), ObjectList.get(i).routeno,Integer.toString(time)+"분");
                                //2분 초과일 경우
                            }else{
                                adapter.addList(ObjectList.get(i).routeno, Integer.toString(time)+"분");
                            }
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception e) {
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

}