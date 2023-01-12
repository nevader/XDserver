import java.io.Serializable;
public interface Request<R> extends Serializable {
    R handle(Storage storage);
}
