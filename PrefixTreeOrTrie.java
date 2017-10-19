import java.util.ArrayList;
import java.util.List;

public class PrefixTreeOrTrie {

    private Node root;
    private int indexOfSingleChild; //for longestcommonprefix problem

    public PrefixTreeOrTrie(){
        this.root=new Node("");
    }

    public void insert(String word){

        Node tempNode=root;

        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            int asciiIndex=c-'a'; //generating index so that the char can be stored in array depeding on which position it should be at

            if(tempNode.getChild(asciiIndex)==null){
                //char not present
                Node node=new Node(String.valueOf(c)); //create new node & add it to tempNode's children array
                tempNode.setChild(asciiIndex,node);
                tempNode=node; //go deeper
            }else{
                //given node has that child
                tempNode=tempNode.getChild(asciiIndex); //we go deeper to its child
            }
        }
        tempNode.setLeaf(true);//after putting the entire word..we reach the end & set the node as leaf
    }

    //WHEN u need to map value with word
    public void insert(String word,int value){

        Node tempNode=root;

        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            int asciiIndex=c-'a';

            if(tempNode.getChild(asciiIndex)==null){

                Node node=new Node(String.valueOf(c));
                tempNode.setChild(asciiIndex,node,value);//hashmap feature
                tempNode=node;
            }else{

                tempNode=tempNode.getChild(asciiIndex);
            }
        }
        tempNode.setLeaf(true);
    }


    //O(L) where L is length of key
    public boolean search(String word){

        Node tempNode=this.root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            int asciiIndex=c-'a';
            if(tempNode.getChild(asciiIndex)!=null){
                //char not present
                tempNode=tempNode.getChild(asciiIndex); //go deeper
            }else{
                return false;
            }
        }
        return true;
    }


    //For hm like functionality

    public Integer searchValue(String word){

        Node tempNode=this.root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            int asciiIndex=c-'a';
            if(tempNode.getChild(asciiIndex)!=null){
                //char not present
                tempNode=tempNode.getChild(asciiIndex); //go deeper
            }else{
                return null;
            }
        }
        return tempNode.getValue();
    }

    //AUTOCOMPLETE FEATURE
    public List<String> allWordsWithPrefix(String prefix){
        //goto last alphabet of prefix & then collect all strings by exploring its children
        Node node=this.root;
        List<String>allWords=new ArrayList<>();
        for(int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            int asciiIndex=c-'a';
            node=node.getChild(asciiIndex);
        }

        collect(node,prefix,allWords);
        return allWords;
    }

    private void collect(Node node, String prefix, List<String> allWords) {

        if(node==null)return;

        if(node.isLeaf()){
            //leaf node so add it to list & nothing to explore further
            //if you reach leaf then its a complete word traced so directly add
            allWords.add(prefix);
        }

        //DFS
        for(Node childNode:node.getChildren()){
            if(childNode==null)continue;
            String childchar=childNode.getCharacter(); //eg e so now explore e's children by recursive call
            collect(childNode,prefix+childchar,allWords); //eg if prefix was app..now we are recursively appe
        }
    }


    public String longestCommonPrefix(){
        StringBuilder sb=new StringBuilder();
        Node node=this.root;
        //while our node is not leaf..so that we can go deeper..
        //& while our node had only 1 child..which means all children of child-node will have that common prefix
        while(!node.isLeaf() && countNumOfChildren(node)==1){
            node=node.getChild(indexOfSingleChild); //
            sb=sb.append(String.valueOf((char)(indexOfSingleChild+'a'))); //we add a because we are getting index and we need to convert that to character
        }
        return sb.toString();
    }
    private int countNumOfChildren(Node n){
        if(n==null)return 0;

        int numofchildren=0;

        for(int i=0;i<n.getChildren().length;i++)
        {
            if(n.getChild(i)!=null){
                numofchildren++;
                indexOfSingleChild=i;
            }
        }
        return numofchildren;
    }

}
