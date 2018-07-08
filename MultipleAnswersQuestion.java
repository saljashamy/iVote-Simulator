import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MultipleAnswersQuestion extends Question implements VoteProcessing {

    private int[] voterAnswers;

    public MultipleAnswersQuestion(Administrator administrator, String questionPrompt, int choiceCount, List<String> choices) {
        super(administrator, questionPrompt, choiceCount, choices);
        voterAnswers = new int[choiceCount];
    }

    @Override
    public void setVoterAnswer(Voter voter, boolean simulation){
        // select: number of choices voter selects from 2 to total number of choices
        // choice: a random int from first to last choice of question
        if(voter == getVoter() && simulation){
            Random random = new Random();
            int select = random.nextInt((this.getChoiceCount() - 1)) + 2;
            for(int i = 0; i < select; i++){
                int choice = random.nextInt(this.getChoiceCount()) + 1;
                // keep picking a random choice, if previously chosen
                while(voterAnswers[choice-1] == 1){
                    choice = random.nextInt(this.getChoiceCount()) + 1;
                }
                voterAnswers[choice-1]++;
            }
        }
        else if (voter == getVoter()){
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter each choice on a line:");
            int choice = (int) Integer.parseInt(keyboard.nextLine());
            while(choice != -1) {
                voterAnswers[choice-1]++;
                choice = (int) Integer.parseInt(keyboard.nextLine());
            }
        }
        else {
            throw new RuntimeException("Not authorized to vote on question.");
        }

    }

    @Override
    public void getVoterAnswer(PollStatistics pollStats){
        int[] answers = pollStats.getAnswers();
        for(int i = 0; i < voterAnswers.length; i++){
            if(voterAnswers[i] != 0){
                answers[i]++;
                voterAnswers[i] = 0;
            }
        }
        pollStats.setAnswers(answers);
    }
}