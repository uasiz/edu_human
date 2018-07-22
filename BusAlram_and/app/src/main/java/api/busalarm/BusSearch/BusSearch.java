package api.busalarm.BusSearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import api.busalarm.BusLine.BusLine;
import api.busalarm.dto.RouteDto;
import api.busalarm.R;

public class BusSearch extends AppCompatActivity {

    EditText input;
    Button searchBtn;
    ListView SearchList;

    ListAdapter adapter;
    ArrayList<RouteDto> ObjectList = new ArrayList<>();
    String data; // RouteDirection/ + 서버에 전달할 버스번호, SelectedBus로 다음 인텐트에 전달할 값


    OutputStream sendMsg;
    InputStream reMsg;
    private Socket socket;  //소켓생성


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_search); //새로 만든 xml레이아웃 파일
        setTitle("BusSearch");

        input = (EditText) findViewById(R.id.inputNum);
        searchBtn = (Button) findViewById(R.id.search);
        SearchList = (ListView) findViewById(R.id.SearchList);

        adapter = new ListAdapter();

        //버튼클릭 이벤트
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = "RouteDirection/" + input.getText().toString(); //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
                adapter.clearItem();
                sending(data);
                SearchList.setAdapter(adapter);
            }
        });

        //리스브튜 클릭 이벤트
        SearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //data=list.get(position);
                data = ObjectList.get(position).routeid;
                Intent intent = new Intent(getApplicationContext(), BusLine.class);
                intent.putExtra("SelectedBusid", data);
                startActivity(intent);
                onStop();
            }
        });

    }

    public void sending(final String msg){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("58.74.90.3", 6666);

                    sendMsg = socket.getOutputStream();
                    sendMsg.write(msg.getBytes());

                    reMsg = socket.getInputStream();    //메시지 수신 자원
                    ObjectInputStream inS = new ObjectInputStream(reMsg);/////////////////////////////////////////////////////////////ERROR!
                    for(;;) {
                        Object a = inS.readObject();
                        if(a instanceof RouteDto) {
                            RouteDto ord1 = (RouteDto) a;
                        }else if(a instanceof ArrayList) {
                            ObjectList = (ArrayList) a;
                            break;
                        }
                    }
                    Log.d("전달 객체 갯수 : ", Integer.toString(ObjectList.size()));
                    for(int i=0; i<ObjectList.size(); i++){
                        adapter.addList(ObjectList.get(i).routeno, ObjectList.get(i).endnodenm);
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