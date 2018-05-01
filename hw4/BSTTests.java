import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
//import org.omg.CORBA.INTERNAL;

import java.security.PrivilegedActionException;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

/**
 * Sample JUnit test cases for BST.
 *
 * @version 1.0
 * @author CS 1332 TAs & added to byPete
 */
public class BSTTests {
    private BSTInterface<Integer> bst;
    public static final int TIMEOUT = 200;
    public static int[] fullTree = {10, 5, 15, 2, 7, 12, 17, 1, 3, 6, 8, 11, 13,
            16, 18};
    public static int[] fullTreePreorder = {10, 5, 2, 1, 3, 7, 6, 8, 15, 12,
    11, 13, 17, 16, 18 };

    public static int[] fullTreeInorder = {1, 2, 3, 5, 6, 7, 8, 10, 11, 12, 13,
    15, 16, 17, 18};

    public static int[] fullTreePostorder = {1, 3, 2, 6, 8, 7, 5, 11, 13, 12,
    16, 18, 17, 15, 10};

    public static int[] fullTreeLevelorder = {10, 5, 15, 2, 7, 12, 17, 1, 3, 6,
            8, 11, 13, 16, 18};

    public static Collection<Integer> fullTreeCol;

    @Before
    public void setup() {

        bst = new BST<Integer>();
        fullTreeCol = new ArrayList<>();
        for (int i : fullTree) {
            fullTreeCol.add(i);
        }

    }

    public static void preorderPrint(BSTInterface<Integer> tree) {
        System.out.print("Preorder: [");
        for (int i : tree.preorder()) {
            System.out.printf("%d ", i);
        }
        System.out.println("]");
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);

