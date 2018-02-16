import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of a binary search tree.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        int i = 0;
        for (T el : data) {
            if (el == null) {
                throw new IllegalArgumentException(
                        "element <" + i + "> was null");
            }
            add(el);
            i += 1;
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data to add cannot be null");
        }

         BSTNode<T> node = new BSTNode<T>(data);

        if (root == null) {
            root = node;
        } else {
            BSTNode<T> parent = root;
            boolean placed = false;
            while (!placed) {
                if (data.compareTo(parent.getData()) < 0) {
                    if (parent.getLeft() == null) {
                        parent.setLeft(node);
                        placed = true;
                    } else {
                        parent = parent.getLeft();
                    }
                } else if (data.compareTo(parent.getData()) > 0) {
                    if (parent.getRight() == null) {
                        parent.setRight(node);
                        placed = true;
                    } else {
                        parent = parent.getRight();
                    }
                } else {
                    return;
                }
            }
        }

        size += 1;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data to remove cannot be null");
        }

        if (root == null) {
            throw new java.util.NoSuchElementException(
                    "the data to remove was not found in the tree");
        }

        boolean left = false;
        BSTNode<T> grandParent = null;
        BSTNode<T> parent = root;
        while (true) {
            if (data.compareTo(parent.getData()) < 0) {
                if (parent.getLeft() == null) {
                    throw new java.util.NoSuchElementException(
                            "the data to remove was not found in the tree");
                }

                left = true;
                grandParent = parent;
                parent = parent.getLeft();
            } else if (data.compareTo(parent.getData()) > 0) {
                if (parent.getRight() == null) {
                    throw new java.util.NoSuchElementException(
                            "the data to remove was not found in the tree");
                }

                left = false;
                grandParent = parent;
                parent = parent.getRight();
            } else { // this is the same data, so remove it
                BSTNode<T> replacementNode = null;
                if (parent.getLeft() == null && parent.getRight() != null) {
                    replacementNode = parent.getRight();
                } else if (parent.getLeft() == null && parent.getRight() == null) {
                    // this is a leaf, so do nothing
                    // we will just delete the parent node
                } else { // parent has two children
                    replacementNode = parent.getLeft();
                }

                T ret = parent.getData();

                if (grandParent != null) {
                    if (left) {
                        grandParent.setLeft(replacementNode);
                    } else {
                        grandParent.setRight(replacementNode);
                    }
                } else { // this is the root node
                    root = replacementNode;
                }

                size -= 1;
                if (size == 0) {
                    root = null;
                    return ret;
                } else {
                    return ret;
                }
            }
        }
    }

    @Override
    public T get(T data) {
        return getNode(data).getData();
    }

    private BSTNode<T> getNode(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data to remove was null");
        }

        if (root == null) {
            throw new java.util.NoSuchElementException(
                    "the data to remove was not found in the tree");
        }

        BSTNode<T> parent = root;
        while (true) {
            if (data.compareTo(parent.getData()) < 0) {
                if (parent.getLeft() == null) {
                    throw new java.util.NoSuchElementException(
                            "the data to remove was not found in the tree");
                }
                parent = parent.getLeft();
            } else if (data.compareTo(parent.getData()) > 0) {
                if (parent.getRight() == null) {
                    throw new java.util.NoSuchElementException(
                            "the data to remove was not found in the tree");
                }
                parent = parent.getRight();
            } else { // this is the same data, so return it
                return parent;
            }
        }
    }

    @Override
    public boolean contains(T data) {
        try {
            return get(data) != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<T> preorder() {
        return preorder(new ArrayList<T>(), root);
    }

    private List<T> preorder(List<T> data, BSTNode<T> node) {
        if (node != null) {
            data.add(node.getData());
            preorder(data, node.getLeft());
            preorder(data, node.getRight());
        }

        return data;
    }

    @Override
    public List<T> postorder() {
        return postorder(new ArrayList<T>(), root);
    }

    private List<T> postorder(List<T> data, BSTNode<T> node) {
        if (node != null) {
            postorder(data, node.getLeft());
            postorder(data, node.getRight());
            data.add(node.getData());
        }

        return data;
    }

    @Override
    public List<T> inorder() {
        return inorder(new ArrayList<T>(), root);
    }

    private List<T> inorder(List<T> data, BSTNode<T> node) {
        if (node != null) {
            inorder(data, node.getLeft());
            data.add(node.getData());
            inorder(data, node.getRight());
        }

        return data;
    }

    @Override
    public List<T> levelorder() {
        ArrayList<T> data = new ArrayList<T>();
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        queue.add(root);
        BSTNode<T> node;
        while (queue.peek() != null) {
            node = queue.remove();
            data.add(node.getData());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }

        return data;
    }

    @Override
    public int distanceBetween(T data1, T data2) {
        BSTNode<T> node1 = getNode(data1);
        BSTNode<T> node2 = getNode(data2);
        return Math.abs(height(node1) - height(node2));
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }

        return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
