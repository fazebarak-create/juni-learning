import java.io.*;
import java.util.*;
// HW 9/21 Get location objects from locations hashmap using .get method with user  and user2. Then use SPT with the 2 locations.

class Main {
  public static SPT path;
  public static weightedGraph<String,Double>graph;
  public static HashMap<String,Location>locations;
  public static Scanner scanner;
    public static void main(String[] args) {
    	scanner = new Scanner(System.in);
    	graph = new weightedGraph<>();
    	locations = new HashMap<>();
    	System.out.println("Welcome to Street Searcher!");
      while(locations.size()<=0){
        loadData();
      }
      System.out.println("Locations");
      for(String name:locations.keySet()){
        System.out.println(name);
      }
      System.out.println();
      path = new SPT(graph,locations);
      System.out.println("Enter a start location: ");
      String user = scanner.nextLine();
      System.out.println("Enter a end location: ");
      String user2 = scanner.nextLine();
      path.findShortestPath(locations.get(user),locations.get(user2));
    }
    private static void loadData(){
      try{
        graph = new weightedGraph<>();
        locations = new HashMap<>();
        System.out.print("Please enter a file name: ");
        File file = new File(scanner.nextLine());
        Scanner fileScanner = new Scanner(file);
        while(fileScanner.hasNext()){
          String[] tokens = fileScanner.nextLine().split(" ");
          parseTokens(tokens);
        }
        System.out.println("Network Loaded");
        System.out.println("Loaded "+graph.getEdgesCount()+" roads");
        System.out.println(locations.size());
      }catch(FileNotFoundException ex){
        System.out.println("Error: "+ ex.getMessage());
    }
  }
  public static void parseTokens(String[] tokens){
    String[] names = tokens[3].split("--");
    String from = names[0];
    String[] fromCoords = tokens[0].split(",");
    double fromLat = Double.parseDouble(fromCoords[0]);
    double fromLong =  Double.parseDouble(fromCoords[1]);
    double distance = Double.parseDouble(tokens[2]); 
    Location locationFrom = new Location(from,fromLat,fromLong);
    locations.putIfAbsent(from,locationFrom);
    String to = names[1];
    String[] toCoords = tokens[1].split(",");
    double toLat = Double.parseDouble(toCoords[0]);
    double toLong =  Double.parseDouble(toCoords[1]);
    Location locationTo = new Location(to,toLat,toLong);
    locations.putIfAbsent(to,locationTo);
    graph.addEdge(from,to,distance);
  }
}
