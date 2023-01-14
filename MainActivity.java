package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button kick1;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//타이틀제거
        dialog.setContentView(R.layout.rent);//다이얼로그

        kick1 = findViewById(R.id.kick1);

        kick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rentDialog();
            }
        });

    }

    public void rentDialog(){
        dialog.show();//일단 다이얼로그 띄움

        //버튼 찾아주기
        Button cancel = dialog.findViewById(R.id.cancel);
        Button scan = dialog.findViewById(R.id.scan);


        //취소버튼 이벤트 구현
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                dialog.dismiss();//다이얼로그 닫힘

            }
        });

        //스캔버튼 이벤트 구현
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트로 넘겨줄 QR 스캔화면
                Intent intent = new Intent(MainActivity.this, ScanGR.class);
                startActivity(intent);


            }
        });






    }


}