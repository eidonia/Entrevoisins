package com.openclassrooms.entrevoisins.neighbour_page;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class NeighbourPage extends AppCompatActivity {

    TextView textName, gridNom;
    ImageView imageAvatar;

    List<Neighbour> listNeighbour;
    NeighbourApiService apiNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_page);

        Intent intent = getIntent();
        int itemPosition = intent.getIntExtra("ID", 0);

        apiNeighbour = DI.getNeighbourApiService();
        listNeighbour = apiNeighbour.getNeighbours();

        Neighbour neighbour = listNeighbour.get(itemPosition);


       textName = findViewById(R.id.textName);
       textName.setText(neighbour.getName());
       imageAvatar = findViewById(R.id.imageAvatar);
       Glide.with(NeighbourPage.this).load(neighbour.getAvatarUrl()).into(imageAvatar);

       gridNom = findViewById(R.id.gridNom);
       gridNom.setText(neighbour.getName());


    }
}
