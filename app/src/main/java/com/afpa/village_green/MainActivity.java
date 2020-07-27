package com.afpa.village_green;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //private TextView textViewResult;
    private ImageView imageview;
    private TextView tvDesign;
    private TextView tvDesc;
    private TextView tvPrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        String idClick = String.valueOf(ListActivity.EXTRA_MESSAGE);

        //Toast.makeText( getBaseContext(), idClick, Toast.LENGTH_LONG ).show();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Liaison avec la vue
        //textViewResult = findViewById(R.id.textView_result);
        imageview = findViewById(R.id.imageview);
        tvDesign = findViewById(R.id.tvDesign);
        tvDesc = findViewById(R.id.tvDesc);
        tvPrix = findViewById(R.id.tvPrix);

        Log.d("@url", "https://dev.amorce.org/hans/Village_Green/ci/index.php/api/detailProduit/" + idClick + "/");

        // Instanciation d'un objet Retrofit qui prend en paramètre l'url de départ et le convertisseur qui va créer nos objets locaux.
        Retrofit retrofit = new Retrofit.Builder()
                // pour la connexion à l'API
                .baseUrl("https://dev.amorce.org/hans/Village_Green/ci/index.php/api/detailProduit/" + idClick + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Puisque nous ne pouvons pas instancier directement une interface, nous devons faire créer par Retrofit le PlaceHolder et l'appeler avec un Call.
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        // Activation du Call.
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){
                    tvDesign.setText("Code "+response.code());
                    tvDesc.setText("Code "+response.code());
                    tvPrix.setText("Code "+response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts){
                    tvDesign.append(post.getText());
                    tvDesc.append(post.getDescription_produit());
                    tvPrix.append(String.valueOf(post.getPrix_vente_produit()) + " €");
                    //imageview.setImageResource(R.drawable.ibanez_sept_cordes);

                    String url = "https://dev.amorce.org/hans/Village_Green/ci/img/" + post.getLien_photo_produit();
                    // Picasso est un plug-in permettant d'aller chercher les url's des photos dans la bd et de les afficher.
                    Picasso.get().load(url).into(imageview);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvDesign.setText(t.getMessage());
            }
        });
        /*
        Nous ne pouvons pas lancer directement une requetes car android nous enverrait une erreur de thread.
        De ce fait nous utilisons la méthode enqueue. Cette méthode doit implémenter deux méthodes: onResponse et onFailure (réponse ou échec).
        De plus, une réponse peut aussi être un code erreur d'où le if(!response.isSuccessful()){...}
        */
    }
}
