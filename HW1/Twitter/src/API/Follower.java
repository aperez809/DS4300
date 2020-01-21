package API;

public class Follower {
    private int id;
    private int userId;
    private int followsId;

    public Follower(int id, int userId, int followsId){
        this.id = id;
        this.userId = userId;
        this.followsId= followsId;
    }

    public Follower(int userId, int followsId){
        this.id = -1;
        this.userId = userId;
        this.followsId= followsId;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowsId() { return followsId; }

    public void setFollowsId(int followsId) {
        this.followsId = followsId;
    }

}
