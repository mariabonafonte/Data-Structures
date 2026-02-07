
#include "CircularLinkedList.h"
#include <stdio.h>
#include <assert.h>

int main(void) {
	struct CircularLinkedList * p_list1 = CircularLinkedList_new ();
	//CircularLinkedList_print(p_list1); //ERROR: el puntero a la lista no es valido
	CircularLinkedList_insert (p_list1 , 3);
	CircularLinkedList_insert (p_list1 , 1);
	CircularLinkedList_insert (p_list1 , 5);
	CircularLinkedList_insert (p_list1 , 2);
	CircularLinkedList_insert (p_list1 , 4);
	CircularLinkedList_insert (p_list1 , 6);
	
	printf ("List1 despues de insertar elementos: " );
	CircularLinkedList_print (p_list1 ); // 1 2 3 4 5 6
	
	CircularLinkedList_remove (p_list1 , 5);
	CircularLinkedList_remove (p_list1 , 1);
	CircularLinkedList_remove (p_list1 , 0);
	
	printf ("List1 despues de eliminar elementos: " );
	CircularLinkedList_print (p_list1 ); // 3 4 5
	
	struct CircularLinkedList * p_list2 = CircularLinkedList_new ();
	
	CircularLinkedList_insert (p_list2 , 5);
	CircularLinkedList_insert (p_list2 , 7);
	CircularLinkedList_insert (p_list2 , 3);
	CircularLinkedList_insert (p_list2 , 77);
	CircularLinkedList_insert (p_list2 , 3);
	
	printf ("List2 despues de insertar elementos: " );
	CircularLinkedList_print (p_list2 ); // 3 4 5
	
	if(CircularLinkedList_equals (p_list1 , p_list2 )) {
		printf ("Listas son iguales\n" );
	} else {
		printf ("Listas no son iguales\n" );
	}
	
	CircularLinkedList_free (&p_list1 );
	CircularLinkedList_free (&p_list2 );
	
	printf ("Listas liberadas\n" );
}
