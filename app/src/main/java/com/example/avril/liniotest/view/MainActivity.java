package com.example.avril.liniotest.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import com.example.avril.liniotest.R;
import com.example.avril.liniotest.adapter.CollectionAdapter;
import com.example.avril.liniotest.adapter.FavoriteAdapter;
import com.example.avril.liniotest.asyncTask.AsyncTask;
import com.example.avril.liniotest.constants.URLService;
import com.example.avril.liniotest.interfaces.ServiceCallback;
import com.example.avril.liniotest.model.Item;
import com.example.avril.liniotest.model.ProductCharacteristics;
import com.example.avril.liniotest.webClient.WSClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView collectionsRecyclerView;
    private RecyclerView favoritesRecyclerView;
    private FavoriteAdapter mAdapterFavorites;
    private CollectionAdapter mAdapterCollections;
    private TextView favoriteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Products Recyclerview
        favoritesRecyclerView  = findViewById(R.id.product_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        favoritesRecyclerView.setHasFixedSize(true);
        favoritesRecyclerView.setLayoutManager(gridLayoutManager);
        favoriteText = findViewById(R.id.favorites_txt);

        //Collections RecyclerView
        collectionsRecyclerView = findViewById(R.id.collection_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        collectionsRecyclerView.setHasFixedSize(true);
        collectionsRecyclerView.setLayoutManager(linearLayoutManager);

        //Obtener JSON
        getFavorites();

    }

    public void getFavorites() {
        //Lama al Asynctask
        AsyncTask task = new AsyncTask(URLService.FavoriteService, WSClient.GET, "", MainActivity.this);
        task.setmCallback(new ServiceCallback() {
            @Override
            public void onServiceCallback(Object o) {
                String result = (String) o;
                if (!result.equals("")) {
                    Gson gson = new Gson();
                    Type collectionType = new TypeToken<List<Item>>() {
                    }.getType();

                    //Deserealizar json
                    List<Item> favorite = gson.fromJson(result, collectionType);

                    if (favorite != null) {

                        ArrayList<ProductCharacteristics> productList = new ArrayList<ProductCharacteristics>();
                        for (Item i : favorite) {
                            for (Map.Entry<String, ProductCharacteristics> entry : i.getProducts().entrySet())
                            {
                                productList.add(entry.getValue());
                                //Header total de Favoritos
                                String size = " " + "(" + String.valueOf(productList.size()) + ")";
                                Log.v("size", size);
                                favoriteText.setText(getResources().getString(R.string.header_favorites)
                                        + size);
                            }
                        }
                        
                        //Enviar lista de productos al adaptador de Colecciones
                        mAdapterCollections = new CollectionAdapter(MainActivity.this, favorite);
                        collectionsRecyclerView.setAdapter(mAdapterCollections);

                        //Enviar lista de productos al adaptador de Favoritos
                        mAdapterFavorites = new FavoriteAdapter(MainActivity.this, productList);
                        favoritesRecyclerView.setAdapter(mAdapterFavorites);
                    }
                } else {

                }
            }
        });
        task.execute();
    }
}
