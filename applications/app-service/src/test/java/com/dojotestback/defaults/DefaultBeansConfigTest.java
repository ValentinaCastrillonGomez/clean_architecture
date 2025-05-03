package com.dojotestback.defaults;

import static org.assertj.core.api.Assertions.*;


import com.dojotestback.domain.todo.TaskToDo;
import com.dojotestback.domain.todo.events.TaskAssigned;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Date;

public class DefaultBeansConfigTest {

    private DefaultBeansConfig config = new DefaultBeansConfig();

    @Test
    public void taskToDoRepositoryShouldHaveNoError() {
        assertThat(config.taskToDoRepository()).isNotNull();
        assertThat(config.taskToDoRepository().findAll()).isNotNull();
        assertThat(config.taskToDoRepository().save(TaskToDo.builder().build())).isNotNull();
        assertThat(config.taskToDoRepository().findById("2")).isNotNull();
        assertThat(config.taskToDoRepository().saveAll(Flux.empty())).isNotNull();
        assertThat(config.taskToDoRepository().findAllUserOpenTasks("3")).isNotNull();
    }

    @Test
    public void eventsGatewayShouldHaveNoError() {
        assertThat(config.eventsGateway()).isNotNull();
        assertThat(config.eventsGateway().emit(new TaskAssigned(TaskToDo.builder().build(), new Date()))).isNotNull();
    }

    @Test
    public void userGateway() {
        assertThat(config.userGateway()).isNotNull();
        assertThat(config.userGateway().findById("2")).isNotNull();
    }

    @Test
    public void userScoreGateway() {
        assertThat(config.userScoreGateway()).isNotNull();
        assertThat(config.userScoreGateway().addPointsToUserScore("1", 12)).isNotNull();
    }
}