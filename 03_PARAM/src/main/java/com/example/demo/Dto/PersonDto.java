package com.example.demo.Dto;

import lombok.*;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder // 객체를 생성하는 방식 중 하나
@Component
public class PersonDto {
    private String name;
    private  int age;
    private String addr;
}
