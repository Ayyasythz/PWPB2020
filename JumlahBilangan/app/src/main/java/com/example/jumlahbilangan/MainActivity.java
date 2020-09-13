package com.example.jumlahbilangan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText bill1;
    private EditText bill2;
    private EditText jumlah;
    private Button btnHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initEvent();
    }

    private void initUI(){
        bill1 = (EditText) findViewById(R.id.edtBill1);
        bill2 = (EditText) findViewById(R.id.edtBill2);
        jumlah = (EditText) findViewById(R.id.edtJumlah);
        btnHasil = (Button) findViewById(R.id.btnHasil);

    }

    private void initEvent(){
        btnHasil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                hitungJumlah();
            }
        });
    }

    private void hitungJumlah(){
        double angka1 = Double.parseDouble(bill1.getText().toString());
        double angka2 = Double.parseDouble(bill2.getText().toString());
        double total = angka1 + angka2;
        jumlah.setText(total+"");
    }
}