public class Question {
    private String text;  // The question text
    private String[] options;  // The answer choices

    // Constructor
    public Question(String text, String[] options) {
        this.text = text;
        this.options = options;
    }

    // Getters for the question text and options
    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }
}
