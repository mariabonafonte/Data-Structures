
#ifndef MEMORIA_H
#define MEMORIA_H

typedef struct RMemoria ZonaMemoria;
struct RMemoria {
    int id;
    int tamano;
    ZonaMemoria* siguiente;
};

typedef struct RListasMemoria ListasMemoria; 
struct RListasMemoria{
    ZonaMemoria* ocupadas;
    ZonaMemoria* borradas;
};
/**
 * @brief Crea e inicializa un nuevo objeto ListasMemoria.
 * 
 * Esta función asigna memoria para un nuevo objeto ListasMemoria e inicializa
 * sus miembros con valores predeterminados. El llamador es responsable de liberar
 * la memoria asignada cuando ya no sea necesaria.
 * 
 * @return Un puntero al objeto ListasMemoria recién creado.
 */
ListasMemoria* crearListasMemoria(void);

/**
 * @brief Inserta una zona de memoria en la lista de memoria.
 * 
 * Esta función inserta una nueva zona de memoria con el ID y tamaño especificados en la lista de memoria dada.
 * 
 * @param lista Puntero a la lista de memoria donde se insertará la nueva zona.
 * @param id El identificador de la zona de memoria a insertar.
 * @param tamaño El tamaño de la zona de memoria a insertar.
 */
void insertarZona(ListasMemoria* lista, int id, int tamano);

/**
 * @brief Elimina una zona de memoria de la lista de memoria.
 * 
 * Esta función elimina la zona de memoria con el ID especificado de la lista de memoria dada.
 * 
 * @param lista Puntero a la lista de memoria donde se eliminará la zona.
 * @param id El identificador de la zona de memoria a eliminar.
 */
void eliminarZona(ListasMemoria* lista, int id);

/**
 * @brief Recorre las listas de memoria.
 * 
 * Esta función recorre las listas de memoria en la lista de memoria dada.
 * 
 * @param lista Puntero a la lista de memoria que se recorrerá.
 */
void recorrerListas(ListasMemoria* lista);

/**
 * @brief Libera la memoria asignada para una lista de memoria.
 * 
 * Esta función libera la memoria asignada para una lista de memoria y todas sus zonas de memoria.
 * 
 * @param lista Puntero a la lista de memoria que se liberará.
 */
void liberarMemoria(ListasMemoria* lista);


#endif // MEMORIA_H
