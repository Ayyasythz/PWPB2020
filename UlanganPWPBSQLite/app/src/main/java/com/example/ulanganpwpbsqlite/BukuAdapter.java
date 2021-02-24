package com.example.ulanganpwpbsqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BukuAdapter extends RecyclerView.Adapter<BukuViewHolder> implements Filterable {
    private Context context;
    private ArrayList<DataBuku> listDataBuku;
    private ArrayList<DataBuku> mArrayList;

    private DataHelper mDatabase;

    public BukuAdapter(Context context, ArrayList<DataBuku> dataBukus){
        this.context = context;
        this.listDataBuku = dataBukus;
        this.mArrayList = dataBukus;
        mDatabase = new DataHelper(context);
    }


    @Override
    public BukuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_buku, parent,false);
        return new BukuViewHolder(view);
    }

    @Override
    public void onBindViewHolder( BukuViewHolder holder, int position) {
        final DataBuku dataBuku = listDataBuku.get(position);

        holder.nama_buku.setText(dataBuku.getNama_buku());
        holder.penulis.setText(dataBuku.getPengarang());
        holder.penerbit.setText(dataBuku.getPengarang());

        holder.editBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(dataBuku);
            }
        });

        holder.deleteBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.delete(dataBuku.getId());

                ((Activity)context).finish();
                context.startActivity(((Activity)context).getIntent());
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()){
                    listDataBuku = mArrayList;
                }
                else {
                    ArrayList<DataBuku> filteredList = new ArrayList<>();

                    for(DataBuku dataBuku : mArrayList){
                        if (dataBuku.getNama_buku().toLowerCase().contains(charString)){
                            filteredList.add(dataBuku);
                        }
                    }
                    listDataBuku = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listDataBuku;
                return  filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listDataBuku = (ArrayList<DataBuku>)filterResults.values;
                notifyDataSetChanged();;

            }
        };
    }

    @Override
    public int getItemCount() {
        return listDataBuku.size();
    }

    private void editTaskDialog(final DataBuku dataBuku){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.form_add, null);

        final EditText namaBukuField = (EditText)subView.findViewById(R.id.edtnama_buku);
        final EditText penulisField = (EditText)subView.findViewById(R.id.edtpengarang);
        final EditText penerbitField = (EditText)subView.findViewById(R.id.edtpenerbit);

        if (dataBuku != null){
            namaBukuField.setText(dataBuku.getNama_buku());
            penulisField.setText(String.valueOf(dataBuku.getPengarang()));
            penerbitField.setText(String.valueOf(dataBuku.getPenerbit())        );
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Data Buku");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT BUKU", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                final String nama_buku = namaBukuField.getText().toString();
                final String penulis = penulisField.getText().toString();
                final String penerbit = penerbitField.getText().toString();

                if (TextUtils.isEmpty(nama_buku)){
                    Toast.makeText(context, "Ada Kesalahan. Perikasa lagi Input anda!!", Toast.LENGTH_LONG).show();
                }
                else {
                    mDatabase.update(new DataBuku(dataBuku.getId(), nama_buku, penulis,penerbit));

                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
