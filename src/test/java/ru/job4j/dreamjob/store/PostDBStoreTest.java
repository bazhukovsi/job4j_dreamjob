package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class PostDBStoreTest {

    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", true, new City(1, "Петрозаводск"));
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = store.findById(1);
        post.setName("Sergey");
        post.setVisible(true);
        post.setCity(new City(1, "Петрозаводск"));
        store.update(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenFindbyIdCandidate() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = store.findById(1);
        assertThat("Sergey", is(post.getName()));
    }
}