package com.todo.list.service;

import com.todo.list.repo.ToDoRepository;
import com.todo.list.vo.ToDoVo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository todorepository;

    // Todo 작성
    @Transactional
    public Long save(ToDoVo todo){
        todorepository.save(todo);

        return todo.getId();
    }

    // Todo 전체 조회
    public List<ToDoVo> findTodos(boolean orderState){
        return todorepository.findAll(orderState);
    }

    // Todo 단건 조회
    public ToDoVo findOne(Long todoId){
        return todorepository.findOne(todoId);
    }

    // Todo 완료 상태 수정
    @Transactional
    public void updateTodoComplted(Long id, boolean completed) {
        ToDoVo todo = todorepository.findOne(id);

        todo.setCompleted(completed);
    }

    // Todo 삭제(DB 업데이트)
    @Transactional
    public void updateTodoUseYn(Long id) {
        ToDoVo todo = todorepository.findOne(id);

        todo.setUseYn("N");
    }

    // Todo 전체 삭제(DB 업데이트)
    @Transactional
    public int updateTodoAllClear() {
        return todorepository.updateTodoAllClear();
    }
}
