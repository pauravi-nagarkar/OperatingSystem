import java.util.Scanner;
public class SJF {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println ("enter no of process:");
        int n = sc.nextInt();
        int process_id[] = new int[n];
        int arrival_time[] = new int[n];
        int burst_t[] = new int[n];
        int end_t[] = new int[n];
        int turn_t[] = new int[n];
        int wait_t[] = new int[n];
        int final_t[] = new int[n];

        int start_t=0, tot=0;
        float avgwait_t=0, avgturn_t=0;

        for(int i=0;i<n;i++)
        {
            System.out.println ("enter process " + (i+1) + " arrival time:");
            arrival_time[i] = sc.nextInt();
            System.out.println ("enter process " + (i+1) + " brust time:");
            burst_t[i] = sc.nextInt();
            process_id[i] = i+1;
            final_t[i] = 0;
        }


        while(true)
        {
            int c=n, min=999999;

            if (tot == n)
                break;

            for (int i=0; i<n; i++)
            {

                if ((arrival_time[i] <= start_t) && (final_t[i] == 0) && (burst_t[i]<min))
                {
                    min=burst_t[i];
                    c=i;
                }
            }
            if (c==n)
                start_t++;
            else
            {
                end_t[c]=start_t+burst_t[c];
                start_t+=burst_t[c];
                turn_t[c]=end_t[c]-arrival_time[c];
                wait_t[c]=turn_t[c]-burst_t[c];
                final_t[c]=1;
                process_id[tot] = c + 1;
                tot++;
            }
        }

        System.out.println("\nprocess_id  arrival_time   brust_time   complete_time  turn_around_time   waiting_time");
        for(int i=0;i<n;i++)
        {
            avgwait_t+= wait_t[i];
            avgturn_t+= turn_t[i];
            System.out.println(process_id[i]+"\t\t"+arrival_time[i]+"\t\t"+burst_t[i]+"\t\t"+end_t[i]+"\t\t"+turn_t[i]+"\t\t"+wait_t[i]);
        }
        System.out.println ("\naverage turn_around_time is "+ (float)(avgturn_t/n));
        System.out.println ("average wait_time is "+ (float)(avgwait_t/n));
        sc.close();
        for(int i=0;i<n;i++)
        {
            System.out.print(process_id[i] + " ");
        }
    }
}
