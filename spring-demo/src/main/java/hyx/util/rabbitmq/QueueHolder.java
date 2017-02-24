package hyx.util.rabbitmq;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class QueueHolder {
	private static QueueHolder holder = new QueueHolder();
	private static Set<String> queues = new HashSet<>();

	private QueueHolder() {

	}

	public static QueueHolder getInstance() {
		return holder;
	}

	public void addQueue(String queueName) {
		queues.add(queueName);
	}

	public Set<String> getQueuesAsMap() {
		return queues;
	}

	public String[] getQueuesAsArray() {
		String[] s = new String[queues.size()];
		Iterator<String> iterator = queues.iterator();
		for (int icount = 0; iterator.hasNext(); icount++) {
			s[icount] = iterator.next();
		}
		return s;
	}
}
