import java.util.Collection;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        for (T el : data) {
            add(el);
        }
    }

    @Override
    public void add(T data) {
        // if BF is -2, (- -> right heavy, + -> left heavy)
        // then check right tree BF
        // if -1 or 0, left rotation
        // if 1, right left rotation
        // if sign of parent BF and child BF differ -> double rotation
        // e.g. parent BF = 2, child BF = -1
        // cur.setLeft(leftRotation(cur.left));
        // return rightRotation(cur);
        // rotations act on the first child of the parent with BF of 2

        if (data == null) {
            throw new IllegalArgumentException("data to add cannot be null");
        }

        root = addHelper(root, data);
    }

    /**
     * Adds a data entry to the tree and balances recursively.
     *
     * @param cur the node from which to start the traversal
     * @param data the data to add into the tree
     * @return the pointer reinforced new child
     */
    private AVLNode<T> addHelper(AVLNode<T> cur, T data) {
        if (cur == null) {
            size += 1;
            return new AVLNode(data);
        }

        if (data.compareTo(cur.getData()) < 0) {
            cur.setLeft(addHelper(cur.getLeft(), data));
        } else if (data.compareTo(cur.getData()) > 0) {
            cur.setRight(addHelper(cur.getRight(), data));
        } // else the node already exists

        updateHandBF(cur);
        cur = balance(cur);
        return cur;
    }

    /**
     * Gives height of a (potentially null) node.
     *
     * @param node the node from which to start the traversal
     * @return height of the node
     */
    private int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }

        return node.getHeight();
    }

    /**
     * Updates the height and balance factors values for a node.
     *
     * @param cur the node to update
     */
    private void updateHandBF(AVLNode<T> cur) {
        if (cur.getLeft() == null) {
            if (cur.getRight() == null) {
                cur.setHeight(0);
            } else {
                // only right is non null
                cur.setHeight(1 + cur.getRight().getHeight());
            }
        } else if (cur.getRight() == null) {
            // left is not null
            cur.setHeight(1 + cur.getLeft().getHeight());
        } else {
            cur.setHeight(1 + Math.max(cur.getRight().getHeight(),
                cur.getLeft().getHeight()));
        }

        cur.setBalanceFactor(height(cur.getLeft())
            - height(cur.getRight()));
    }

    /**
     * Performs rotations to preserve balance of the tree.
     *
     * @param cur the node from which to start the balancing
     * @return pointer to the balanced node
     */
    private AVLNode<T> balance(AVLNode<T> cur) {
        int bf = cur.getBalanceFactor();

        if (bf < -1) {
            if (cur.getRight().getBalanceFactor() <= 0) {
                System.out.println("HERE:" + cur.toString());
                return leftRotation(cur);
            } else { // child BF differs in sign
                cur.setRight(rightRotation(cur.getRight()));
                return leftRotation(cur);
            }
        } else if (bf > 1) {
            if (cur.getLeft().getBalanceFactor() >= 0) {
                return rightRotation(cur);
            } else { // child BF differs in sign: double rotation
                cur.setLeft(leftRotation(cur.getLeft()));
                return rightRotation(cur);
            }
        }

        return cur;
    }

    //   1
    //  /  \
    // A    2
    //     /  \
    //    B    3
    //        /  \
    //       C    D
    /**
     * Performs a left rotation of a node.
     *
     * @param cur the node from which to start the rotation
     * @return pointer to the rotated node
     */
    private AVLNode<T> leftRotation(AVLNode<T> cur) {
        AVLNode<T> newRoot = cur.getRight();
        AVLNode<T> newRight = newRoot.getLeft();
        newRoot.setLeft(cur);
        cur.setRight(newRight);

        updateHandBF(cur);
        updateHandBF(newRoot);
        return newRoot;
    }

    /**
     * Performs a right rotation of a node.
     *
     * @param cur the node from which to start the rotation
     * @return pointer to the rotated node
     */
    private AVLNode<T> rightRotation(AVLNode<T> cur) {
        AVLNode<T> newRoot = cur.getLeft();
        AVLNode<T> newLeft = newRoot.getRight();
        newRoot.setRight(cur);
        cur.setLeft(newLeft);

        updateHandBF(cur);
        updateHandBF(newRoot);
        return newRoot;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data to remove cannot be null");
        }

        T ret = get(data);
        if (ret == null) {
            throw new java.util.
                NoSuchElementException("data not found in the tree");
        }

        root = removeNode(root, data);
        return ret;
    }

    /**
     * Removeds a data entry from the tree and balances recursively.
     *
     * @param cur the node from which to start the traversal
     * @param data the data to remove from the tree
     * @return the pointer reinforced new child
     */
    private AVLNode<T> removeNode(AVLNode<T> cur, T data) {
        if (cur == null) {
            return null;
        }

        if (data.compareTo(cur.getData()) < 0) {
            cur.setLeft(removeNode(cur.getLeft(), data));
        } else if (data.compareTo(cur.getData()) > 0) {
            cur.setRight(removeNode(cur.getRight(), data));
        } else { // this is the data to remove
            size -= 1;

            if (cur.getLeft() == null || cur.getRight() == null) {
                if (cur.getRight() != null) {
                    return cur.getRight();
                } else if (cur.getLeft() != null) {
                    return cur.getLeft();
                } else { // both children are null
                    return null;
                }
            } else { // find successor
                AVLNode<T> node = cur.getRight();
                while (node.getLeft() != null) {
                    node = node.getLeft();
                }

                cur.setData(node.getData());
                removeNode(cur.getRight(), node.getData());
            }
        }

        updateHandBF(cur);
        balance(cur);
        return cur;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data to get cannot be null");
        }

        T ret = get(root, data);
        if (ret == null) {
            throw new java.util.
                NoSuchElementException("data not found in the tree");
        }

        return ret;
    }

    /**
     * Helper function to recursively find a node.
     *
     * @param cur the node from which to start the traversal
     * @param data the data to find in the tree
     * @return the pointer to the node's data
     */
    private T get(AVLNode<T> cur, T data) {
        if (cur == null) {
            return null;
        }

        if (data.compareTo(cur.getData()) < 0) {
            return get(cur.getLeft(), data);
        } else if (data.compareTo(cur.getData()) > 0) {
            return get(cur.getRight(), data);
        }

        return cur.getData();
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data to check cannot be null");
        }

        return get(root, data) != null;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public T getSecondLargest() {
        if (root == null || root.getRight() == null) {
            throw new java.util.NoSuchElementException(
                "not enough elements in tree to get second largest");
        }

        AVLNode<T> node = root;
        while (node.getRight().getRight() != null) {
            node = node.getRight();
        }

        return node.getData();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AVL)) {
            return false;
        }
        AVL<T> avl = (AVL<T>) obj;

        return equalsHelper(this.root, avl.root);
    }

    /**
     * Helper function to check for tree eqaulity.
     *
     * @param node1 the node from which to start the traversal on the frist tree
     * @param node2 the node from which to start the traversal on the
        second tree
     * @return whether the subtrees are equivalent
     */
    private boolean equalsHelper(AVLNode<T> node1, AVLNode<T> node2) {
        if (node1 == null || node2 == null) {
            return node1 == node2; // both are null
        }

        // check that preorder traversal is the same in both trees
        if (!node1.getData().equals(node2.getData())) {
            return false;
        }
        if (!equalsHelper(node1.getLeft(), node2.getLeft())) {
            return false;
        }
        if (!equalsHelper(node1.getRight(), node2.getRight())) {
            return false;
        }

        return true;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return (root != null) ? root.getHeight() : -1;
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
