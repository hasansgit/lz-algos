import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lz78 {
    public static class Node {
        final int pos;
        final byte next;

        Node(int pos, byte next) {
            this.pos = pos;
            this.next = next;
        }
    }

    public List<Node> compress(byte[] bytes) {
        List<Node> res = new ArrayList<>();

        Map<List<Byte>, Integer> dict = new HashMap<>();
        dict.put(new ArrayList<>(), 0);

        List<Byte> buff = new ArrayList<>();

        for (byte aByte : bytes) {
            List<Byte> temp = new ArrayList<>(buff);
            temp.add(aByte);
            if (dict.containsKey(temp)) {
                buff.add(aByte);
            } else {
                res.add(new Node(dict.get(buff), aByte));
                dict.put(temp, dict.size());
                buff.clear();
            }
        }

        if (!buff.isEmpty()) {
            byte last = buff.removeLast();
            res.add(new Node(dict.get(buff), last));
        }

        return res;
    }

    public byte[] decompress(List<Node> nodes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Map<Integer, List<Byte>> dict = new HashMap<>();
        dict.put(0, new ArrayList<>());

        for (Node node : nodes) {
            List<Byte> newEntry = new ArrayList<>(dict.get(node.pos));
            newEntry.add(node.next);

            for (byte b : newEntry) out.write(b);
            dict.put(dict.size(), newEntry);
        }

        return out.toByteArray();
    }
}
