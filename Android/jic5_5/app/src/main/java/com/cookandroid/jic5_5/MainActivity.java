package com.cookandroid.jic5_5;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2;
    Button add, sub, mul, div;
    TextView result1;
    String num1, num2;
    Integer re;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.n0, R.id.n1, R.id.n2, R.id.n3, R.id.n4, R.id.n5, R.id.n6, R.id.n7, R.id.n8, R.id.n9};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("테이블 레이아웃 계산기");

        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        result1 = (TextView) findViewById(R.id.Result);

        add.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                num1 = ed1.getText().toString();
                num2 = ed2.getText().toString();
                re = Integer.parseInt(num1) + Integer.parseInt(num2);
                result1.setText("계산 결과 : " + re.toString());
                return false;

            }
        });

        sub.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                num1 = ed1.getText().toString();
                num2 = ed2.getText().toString();
                re = Integer.parseInt(num1) - Integer.parseInt(num2);
                result1.setText("계산 결과 : " + re.toString());
                return false;

            }
        });

        mul.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                num1 = ed1.getText().toString();
                num2 = ed2.getText().toString();
                re = Integer.parseInt(num1) * Integer.parseInt(num2);
                result1.setText("계산 결과 : " + re.toString());
                return false;

            }
        });

        div.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                num1 = ed1.getText().toString();
                num2 = ed2.getText().toString();
                re = Integer.parseInt(num1) / Integer.parseInt(num2);
                result1.setText("계산 결과 : " + re.toString());
                return false;

            }
        });

        for(i=0; i<numBtnIDs.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }

        for(i=0; i<numBtnIDs.length; i++) {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(ed1.isFocusable()==true) {
                        num1 = ed1.getText().toString()+numButtons[index].getText().toString();
                        ed1.setText(num1);
                    } else if (ed2.isFocusable()==true) {
                        num2 = ed2.getText().toString()+numButtons[index].getText().toString();
                        ed2.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "먼저 에디트 텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }}
}

