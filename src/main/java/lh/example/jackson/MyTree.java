package lh.example.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihong on 27.06.17.
 */
public class MyTree {

    //final public Map<String,List<String>> map = new HashMap<>();
//    public Map<String, List<String>> map = new HashMap<>();
    final private Map<Integer, Node> tree = new HashMap<>();
    public MyTree(){

    }
    public void addNode(Node n){
        tree.put(n.id,n);
    }
    @JacksonXmlElementWrapper(localName = "nodes",useWrapping=true)
    @JacksonXmlProperty(localName = "node")
    @JsonProperty("vertices")
    public Collection<Node> getNodes(){
        return tree.values();
    }
    public void setNodes(Collection<Node> nodes){
        for(Node n:nodes){
            addNode(n);
        }
        System.out.println("debug: set nodes");
    }


    public static class Node{
        @JacksonXmlProperty(isAttribute=true)
        public final int id;

        public Node(@JsonProperty("id") int id){
            this.id = id;
        }
        @Override
        public String toString(){
            return "node "+id;
        }
    }

}
