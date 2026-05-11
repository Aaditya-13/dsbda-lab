import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class LoginMapper
        extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text user = new Text();

    private IntWritable duration = new IntWritable();

    public void map(LongWritable key,
                    Text value,
                    Context context)
            throws IOException, InterruptedException {

        String line = value.toString();

        String[] parts = line.split(",");

        if (parts.length == 3) {

            String username = parts[0];

            int login =
                    Integer.parseInt(parts[1]);

            int logout =
                    Integer.parseInt(parts[2]);

            int loginHour = login / 100;
            int loginMin = login % 100;

            int logoutHour = logout / 100;
            int logoutMin = logout % 100;

            int loginTotal =
                    loginHour * 60 + loginMin;

            int logoutTotal =
                    logoutHour * 60 + logoutMin;

            int diff =
                    logoutTotal - loginTotal;

            user.set(username);

            duration.set(diff);

            context.write(user, duration);
        }
    }
}