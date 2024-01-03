package com.ricardobove.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardobove.todosimple.models.User;
import com.ricardobove.todosimple.repositories.TaskRepository;
import com.ricardobove.todosimple.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

  
    public User findUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
               "Usuário não encontrado! id:" + id + "Tipo: " + User.class.getName() 
        ) );
    }

    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findUserById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){
       findUserById(id);
       
       try {
        this.userRepository.deleteById(id);
       } catch (Exception e) {
        throw new RuntimeException
        ("Não é possivel excluir pois há entidades relacionadas!");
       }
    }
}
