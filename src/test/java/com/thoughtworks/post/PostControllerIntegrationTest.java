package com.thoughtworks.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

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
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String entityBody = entity.getBody();
        assertThat(entityBody).startsWith(
                "{\"title\":\"Test Title\",\"content\":\"Test Content\"");
        assertThat(entityBody).contains("_links\":{\"self\":{\"href\"");

        int idStartIndex = entityBody.lastIndexOf("/") + 1;
        String id = entityBody.substring(idStartIndex, entityBody.length() - 4);

        assertNotNull(postController.getPosts().get(UUID.fromString(id)));
    }

}
