package com.ricardobove.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ricardobove.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id);

    /*   @Query(value = "SELECT t FROM Task t WHERE t.user.id = :user_id" )
    List<Task> findUserId(@Param "user_id" Long id);*/
  
    /*@Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
    List<Task> findByUser_id(@Param "id" Long id); */
    
}