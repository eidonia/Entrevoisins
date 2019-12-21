package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();

        Neighbour neigh = new Neighbour(54, "Bastien", "loremipsum.com");
        Neighbour neigh2 = new Neighbour(55, "Julie", "loremipsum.com");
        Neighbour neigh3 = new Neighbour(56, "Anthony", "loremipsum.com");

        neigh.setFavorite(true);
        neigh2.setFavorite(false);
        neigh3.setFavorite(true);
        service.getNeighboursFavorite().add(neigh);
        service.getNeighboursFavorite().add(neigh3);
        service.getNeighbours().add(neigh);
        service.getNeighbours().add(neigh2);
        service.getNeighbours().add(neigh3);
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertFalse(neighbours.contains(expectedNeighbours));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void deleteNeighbourFavWithSuccess() {
        Neighbour neighbour = service.getNeighboursFavorite().get(0);
        service.deleteNeighbourFav(neighbour);
        assertFalse(service.getNeighboursFavorite().contains(neighbour));


    }

    @Test
    public void FavoriteNeighbourDelIfNeighDel(){
        Neighbour neighbour = service.getNeighbours().get(0);
        neighbour.setFavorite(true);
        List<Neighbour> favNeigh = service.getNeighboursFavorite();
        favNeigh.add(neighbour);
        assertTrue(service.getNeighboursFavorite().contains(neighbour));
        service.deleteNeighbour(neighbour);
        assertFalse(service.getNeighbours().contains(neighbour));
        assertFalse(service.getNeighboursFavorite().contains(neighbour));


    }


    @Test
    public void DeleteFavButNotNeigh(){
        Neighbour neighbour = service.getNeighbours().get(0);
        neighbour.setFavorite(true);
        List<Neighbour> favNeigh = service.getNeighboursFavorite();
        favNeigh.add(neighbour);
        assertTrue(service.getNeighboursFavorite().contains(neighbour));
        favNeigh.remove(neighbour);
        assertFalse(favNeigh.contains(neighbour));
        assertTrue(service.getNeighbours().contains(neighbour));
    }

    @Test
    public void checkNeighbourFavorites(){
        List<Neighbour> favNeigh = service.getNeighboursFavorite();
        assertTrue(favNeigh.size()>0);
    }


}
