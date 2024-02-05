package com.todo.list.controller;

import com.todo.list.service.ToDoService;
import com.todo.list.vo.ToDoVo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ToDoController {

    private final ToDoService todoservice;

    // Todo 등록
    @PostMapping("/todos/save")
    public String createJsonTodo(@RequestBody @Valid ToDoForm form, BindingResult bindingResult){
        log.info("Post : Todo Save");

        // 하단 - 요청 파라미터 validation 체크
        return validation(form, bindingResult);
    }

    // Todo 목록
    //@GetMapping("/todos/{orderState}")
    @GetMapping("/todos/{orderState}")
    public List<ToDoVo> list(@PathVariable("orderState") Boolean orderState, @RequestParam String sort){

        log.info("Get : Todos List");

        Map<String, Object> params = new HashMap<>();
        params.put("orderState", orderState);
        params.put("sort", sort);

        return todoservice.findTodos(params);
    }

    // Todo 완료 상태 업데이트
    @PutMapping("/todos/{id}")
    public String updateTodo(
            @PathVariable("id") Long id,
            @RequestBody UpdateTodoRequest request
    ){
        log.info("Put : Todo update");

        todoservice.updateTodoComplted(id, request.isCompleted());

        ToDoVo findTodo = todoservice.findOne(id);

        if(request.isCompleted() == findTodo.isCompleted()){
            return "ok";
        } else {
            return "fail";
        }
    }

    // Todo 삭제(DB 업데이트)
    @PutMapping("/todos/delete/{id}")
    public String deleteTodo(
            @PathVariable("id") Long id
    ){
        log.info("Delete : Todo Delete");

        todoservice.updateTodoUseYn(id);

        ToDoVo findTodo = todoservice.findOne(id);

        if(findTodo.getUseYn().equals("N")){
            return "ok";
        } else {
            return "fail";
        }
    }

    @PutMapping("/todos/clear")
    public String clearAllTodo(){
        log.info("Clear : Todo All Clear");

        int result = todoservice.updateTodoAllClear();

        if(result > 0){
            return "ok";
        } else {
            return "fail";
        }
    }

    // 요청 파라미터 validation 체크
    private String validation(@Valid @RequestBody ToDoForm form, BindingResult bindingResult) {

        // hasErrors : error의 유무를 판단
        if(bindingResult.hasErrors()) {
            return "todo error";
        }

        ToDoVo todo = new ToDoVo();

        todo.setItem(form.getItem());
        todo.setCompleted(form.isCompleted());
        todo.setDate(form.getDate());
        todo.setTime(form.getTime());
        todo.setWriteDate(LocalDateTime.now());
        todo.setUpdateDate(LocalDateTime.now());

        todoservice.save(todo);

        return "ok";
    }

    @Data
    static class UpdateTodoRequest{
        private Long id;
        @NotEmpty
        private boolean completed;

    }
}
