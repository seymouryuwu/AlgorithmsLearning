package unionFind;

/*
https://leetcode.com/problems/accounts-merge/
 */
import java.util.*;

public class AccountsMerge {
    Map<String, String> parents = new HashMap<>();
    Map<String, Integer> size = new HashMap<>();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ans = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) {
            return ans;
        }

        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                parents.put(email, email);
                size.put(email, 1);
            }
        }

        for (List<String> account : accounts) {
            String emailA = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                String emailB = account.get(i);
                union(emailA, emailB);
            }
        }

        Map<String, Queue<String>> merged = new HashMap<>();
        for (Map.Entry<String, String> e : parents.entrySet()) {
            String email = e.getKey();
            String parent = find(email);

            Queue<String> emailList;
            if (!merged.containsKey(parent)) {
                emailList = new PriorityQueue<String>((a, b) -> a.compareToIgnoreCase(b));
            } else {
                emailList = merged.get(parent);
            }

            emailList.offer(email);
            merged.put(parent, emailList);
        }

        Map<String, String> username = new HashMap<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            String parent = find(account.get(1));
            if (!username.containsKey(parent)) {
                username.put(parent, name);
            }
        }

        for (Map.Entry<String, Queue<String>> e : merged.entrySet()) {
            String parent = e.getKey();
            Queue<String> emailList = e.getValue();

            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(username.get(parent));
            while(!emailList.isEmpty()) {
                mergedAccount.add(emailList.poll());
            }

            ans.add(mergedAccount);
        }

        return ans;
    }

    private String find(String email) {
        String root = email;
        while (!root.equals(parents.get(root))) {
            root = parents.get(root);
        }

        String curr = email;
        while (!curr.equals(root)) {
            String oldParent = parents.get(curr);
            parents.put(curr, root);
            curr = oldParent;
        }

        return root;
    }

    private void union(String emailA, String emailB) {
        String parentA = find(emailA);
        String parentB = find(emailB);

        if (parentA.equals(parentB)) {
            return;
        }

        if (size.get(parentA) > size.get(parentB)) {
            parents.put(parentB, parentA);
            size.put(parentA, size.get(parentA) + size.get(parentB));
        } else {
            parents.put(parentA, parentB);
            size.put(parentB, size.get(parentA) + size.get(parentB));
        }
    }
}
