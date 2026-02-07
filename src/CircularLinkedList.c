// Nombre:
// DNI:
// Fuente:

#include "CircularLinkedList.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

//// BEGIN (A)
struct CircularLinkedList *CircularLinkedList_new (void) {
	struct CircularLinkedList *new = malloc(sizeof(struct CircularLinkedList));
	if(new!=NULL){
		new->p_last=NULL;
		new->size=0;
	}
	return new;
}
//// END (A)

static struct Node *CircularLinkedList_newNode(int element, struct Node *p_next) {
	struct Node *new = malloc(sizeof (struct Node));
	new->element = element;
	new->p_next = p_next;
	return (new);
}

//// BEGIN (B)
void CircularLinkedList_insert (struct CircularLinkedList * p_list , int element ) {
	if(p_list==NULL){
		return;
	}
	struct Node* p_node = CircularLinkedList_newNode(element, NULL);
	if(p_list->size==0){
		p_list->p_last= p_node;
		p_node->p_next = p_node;
		p_list->size++;
		return;
	}

	if(p_list->p_last->element<element){
		p_node->p_next=p_list->p_last->p_next;
		p_list->p_last->p_next = p_node;
		p_list->p_last = p_node;
		p_list->size++;
	}else{
		struct Node* p_current = p_list->p_last->p_next; //First element
		struct Node* p_previous = p_list->p_last;

		if(p_current->element>element){
			p_node->p_next = p_list->p_last->p_next;
			p_list->p_last->p_next = p_node;
			p_list->size++;
			return;
		}

		do {
			p_previous = p_current;
			p_current = p_current->p_next;
		
		}while (p_current->element<element && p_current!=p_list->p_last);
		//while elemento del actual > elemento y actual no sea ultimo
		p_previous->p_next=p_node;
		p_node->p_next=p_current;
		p_list->size++;
	}
	
}
//// END (B)


//// BEGIN (C)
void CircularLinkedList_remove (struct CircularLinkedList * p_list , size_t index) {
	if(p_list==NULL|| p_list->size==0 || index>=p_list->size){
		return;
	}
	// 1 element list 
	if(p_list->size==1){
		free(p_list->p_last);
		p_list->p_last=NULL;
		p_list->size--;
		return;
	}
	// remove first element
	if(index==0){
		struct Node* p_remove = p_list->p_last->p_next;
		p_list->p_last->p_next = p_remove->p_next;
		free(p_remove);
		p_list->size--;
		return;
	}
	struct Node* p_current = p_list->p_last->p_next; //starting point (index 0)
	struct Node* p_previous = p_list->p_last;

	for(size_t i=0; i<index; i++){
		p_previous=p_current;
		p_current=p_current->p_next;
	}

	// remove last element 
	if(p_current==p_list->p_last){
		p_list->p_last = p_previous;
	}
	p_previous->p_next=p_current->p_next;
	free(p_current);
	p_list->size--;

}
//// END (C)


//// BEGIN (D)
void CircularLinkedList_print (struct CircularLinkedList * p_list ) {
	struct Node* p_current = p_list->p_last->p_next;//p_first

	do {
	printf("%d", p_current->element);
	p_current=p_current->p_next;

	}while (p_current!=p_list->p_last);
	printf("%d", p_list->p_last->element);
	printf("\n");

}
//// END (D)


//// BEGIN (E)
void CircularLinkedList_free (struct CircularLinkedList ** p_p_list ) {
	if(p_p_list==NULL || *p_p_list==NULL){
		return;
	}
	struct CircularLinkedList* p_list = *p_p_list;
	struct Node* p_current = p_list->p_last->p_next; //first element
	struct Node* p_next;

	while (p_current!=p_list->p_last){
		p_next=p_current->p_next;
		free(p_current);
		p_current=p_next;
	}
	free(p_current); //last element
	free(p_list);
	*p_p_list=NULL;


}
//// END (E)


//// BEGIN (F)
bool CircularLinkedList_equals (struct CircularLinkedList * p_list1 , struct
CircularLinkedList * p_list2 ) {

}
//// END (F)
