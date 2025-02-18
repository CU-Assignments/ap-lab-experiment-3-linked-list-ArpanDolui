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
    public ListNode sortList(ListNode head) {
        // Base case: If the list is empty or has only one node, return it
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Find the middle of the list
        ListNode mid = findMiddle(head);

        // Step 2: Split the list into two halves
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null; // Break the link between the two halves

        // Step 3: Recursively sort the two halves
        left = sortList(left);
        right = sortList(right);

        // Step 4: Merge the two sorted halves
        return merge(left, right);
    }

    // Helper function to find the middle of the list
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Helper function to merge two sorted lists
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); // Dummy node to simplify merging
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Attach the remaining nodes from either list
        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }

        return dummy.next;
    }
}
