package ua.com.reactive.gr_404_reactive.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class User {

    private Integer id;
    private String firstname;
    private String lastname;
    private int age;

}
