import java.util.List;

public class Question{

    private Administrator administrator;
    private Voter voter;
    private String questionPrompt;
    private List<String> choices;
    private int choiceCount;

    public Question(Administrator administrator, String questionPrompt, int choiceCount, List<String> choices) {
        setAdministrator(administrator);
        setQuestionPrompt(questionPrompt);
        setChoiceCount(choiceCount);
        setChoices(choices);
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    private void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        if (getVoter() == null || getVoter() == voter) {
            this.voter = voter;
        }
        else{
            throw new RuntimeException("Cannot change the voter of the question.");
        }
    }

    public List<String> getChoices() {
        return choices;
    }

    private void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public int getChoiceCount() {
        return choiceCount;
    }

    private void setChoiceCount(int choiceCount) {
        this.choiceCount = choiceCount;
    }

    public String getQuestionPrompt() {
        return questionPrompt;
    }

    private void setQuestionPrompt(String questionPrompt) {
        this.questionPrompt = questionPrompt;
    }
}
