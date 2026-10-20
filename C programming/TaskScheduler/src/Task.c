//
// Pepe Gallardo, Data Structures, UMA.
//

#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "Task.h"
#include "test/unit/UnitTest.h"

struct Task *Task_new(unsigned int id, const char name[], unsigned int quantum)
{
  if(strlen(name) > MAX_NAME_LEN){
    return NULL;
  }
  struct Task *newTask = (struct Task *)malloc(sizeof(struct Task));
  if (newTask == NULL){
    return NULL;
  }
  newTask->id = id;
  strcpy(newTask->name, name);
  newTask->quantum = quantum;

  return newTask;
}

void Task_free(struct Task **p_p_task)
{
if(p_p_task==NULL||*p_p_task==NULL){
  return;
}
  free(*p_p_task);
  *p_p_task = NULL;
}

struct Task *Task_copyOf(const struct Task *p_task)
{
  if(p_task==NULL){
    return NULL;
  }
  
  return Task_new(p_task->id, p_task->name, p_task->quantum);;
}

void Task_print(const struct Task *p_task)
{
  // already implemented
  assert(p_task != NULL && "Task_print: NULL p_task");
  printf("Task(ID: %u, Name: %s, Quantum: %u)", p_task->id, p_task->name, p_task->quantum);
}
