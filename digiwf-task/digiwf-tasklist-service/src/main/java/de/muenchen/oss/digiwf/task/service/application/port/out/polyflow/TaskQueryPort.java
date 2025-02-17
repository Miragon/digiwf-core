package de.muenchen.oss.digiwf.task.service.application.port.out.polyflow;

import de.muenchen.oss.digiwf.task.service.domain.PageOfTasks;
import io.holunda.polyflow.view.Task;
import io.holunda.polyflow.view.auth.User;
import de.muenchen.oss.digiwf.task.service.domain.PagingAndSorting;

import java.time.LocalDate;

/**
 * Port for searching tasks.
 */
public interface TaskQueryPort {
  /**
   * Retrieves tasks for current user.
   *
   * @param user             user.
   * @param query            additional query.
   * @param tag              additional tag of the task.
   * @param followUp         date to indicate that the tasks with follow-up date later than given date are excluded.
   * @param pagingAndSorting paging and sorting info.
   * @return page of tasks.
   */
  PageOfTasks getTasksForCurrentUser(User user, String query, String tag, LocalDate followUp, PagingAndSorting pagingAndSorting);

  /**
   * Retrieves tasks visible via user's group.
   *
   * @param user             user.
   * @param tag              task tag.
   * @param assignedUserId   user id of assigned user.
   * @param includeAssigned  include tasks assigned to someone.
   * @param query            additional query.
   * @param pagingAndSorting paging and sorting info.
   * @return page of tasks.
   */
  PageOfTasks getTasksForCurrentUserGroup(User user, String query, String tag, String assignedUserId, boolean includeAssigned, PagingAndSorting pagingAndSorting);

  /**
   * Load task by id.
   *
   * @param user   user
   * @param taskId task id.
   * @return a task.
   * @throws TaskNotFoundException if no task is available or access is permitted.
   */
  Task getTaskByIdForCurrentUser(User user, String taskId) throws TaskNotFoundException;
}
