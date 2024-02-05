package com.todo.list.repo;

import com.todo.list.vo.ToDoVo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ToDoRepository {

    /*
     EntityManager 란?
     - JPA를 사용하기 위해서는 Database 구조와 맵핑된 JPA Entity 들을 먼저 생성한다.
       그리고, 모든 JPA의 동작은 이 Entity들을 기준으로 돌아가게 되는데,
       이 때 Entity들을 관리하는 역할 -> EntityManager
    */

    private final EntityManager em;

    // 작성
    public void save(ToDoVo todo){
        // insert - 영속 컨텍스트 등록
        em.persist(todo);
    }

    // 단건 조회
    public ToDoVo findOne(Long id) {
        return em.find(ToDoVo.class, id);
    }

    // 전체 조회
    public List<ToDoVo> findAll(Map<String, Object> params){

        // orderState 가져오기
        Boolean orderState = (Boolean) params.get("orderState");
        String sort = (String) params.get("sort");

        List<ToDoVo> todoList = null;

        todoList = em.createQuery(
                        "select t from ToDoVo t" +
                                " where t.useYn = 'Y'" +
                                " order by t.writeDate " + sort, ToDoVo.class)
                .getResultList();

        return todoList;
    }

    /*
    * executeUpdate
    * 벌크성 수정 쿼리; 특정 하나가 아닌 다수의 데이터를 수정하는 쿼리
    * */
    // 전체 업데이트
    public int updateTodoAllClear() {
        return em.createQuery(
                        "update ToDoVo t " +
                                " set t.useYn = 'N' " +
                                " where t.useYn = 'Y'")
                .executeUpdate();
    }

}
