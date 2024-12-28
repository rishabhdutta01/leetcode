/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        Deque<ListNode> deq = new LinkedList<ListNode>();
        ListNode curr= head;
        while(curr!=null){
            deq.offerLast(curr);
            curr = curr.next;
        }

        head = deq.pollFirst();
        curr=head;

        while(!deq.isEmpty()) {
            curr.next = deq.pollLast();
            curr=curr.next;
            if(deq.isEmpty())
                break;
            curr.next = deq.pollFirst();
            curr=curr.next;
        }
        curr.next = null;
    }
}