import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class SingleServerQueue
{
  int Queue_size = 1000;
  int next_event_type;
  int num_custs_delayed;
  int num_events;
  int num_in_q;
  int server_status;
  int num_of_customer;
  double area_num_in_q;
  double area_server_status;
  double sim_time;
  double time_last_event;
  double total_of_delays;
  double mean_interarrival;
  double mean_service;
 
  double[] time_arrival = new double[Queue_size];
  double[] time_next_event=new double[3];
 
  Random random = new Random(10000);



	public  void initialize()
	{
		sim_time = 0.0;
		num_events=2;
		server_status = 0;
		num_in_q = 0;
		time_last_event = 0.0;
	 
		num_custs_delayed = 0;
		total_of_delays = 0.0;
		area_num_in_q = 0.0;
		area_server_status = 0.0;
	 
		time_next_event[1] = sim_time + expon(mean_interarrival);
		time_next_event[2] = 1.0e+30;
	}
	 
	
	
	
	 
	public  void timing()
	{
		int i;
		double min_time_next_event =  1.0e+30;
		next_event_type=0;
		
		for(i=1;i<=num_events;++i)
		{
			if (time_next_event[i] < min_time_next_event)
				{
					min_time_next_event=time_next_event[i];
					next_event_type=i;
				}
		}
			
		if(next_event_type==0)
		{
			System.out.println("Event List is Empty");
			System.exit(1);
		}
			
	 
		sim_time = min_time_next_event;
	}
	 
	
	public  void arrive()
	{
		double delay;
	 
		time_next_event[1] = sim_time + expon(mean_interarrival);
	 
		if (server_status == 1)
		{
			++num_in_q;
			
			if(num_in_q>Queue_size)
			{
				System.out.println("Over flow of the queue time arrival at "+sim_time);
				System.exit(0);
				
			}
			time_arrival[num_in_q] = sim_time;
	
		}
	 
		else
		{
			delay = 0;
			total_of_delays += delay;
	 
			++num_custs_delayed;
			server_status = 1;
			time_next_event[2] = sim_time + expon(mean_service);
		}
	}
	 
	
	public  void depart()
	{
		int i;
		double delay;
		if (num_in_q == 0)
		{
			server_status = 0;
			time_next_event[2] = 1.0e+30;
		}
		else
		{
			--num_in_q;
			delay = sim_time-time_arrival[1];
			total_of_delays  += delay;
			++num_custs_delayed;
			
			time_next_event[2] = sim_time + expon(mean_service);
			for ( i = 1; i <= num_in_q; ++i)
				time_arrival[i] = time_arrival[i+1];
		}
	}
	 
	
	public  void print()
	{
		System.out.println("Single server queue\n");
		System.out.println( "Total customer uses this server \n" + num_custs_delayed);
		System.out.println("\n");
		System.out.println( "Average delay in queue minutes  ");
		System.out.format("%.2f",  total_of_delays / num_custs_delayed);
		System.out.println("\n");
		System.out.println( "Average number in queue  ");
		System.out.format("%.2f", area_num_in_q / sim_time );
		System.out.println("\n");
		System.out.println( "Server utilization  ");
		System.out.format("%.2f",area_server_status / sim_time);
		System.out.println("\n");
		System.out.println("Time simulation ended ");
		System.out.format("%.0f", sim_time );
		System.out.println("\n");
		try {
 
            // Create an object of BufferedWriter
            BufferedWriter f_writer
                = new BufferedWriter(new FileWriter("/home/nyamwas/Documents/simulation-and-modelling-ass1/output_file.txt"));
 
            // Write text(content) to file
            f_writer.write("Total customer uses this server \n" + num_custs_delayed + "\n" + "Average delay in queue minutes  \n" + String.format("%.2f", (total_of_delays / num_custs_delayed)) +"\n" + "Average number in queue  \n" + String.format("%.2f",(area_num_in_q / sim_time)) + "\n" + "Server utilization  \n" + String.format("%.2f",(area_server_status / sim_time)) + "\n"+"Time simulation ended \n" + String.format("%.0f",sim_time) + "\n");
 
    
            // Close the BufferedWriter object
            f_writer.close();
        }
 
        // Catch block to handle if exceptions occurs
        catch (IOException e) {
 
            // Print the exception on console
            // using getMessage() method
            System.out.print(e.getMessage());
        }
	}
	 
	public  void update_time_avg_stats()
	{
		double time_since_last_event;
	 
		time_since_last_event = sim_time - time_last_event;
		time_last_event = sim_time;
	 
		area_num_in_q += num_in_q * time_since_last_event;
	 
		area_server_status += server_status * time_since_last_event;
	}
	 
	public  double expon(double  mean)
	{
		return (-mean * Math.log(Math.random()));
	}

	public static void main(String[] args) throws Exception
	{
		// File path is passed as parameter
        File file = new File("/home/nyamwas/Documents/simulation-and-modelling-ass1/input_file.txt");
		BufferedReader br  = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
		SingleServerQueue s = new SingleServerQueue();
			while ((st = br.readLine()) != null){
				
				Scanner sc = new Scanner(System.in);
				
				//mean inter arrival time 
				s.mean_interarrival=Double.parseDouble(String.valueOf(st.charAt(24)).concat(String.valueOf(st.charAt(25))).concat(String.valueOf(st.charAt(26))).concat(String.valueOf(st.charAt(27))));
				
				
				// mean service time of server
				s.mean_service=Double.parseDouble(String.valueOf(st.charAt(49)).concat(String.valueOf(st.charAt(50))).concat(String.valueOf(st.charAt(51))).concat(String.valueOf(st.charAt(52))));
			
				//number of customer
				s.num_of_customer=Integer.parseInt(String.valueOf(st.charAt(67)).concat(String.valueOf(st.charAt(68))).concat(String.valueOf(st.charAt(69))).concat(String.valueOf(st.charAt(70))));
				
			}
			s.initialize();
			
			while(s.num_custs_delayed<s.num_of_customer)
			{
				s.timing();
				s.update_time_avg_stats();
				
				switch (s.next_event_type)
				{
					case 1: s.arrive();
					break;
					case 2: s.depart();
					break;
				}	
			}
			s.print();
		
		
		
    }
}

