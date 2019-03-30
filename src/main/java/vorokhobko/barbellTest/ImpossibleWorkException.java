package vorokhobko.barbellTest;

/**
 * ImpossibleWorkException.
 *
 * Class ImpossibleWorkException inherits from a class Exception.
 * @author Evgeny Vorokhobko (vorokhobko2011@yandex.ru).
 * @since 15.01.2018.
 * @version 1.
 */
public class ImpossibleWorkException extends Exception {
    /**
     * Create an overloaded constructor.
     * Add ImpossibleWorkException.
     * @param ime - ime.
     */
    public ImpossibleWorkException(String ime) {
        super(ime);
    }
}