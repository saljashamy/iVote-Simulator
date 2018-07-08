import java.util.List;

public class SimulationDriver {
    private iVoteService iVote;
    private Administrator professor;
    private Question question;
    
    public void iVoteServiceStart(){
        iVote = new iVoteService();
    }
    
    public void createAndRegisterProfessor(){
        professor = new Professor(iVote.nextPollerID());
        iVote.register(professor);
    }
    
    public void createAndRegisterStudents(int count){
        for (int s = 0; s < count; s++) {
            Voter student = new Student(iVote.nextVoterID());
            iVote.register(student);
        }
    }
    
    public void createAndSubmitQuestion(){
        question = professor.createQuestion();
        iVote.submitPoll(professor.getID(), question);
    }

    public void votingRound(int round){
        System.out.println("\nVoting Round " + round);
        List<Voter> voters = iVote.getVoters();
        for(Voter voter: voters){
            voter.castVote(professor.getID());
        }
    }

    public void getPollStatistics(){
        System.out.println("Poll Statistics:");
        iVote.getPollStatistics(professor.getID(), question);
    }

    public void endVoting(){
        iVote.endVote(professor.getID());
    }

    public static void main(String[] args) {
        
        SimulationDriver ivs = new SimulationDriver();
        
        ivs.iVoteServiceStart();
        
        ivs.createAndRegisterProfessor();
        
        ivs.createAndRegisterStudents(100);

        ivs.createAndSubmitQuestion();

        ivs.votingRound(1);

        ivs.getPollStatistics();

        System.out.println();

        ivs.votingRound(2);

        ivs.getPollStatistics();

        ivs.endVoting();
    }
}
