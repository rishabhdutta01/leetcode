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
        if(head == null || head.next == null) return;

        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Stack<ListNode> s = new Stack<>();
        while(slow!=null){
            s.push(slow);
            slow = slow.next;
        }

        ListNode curr = head;
        ListNode prev = curr;
        while(s.size()!=1){
            curr = curr.next;
            prev.next = s.pop();
            prev.next.next = curr;
            prev = curr;
        }
        curr.next = s.pop();
        curr.next.next = null;
    }
}