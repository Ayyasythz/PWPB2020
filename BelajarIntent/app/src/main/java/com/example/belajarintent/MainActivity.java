package com.example.belajarintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button explicitintent;
    Button explicitintent2;
    Button implicitintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explicitintent = (Button)findViewById(R.id.expilicitintent);
        explicitintent.setOnClickListener(this);
        implicitintent = (Button)findViewById(R.id.implicitintent);
        implicitintent.setOnClickListener(this);
        explicitintent2 = (Button)findViewById(R.id.expilicitintent2);
        explicitintent2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.expilicitintent:
                Intent explicit = new Intent(MainActivity.this, IntentActivity.class );
                startActivity(explicit);
                break;
            case R.id.implicitintent:
                Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.w3school.com"));
                startActivity(implicit);
                break;
            case R.id.expilicitintent2:
                Intent explicit2 = new Intent(MainActivity.this, Intent2Activity.class);
                startActivity(explicit2);
                break;
            default:
                break;

        }
    }
}