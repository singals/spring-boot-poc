package com.shashank.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostController postController;

    @Before
    public void setUp() throws Exception {
        postController.getPosts().clear();
    }

    private final Post post = new Post("Test Title", "Test Content");

    @Test
    public void shouldCreatePost() throws Exception {
        ResponseEntity<String> entity = restTemplate.postForEntity("/post", post, String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
        String entityBody = entity.getBody();
        assertNull(entityBody);

        List<String> location = entity.getHeaders().get("Location");
        assertThat(location.size(), is(1));

        UUID postId = postController.getPosts().keySet().stream().findFirst().get();
        assertThat(location.get(0), is("/post/"+postId.toString()));
    }

    @Test
    public void shouldGetPostById() {
        UUID postId = UUID.randomUUID();
        postController.getPosts().put(postId, post);

        ResponseEntity<Post> responseEntity = restTemplate.getForEntity("/post/" + postId.toString(), Post.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

        assertThat(responseEntity.getBody(), is(post));
    }

    @Test
    public void shouldReturnNotFoundOnGetPostByIdWhenPostDoesNotExist() {
        UUID postId = UUID.randomUUID();
        ResponseEntity<Post> responseEntity = restTemplate.getForEntity("/post/" + postId.toString(), Post.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void shouldReturnAllPosts() {
        UUID postId1 = UUID.randomUUID();
        UUID postId2 = UUID.randomUUID();
        Post post2 = new Post("Test Title2", "Test Content2");
        postController.getPosts().put(postId1, post);
        postController.getPosts().put(postId2, post2);

        Post[] forObject = restTemplate.getForObject("/post", Post[].class);
        List<Post> actualPosts = asList(forObject);

        assertThat(actualPosts.size(), is(2));
        assertThat(actualPosts, is(asList(post, post2)));
    }
}
