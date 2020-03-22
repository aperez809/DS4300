import Redis.Redis
import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue
import scala.collection.mutable.HashSet


class Graph(redis: Redis) {
    def addNode(v: String) = {
        this.redis.rpush("GraphNodes", v);
    }

    def addEdge(u: String, v: String) = {
        if (this.redis.stringToListMap.get(u).contains(v) || this.redis.stringToListMap.get(v).contains(u)) { return; }
        this.redis.rpush("GraphEdges-" + u, v);
        this.redis.rpush("GraphEdges-" + v, u);
    }
    def adjacent(v: String): List[String] = {
        return this.redis.lrange("GraphEdges-" + v, 0, -1);
    }

    // Uses BFS method to search and store the shortest path from u to v
    def shortestPath(u: String, v: String): List[String] = {
        var previousNodes = HashMap[String, String]()
        var distances = HashMap[String, Int]()

        BFS(u, v, pred, dist);
        return previousNodes.get(v);
    }
    
    def BFS(begin: String, end: String, previousNodes: HashMap[String, List[String]], distances: HashMap[String, Int]): Boolean = { 
        // queue of next vertices to search
        var frontier: Queue[String] = Queue[String]();
        // set of nodes that have been seen
        var seen: HashSet[String] = HashSet[String]();
        // Each node in the graph
        var nodes: ListBuffer[String] = this.redis.getList("GraphNodes");

        // For each node, initialize in `distances` map with value -1 
        // and intialize in `previousNodes` with empty list
        nodes.foreach(node => {
            distances.put(node, -1);
            previousNodes.put(node, List[String]());
            return;
        });

        seen.add(begin);
        distances.put(begin, 0);
        frontier.enqueue(begin);

        while(!frontier.isEmpty) {
            var currNode = frontier.dequeue();
            this.adjacent(currNode).foreach(neighbor => {
                if(!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    dist.put(neighbor, dist.get(neighbor) + 1);
                    previousNodes.put(neighbor, previousNodes.get(neighbor) += currNode);
                    frontier.enqueue(neighbor);
                    if(neighbor == end) { return true;}
                }
            })
                
        }
        return false; 
    } 
}