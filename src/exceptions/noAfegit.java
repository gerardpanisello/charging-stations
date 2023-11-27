package exceptions;
import java.io.Serial;
public class noAfegit extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public noAfegit() {
        super("l'lement no s'ha inserit correctament");
    }
}

