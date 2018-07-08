public interface Voting{
    void addPoll(String pollerID, Question question);
    void castVote(String pollerID);
    Question submitVote(String pollerID);
    void removePoll(String pollerID);
}
