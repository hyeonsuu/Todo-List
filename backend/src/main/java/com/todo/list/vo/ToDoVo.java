package com.todo.list.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicInsert
public class ToDoVo {

    /*
    @Entity : 클래스에 선언하면 해당 클래스는 JPA가 관리한다. (DB 맵핑)
    @Getter, @Setter : getter , setter 메소드를 생성한다.
    @DynamicInsert : Insert 구문 생성 시점에 null이 아닌 컬럼들만 포함한다. (= null이면 컬럼의 지정된 default 값을 적용시키며 Insert)
    * */
    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    private String item;

    private String date;

    private boolean completed;

    private String time;

    private LocalDateTime writeDate;

    private LocalDateTime updateDate;

    @Column(columnDefinition = "varchar(1) default 'Y'")
    private String useYn;

}
