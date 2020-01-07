package com.openclassrooms.entrevoisins.neighbour_page;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourPage extends AppCompatActivity {

    @BindView(R.id.gridNom)
    TextView gridNom;
    @BindView(R.id.textPhone)
    TextView textPhone;
    @BindView(R.id.textLoc)
    TextView textMail;
    @BindView(R.id.textWebSite)
    TextView textWebSite;
    @BindView(R.id.textTitreAbout)
    TextView textTitreAbout;
    @BindView(R.id.textAbout)
    TextView textAbout;
    @BindView(R.id.imageAvatar)
    ImageView imageAvatar;
    @BindView(R.id.buttFav)
    FloatingActionButton buttFav;
    @BindView(R.id.collapseTool)
    CollapsingToolbarLayout collapseTool;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    List<Neighbour> listNeighbour;
    NeighbourApiService apiNeighbour;
    Neighbour neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_page);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int itemPosition = intent.getIntExtra("ID", 0);

        apiNeighbour = DI.getNeighbourApiService();
        listNeighbour = apiNeighbour.getNeighbours();

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NeighbourPage.this.finish();
            }
        });


        neighbour = listNeighbour.get(itemPosition);

        collapseTool.setTitle(neighbour.getName());
        collapseTool.setContentScrimColor(getResources().getColor(R.color.colorPrimaryDark));
        textPhone.setText("+33 6 42 51 48 87");
        textMail.setText("5 rue Auguste Mounié Antony");
        textWebSite.setText("openclassrooms.com");
        textTitreAbout.setText("A propos de moi");
        textAbout.setText(R.string.lorem_ipsum);
        Glide.with(NeighbourPage.this).load(neighbour.getAvatarUrl()).into(imageAvatar);

        gridNom.setText(neighbour.getName());

        checkIsFavorite(neighbour);


        buttFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!neighbour.isFavorite()) {
                    buttFav.setImageResource(R.drawable.ic_star_white_24dp);
                    neighbour.setFavorite(true);
                    apiNeighbour.getNeighboursFavorite().add(neighbour);
                } else {
                    buttFav.setImageResource(R.drawable.ic_star_border_white_24dp);
                    neighbour.setFavorite(false);
                    apiNeighbour.getNeighboursFavorite().remove(neighbour);

                }

            }
        });
    }

    public void checkIsFavorite(Neighbour neighbour){
        if (!neighbour.isFavorite()){
            buttFav.setImageResource(R.drawable.ic_star_border_white_24dp);
        }else {
            buttFav.setImageResource(R.drawable.ic_star_white_24dp);
        }
    }
}
