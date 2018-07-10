package com.shashank.post;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostController postController;

    @Test
    public void testThatAPostIsCreated() throws Exception {
        Post post = new Post("Test Title", "Test Content");
        ResponseEntity<String> entity = this.restTemplate.postForEntity("/post", post, String.class);
        assertThat(entity.getStatusCode(), Is.is(HttpStatus.CREATED));
        String entityBody = entity.getBody();
        assertNull(entityBody);

        List<String> location = entity.getHeaders().get("Location");
        assertThat(location.size(), Is.is(1));
        assertTrue(location.get(0).contains("/post/"));
    }

}
