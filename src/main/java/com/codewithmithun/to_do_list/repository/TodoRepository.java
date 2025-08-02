package com.codewithmithun.to_do_list.repository;

import com.codewithmithun.to_do_list.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

}
