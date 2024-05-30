package control;

public class sanitizer {
		public static String sanitize(String input) {
        // Rimuovi caratteri speciali
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        return input;
    }
}
