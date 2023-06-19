package com.julius.pinjambukuandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private ArrayList<Book> bookList ;
    private Context context;
    private RvCallback callback;

    private String type;

    public RvAdapter(ArrayList<Book> bookList, Context context,String type) {
        this.bookList=bookList;
        this.context=context;
        this.type=type;
    }
    public void setCallBack(RvCallback callback){
        this.callback=callback;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        // deklarasi komponen apa saja yang bisa diatur
        public TextView txtTitle, txtTahun,txtAuthor,txtPenerbit,txtHalaman,txtStatus;
        public Button btnPinjam;

        public ViewHolder(View Itemview){
            super(Itemview);
            txtTitle=Itemview.findViewById(R.id.txtTitle);
            txtTahun=Itemview.findViewById(R.id.txtTahun);
            txtAuthor=Itemview.findViewById(R.id.txtAuthor);
            txtPenerbit=Itemview.findViewById(R.id.txtPenerbit);
            txtHalaman=Itemview.findViewById(R.id.txtHalaman);
            txtStatus=Itemview.findViewById(R.id.txtStatus);

            btnPinjam = Itemview.findViewById(R.id.btnPinjam);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_book_list,parent,false);
        return new ViewHolder(view);
        // menyiapkan layout mana yang mau dipakek
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // ngisi data pada layout
        Book book = bookList.get(position);
        holder.txtTitle.setText(book.getJudul());
        holder.txtTahun.setText(book.getTahun());
        holder.txtAuthor.setText(book.getAuthor());
        holder.txtPenerbit.setText(book.getPenerbit());
        holder.txtHalaman.setText(book.getHalaman());
        holder.txtStatus.setText(book.getStatus()+"");
        if (type.equals("pinjam")){
            holder.btnPinjam.setEnabled(false);
        }
        else{
            holder.btnPinjam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback!=null){
                        callback.onitemClick(position);
                    }

                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
