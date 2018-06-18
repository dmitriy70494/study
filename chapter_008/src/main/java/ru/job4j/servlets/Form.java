package ru.job4j.servlets;

import java.util.ArrayList;
import java.util.Queue;
import java.util.TreeMap;

public class Form {

    public String getFormNullOrFill(Queue<String> data, boolean isFillOrNull) {
        StringBuilder result = new StringBuilder("<form action='").append(data.poll()).append("' method='post'> ");
        while (data.size() != 2) {
            result.append(data.poll()).append(" <input type='text' name='").append(data.poll());
            if (isFillOrNull) {

                result.append("' value='").append(data.poll());
            }
            result.append("'/><br>");
        }
        result.append("<input type='submit' name='").append(data.poll()).append("'/><input type='hidden' name='action' value='").append(data.poll()).append("'/></form>");
        return result.toString();
    }
}
