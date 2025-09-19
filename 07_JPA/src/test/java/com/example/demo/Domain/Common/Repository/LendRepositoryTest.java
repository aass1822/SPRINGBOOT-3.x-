package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import com.example.demo.Domain.Common.Entity.Lend;
import com.example.demo.Domain.Common.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LendRepositoryTest {

    @Autowired
    private LendRepository lendRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void t1(){
        //SELECT ALL
//        List<Lend> list =  lendRepository.findAll();
//        list.forEach(System.out::println);

        //INSERT
        User user = userRepository.findById("user1").get();
        Book book = bookRepository.findById(1L).get();

        Lend lend = Lend.builder()
                .id(null)
                .user(user)
                .book(book)
                .build();
        lendRepository.save(lend);


    }

    @Test
    public void t2(){
        // user1 bookCode 1L 대여
        // user1 bookCode 2L 대여
        // user2 bookCode 3L 대여
        // user3 bookCode 4L 대여
        User user = userRepository.findById("user1").get();
        Book book = bookRepository.findById(1L).get();

        Lend lend = Lend.builder()
                .id(null)
                .user(user)
                .book(book)
                .build();
        lendRepository.save(lend);



        // user1 bookCode 1L 대여
        // user1 bookCode 2L 대여
        // user2 bookCode 3L 대여
        // user3 bookCode 4L 대여
        User user2 = userRepository.findById("user1").get();
        Book book2 = bookRepository.findById(2L).get();

        Lend lend2 = Lend.builder()
                .id(null)
                .user(user2)
                .book(book2)
                .build();
        lendRepository.save(lend2);



        User user3 = userRepository.findById("user2").get();
        Book book3= bookRepository.findById(3L).get();

        Lend lend3 = Lend.builder()
                .id(null)
                .user(user3)
                .book(book3)
                .build();
        lendRepository.save(lend3);



        User user4 = userRepository.findById("user3").get();
        Book book4 = bookRepository.findById(4L).get();

        Lend lend4 = Lend.builder()
                .id(null)
                .user(user4)
                .book(book4)
                .build();
        lendRepository.save(lend4);
    }


}