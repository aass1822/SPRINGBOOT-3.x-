package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder // 객체를 생성하는 방식 중 하나
public class PersonDto {
    private String name;
    private  int age;
    private String addr;
}
