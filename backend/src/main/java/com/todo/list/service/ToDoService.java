package com.todo.list.service;

import com.todo.list.repo.ToDoRepository;
import com.todo.list.vo.ToDoVo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository todorepository;

    // Todo 작성
    /*
    * Transactional: 트랜잭션에 대한 읽기 전용 및 롤백 조건 설정 가능 , 관리자 지정 가능
    * ex) 송금 -> 수취 -> 이체 순서로 처리할 save라는 메서드가 있다면, @Transactional을 메서드 위에 설정했을때
    * 3단계 로직들은 하나의 트랜잭션으로 묶어서 처리된다. (Transactional 어노테이션이 적용되어있기 때문)
    * => 어디서든 예외 상황이 발생하더라도 모두 롤백 처리 되기 때문에 데이터 일관성이 유지된다.
    * */
    @Transactional
    public Long save(ToDoVo todo){
        todorepository.save(todo);

        return todo.getId();
    }

    // Todo 전체 조회
    public List<ToDoVo> findTodos(Map<String, Object> params){

        return todorepository.findAll(params);
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
