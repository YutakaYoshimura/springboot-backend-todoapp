package com.example.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.todoapp.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "SELECT * FROM todos WHERE delflg = false ORDER BY date", nativeQuery = true)
    List<Todo> findAllOrderByDate();

    @Query(value = "SELECT * FROM todos WHERE category = ?1 AND delflg = false ORDER BY date", nativeQuery = true)
    List<Todo> findByCategoryOrderByDate(int category);

    @Query(value = "SELECT MAX(id) FROM todos ORDER BY id", nativeQuery = true)
    Long findMaxId();

    @Query(value = "SELECT * FROM todos WHERE DATE_FORMAT(date, '%Y%m%d') BETWEEN ?1 AND ?2 ORDER BY date", nativeQuery = true)
    List<Todo> findByDateBetween(String strFrom, String strTo);

}
