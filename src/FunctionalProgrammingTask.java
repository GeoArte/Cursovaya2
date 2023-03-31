import java.util.*;

public class FunctionalProgrammingTask {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст: ");
    String text = scanner.nextLine();

    Map<String, Integer> wordCountMap = getWordCount(text);
        System.out.println("Количество слов в тексте: " + wordCountMap.size());

    List<Map.Entry<String, Integer>> topWords = getTopWords(wordCountMap);
        System.out.println("Топ-10 самых часто упоминаемых слов:");
        for (Map.Entry<String, Integer> entry : topWords) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}

    private static Map<String, Integer> getWordCount(String text) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        String[] words = text.split("\\s+");
        for (String word : words) {
            word = word.replaceAll("[^\\p{L}\\p{Nd}]+", "").toLowerCase();
            if (word.length() > 0) {
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }
        return wordCountMap;
    }

    private static List<Map.Entry<String, Integer>> getTopWords(Map<String, Integer> wordCountMap) {
        List<Map.Entry<String, Integer>> wordCountList = new ArrayList<>(wordCountMap.entrySet());
        Collections.sort(wordCountList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                int countCompare = e2.getValue().compareTo(e1.getValue());
                if (countCompare != 0) {
                    return countCompare;
                } else {
                    return e1.getKey().compareTo(e2.getKey());
                }
            }
        });

        List<Map.Entry<String, Integer>> topWords = new ArrayList<>();
        for (int i = 0; i < Math.min(10, wordCountList.size()); i++) {
            topWords.add(wordCountList.get(i));
        }
        return topWords;
    }
}

