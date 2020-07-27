package com.afpa.village_green;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    public static int EXTRA_MESSAGE;
    ListView lv;
    List<Post> posts = new ArrayList<>();

    public void sendMessage(View view, long id) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(String.valueOf(EXTRA_MESSAGE), id);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.listview);

        Retrofit retrofit = new Retrofit.Builder()
                //pour la connexion à l'API
                .baseUrl("https://dev.amorce.org/hans/Village_Green/ci/index.php/api/listeProduit/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){
                    Log.d("err", "err api");
                    return;
                }

                posts = response.body();

                getAdapter();
                //Toast.makeText( getBaseContext(), "liste chargée", Toast.LENGTH_LONG ).show();

                }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("err", "err callback");
            }
        });


        /*
        Nous ne pouvons pas lancer directement une requetes car android nous enverrait une erreur de thread.
        De ce fait nous utilisons la méthode enqueue. Cette méthode doit implémenter deux méthodes: onResponse et onFailure (réponse ou échec).
        De plus, une réponse peut aussi être un code erreur d'où le if(!response.isSuccessful()){...}
        */


    }

    public void  getAdapter() {
        ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(this, android.R.layout.simple_list_item_1,posts);
        lv.setAdapter(adapter);
        AdapterView.OnItemClickListener listPairedClickItem = null;

        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView < ? > parent, View view,
                                    int position, long id) {
                //Toast.makeText( getBaseContext(), "id : " + posts.get(position).getId_produit(), Toast.LENGTH_LONG ).show();
                EXTRA_MESSAGE = posts.get(position).getId_produit();
                sendMessage(view, posts.get(position).getId_produit());
            }
        } );


    }
}
