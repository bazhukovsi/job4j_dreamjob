package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.Collection;

@Service
public class PostService {

    private final PostStore postStore = PostStore.instOf();

    public Collection<Post> findAll() {
        return postStore.findAll();
    }

    public void save(Post post) {
        postStore.save(post);
    }

    public Post findById(int id) {
        return postStore.findById(id);
    }

}
