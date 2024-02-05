package com.todo.list.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoForm {

    /* NotEmpty : null, ""을 허용하지 않습니다. " "는 허용합니다. */
    @NotEmpty(message = "내용은 필수입니다.")
    private String item;

    private String date;

    private boolean completed;

    private String time;
}
