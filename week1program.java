import java.util.*;

public class week1program {
    private final Map<String, Long> userRegistry = new HashMap<>();
    private final Map<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !userRegistry.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        int suffix = 1;
        while (suggestions.size() < 3) {
            String candidate = username + suffix;
            if (!userRegistry.containsKey(candidate)) {
                suggestions.add(candidate);
            }
            suffix++;
        }
        return suggestions;
    }

    public String getMostAttempted() {
        return Collections.max(attemptFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) {
        week1program system = new week1program();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a username to check (or 'exit' to quit):");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            
            if (system.checkAvailability(input)) {
                System.out.println("Username '" + input + "' is available.");
                // Simulate registering the user so future checks fail
                system.userRegistry.put(input, System.currentTimeMillis());
            } else {
                System.out.println("Username '" + input + "' is taken.");
                System.out.println("Suggestions: " + system.suggestAlternatives(input));
            }
        }
        scanner.close();
    }
}