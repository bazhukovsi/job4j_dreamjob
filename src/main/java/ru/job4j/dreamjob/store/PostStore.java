package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final AtomicInteger POST_ID = new AtomicInteger(4);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private static final PostStore INST = new PostStore();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job"));
        posts.put(2, new Post(2, "Middle Java Job"));
        posts.put(3, new Post(3, "Senior Java Job"));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void save (Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.getAndIncrement());
        }
        posts.put(post.getId(), post);
    }
}
