class ChatMessageHandler:
    def createMessage(sender, msg):
        match = re.match("/([^ ]+)", msg)
        if match:
            try:
                if match.group(1) == "=":
                    return f"You sent a command with arguments: {msg.split()[-1]}"
                else:
                    return "Unknown command!"
            except:
                return f"Error during command execution: {sys.exc_info()[0]}"
        return f"[{sender}] {msg}"

    def isValid(sender, senderTopic, message, receiver, receiverTopic):
        return senderTopic == receiverTopic
