package com.openclassrooms.entrevoisins.neighbour_page;

import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Test;

import static org.junit.Assert.*;

public class NeighbourPageTest {

    @Test
    public void onCreate() {
    }

    @Test
    public void checkIsFavorite() {
        Neighbour neighbour = new Neighbour(1, "Bastien", "loremipsum.com");
        neighbour.setFavorite(true);
        assertTrue(neighbour.isFavorite());
        neighbour.setFavorite(false);
        assertFalse(neighbour.isFavorite());
    }
}