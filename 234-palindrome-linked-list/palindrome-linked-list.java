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
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        stack.push(slow.val);
        while(true){
            if(fast.next == null){
                stack.pop();
                slow = slow.next;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
            if(fast == null) break;
            stack.push(slow.val);
        }

        while(!stack.isEmpty() && slow!=null){
            if(stack.pop() != slow.val) return false;
            slow=slow.next;
        }

        return stack.isEmpty() ? true : false;
    }
}