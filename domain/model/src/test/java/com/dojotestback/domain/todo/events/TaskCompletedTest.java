package com.dojotestback.domain.todo.events;

import com.dojotestback.domain.todo.TaskToDo;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskCompletedTest {

    @Test
    public void shouldHasName() {
        final TaskCompleted taskCompleted = new TaskCompleted(TaskToDo.builder().build(), new Date());
        assertThat(taskCompleted.name()).isEqualTo(TaskCompleted.EVENT_NAME);
    }

}