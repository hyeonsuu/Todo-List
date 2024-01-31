package com.todo.list.repo;

import com.todo.list.vo.ToDoVo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ToDoRepository {

    private final EntityManager em;

    // 작성
    public void save(ToDoVo todo){
        em.persist(todo);
    }

    // 단건 조회
    public ToDoVo findOne(Long id){
        return em.find(ToDoVo.class, id);
    }

    // 전체 조회
    public List<ToDoVo> findAll(boolean orderState){

        List<ToDoVo> todoList = null;
        if(orderState){
            todoList = em.createQuery(
                            "select t from ToDoVo t" +
                                    " where t.useYn = 'Y'" +
                                    " order by t.writeDate DESC ", ToDoVo.class)
                    .getResultList();
        } else {
            todoList = em.createQuery(
                            "select t from ToDoVo t" +
                                    " where t.useYn = 'Y'" +
                                    " order by t.writeDate ASC ", ToDoVo.class)
                    .getResultList();
        }

        return todoList;
    }

    // 전체 업데이트
    public int updateTodoAllClear() {
        return em.createQuery(
                        "update ToDoVo t " +
                                " set t.useYn = 'N' " +
                                " where t.useYn = 'Y'")
                .executeUpdate();
    }

}
