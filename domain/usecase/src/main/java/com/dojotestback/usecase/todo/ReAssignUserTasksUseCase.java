package com.dojotestback.usecase.todo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import com.dojotestback.domain.todo.gateway.TaskToDoRepository;

import static com.dojotestback.domain.todo.TaskToDoOperations.setPendingToReAssign;

@RequiredArgsConstructor
public class ReAssignUserTasksUseCase {

    private final TaskToDoRepository tasks;

    public Mono<Void> markUserTaskAsPendingToReAssign(String userId) {
        return tasks.findAllUserOpenTasks(userId)
            .map(setPendingToReAssign())
            .as(tasks::saveAll);
    }
}
