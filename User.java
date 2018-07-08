public class User {
    private String ID = null;

    public User(String ID) {
        setID(ID);
    }

    public void setID(String ID){
        if(this.ID == null) {
            this.ID = ID;
        }
        else {
            throw new RuntimeException("Cannot set id twice.");
        }
    }

    public String getID() {
        return this.ID;
    }
}
