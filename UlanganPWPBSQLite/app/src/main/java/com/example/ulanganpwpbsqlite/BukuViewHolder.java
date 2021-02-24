package com.example.ulanganpwpbsqlite;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BukuViewHolder extends RecyclerView.ViewHolder {
    public TextView nama_buku, penulis, penerbit;
    public ImageView deleteBuku;
    public ImageView editBuku;

    public BukuViewHolder(View view){
        super(view);
        nama_buku = (TextView)view.findViewById(R.id.ctxtnama_buku);
        penulis = (TextView)view.findViewById(R.id.ctxtpengarang);
        penerbit = (TextView)view.findViewById(R.id.ctxtpenerbit);
        deleteBuku = (ImageView)view.findViewById(R.id.imgdelete);
        editBuku = (ImageView)view.findViewById(R.id.imgedit);
    }
}
