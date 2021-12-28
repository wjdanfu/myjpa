package com.example.myjpa.member.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor@Builder@ToString
public class MemberDto {
    private Long id;
    private String name;
    private String nickName;
    private int age;
    private String address;
    private String description;
    private String create_by;
}
