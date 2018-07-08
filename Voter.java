import java.util.Map;
import java.util.HashMap;

public class Voter extends User implements Voting {
    private Map<String, Question> questions = new HashMap<String, Question>();

    public Voter(String voterID) {
        super(voterID);
    }

    @Override
    public void addPoll(String pollerID, Question question){
        question.setVoter(this);
        questions.put(pollerID, question);
    }

    @Override
    public void castVote(String pollerID) {
        VoteProcessing question = (VoteProcessing)questions.get(pollerID);
        question.setVoterAnswer(this, true);
        questions.put(pollerID, (Question) question);
    }

    @Override
    public Question submitVote(String pollerID){
        return questions.get(pollerID);
    }

    @Override
    public void removePoll(String pollerID){
        questions.remove(pollerID);
    }

}
