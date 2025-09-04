package com.example.demo.Dto;

import org.junit.jupiter.api.Test; // Test Import
import org.springframework.boot.test.context.SpringBootTest; // SpringBootTest 어노테이션

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonDtoTest {

    @Test
    public void t1(){
        PersonDto dto = new PersonDto("홍길동",50,"대구");
        System.out.println(dto);
    }

    @Test
    public void t2(){
        PersonDto dto = PersonDto.builder() // builder() 불러와서
                .age(20) // builder패턴 지정해서 객체 쉽게 만들수 있
                .name("티모")
                .build();
        System.out.println(dto);

    }

}