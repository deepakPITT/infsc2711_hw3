package Recommendation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceMap extends Reducer<LongWritable, FriendCount, LongWritable, Text> {
    @Override
    public void reduce(LongWritable key, Iterable<FriendCount> values, Context context)
            throws IOException, InterruptedException {

        //Map data structure of <key, value> is used key is recommended friend and value is list of mutual friends

        final java.util.Map<Long, List> mutualFriends = new HashMap<Long, List>();

        for (FriendCount val : values) {
            final Boolean isFriend = (val.mFriend == -1);  // iterating the list of friend
            final Long toUser = val.user;
            final Long mFriend = val.mFriend;

            if (mutualFriends.containsKey(toUser)) {  // checks for a user is present or not
                if (isFriend) {
                    mutualFriends.put(toUser, null);
                } else if (mutualFriends.get(toUser) != null) {
                    mutualFriends.get(toUser).add(mFriend);  // mutual friend is added 
                }
            } else {
                if (!isFriend) {
                    mutualFriends.put(toUser, new ArrayList() {
                        {
                            add(mFriend);
                        }
                    });
                } else {
                    mutualFriends.put(toUser, null);
                }
            }
        }
         
          Map<Long, List> umFriends = new HashMap<Long, List>();
        Compare v1 =  new Compare(umFriends); 
		TreeMap<Long, List> smFriends=new TreeMap<>(v1);
         // Tree Map to store values in sorted order
        for (java.util.Map.Entry<Long, List> entry : mutualFriends.entrySet()) {
            if (entry.getValue() != null) {
               umFriends.put(entry.getKey(), entry.getValue());
            }
        }
        
        smFriends.putAll(umFriends);

     int n=0;String result = "";
         Integer k = 0; 
       // accessing  every pair contained within smFrirnds using entrySet
       for (java.util.Map.Entry<Long, List> entry : smFriends.entrySet()) {
            	
       		 if (k == 0) {
                    result = entry.getKey().toString(); 
                   } 
                 
                 else 
                 
                 {
                    result += "," + entry.getKey().toString() ;
                 }
                 
                ++k;	
                if(k>=10)
                {
                break;
                }
       	     }
        
        context.write(key, new Text(result));
    }
}
 
        	