package Recommendation;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable; // jar file for hadoop is included

public class FriendCount implements Writable {  // interface Writable implemented
    public Long user;
    public Long mFriend;
    
 public FriendCount() { // default constructor 
        this(-1L, -1L);
    }    

public FriendCount(Long user, Long mutualFriend) { // parameterized constructor for user and mutual friend
        this.user = user;
        this.mFriend = mutualFriend;
    }

    @Override
    public void write(DataOutput out) throws IOException { 
        out.writeLong(user);
        out.writeLong(mFriend);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        user = in.readLong();
        mFriend = in.readLong();
    }

    @Override
    public String toString() { 
        return " User: "+ Long.toString(user) + " mutualFriend: " + Long.toString(mFriend);
               
                
    }
}
