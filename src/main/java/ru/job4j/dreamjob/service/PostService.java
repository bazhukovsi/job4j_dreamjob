package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostDBStore;

import java.util.Collection;

@Service
@ThreadSafe
public class PostService {

    private final PostDBStore postDBStore;

    public PostService(PostDBStore postDBStore) {
        this.postDBStore = postDBStore;
    }

    public Collection<Post> findAll() {
        return postDBStore.findAll();
    }

    public Post findById(int id) {
        return postDBStore.findById(id);
    }

    public void add(Post post) {
        postDBStore.add(post);
    }

    public void update(Post post) {
        postDBStore.update(post);
    }

}