        assertEquals(3, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 3, bst.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(5);

        assertEquals((Integer) 3, bst.remove(3));
        assertEquals(4, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight()
                .getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertTrue(bst.contains(58));
        assertEquals((Integer) 58, bst.get(58));
        assertTrue(bst.contains(12));
        assertEquals((Integer) 12, bst.get(12));
        assertTrue(bst.contains(94));
        assertEquals((Integer) 94, bst.get(94));
        assertTrue(bst.contains(24));
        assertEquals((Integer) 24, bst.get(24));
    }

    @Test(timeout = TIMEOUT)
    public void testGetDifferent() {
        Integer testingInteger = new Integer(12);

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(testingInteger);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertSame(testingInteger, bst.get(new Integer(12)));
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(24);
        levelorder.add(1);
        levelorder.add(94);
        levelorder.add(7);
        levelorder.add(58);
        levelorder.add(12);
        levelorder.add(73);
        levelorder.add(68);
        levelorder.add(77);

        assertEquals(levelorder, bst.levelorder());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addExpectException() {
        bst.add(null);
    }


    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getEmpty() {
        bst.get(20);
    }

    @Test(timeout = TIMEOUT, expected =  IllegalArgumentException.class)
    public void testAddNull() {
        bst.add(null);
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAllRight() {
        for (int i = 0; i < 11; i++) {
            bst.add(i);
        }

        assertEquals(11, bst.size());
        BSTNode<Integer> cur = bst.getRoot();
        while (cur != null) {
            assertEquals(null, cur.getLeft());
            cur = cur.getRight();
        }

    }

    @Test(timeout = TIMEOUT)
    public void testAddAllLeft() {
        for (int i = 10; i >= 0; i--) {
            bst.add(i);
        }
        assertEquals(11, bst.size());

        BSTNode<Integer> cur = bst.getRoot();
        while (cur != null) {
            assertEquals(null, cur.getRight());
            cur = cur.getLeft();
        }

    }

    @Test(timeout = TIMEOUT)
    public void testAddFullTree() {
        bst.add(10); //                10
        bst.add(5);  //         /              \
        bst.add(2); //         5               15
        bst.add(7); //       /  \            /   \
        bst.add(1); //      /   \           /     \
        bst.add(3); //    2      7       12      17
        bst.add(6); //   / \   /  \     /  \    /  \
        bst.add(8); //  1  3   6  8   11  13   16  8
        bst.add(15);
        bst.add(12);
        bst.add(17);
        bst.add(11);
        bst.add(13);
        bst.add(16);
        bst.add(18);

        assertEquals(15, bst.size());

        bst.add(18);
        assertEquals(15, bst.size());
        bst.add(10);
        assertEquals(15, bst.size());

    }

    @Test(timeout = TIMEOUT)
    public void testAddUsingCollection() {
        Collection<Integer>  col = new ArrayList<>();
        for (int i : fullTree) {
            col.add(i);
        }
        BST<Integer> bst2 = new BST<>(col);
        assertEquals(15, bst2.size());

        bst2.add(18);
        assertEquals(15, bst2.size());
        bst2.add(10);
        assertEquals(15, bst2.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        bst.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFromEmpty() {
        bst.remove(1);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRootWhenSizeOne() {
        bst.add(1);
        bst.remove(1);
        assertEquals(null, bst.getRoot());
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRootWhenLeftKid() {
        bst.add(2);
        bst.add(1);
        assertEquals(2, bst.size());
        bst.remove(2);
        assertEquals(new Integer(1), bst.getRoot().getData());
        assertEquals(1, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRootWhenRightKid() {
        bst.add(2);
        bst.add(3);
        assertEquals(2, bst.size());
        bst.remove(2);
        assertEquals((Integer) 3, bst.getRoot().getData());
        assertEquals(1, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRootTwoKids() {
        bst.add(2);
        bst.add(1);
        bst.add(3);

        bst.remove(2);
        System.out.println(bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getData());
        assertEquals(2, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLeafFromFull() {
        bst = new BST<>(fullTreeCol);
        assertEquals(fullTree.length, bst.size());

        bst.remove(8);
        assertEquals(14, bst.size());
        assertEquals(null,
                bst.getRoot().getLeft().getRight().getRight());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveOneChildFromFullPlusANode() {
        bst = new BST<>(fullTreeCol);
        assertEquals(fullTree.length, bst.size());

        bst.add(90);

        bst.remove(18);
        assertEquals(fullTreeCol.size(), bst.size());
        assertEquals((Integer) 90,
                bst.getRoot().getRight().getRight().getRight().getData());
        assertEquals(null,
                bst.getRoot().getRight().getRight().getRight().getRight());

        bst.add(9);

        bst.remove(8);
        assertEquals(15, bst.size());
        assertEquals((Integer) 9,
                bst.getRoot().getLeft().getRight().getRight().getData());
        assertEquals(null,
                bst.getRoot().getLeft().getRight().getRight().getRight());


    }

    @Test(timeout = TIMEOUT)
    public void testRemoveTwoChildrenFromFull() {
        bst = new BST<>(fullTreeCol);
        assertEquals(fullTree.length, bst.size());

        System.out.println(bst.preorder());
        bst.remove(10);
        assertEquals(14, bst.size());
        assertEquals((Integer) 8, bst.getRoot().getData());

        assertEquals(null,
                bst.getRoot().getLeft().getRight().getRight());

        bst.remove(5);
        assertEquals(13, bst.size());
        assertEquals((Integer) 3, bst.getRoot().getLeft().getData());
        assertEquals(null,
                bst.getRoot().getLeft().getLeft().getRight());

        bst.remove(17);
        assertEquals(12, bst.size());
        assertEquals((Integer) 16,
                bst.getRoot().getRight().getRight().getData());
        assertEquals(null,
                bst.getRoot().getRight().getRight().getLeft());

        bst.remove(12);
        assertEquals(11, bst.size());
        assertEquals((Integer) 11,
                bst.getRoot().getRight().getLeft().getData());
        assertEquals(null,
                bst.getRoot().getRight().getLeft().getLeft());

        //Now will continue to remove the remaining elements from the tree

        bst.remove(8);
        assertEquals((Integer) 7, bst.getRoot().getData());
        assertEquals(10, bst.size());
        assertEquals(null,
                bst.getRoot().getLeft().getRight().getLeft());

        bst.remove(15);
        assertEquals(9, bst.size());
        assertEquals((Integer) 13, bst.getRoot().getRight().getData());
        assertEquals(null,
                bst.getRoot().getRight().getLeft().getRight());

        bst.remove(13);
        assertEquals(8, bst.size());
        assertEquals((Integer) 11, bst.getRoot().getRight().getData());
        assertEquals(null, bst.getRoot().getRight().getLeft());

        bst.remove(16);
        assertEquals(7, bst.size());
        assertEquals((Integer) 18,
                bst.getRoot().getRight().getRight().getData());

        bst.remove(18);
        assertEquals(6, bst.size());
        assertEquals(null, bst.getRoot().getRight().getRight());

        bst.remove(6);
        assertEquals(5, bst.size());
        assertEquals(null, bst.getRoot().getLeft().getRight());

        bst.remove(2);
        assertEquals(4, bst.size());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getLeft().getData());

        assertEquals((Integer) 3, bst.remove(3));
        assertEquals(3, bst.size());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());

        assertEquals((Integer) 7, bst.remove(7));
        assertEquals(2, bst.size());
        assertEquals((Integer) 1, bst.getRoot().getData());
        assertEquals(null, bst.getRoot().getLeft());

        assertEquals((Integer) 1, bst.remove(1));
        assertEquals(1, bst.size());
        assertEquals((Integer) 11, bst.getRoot().getData());

        assertEquals((Integer) 11, bst.remove(11));
        assertEquals(0, bst.size());
        assertEquals(null, bst.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveDegenerateTree() {
        for (int i =  0; i < 100; i++) {
            bst.add(i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals((Integer) i, bst.remove(i));
            assertEquals(99 - i, bst.size());
        }

        assertEquals(null, bst.getRoot());

        for (int i = 99; i >= 0; i--) {
            bst.add(i);
        }

        for (int i = 99; i >= 0; i--) {
            assertEquals((Integer) i, bst.remove(i));
            assertEquals(i, bst.size());
        }

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveSwappingTree() {
        int low = 0;
        int high = 60;

        while (low <= high) {
            bst.add(low);
            bst.add(high);
            low++;
            high--;
        }

        for (int i = 0; i <= 60; i++) {
            assertEquals((Integer) i, bst.remove(i));
        }

    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        bst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNonExistant() {
        bst.get(27);

        for (int i : fullTreeCol) {
            bst.add(i);
        }

        bst.get(9);
    }

    @Test(timeout = TIMEOUT)
    public void testGetFullTree() {
        for (int i : fullTreeCol) {
            bst.add(i);
        }
        for (int i = 0; i < fullTreeCol.size(); i++) {
            assertEquals((Integer) fullTree[i], bst.get(fullTree[i]));
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        bst.get(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        for (int i : fullTreeCol) {
            bst.add(i);
        }
        for (int i = 0; i < fullTreeCol.size(); i++) {
            assertEquals(true, bst.contains(fullTree[i]));
        }

        assertEquals(false, bst.contains(87));
        assertEquals(false, bst.contains(0));

    }

    @Test(timeout = TIMEOUT)
    public void testPreorderJustRoot() {
        bst.add(fullTree[0]);
        for (int i = 0; i < bst.preorder().size(); i++) {
            assertEquals((Integer) fullTree[i], bst.preorder().get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPreorderFullTree() {
        for (int i : fullTreeCol) {
            bst.add(i);
        }
        for (int i = 0; i < bst.preorder().size(); i++) {
            assertEquals((Integer) fullTreePreorder[i], bst.preorder().get(i));
        }

    }

    @Test(timeout = TIMEOUT)
    public void testInorderJustRoot() {
        bst.add(fullTree[0]);
        for (int i = 0; i < bst.inorder().size(); i++) {
            assertEquals((Integer) fullTree[i], bst.inorder().get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testInorderFullTree() {
        for (int i : fullTreeCol) {
            bst.add(i);
        }
        for (int i = 0; i < bst.inorder().size(); i++) {
            assertEquals((Integer) fullTreeInorder[i], bst.inorder().get(i));
        }

    }

    @Test(timeout = TIMEOUT)
    public void testPostorderJustRoot() {
        bst.add(fullTree[0]);
        for (int i = 0; i < bst.postorder().size(); i++) {
            assertEquals((Integer) fullTree[i], bst.postorder().get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderFullTree() {
        for (int i : fullTreeCol) {
            bst.add(i);
        }
        for (int i = 0; i < bst.postorder().size(); i++) {
            assertEquals((Integer) fullTreePostorder[i],
                    bst.postorder().get(i));
        }

    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderJustRoot() {
        bst.add(fullTree[0]);
        for (int i = 0; i < bst.levelorder().size(); i++) {
            assertEquals((Integer) fullTree[i], bst.levelorder().get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderFullTree() {
        for (int i : fullTreeCol) {
            bst.add(i);
        }
        for (int i = 0; i < bst.levelorder().size(); i++) {
            assertEquals((Integer) fullTreeLevelorder[i],
                    bst.levelorder().get(i));
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDistanceBetweenNull() {
        bst.distanceBetween(null, null);
        bst.distanceBetween(0, null);
        bst.distanceBetween(null, 0);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDistBetweenOneBothNotInTree() {
        bst.distanceBetween(0, 1);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDistBetweenOneNotInTree() {
        bst.add(0);
        bst.distanceBetween(0, 1);
    }

    @Test(timeout = TIMEOUT)
    public void testDistBetweenFullTree() {
        for (int i : fullTreeCol) {
            bst.add(i);
        }

        assertEquals(1, bst.distanceBetween(10, 5));
        assertEquals(1, bst.distanceBetween(5, 10));
        assertEquals(2, bst.distanceBetween(2, 10));
        assertEquals(3, bst.distanceBetween(1, 10));
        assertEquals(3, bst.distanceBetween(3, 10));
        assertEquals(2, bst.distanceBetween(7, 10));
        assertEquals(3, bst.distanceBetween(6, 10));
        assertEquals(3, bst.distanceBetween(10, 8));
        assertEquals(1, bst.distanceBetween(10, 15));
        assertEquals(2, bst.distanceBetween(17, 10));
        assertEquals(3, bst.distanceBetween(10, 18));
        assertEquals(3, bst.distanceBetween(16, 10));
        assertEquals(2, bst.distanceBetween(12, 10));
        assertEquals(3, bst.distanceBetween(13, 10));
        assertEquals(3, bst.distanceBetween(11, 10));
        assertEquals(0, bst.distanceBetween(10, 10));

        assertEquals(0, bst.distanceBetween(5, 5));
        assertEquals(1, bst.distanceBetween(5, 10));
        assertEquals(1, bst.distanceBetween(5, 2));
        assertEquals(1, bst.distanceBetween(5, 7));
        assertEquals(2, bst.distanceBetween(5, 1));
        assertEquals(2, bst.distanceBetween(5, 3));
        assertEquals(2, bst.distanceBetween(5, 6));
        assertEquals(2, bst.distanceBetween(5, 8));
        assertEquals(2, bst.distanceBetween(5, 15));
        assertEquals(3, bst.distanceBetween(5, 12));
        assertEquals(3, bst.distanceBetween(5, 17));
        assertEquals(4, bst.distanceBetween(5, 11));
        assertEquals(4, bst.distanceBetween(5, 13));
        assertEquals(4, bst.distanceBetween(5, 16));
        assertEquals(4, bst.distanceBetween(5, 18));

        assertEquals(0, bst.distanceBetween(2, 2));
        assertEquals(1, bst.distanceBetween(2, 1));
        assertEquals(1, bst.distanceBetween(2, 1));
        assertEquals(1, bst.distanceBetween(2, 3));
        assertEquals(1, bst.distanceBetween(2, 5));
        assertEquals(2, bst.distanceBetween(2, 7));
        assertEquals(2, bst.distanceBetween(2, 10));
        assertEquals(3, bst.distanceBetween(2, 6));
        assertEquals(2, bst.distanceBetween(2, 10));
        assertEquals(3, bst.distanceBetween(2, 8));
        assertEquals(3, bst.distanceBetween(2, 15));
        assertEquals(4, bst.distanceBetween(12, 2));
        assertEquals(4, bst.distanceBetween(17, 2));
        assertEquals(5, bst.distanceBetween(2, 11));
        assertEquals(5, bst.distanceBetween(2, 13));
        assertEquals(5, bst.distanceBetween(2, 16));
        assertEquals(5, bst.distanceBetween(2, 18));


        assertEquals(0, bst.distanceBetween(7, 7));
        assertEquals(1, bst.distanceBetween(7, 6));
        assertEquals(1, bst.distanceBetween(7, 8));
        assertEquals(1, bst.distanceBetween(2, 5));
        assertEquals(2, bst.distanceBetween(2, 10));
        assertEquals(3, bst.distanceBetween(7, 1));
        assertEquals(3, bst.distanceBetween(7, 3));
        assertEquals(3, bst.distanceBetween(7, 15));
        assertEquals(4, bst.distanceBetween(12, 7));
        assertEquals(4, bst.distanceBetween(17, 7));
        assertEquals(5, bst.distanceBetween(2, 11));
        assertEquals(5, bst.distanceBetween(2, 13));
        assertEquals(5, bst.distanceBetween(2, 16));
        assertEquals(5, bst.distanceBetween(2, 18));

        assertEquals(0, bst.distanceBetween(1, 1));
        assertEquals(2, bst.distanceBetween(1, 3));
        assertEquals(4, bst.distanceBetween(1, 6));
        assertEquals(4, bst.distanceBetween(1, 8));
        assertEquals(4, bst.distanceBetween(1, 15));
        assertEquals(5, bst.distanceBetween(1, 12));
        assertEquals(5, bst.distanceBetween(1, 17));
        assertEquals(6, bst.distanceBetween(13, 1));
        assertEquals(6, bst.distanceBetween(16, 1));
        assertEquals(6, bst.distanceBetween(18, 1));
        assertEquals(6, bst.distanceBetween(11, 1));

        assertEquals(0, bst.distanceBetween(3, 3));
        assertEquals(4, bst.distanceBetween(3, 6));
        assertEquals(4, bst.distanceBetween(3, 8));
        assertEquals(4, bst.distanceBetween(15, 3));
        assertEquals(5, bst.distanceBetween(12, 3));
        assertEquals(5, bst.distanceBetween(17, 3));
        assertEquals(6, bst.distanceBetween(3, 11));
        assertEquals(6, bst.distanceBetween(3, 13));
        assertEquals(6, bst.distanceBetween(3, 16));
        assertEquals(6, bst.distanceBetween(3, 18));


        assertEquals(1, bst.distanceBetween(10, 15));

        assertEquals(2, bst.distanceBetween(5, 15));
        assertEquals(3, bst.distanceBetween(5, 17));
    }

    @Test (timeout = TIMEOUT)
    public void testClear() {
        bst.clear();
        assertEquals(0, bst.size());
        assertEquals(null, bst.getRoot());

        bst.add(1);
        assertEquals(1, bst.size());

        bst.clear();
        assertEquals(0, bst.size());
        assertEquals(null, bst.getRoot());

        for (int i : fullTreeCol) {
            bst.add(i);
        }

        assertEquals(15, bst.size());
        bst.clear();
        assertEquals(0, bst.size());
        assertEquals(null, bst.getRoot());
    }
     @Test(timeout = TIMEOUT)
    public void testHeight() {
        assertEquals(-1, bst.height());

        bst.add(fullTree[0]);
        assertEquals(0, bst.height());

        for (int i = 1; i < 3; i++) {
            bst.add(fullTree[i]);
            assertEquals(1, bst.height());
        }

         for (int i = 3; i < 7; i++) {
             bst.add(fullTree[i]);
             assertEquals(2, bst.height());
         }

         for (int i = 7; i < fullTree.length; i++) {
             bst.add(fullTree[i]);
             assertEquals(3, bst.height());
         }
     }

     @Test(timeout = TIMEOUT)
    public void testHeightDegenerateTree() {
        bst.add(1);

        for (int i = 2; i < 100; i++) {
            assertEquals(i - 2, bst.height());
            bst.add(i);
        }
        bst.clear();

        bst.add(100);
        int height = 0;
        for (int i = 99; i >= 0; i--) {
            assertEquals(height, bst.height());
            bst.add(i);
            height++;
        }
     }
}
