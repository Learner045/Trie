public class Node {
    private String character;
    private Node[] children;
    private int value; //when u want it to be used like hashmap where words are mapped to value
    private boolean visited; //req for autocomplete and sort while traversing DFS
    private boolean leaf;

    public Node(String character){
        this.character=character;
        children=new Node[26]; //here we are taking size as 26
    }
    public void setChild(int index,Node node){
        this.children[index]=node;
    }
    public Node getChild(int index){
        return this.children[index];
    }

    //for hashmap like functionality
    public void setChild(int index,Node node,int value){
        node.setValue(value); //when u need to map word with val
        this.children[index]=node;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return this.character;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}
