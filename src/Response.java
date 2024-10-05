
public class Response {

	    private Question question;  // The question being answered
	    private int answer;  // The user's selected answer (1 to 4)

	    // Constructor
	    public Response(Question question, int answer) {
	        this.question = question;
	        this.answer = answer;
	    }

	    // Getter for the selected answer
	    public int getAnswer() {
	        return answer;
	    }
	
}
