// Student's name:
// Student's group:
// Identity number (DNI if Spanish/passport if Erasmus):

package dataStructures.setChain;

import dataStructures.dictionary.Dictionary;
import dataStructures.dictionary.AVLDictionary;
import dataStructures.list.LinkedList;
import dataStructures.set.Set;
import dataStructures.set.HashSet;
import dataStructures.list.List;
import dataStructures.tuple.Tuple2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SetChain<T extends Comparable<? super T>> implements Iterable<T>{
  private Set<T> mempool;
  private final Dictionary<Integer, Set<T>> history;
  private int epochNum;

  /* *************************** */
  /* DON'T MODIFY THE CODE ABOVE */
  /* *************************** */

  // * Exercise a) 
  public SetChain() {
    mempool = new HashSet<>();
    history = new AVLDictionary<>();
    epochNum = 0;
  }

  // * Exercise b) 
  public boolean isEmpty() {
    return mempool.isEmpty() && history.isEmpty();
  }

  // * Exercise c) 
  public int getEpoch(T transaction) {
    for(Tuple2<Integer, Set<T>> kv : history.keysValues()){
      if(kv._2().isElem(transaction)){
        return kv._1();
      }
    }
    return -1;
  }

  // * Exercise d) 
  public int size() {
    Iterator<Set<T>> it = history.values().iterator();
    int suma = 0;
    while(it.hasNext()){
      Set<T> set = it.next();
      suma += set.size();
    }
    return suma;
  }

  /*  public int size() {
  int suma = 0;
  for(Set<T> set : history.values()){
    suma += set.size();
  }
  return suma;
  } */

  // * Exercise e) 
  public boolean pendingTransactions() {
    return (!mempool.isEmpty());
  }

  // * Exercise f) 
  public void add(T transaction) {
    mempool.insert(transaction);
  }

  // * Exercise g) 
  public void validate() {
    if(!mempool.isEmpty()){
      for(Set<T> set: history.values()){
        for(T elem: set){
          if(mempool.isElem(elem)){
            throw new SetChainException("transaction already validated");
          }
        }
      }
      history.insert(epochNum, mempool);
      mempool = new HashSet<>();
      ++epochNum;
    }
  }

  // * Exercise h) 
  public void addAll(List<T> transactions) {
    for(T transaction : transactions){
      mempool.insert(transaction);
    }
  }

  private class SetChainIterator implements Iterator<T> {
    private Iterator<T> mempoolIterator; // Iterador para las transacciones en el mempool
    private Iterator<Tuple2<Integer, Set<T>>> historyIterator; // Iterador para el historial
    private Iterator<T> historySetIterator; // Iterador para los elementos de cada set en el historial
    private T nextItem; // Elemento siguiente a devolver

    public SetChainIterator() {
        this.mempoolIterator = mempool.iterator();
        this.historyIterator = history.keysValues().iterator();
        this.historySetIterator = null; // Aún no hemos comenzado a recorrer el historial
        this.nextItem = null;
    }

    @Override
    public boolean hasNext() {
        // Si aún quedan elementos en el mempool, devolvemos true
        if (mempoolIterator.hasNext()) {
            return true;
        }

        // Si el iterador del historial tiene más elementos, comenzamos a iterar sobre el siguiente set
        if (historyIterator.hasNext()) {
            return true;
        }

        // Si el iterador del historial tiene más elementos dentro de un set, también debemos devolver true
        if (historySetIterator != null && historySetIterator.hasNext()) {
            return true;
        }

        return false;
    }

    @Override
    public T next() {
        if (nextItem != null) {
            T result = nextItem;
            nextItem = null; // Limpiamos el siguiente item, ya que ha sido devuelto
            return result;
        }

        // Primero, intentamos obtener un elemento del mempool
        if (mempoolIterator.hasNext()) {
            return mempoolIterator.next();
        }

        // Si no hay más elementos en el mempool, avanzamos al siguiente set en el historial
        if (historySetIterator == null || !historySetIterator.hasNext()) {
            if (historyIterator.hasNext()) {
                Tuple2<Integer, Set<T>> entry = historyIterator.next();
                historySetIterator = entry._2().iterator();
            }
        }

        // Ahora obtenemos el siguiente elemento del historial (si existe)
        if (historySetIterator != null && historySetIterator.hasNext()) {
            return historySetIterator.next();
        }

        // Si no hay más elementos, lanzamos una excepción
        throw new NoSuchElementException();
    }
}


  // * Exercise j) 
  public Iterator<T> iterator() {
    return new SetChainIterator();
  }

  /* *************************** */
  /* DON'T MODIFY THE CODE BELOW */
  /* *************************** */

  private String setToString(Set<T> set) {
    if(set == null)
      return "(null)";

    StringJoiner sj = new StringJoiner(", ", "{", "}");
    for (T x : set) {
      sj.add(x.toString());
    }
    return sj.toString();
  }

  @Override
	public String toString() {
    String className = getClass().getSimpleName();
    StringJoiner sjHistory = new StringJoiner(", ", "History{", "}");

    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add("Mempool" + setToString(mempool));

    if(history == null)
      sj.add("null");
    else {
      for (Tuple2<Integer, Set<T>> t : history.keysValues()) {
        sjHistory.add(t._1() + " -> " + setToString(t._2()));
      }
      sj.add(sjHistory.toString());
    }

    sj.add("EpochNum -> " + epochNum);

    return sj.toString();
  }
	
	public boolean equals(Object object) {
    if(this == object)
      return true;

    if (!(object instanceof SetChain<?>))
      return false;

    SetChain<?> that = (SetChain<?>) object;

    if (this.epochNum != that.epochNum)
      return false;

    if (!this.mempool.equals(that.mempool))
      return false;

    for (Tuple2<Integer, Set<T>> t : this.history.keysValues()) {
      int epoch = t._1();
      Set<? extends Comparable<?>> set = that.history.valueOf(epoch);
      if (set == null || !set.equals(t._2()))
        return false;
    }
    return true;
  }
}
