#include "Memoria.h"
#include <stdio.h>


int main(void) {
    ListasMemoria* lista = crearListasMemoria();

    insertarZona(lista, 4, 200);
    insertarZona(lista, 7, 300);
    insertarZona(lista, 1, 100);
    insertarZona(lista, 6, 100);

    
    printf("Después de insertar zonas:\n");
    recorrerListas(lista);

    eliminarZona(lista, 4);

    printf("\nDespués de eliminar la zona con ID 4:\n");
    recorrerListas(lista);

    eliminarZona(lista, 1);

    printf("\nDespués de eliminar la zona con ID 1:\n");
    recorrerListas(lista);

    liberarMemoria(lista);
    printf("\nDespués de liberar memoria:\n");
    recorrerListas(lista);

    return 0;
}
