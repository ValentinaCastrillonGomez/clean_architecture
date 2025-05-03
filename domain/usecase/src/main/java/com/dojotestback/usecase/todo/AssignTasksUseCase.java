package com.dojotestback.usecase.todo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import com.dojotestback.domain.common.EventsGateway;
import com.dojotestback.domain.common.ex.BusinessException.Type;
import com.dojotestback.domain.todo.TaskToDo;
import com.dojotestback.domain.todo.TaskToDoOperations;
import com.dojotestback.domain.todo.events.TaskAssigned;
import com.dojotestback.domain.todo.gateway.TaskToDoRepository;
import com.dojotestback.domain.user.User;
import com.dojotestback.domain.user.gateway.UserGateway;

import static reactor.core.publisher.Mono.error;
import static reactor.core.publisher.Mono.zip;
import static reactor.function.TupleUtils.function;
import static com.dojotestback.domain.common.UniqueIDGenerator.now;

@RequiredArgsConstructor
public class AssignTasksUseCase {

    private final TaskToDoRepository tasks;
    private final UserGateway users;
    private final EventsGateway eventBus;

    public Mono<Void> assignTask(String taskId, String userId){
        return zip(findTask(taskId), findUser(userId))
            .flatMap(function(TaskToDoOperations::assignToUser))
            .flatMap(tasks::save)
            .flatMap(this::emitAssignedEvent);
    }

    private Mono<Void> emitAssignedEvent(TaskToDo task) {
        return now().flatMap(now -> eventBus.emit(new TaskAssigned(task, now)));
    }

    private Mono<TaskToDo> findTask(String id){
        return tasks.findById(id).switchIfEmpty(error(Type.TASK_NOT_FOUND.defer()));
    }

    private Mono<User> findUser(String id){
        return users.findById(id).switchIfEmpty(error(Type.USER_NOT_EXIST.defer()));
    }
}
