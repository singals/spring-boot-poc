package com.thoughtworks.post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/post")
@ExposesResourceFor(Post.class)
public class PostController {

    private static Log logger = LogFactory.getLog(PostController.class);
    private Map<UUID, Post> posts = new HashMap();
    private final EntityLinks entityLinks;

    public PostController(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @PostMapping
    public ResponseEntity<Resource<Post>> createPost(@RequestBody @Valid Post post,
                                               HttpServletResponse response) {

        UUID uuid = UUID.randomUUID();
        posts.put(uuid, post);

        Resource<Post> postResource = new Resource(post);
        postResource.add(entityLinks.linkToSingleResource(Post.class, uuid.toString()));

        return new ResponseEntity<Resource<Post>>(postResource, HttpStatus.OK);
    }

    @GetMapping()
    public Post getPostById() {
        return null;
    }
}
