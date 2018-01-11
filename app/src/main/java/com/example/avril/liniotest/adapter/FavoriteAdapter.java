package com.example.avril.liniotest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.avril.liniotest.R;
import com.example.avril.liniotest.model.ProductCharacteristics;
import com.example.avril.liniotest.view.MainActivity;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by avril on 09/01/18.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Context context;
    private List<ProductCharacteristics> myList;

    public FavoriteAdapter(MainActivity mainActivity, List<ProductCharacteristics> myList) {
        this.myList = myList;
        this.context= mainActivity;
    }

    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_recycler_view,
                null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.ViewHolder holder, int position) {

        //Promociones de Producto
        //Linio Plus
        if(myList.get(position).getLinioPlusLevel() == 1){
            holder.linioPlus.setVisibility(View.VISIBLE);
        }else{
            holder.linioPlus.setVisibility(View.GONE);
        }

        //Linio Plus 48
        if(myList.get(position).getLinioPlusLevel() == 2){
            holder.linioPlus48.setVisibility(View.VISIBLE);
        }else{
            holder.linioPlus48.setVisibility(View.GONE);
        }

        //Condition Type refurbished
        if(myList.get(position).getConditionType().equals("refurbished")){
            holder.refurbished.setVisibility(View.VISIBLE);
        }else{
            holder.refurbished.setVisibility(View.GONE);
        }

        //Condition Type new
        if(myList.get(position).getConditionType().equals("new")){
            holder.newProduct.setVisibility(View.VISIBLE);
        }else{
            holder.newProduct.setVisibility(View.GONE);
        }

        //FreeShipping
        if(myList.get(position).isFreeShipping()){
            holder.freeshipping.setVisibility(View.VISIBLE);
        }else{
            holder.freeshipping.setVisibility(View.GONE);
        }

        //Imported
        if(myList.get(position).isImported()){
            holder.internationalProduct.setVisibility(View.VISIBLE);
        }else{
            holder.internationalProduct.setVisibility(View.GONE);
        }

        //Imagen de producto
        String url = myList.get(position).getImage();
        Picasso.with(context).load(url).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        ImageView newProduct;
        ImageView linioPlus;
        ImageView internationalProduct;
        ImageView linioPlus48;
        ImageView freeshipping;
        ImageView refurbished;
        LinearLayout promotions;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgProduct = itemView.findViewById(R.id.imgProduct);
            this.newProduct = itemView.findViewById(R.id.newProduct);
            this.linioPlus  =  itemView.findViewById(R.id.linioPlus);
            this.internationalProduct = itemView.findViewById(R.id.internationalProduct);
            this.linioPlus48   = itemView.findViewById(R.id.linioPlus48);
            this.freeshipping = itemView.findViewById(R.id.freeshipping);
            this.refurbished = itemView.findViewById(R.id.refurbished);
            this.promotions = itemView.findViewById(R.id.promotions);
        }
    }
}
