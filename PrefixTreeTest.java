import java.util.ArrayList;
import java.util.List;

public class PrefixTreeTest {

    public static void main(String args[]){
        PrefixTreeOrTrie tree=new PrefixTreeOrTrie();
        tree.insert("adam");
        tree.insert("ada");
        tree.insert("adaa");



    //   System.out.println(tree.search("anj"));

    //   tree.insert("yoyo",5);

     //  System.out.println(tree.searchValue("yoyop"));

       List<String> list=tree.allWordsWithPrefix(""); //this will return sorted output
        for(String s:list)System.out.println(s);

        List<String> list2=tree.allWordsWithPrefix("sh");
        for(String s:list2)System.out.println(s);

        //LONGEST COMMON PREFIX
        System.out.println(tree.longestCommonPrefix()+" is longest common prefix");
    }
}
