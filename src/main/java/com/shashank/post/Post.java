package com.shashank.post;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
