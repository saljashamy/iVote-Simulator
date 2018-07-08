import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Poll {
    private Scanner keyboard;

    public Poll() {
    }

    public Question getQuestion(Administrator administrator){
        keyboard = new Scanner(System.in);
        String questionPrompt = questionPrompt();
        int type = questionType();
        int choiceCount = numberOfChoices();
        List<String> choices = questionChoices(choiceCount);
        Question question = getQuestion(administrator, questionPrompt, type, choiceCount, choices);
        return question;
    }

    private String questionPrompt(){
        System.out.println("\nEnter the question:\n");
        String questionPrompt = keyboard.nextLine();
        return questionPrompt;
    }

    private int questionType(){
        System.out.println("\nSelect type of question:\n" +
                            "1. Single Answer\n" +
                            "2. Multiple Answers");
        int type = Character.getNumericValue(keyboard.nextLine().charAt(0));
        if (type != 1 && type != 2){
            throw new RuntimeException(type + " is an invalid choice for question type");
        }
        return type;
    }

    private int numberOfChoices(){
        System.out.println("\nEnter the number of choices:");
        int choiceCount = (int)Integer.parseInt(keyboard.nextLine());
        if (choiceCount < 2 || choiceCount > 7){
            throw new RuntimeException(choiceCount + " is an invalid number of choices");
        }
        return choiceCount;
    }

    private List<String> questionChoices(int choiceCount){
        List<String> choices = new ArrayList<String>(choiceCount);
        for (int i = 0; i < choiceCount; i++) {
            System.out.println("Enter choice #" + (i+1));
            String choice = keyboard.nextLine();
            choices.add(choice);
        }
        return choices;
    }

    public Question getQuestion(Administrator administrator, String questionPrompt, int type, int choiceCount, List<String> choices){
       Question question;
        if(type == 1){
            question = new SingleAnswerQuestion(administrator, questionPrompt, choiceCount, choices);
        }
        else{
            question = new MultipleAnswersQuestion(administrator, questionPrompt, choiceCount, choices);
        }
        return question;
    }
}
