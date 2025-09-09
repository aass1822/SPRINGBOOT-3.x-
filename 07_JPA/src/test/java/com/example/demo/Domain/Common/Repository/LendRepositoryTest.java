package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import com.example.demo.Domain.Common.Entity.Lend;
import com.example.demo.Domain.Common.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LendRepositoryTest {

    @Autowired
    private LendRepository lendRepository;
    @Autowired
    private LendRepository userRepository;
    @Autowired
    private LendRepository bookRepository;


    @Test
    public void t1(){
        // SELECT ALL
//        List<Lend> list = lendRepository.findAll();
//        list.forEach(System.out::println);

        // INSERT
        User user = userRepository.findById("user1").get();
        Book book = bookRepository.findById(1L).get();

        Lend lend = Lend.builder()
                .id(null)
                .user(user)
                .book(book)
                .build();
        lendRepository.save(lend);
    }

}