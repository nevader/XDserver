public interface Request<R> extends Message {
    R handle(Storage storage);
    @Override
    default MessageType getType() {
        return MessageType.REQUEST;
    }
}