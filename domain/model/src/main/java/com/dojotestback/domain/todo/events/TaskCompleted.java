package com.dojotestback.domain.todo.events;

import com.dojotestback.domain.todo.TaskToDo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.dojotestback.domain.common.Event;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class TaskCompleted implements Event {

    public static final String EVENT_NAME = "todoTasks.task.completed";
    private final TaskToDo task;
    private final Date date;

    @Override
    public String name() {
        return EVENT_NAME;
    }
}
