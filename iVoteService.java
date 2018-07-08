import java.util.ArrayList;
import java.util.List;

public class iVoteService {
    private static int pollerCount = 1;
    private static int voterCount = 1;
    private List<Administrator> administrators = new ArrayList<Administrator>();
    private List<Voter> voters = new ArrayList<Voter>();

    public String nextPollerID(){
        String pollerID = 'p' + Integer.toString(pollerCount);
        pollerCount++;
        return pollerID;
    }

    public String nextVoterID(){
        String voterID = 'v' + Integer.toString(voterCount);
        voterCount++;
        return voterID;
    }

    public void register(User user){
        String id = user.getID();
        if(id.charAt(0) == 'p'){
            registerPoller(user);
        }
        if(id.charAt(0) == 'v'){
            registerVoter(user);
        }
    }

    private void registerPoller(User user){
        this.administrators.add((Administrator)user);
    }

    private void registerVoter(User user){
        this.voters.add((Voter)user);
    }

    public void submitPoll(String pollerID, Question question){
        QuestionCopier copier = new QuestionCopier();
        for(Voter voter : voters){
            Question copyQuestion = copier.copy(question);
            voter.addPoll(pollerID, copyQuestion);
        }
    }

    public void getPollStatistics(String pollerID, Question question){
        PollStatistics pollStats = new PollStatistics(question);
        for(Voter voter : voters){
            pollStats.countVote(pollerID, voter);
        }
        pollStats.displayStatistics(question);
    }

    public void endVote(String pollerID){
        for(Voter voter : voters){
            voter.removePoll(pollerID);
        }
    }

    public List<Voter> getVoters(){
        return voters;
    }
}
