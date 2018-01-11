package com.example.avril.liniotest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.avril.liniotest.R;
import com.example.avril.liniotest.model.Item;
import com.example.avril.liniotest.model.ProductCharacteristics;
import com.example.avril.liniotest.view.MainActivity;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Map;

/**
 * Created by avril on 10/01/18.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder>{

    private Context context;
    private List<Item> favorites;

    public CollectionAdapter(MainActivity mainActivity, List<Item> favorites) {
        this.favorites = favorites;
        this.context= mainActivity;
    }

    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_recycler_view,
                null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CollectionAdapter.ViewHolder holder, int position) {

        holder.collectionName.setText(favorites.get(position).getName());
        holder.collectionTotal.setText(String.valueOf(favorites.get(position).getProducts().size()));

        int j = 0;

        for (Map.Entry<String, ProductCharacteristics> entry : favorites.get(position).
                getProducts().entrySet()) {
            if (j == 0) {
                Picasso.with(context).load(entry.getValue().getImage()).into(holder.imgOne);
            }
            if (j == 1) {
                Picasso.with(context).load(entry.getValue().getImage()).into(holder.imgTwo);
            }
            if (j == 2) {
                Picasso.with(context).load(entry.getValue().getImage()).into(holder.imgThree);
            }
            if (j >= 3) break;
            j++;
        }
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgOne;
        ImageView imgTwo;
        ImageView imgThree;
        TextView collectionName;
        TextView collectionTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgOne = itemView.findViewById(R.id.collection_one);
            this.imgTwo = itemView.findViewById(R.id.collection_two);
            this.imgThree = itemView.findViewById(R.id.collection_three);
            this.collectionName = itemView.findViewById(R.id.collection_name_tv);
            this.collectionTotal = itemView.findViewById(R.id.collection_count_tv);
        }
    }
}
