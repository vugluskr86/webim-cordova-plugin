package ru.webim.plugin.models;

public class Message {
    public String id;
    public String text;
    public String url;
    public String timestamp;
    public String sender;
    public Employee operator;

    public static Message fromParams(String id,
                                     String text,
                                     String url,
                                     String timestamp,
                                     String sender) {
        Message resultMessage = new Message();
        resultMessage.id = id;
        resultMessage.text = text;
        resultMessage.sender = sender;
        resultMessage.timestamp = timestamp;
        resultMessage.url = url;

        return resultMessage;
    }

    public static Message fromWebimMessage(com.webimapp.android.sdk.Message message) {
        Message resultMessage = new Message();
        resultMessage.id = message.getId().toString();
        resultMessage.text = message.getText();
        if (message.getType() != com.webimapp.android.sdk.Message.Type.FILE_FROM_OPERATOR
                && message.getType() != com.webimapp.android.sdk.Message.Type.OPERATOR) {
            resultMessage.sender = message.getSenderName();
        } else {
            resultMessage.operator = Employee.getEmployeeFromParams(message.getSenderName(),
                    message.getSenderAvatarUrl());
        }
        if (message.getAttachment() != null) {
            resultMessage.url = message.getAttachment().getUrl();
        }
        resultMessage.timestamp = Long.toString(message.getTime());

        return resultMessage;
    }
}