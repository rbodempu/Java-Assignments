import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class Service{
	private int counter = 0;
	private String name = null;
	private List<String> stats = null;
	
	public Service () {}
	
	public Service (String name)
	   {
	      counter = 1;
	      this.name = name;
	      stats = new ArrayList<String>();
	   }

 

	public String getStats() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = stats.iterator();
		while (it.hasNext()) {
			buffer.append(it.next());
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		Task task = new Task();
		Service thread = new Service("First Thread");
		System.out.printf("Starting the Threads\n\n");
		for (int i = 1; i <= 10; i++) {
			ThreadFactory t1 =(Runnable runnable) -> {
				Thread t = new Thread(runnable, thread.name + "-Thread_" + thread.counter);
				thread.counter++;
				thread.stats.add(String.format("Created thread %d with name %s on %s \n",
						t.getId(), t.getName(), new Date()));
				return t;
			};
			
			t1.newThread(task).start();
		}
		System.out.printf("All Threads are created now\n\n");
		System.out.printf("Give me CustomThreadFactory stats:\n\n"
				+ thread.getStats());
	}
}
