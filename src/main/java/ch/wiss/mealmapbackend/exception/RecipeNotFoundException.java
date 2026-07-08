package ch.wiss.mealmapbackend.exception;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(Long id) {
super("Kein Rezept mit der ID " + id + " gefunden!");
    }
}
