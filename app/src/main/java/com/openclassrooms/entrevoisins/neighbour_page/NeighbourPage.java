package com.openclassrooms.entrevoisins.neighbour_page;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavoriteFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourPage extends AppCompatActivity {

    @BindView(R.id.textName) TextView textName;
    @BindView(R.id.gridNom) TextView gridNom;
    @BindView(R.id.textPhone) TextView textPhone;
    @BindView(R.id.textMail) TextView textMail;
    @BindView(R.id.textWebSite) TextView textWebSite;
    @BindView(R.id.textTitreAbout) TextView textTitreAbout;
    @BindView(R.id.textAbout) TextView textAbout;
    @BindView(R.id.imageAvatar) ImageView imageAvatar;
    @BindView(R.id.buttFav) FloatingActionButton buttFav;
    @BindView(R.id.buttonRetour) Button buttonRetour;

    List<Neighbour> listNeighbour;
    NeighbourApiService apiNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_page);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int itemPosition = intent.getIntExtra("ID", 0);

        apiNeighbour = DI.getNeighbourApiService();
        listNeighbour = apiNeighbour.getNeighbours();

        Neighbour neighbour = listNeighbour.get(itemPosition);

       textName.setText(neighbour.getName());
       textPhone.setText("+336425148");
       textMail.setText("your@yours.com");
       textWebSite.setText("openclassrooms.com");
       textTitreAbout.setText("A propos de moi");
       textAbout.setText(R.string.lorem_ipsum);
       Glide.with(NeighbourPage.this).load(neighbour.getAvatarUrl()).into(imageAvatar);

       gridNom.setText(neighbour.getName());

       if (!neighbour.isFavorite()){
           buttFav.setImageResource(R.drawable.ic_star_border_white_24dp);
       }else {
           buttFav.setImageResource(R.drawable.ic_star_white_24dp);
       }

       buttFav.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (!neighbour.isFavorite()){
                   buttFav.setImageResource(R.drawable.ic_star_white_24dp);
                   neighbour.setFavorite(true);
                   Toast.makeText(NeighbourPage.this, "favoris : " + neighbour.isFavorite(), Toast.LENGTH_LONG).show();
                   apiNeighbour.getNeighboursFavorite().add(neighbour);
               }else {
                   buttFav.setImageResource(R.drawable.ic_star_border_white_24dp);
                   neighbour.setFavorite(false);
                   apiNeighbour.getNeighboursFavorite().remove(neighbour);

               }

           }
       });

       buttonRetour.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               NeighbourPage.this.finish();
           }
       });


    }
}
