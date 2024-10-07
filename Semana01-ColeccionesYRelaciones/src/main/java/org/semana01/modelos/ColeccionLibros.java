package org.semana01.modelos;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

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
        int cantidad = 0;
        for (Libro libro : libros) {
            if (libro.getPaginas() > 500) {
                cantidad++;
            }
        }
        return cantidad;
    }

    //2. Obtener la cantidad de libros con menos de 300 páginas
    public int cantidadLibrosMenos300Paginas() {
        int cantidad = 0;
        for (Libro libro : libros) {
            if (libro.getPaginas() < 300) {
                cantidad++;
            }
        }
        return cantidad;
    }

    //3. Listar el título de todos aquellos libros con más de 500 páginas.
    public ArrayList<String> listarLibrosMas500Paginas() {
        ArrayList<String> titulos = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getPaginas() > 500) {
                titulos.add(libro.getTitulo());
            }
        }
        return titulos;
    }

    //4. Obtener el título de los 3 libros con mayor número de páginas.
    public ArrayList<String> listarTresLibrosMasPaginas() {
        // Ordenación con lambda:
        // libros.sort((l1, l2) -> l2.getPaginas() - l1.getPaginas());
        Libro top1 = null, top2 = null, top3 = null;
        for (Libro libro : libros) {
            if (top1 == null || libro.getPaginas() > top1.getPaginas()) {
                top3 = top2;
                top2 = top1;
                top1 = libro;
            } else if (top2 == null || libro.getPaginas() > top2.getPaginas()) {
                top3 = top2;
                top2 = libro;
            } else if (top3 == null || libro.getPaginas() > top3.getPaginas()) {
                top3 = libro;
            }
        }

        ArrayList<String> titulos = new ArrayList<>();
        if (top1 != null)
            titulos.add(top1.getTitulo());
        if (top2 != null)
            titulos.add(top2.getTitulo());
        if (top3 != null)
            titulos.add(top3.getTitulo());
        return titulos;
    }


    //5. Obtener la suma total de las páginas de todos los libros.
    public int sumaTotalPaginas() {
        int suma = 0;
        for (Libro libro : libros) {
            suma += libro.getPaginas();
        }
        return suma;
    }

    //6. Obtener todos aquellos libros que superen el promedio en cuanto a número de páginas se refiere.
    public ArrayList<Libro> listarLibrosMasPaginasPromedio() {
        int suma = sumaTotalPaginas();
        int promedio = suma / libros.size();
        ArrayList<Libro> librosMasPromedio = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getPaginas() > promedio) {
                librosMasPromedio.add(libro);
            }
        }
        return librosMasPromedio;
    }

    //7. Obtener los autores de todos los libros, sin repetir nombres de autores.
    public ArrayList<String> listarAutores() {
        HashSet<String> autores = new HashSet<>();
        for (Libro libro : libros) {
            autores.add(libro.getAutor());
        }
        return new ArrayList<>(autores);
    }

    //8. Obtener el libro con mayor número de páginas.
    public Libro libroMasPaginas() {
        if (libros.isEmpty()) {
            return null;
        }
        Libro libroMasPaginas = null;
        for (Libro libro : libros) {
            if (libroMasPaginas == null || libro.getPaginas() > libroMasPaginas.getPaginas()) {
                libroMasPaginas = libro;
            }
        }
        return libroMasPaginas;
    }

    //9. Obtener una colección con todos los títulos de los libros.
    public ArrayList<String> listarTitulos() {
        ArrayList<String> titulos = new ArrayList<>();
        for (Libro libro : libros) {
            titulos.add(libro.getTitulo());
        }
        return titulos;
    }

    //10. Obtener los autores que tengan más de 1 libro listado.
    public ArrayList<String> listarAutoresConMasDeUnLibro() {
        HashMap<String, Integer> autores = new HashMap<>();
        for (Libro libro : libros) {
            String autor = libro.getAutor();
            autores.merge(autor, 1, Integer::sum);
            // Merge es una forma más compacta de hacer lo siguiente:
            // autores.put(autor, autores.getOrDefault(autor, 0) + 1);

            // La solución conceptualmente más sencilla sería:
            /* if (autores.containsKey(autor)) {
                autores.put(autor, autores.get(autor) + 1);
            } else {
                autores.put(autor, 1);
            }
            */
        }

        ArrayList<String> autoresConMasDeUnLibro = new ArrayList<>();
        for (String autor : autores.keySet()) {
            if (autores.get(autor) > 1) {
                autoresConMasDeUnLibro.add(autor);
            }
        }
        return autoresConMasDeUnLibro;
    }
}