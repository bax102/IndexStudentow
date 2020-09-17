package ps.sda.javagdy2.database.model;

/**
 * interfejs uwspólniający cechę identyfikatora dla wszystkich klas bazodanowych.
 *
 * Jeśli klasa jest bazodanowa to musi mieć identyfikator - czyli może implementować ten interfejs
 */
public interface IBaseEntity {
    Long getId();
}
