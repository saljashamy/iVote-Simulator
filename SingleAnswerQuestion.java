import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SingleAnswerQuestion extends Question implements VoteProcessing {
    private SingleAnswerQuestion copy;
    private int voterAnswer;

    public SingleAnswerQuestion(Administrator administrator, String questionPrompt, int choiceCount, List<String> choices) {
        super(administrator, questionPrompt, choiceCount, choices);
    }

    @Override
    public void setVoterAnswer(Voter voter, boolean simulation){
        // voterAnswer: a random int from first to last choice of question
        if(voter == getVoter() && simulation){
            Random random = new Random();
            this.voterAnswer = random.nextInt(this.getChoiceCount()) + 1;
            //System.out.printf("Voter %4s choice : %2d\n", voter.getID(), voterAnswer);
        }
        else if (voter == getVoter()){
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the choice # of the answer:");
            this.voterAnswer = (int) Integer.parseInt(keyboard.nextLine());
        }
        else {
            throw new RuntimeException("Not authorized to vote on question.");
        }

    }

    @Override
    public void getVoterAnswer(PollStatistics pollStats){
        int[] answers = pollStats.getAnswers();
        answers[voterAnswer-1]++;
        pollStats.setAnswers(answers);
    }
}
