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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length == 0) {
            return null;
        }
        return mergeLists(lists, 0, lists.length-1);
    }

    ListNode mergeLists(ListNode[] lists, int start, int end){
        if(start == end) return lists[start];
        if (start > end) return null;

        int mid = start+(end-start)/2;

        return merge(mergeLists(lists,start,mid), mergeLists(lists,mid+1,end));

    }

    ListNode merge(ListNode l1, ListNode l2){
        if(l1 == null){
            if(l2==null) return null;
            else return l2;
        } else if(l2 == null){
            return l1;
        }
        ListNode curr1 = l1;
        ListNode curr2 = l2;

        while(curr1 !=null && curr2 !=null){
            if(curr1.val <= curr2.val){
                ListNode prev = curr1;
                curr1 = curr1.next;
                while(curr1!=null && curr1.val<=curr2.val){
                    prev = curr1;
                    curr1 = curr1.next;
                }
                prev.next = curr2;
            }else{
                ListNode prev = curr2;
                curr2 = curr2.next;
                while(curr2!=null && curr2.val<=curr1.val){
                    prev = curr2;
                    curr2 = curr2.next;
                }
                prev.next = curr1;
            }
        }
        if(l1.val <= l2.val) return l1;
        else return l2;
    }
}