public class Administrator extends User implements Polling{
    private Poll poll;

    public Administrator(String pollerID) {
        super(pollerID);
        poll = new Poll();
    }

    @Override
    public Question createQuestion() {
        Question question = poll.getQuestion(this);
        return question;
    }
}
