package com.dojotestback.domain.todo.events;

import com.dojotestback.domain.todo.TaskToDo;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskAssignedTest {

    @Test
    public void shouldHasName() {
        final TaskAssigned taskAssigned = new TaskAssigned(TaskToDo.builder().build(), new Date());
        assertThat(taskAssigned.name()).isEqualTo(TaskAssigned.EVENT_NAME);
    }
}