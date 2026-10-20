//
// Pepe Gallardo, Data Structures, UMA.
//

#include <assert.h>
#include <stdlib.h>
#include <stdio.h>

#include "Scheduler.h"
#include "test/unit/UnitTest.h"

struct Node *Scheduler_new()
{
  struct Node *newNode = (struct Node *)malloc(sizeof(struct Node));
  newNode->p_next = NULL;// Circular linked list
  return newNode;
}

size_t Scheduler_size(const struct Node *p_last)
{
  if(p_last==NULL){
    return 0;
  }
  size_t count = 0;
  struct Node *p_first = p_last->p_next;
  struct Node *p_current = p_first;
  do
  {
    count++;
    p_current = p_current->p_next;
  }
  while (p_current != p_first);

  return count;
}

void Scheduler_clear(struct Node **p_p_last)
{
  // todo
}

struct Task *Scheduler_first(const struct Node *p_last)
{
  // todo
  return NULL;
}

void Scheduler_enqueue(struct Node **p_p_last, const struct Task *p_task)
{
  // todo
}

void Scheduler_dequeue(struct Node **p_p_last)
{
  // todo
}

void Scheduler_print(const struct Node *p_last)
{
  // already implemented
  printf("Scheduler(");
  if (p_last != NULL)
  {
    struct Node *p_first = p_last->p_next;
    struct Node *p_current = p_first;
    do
    {
      printf("\n  ");
      Task_print(&(p_current->task));
      p_current = p_current->p_next;
      printf(p_current != p_first ? "," : "\n");
    } while (p_current != p_first);
  }
  printf(")");
}
