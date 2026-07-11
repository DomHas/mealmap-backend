package ch.wiss.mealmapbackend.exception;

/**
 * Wird geworfen, wenn ein Rezept mit der angegebenen ID
 * nicht in der Datenbank gefunden werden kann.
 */
public class RecipeNotFoundException extends RuntimeException {

    /**
     * @param id die ID des nicht gefundenen Rezepts
     */
    public RecipeNotFoundException(Long id) {
        super("Kein Rezept mit der ID " + id + " gefunden!");
    }
}
