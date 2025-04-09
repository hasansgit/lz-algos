import java.util.ArrayList;
import java.util.HashMap;

public class lz78 {
    class Node {
        final int pos;
        final char next;

        Node(int pos, char next) {
            this.pos = pos;
            this.next = next;
        }
    }

    ArrayList<Node> compress(String s) {
        ArrayList<Node> res = new ArrayList<>();

        HashMap<String, Integer> dict = new HashMap<>();

        dict.put("", 0);
        String buff = "";
        for (int i = 0; i < s.length(); i++) {
            if (dict.containsKey(buff + s.charAt(i))) {
                buff += s.charAt(i);
            } else {
                res.add(new Node(dict.get(buff), s.charAt(i)));
                dict.put(buff + s.charAt(i), dict.size());
                buff = "";
            }
        }

        return res;
    }

    String decompress(ArrayList<Node> a) {
        StringBuilder sb = new StringBuilder();

        HashMap<Integer, String> dict = new HashMap<>();

        dict.put(0, "");

        for (Node i : a) {
            sb.append(dict.get(i.pos) + i.next);
            dict.put(dict.size(), dict.get(i.pos) + i.next);
        }

        return sb.toString();
    }
}
