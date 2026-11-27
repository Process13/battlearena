package server_client;

import java.util.Locale;

public enum Command {
    SAVE,
    HISTORY,
    OK,
    ERROR,
    UNKNOWN;
    // Tolkar text från nätverket som protokollvärde, okänt värde eller null fångas upp
    public static Command parse(String text){
        if(text == null) {
            return UNKNOWN;
        }
        return switch (text.trim().toLowerCase()) {
            case "save" -> SAVE;
            case "history" -> HISTORY;
            case "ok" -> OK;
            case "error" -> ERROR;
            default -> UNKNOWN;
        };
    }
}
