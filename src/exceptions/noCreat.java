package exceptions;
import java.io.Serial;
public class noCreat extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public noCreat() {
        super("l'lement no s'ha creat");
    }
}
