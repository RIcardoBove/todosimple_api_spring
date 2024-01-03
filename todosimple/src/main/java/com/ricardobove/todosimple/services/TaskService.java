package com.ricardobove.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardobove.todosimple.models.Task;
import com.ricardobove.todosimple.models.User;
import com.ricardobove.todosimple.repositories.TaskRepository;
import com.ricardobove.todosimple.services.UserService;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
            "Tarefa não encontrada! Id: " + id + "Tipo: " + Task.class.getName()
        ));
    }

    public Task create(Task obj){
        User user = this.userService.findUserById(obj.getUser().getId());
        obj.setId(0);
    }
    
}
