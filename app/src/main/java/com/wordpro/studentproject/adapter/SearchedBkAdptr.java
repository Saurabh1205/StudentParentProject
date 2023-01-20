package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.SearchBkModel;

import java.util.ArrayList;

public class SearchedBkAdptr extends RecyclerView.Adapter<SearchedBkAdptr.ViewHolder> {

    Context context;
    ArrayList<SearchBkModel.BookSearchDetailsBean> searchDetailsArrayList;

    public SearchedBkAdptr(Context context, ArrayList<SearchBkModel.BookSearchDetailsBean> searchDetailsArrayList) {
        this.context = context;
        this.searchDetailsArrayList = searchDetailsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtbookTitle.setText(searchDetailsArrayList.get(position).getBOOKTITLE());
        holder.txtBookTitle.setText(searchDetailsArrayList.get(position).getBOOKTITLE());
        holder.txtPublication.setText(searchDetailsArrayList.get(position).getPUBLICATIONNAME());
        holder.txtAuthor.setText(searchDetailsArrayList.get(position).getAUTHORNAME());
        holder.txtISBNNo.setText("ISBN No : "+searchDetailsArrayList.get(position).getBOOKTITLEID());

    }

    @Override
    public int getItemCount() {
        return searchDetailsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtbookTitle,txtAuthor,txtPublication,txtBookTitle,txtISBNNo;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface type_faceHeading = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Regular.ttf");
            txtbookTitle=(TextView)itemView.findViewById(R.id.txtbookTitle);
            txtbookTitle.setTypeface(type_faceHeading);
            txtAuthor=(TextView)itemView.findViewById(R.id.txtAuthor);
            txtAuthor.setTypeface(type_faceContent);
            txtPublication=(TextView)itemView.findViewById(R.id.txtPublication);
            txtPublication.setTypeface(type_faceContent);
            txtBookTitle=(TextView)itemView.findViewById(R.id.txtBookTitle);
            txtBookTitle.setTypeface(type_faceContent);
            txtISBNNo=(TextView)itemView.findViewById(R.id.txtISBNNo);
            txtISBNNo.setTypeface(type_faceContent);

        }
    }
}