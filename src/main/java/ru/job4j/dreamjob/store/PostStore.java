package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class PostStore {

    private static final AtomicInteger POST_ID = new AtomicInteger(4);
    private static final PostStore INST = new PostStore();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", false, new City()));
        posts.put(2, new Post(2, "Middle Java Job", false, new City()));
        posts.put(3, new Post(3, "Senior Java Job", false, new City()));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.getAndIncrement());
        }
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

}
