import java.util.List;

public class PollStatistics {

    private boolean manyAnswers;
    private int answers[];

    public PollStatistics(Question question) {
        setQuestion(question);
    }

    private void setQuestion(Question question){
        if(question instanceof SingleAnswerQuestion){
            setSingleAnswerQuestion(question);
        }
        else if(question instanceof MultipleAnswersQuestion){
            setMultipleAnswerQuestion(question);
        }
    }

    private void setSingleAnswerQuestion(Question question){
        setManyAnswers(false);
        answers = new int[question.getChoiceCount()];
        setAnswers(answers);
    }

    private void setMultipleAnswerQuestion(Question question){
        setManyAnswers(true);
        answers = new int[question.getChoiceCount()];
        setAnswers(answers);
    }

    public void countVote(String pollerID, Voter voter){
        if(isManyAnswers()){
            MultipleAnswersQuestion question = (MultipleAnswersQuestion)voter.submitVote(pollerID);
            question.getVoterAnswer(this);
        }
        else{
            SingleAnswerQuestion question = (SingleAnswerQuestion)voter.submitVote(pollerID);
            question.getVoterAnswer(this);
        }
    }

    public void displayStatistics(Question question){
        System.out.println("Question:\n" +
                            question.getQuestionPrompt());
        List<String> choices = question.getChoices();
        for(int i = 0; i < question.getChoiceCount(); i++){
            System.out.printf("%3d votes: %2s\n", answers[i], choices.get(i));
        }
    }

    public boolean isManyAnswers() {
        return manyAnswers;
    }

    public void setManyAnswers(boolean manyAnswers) {
        this.manyAnswers = manyAnswers;
    }

    public int[] getAnswers() {
        return answers;
    }

    public void setAnswers(int[] answers) {
        this.answers = answers;
    }

}
