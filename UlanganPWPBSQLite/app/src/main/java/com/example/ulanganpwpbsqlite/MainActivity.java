package com.example.ulanganpwpbsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DataHelper mDatabase;
    private ArrayList<DataBuku> allDataBuku = new ArrayList<>();
    private BukuAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout flayout = (FrameLayout)findViewById(R.id.activity_to_do);

        RecyclerView dataBukuView = (RecyclerView)findViewById(R.id.product_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        dataBukuView.setLayoutManager(linearLayoutManager);
        dataBukuView.setHasFixedSize(true);
        mDatabase = new DataHelper(this);
        allDataBuku= mDatabase.listDataBuku();

        if (allDataBuku.size() > 0){
            dataBukuView.setVisibility(View.VISIBLE);
            mAdapter = new BukuAdapter(this, allDataBuku);
            dataBukuView.setAdapter(mAdapter);
        }
        else {
            dataBukuView.setVisibility(View.GONE);
            Toast.makeText(this, "Data Buku Kosong", Toast.LENGTH_LONG).show();
        }
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTaskDialog();
            }
        });

    }

    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.form_add, null);

        final EditText namaBukuField = (EditText)subView.findViewById(R.id.edtnama_buku);
        final EditText penulisField = (EditText)subView.findViewById(R.id.edtpengarang);
        final EditText penerbitField = (EditText)subView.findViewById(R.id.edtpenerbit);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Buku");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("ADD BUKU", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                final String nama_buku = namaBukuField.getText().toString();
                final String penulis = penulisField.getText().toString();
                final String penerbit = penerbitField.getText().toString();

                if(TextUtils.isEmpty(nama_buku)){
                    Toast.makeText(MainActivity.this, "Terjadi Kesalahan. Periksa Input", Toast.LENGTH_LONG).show();
                }
                else {
                    DataBuku newDataBuku = new DataBuku(nama_buku,penulis,penerbit);
                    mDatabase.insert(newDataBuku);

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(MainActivity.this, "Task Cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    private void search(SearchView searchView){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(mAdapter != null)
                    mAdapter.getFilter().filter(s);
                return true;
            }
        });
    }
}