public class QuestionCopier {

    public QuestionCopier() {
    }

    public Question copy(Question question){
        Question copyQuestion;
        if(question instanceof SingleAnswerQuestion){
            copyQuestion = new SingleAnswerQuestion(question.getAdministrator(),
                    question.getQuestionPrompt(), question.getChoiceCount(), question.getChoices());
        }
        else {
            copyQuestion = new MultipleAnswersQuestion(question.getAdministrator(),
                    question.getQuestionPrompt(), question.getChoiceCount(), question.getChoices());
        }
        return copyQuestion;
    }
}
