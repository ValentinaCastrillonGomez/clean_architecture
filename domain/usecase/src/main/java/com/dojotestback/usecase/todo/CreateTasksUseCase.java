package com.dojotestback.usecase.todo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import com.dojotestback.domain.common.EventsGateway;
import com.dojotestback.domain.todo.TaskToDo;
import com.dojotestback.domain.todo.TaskToDoFactory;
import com.dojotestback.domain.todo.events.TaskCreated;
import com.dojotestback.domain.todo.gateway.TaskToDoRepository;

import static com.dojotestback.domain.common.UniqueIDGenerator.now;
import static com.dojotestback.domain.common.UniqueIDGenerator.uuid;

@RequiredArgsConstructor
public class CreateTasksUseCase {

    private final TaskToDoRepository tasks;
    private final EventsGateway eventBus;

    public Mono<TaskToDo> createNew(String name, String description) {
        return uuid()
            .flatMap(id -> TaskToDoFactory.createTask(id, name, description))
            .flatMap(tasks::save)
            .flatMap(task -> emitCreatedEvent(task).thenReturn(task));
    }

    private Mono<Void> emitCreatedEvent(TaskToDo task) {
        return now().flatMap(now -> eventBus.emit(new TaskCreated(task, now)));
    }

}
