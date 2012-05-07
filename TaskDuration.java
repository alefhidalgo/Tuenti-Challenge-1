import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Tuenti Programming Contest
 * Challenge 4: Task Duration 
 * @author alefhidalgo [at] gmail [dot] com
 */
public class TaskDuration {

  private int nTasks = 0;
  private Node[] tasks;
  private long totalCost = 0;

  /**
   * Inner Class Node
   * 
   * @author alefhidalgo@gmail.com
   * 
   */
  private class Node implements Comparable<Node> {
    private int duration;
    private Set<Node> dependencies;

    public Node(int duration) {
      this.duration = duration;
      dependencies = new TreeSet<Node>();
    }

    public void addDependency(Node n) {
      dependencies.add(n);
    }

    public Set<Node> getDependencies() {
      return dependencies;
    }

    public int getDuration() {
      return duration;
    }

    @Override
    public int compareTo(Node o) {
      return -1;
    }

  }

  /**
   * Load file 'in' into a data structure
   * 
   * @throws IOException
   */
  public void load() throws IOException {
    FileChannel fc = new FileInputStream("in").getChannel();
    MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
    Charset charset = Charset.forName("US-ASCII");
    CharsetDecoder decoder = charset.newDecoder();
    CharBuffer charBuffer = decoder.decode(byteBuffer);
    Scanner sc = new Scanner(charBuffer);
    String aux = null;
    String spl[] = null;
    Node n = null;
    Node nDep = null;

    while (sc.hasNextLine()) {
      String next = sc.nextLine();
      if (next.startsWith("#Number of tasks")) {
        nTasks = sc.nextInt();
        tasks = new Node[nTasks];
      }
      if (next.startsWith("#Task duration")) {
        for (int i = 0; i < nTasks; i++) {
          aux = sc.nextLine();
          spl = aux.split(",");
          tasks[Integer.parseInt(spl[0])] = new Node(Integer.parseInt(spl[1]));
        }
      }
      if (next.startsWith("#Task dependencies")) {
        while (sc.hasNext()) {
          aux = sc.next();
          spl = aux.split(",");
          n = tasks[Integer.parseInt(spl[0])];
          for (int i = 1; i < spl.length; i++) {
            nDep = tasks[Integer.parseInt(spl[i])];
            n.addDependency(nDep);
          }
        }
      }
    }
    fc.close();
  }

  /**
   * newTimeCost
   * 
   * @param cost
   */
  public void newTimeCost(long cost) {
    if (cost > totalCost) {
      totalCost = cost;
    }
  }

  /**
   * iterativeCalculation
   * 
   * @param root
   */
  private void iterativeCalculation(Node root) {
    Stack<Node> nodes = new Stack<Node>();
    nodes.push(root);
    Node currentNode;
    long timeAcum = 0;
    while (!nodes.isEmpty()) {
      currentNode = nodes.pop();
      timeAcum += currentNode.getDuration();
      if (currentNode.getDependencies().size() == 0) {
        newTimeCost(timeAcum);
        timeAcum -= currentNode.getDuration();
      }
      for (Node n1 : currentNode.getDependencies()) {
        nodes.push(n1);
      }

    }
  }

  /**
   * calculateDuration
   * 
   * @param taskId
   */
  public void calculateDuration(String taskId) {
    Node n = tasks[Integer.parseInt(taskId)];
    iterativeCalculation(n);
    System.out.println(taskId + " " + totalCost);
    totalCost = 0;
  }

  public static void main(String[] s) throws IOException {
    TaskDuration taskDuration = new TaskDuration();
    taskDuration.load();
    Scanner in = new Scanner(System.in);
    in.useDelimiter(",");
    while (in.hasNext()) {
      taskDuration.calculateDuration(in.next());
    }
  }
}