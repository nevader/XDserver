public interface ClientRequest<R> extends Message {
    R handle(Storage storage);
    @Override
    default MessageType getType() {
        return MessageType.REQUEST;
    }
}