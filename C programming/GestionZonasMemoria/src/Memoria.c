#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "Memoria.h"

ListasMemoria* crearListasMemoria(void) {
    ListasMemoria* lista = malloc(sizeof(ListasMemoria));
    assert(lista != NULL);
    lista->ocupadas = NULL;
    lista->borradas = NULL;

    return lista;
}

void insertarZona(ListasMemoria* lista, int id, int tamano) {
    ZonaMemoria* zona = malloc(sizeof(ZonaMemoria));
    assert(zona != NULL);
    zona->id = id;
    zona->tamano = tamano;

    if(lista->ocupadas != NULL){
        zona->siguiente = lista->ocupadas;
    }
    lista->ocupadas= zona; 
    
}


void eliminarZona(ListasMemoria* lista, int id) {
    ZonaMemoria* current = lista->ocupadas;
     ZonaMemoria* previous = NULL;

    while(current->id != id|| current->siguiente!=NULL){
        previous = current;
        current= current->siguiente;
    }

    previous->siguiente = current->siguiente;
    current->siguiente = lista->borradas;
    lista->borradas = current;

}

void liberarMemoria(ListasMemoria* lista) {
    ZonaMemoria* previous = NULL;
    ZonaMemoria* ocupada = lista->ocupadas;
    while(ocupada->siguiente != NULL){
        free(previous);
        previous=ocupada;
        ocupada=ocupada->siguiente;
    }
    free(ocupada);

    previous=NULL;
    ZonaMemoria* borrada = lista->borradas;
    while(borrada->siguiente != NULL){
        free(previous);
        previous=borrada;
        borrada=borrada->siguiente;
    }
    free(lista);
}
