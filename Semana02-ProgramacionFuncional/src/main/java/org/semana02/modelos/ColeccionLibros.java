package org.semana02.modelos;

import java.util.*;
import java.util.stream.Collectors;

public class ColeccionLibros {

    private ArrayList<Libro> libros;

    public ColeccionLibros() {
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
    }

    // Crea los métodos solicitados en el enunciado del ejercicio

    //1. Obtener la cantidad de libros con más de 500 páginas
    public int cantidadLibrosMas500Paginas() {
        return (int) libros.stream()
                .filter(l -> l.paginas > 500)
                .count();
    }

    //2. Obtener la cantidad de libros con menos de 300 páginas
    public int cantidadLibrosMenos300Paginas() {
        return (int) libros.stream()
                .filter(l -> l.paginas < 300)
                .count();
    }

    //3. Listar el título de todos aquellos libros con más de 500 páginas.
    public List<String> listarLibrosMas500Paginas() {
        return libros.stream()
                .filter(l -> l.paginas > 500)
                .map(Libro::getTitulo)
                .toList();
    }

    //4. Obtener el título de los 3 libros con mayor número de páginas.
    public List<String> listarTresLibrosMasPaginas() {
        return libros.stream()
                // .sorted((l1, l2) -> l2.paginas - l1.paginas)
                .sorted(Comparator.comparingInt(Libro::getPaginas).reversed())
                // Limit antes que map es más eficiente para evitar que map se realice en todos los elementos innecesariamente
                .limit(3)
                .map(Libro::getTitulo)
                .toList();
    }


    //5. Obtener la suma total de las páginas de todos los libros.
    public int sumaTotalPaginas() {
        return libros.stream()
                .mapToInt(Libro::getPaginas)
                .sum();
    }

    //6. Obtener todos aquellos libros que superen el promedio en cuanto a número de páginas se refiere.
    public List<Libro> listarLibrosMasPaginasPromedio() {
        if (libros.isEmpty()) {
            return Collections.emptyList();
        }
        double promedio = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0);

        return libros.stream()
                .filter(l -> l.getPaginas() > promedio)
                .toList();
    }

    //7. Obtener los autores de todos los libros, sin repetir nombres de autores.
    public List<String> listarAutores() {
        return libros.stream()
                .map(Libro::getAutor)
                .distinct()
                .toList();
        // Otra forma de hacerlo es mediante Set, ya que distinct() es una operación costosa.
        // return new ArrayList<>(new HashSet<>(libros.stream().map(Libro::getAutor).toList()));
    }

    //8. Obtener el libro con mayor número de páginas.
    public Libro libroMasPaginas() {
        return libros.stream()
                .max(Comparator.comparing(Libro::getPaginas)) // Devuelve un Optional<Libro>, no un Libro
                .orElse(null);
    }

    //9. Obtener una colección con todos los títulos de los libros.
    public List<String> listarTitulos() {
        return libros.stream()
                .map(Libro::getTitulo)
                .toList();
    }

    //10. Obtener los autores que tengan más de 1 libro listado.
    public List<String> listarAutoresConMasDeUnLibro() {
        // Opción 1
        /*
        HashMap<String, Integer> autores = new HashMap<>();
        // libros.forEach(l -> autores.put(l.getAutor(), autores.getOrDefault(l.getAutor(), 0) + 1));
        libros.forEach(l -> autores.merge(l.getAutor(), 1, Integer::sum));
        return autores.entrySet().stream()
                .filter(a -> a.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
       */
        // Opción 2
        return libros.stream()
                // Recopila los libros del stream en un mapa,
                // agrupándolos por autor (clave) y contando cuántos libros tiene cada autor (valor)
                .collect(Collectors.groupingBy(Libro::getAutor, Collectors.counting()))
                // Convertimos la estructura mapa en un stream
                .entrySet().stream()
                // Nos quedamos con los autores cuya frecuencia sea mayor que 1
                .filter(a -> a.getValue() > 1)
                // Extraemos los nombres de los autores y los imprimimos
                .map(Map.Entry::getKey)
                .toList();
    }
}