package Recommendation;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;



public class Map extends Mapper<LongWritable, Text, LongWritable, FriendCountWritable> {
    private Text word = new Text();
    List tUser = new ArrayList(); 
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String split[] = value.toString().split("\t");
        Long fUser = Long.parseLong(line[0]);
       

        if (line.length == 2) {
            StringTokenizer tokenizer = new StringTokenizer(line[1], ",");
            while (tokenizer.hasMoreTokens()) { // checking for more tokens
                Long toUser = Long.parseLong(tokenizer.nextToken());
                tUser.add(toUser); // arraylist is updated and user is added
                context.write(new LongWritable(fromUser),
                        new FriendCountWritable(toUser, -1L));
            }

            for (int k = 0; k < tUser.size(); k++) {
                for (int j = k + 1; j < tUser.size(); j++) {
                    context.write(new LongWritable((long) tUser.get(k)),
                            new FriendCountWritable((Long) (tUser.get(j)), fUser));
                    context.write(new LongWritable((long) tUser.get(j)),
                            new FriendCountWritable((Long) (tUser.get(k)), fUser));
                }
                }
            }
        }
    }