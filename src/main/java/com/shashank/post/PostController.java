package com.shashank.post;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Controller
@RequestMapping("/post")
public class PostController {

    @Getter
    Map<UUID, Post> posts = new HashMap<>();

    @PostMapping
    public ResponseEntity createPost(@RequestBody @Valid Post post) throws URISyntaxException {

        UUID uuid = UUID.randomUUID();
        posts.put(uuid, post);

        return ResponseEntity.created(new URI("/post/"+uuid.toString())).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getPostById(@PathVariable UUID id) {
        ResponseEntity response = ResponseEntity.notFound().build();
        Post post = posts.get(id);

        if (Objects.nonNull(post)) {
            response = ResponseEntity.ok(post);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity getAllPosts() {
        return ResponseEntity.ok(posts.values());
    }
}
