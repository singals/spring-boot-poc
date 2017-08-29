package com.thoughtworks.post;

import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/post")
@ExposesResourceFor(Post.class)
public class PostController {

    @Getter
    Map<UUID, Post> posts = new HashMap<>();

    private static Log logger = LogFactory.getLog(PostController.class);
    private final EntityLinks entityLinks;

    public PostController(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @PostMapping
    public ResponseEntity<Resource<Post>> createPost(@RequestBody @Valid Post post) {

        UUID uuid = UUID.randomUUID();
        posts.put(uuid, post);

        Resource<Post> postResource = new Resource(post);
        postResource.add(entityLinks.linkToSingleResource(Post.class, uuid.toString()));

        return new ResponseEntity<>(postResource, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Resource<Post>> getPostById(@PathVariable UUID id) {
        Post post = posts.get(id);
        Resource<Post> postResource = new Resource(post);
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (post != null) {
            postResource.add(entityLinks.linkToSingleResource(Post.class, id.toString()));
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(postResource, status);
    }

    @GetMapping
    public ResponseEntity<List<Resource<Post>>> getAllPosts() {
        Set<Map.Entry<UUID, Post>> entries = posts.entrySet();
        List<Resource<Post>> posts = entries.stream().map(entry -> {
            Post post = entry.getValue();
            Resource<Post> postResource = new Resource(post);
            postResource.add(entityLinks.linkToSingleResource(Post.class, entry.getKey().toString()));
            return postResource;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
