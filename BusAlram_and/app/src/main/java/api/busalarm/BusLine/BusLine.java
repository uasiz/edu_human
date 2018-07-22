package api.busalarm.BusLine;

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

import api.busalarm.BusArrival.BusArrival;

import api.busalarm.R;
import api.busalarm.dto.RouteorderDto;

public class BusLine extends AppCompatActivity {

    Button reset;
    private ListView BusLineList;

    ListAdapter adapter;
    ArrayList<RouteorderDto> ObjectList = new ArrayList<>();

    String BusID;   //처음에 받아와서 서버에 전달해줄 값
    String selStation;  //클릭된 리스트값을 다음 액티비티에 전달

    OutputStream sendMsg;
    InputStream reMsg;
    private Socket socket;  //소켓생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_line); //새로 만든 xml레이아웃 파일
        setTitle("BusLine");

        reset = (Button) findViewById(R.id.reset);
        BusLineList = (ListView)findViewById(R.id.BusStop);

        adapter = new ListAdapter();

        //새로고침 버튼클릭 이벤트
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(BusLine.this, BusLine.this.getClass());
                intent.putExtra("SelectedBusid",BusID);
                BusLine.this.finish();
                BusLine.this.startActivity(intent);
            }
        });
        //리스트 클릭 이벤트
        BusLineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //selStation=adapter.mItems.get(position).getNumber();
                selStation=ObjectList.get(position).nodeid;
                Log.d("선택한 버스정류장",selStation);
                Intent intent = new Intent(getApplicationContext(), BusArrival.class);
                intent.putExtra("selStation",selStation);

                startActivity(intent);
                onStop();
            }
        });
    }

    @Override
    protected  void onStart(){
        super.onStart();
        Intent intent = getIntent();
        BusID = intent.getStringExtra("SelectedBusid");
        ListSet();
        BusLineList.setAdapter(adapter);
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
                    String msg = "RouteRoute/"+BusID;
                    sendMsg.write(msg.getBytes());
                    Log.d("버스루트아이디",BusID);

                    //메시지 수신
                    ObjectInputStream inS = new ObjectInputStream(reMsg);
                    for(;;) {
                        Object a = inS.readObject();
                        if(a instanceof RouteorderDto) {
                            RouteorderDto ord1 = (RouteorderDto) a;
                        }else if(a instanceof ArrayList) {
                            ObjectList = (ArrayList) a;
                            break;
                        }
                    }

                    for(int i=0; i<ObjectList.size(); i++){
                        if(ObjectList.get(i).check){
                            Log.d("true",ObjectList.get(i).nodeord);  //1~41
                            adapter.addList(ContextCompat.getDrawable(getApplicationContext(), R.drawable.busicon), ObjectList.get(i).nodeord, ObjectList.get(i).nodenm);
                        }else{
                            adapter.addList(ObjectList.get(i).nodeord, ObjectList.get(i).nodenm);
                        }
                    }
                }catch (IOException e) {
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