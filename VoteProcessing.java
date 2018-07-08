public interface VoteProcessing {
    void setVoterAnswer(Voter voter, boolean simulation);
    void getVoterAnswer(PollStatistics pollStatistics);
}
