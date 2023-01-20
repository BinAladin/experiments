package cz.boucnikd.graalvm;

public interface MessageHandler {

    boolean isValid(String sender, String senderTopic, String message, String rcvr, String rcvrTopic);

    String createMessage(String sender, String message);
}
