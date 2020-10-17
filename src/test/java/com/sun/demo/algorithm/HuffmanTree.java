package com.sun.demo.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/8 17:13
 * 实现 哈夫曼树(最优二叉树)
 */
public class HuffmanTree {

    // 根节点
    private Node root;

    // 节点
    private Node[] nodes;

    private HuffmanTree() {
    }

    public static HuffmanTree create(int[] weights) {
        HuffmanTree tree = new HuffmanTree();
        tree.nodes = new Node[weights.length];

        // 权重优先队列
        Queue<Node> queue = new PriorityQueue<>();

        for (int i = 0; i < weights.length; i++) {
            tree.nodes[i] = new Node(weights[i]);
            queue.add(tree.nodes[i]);
        }

        while (queue.size() > 1) {
            final Node left = queue.poll();
            final Node right = queue.poll();

            final Node parent = new Node(left.getWeight() + right.getWeight(), left, right);
            queue.add(parent);
        }
        tree.root = queue.poll();
        return tree;
    }

    public static void main(String[] args) {

        int[] weights = {2, 3, 7, 9, 18, 25};
        final HuffmanTree huffmanTree = HuffmanTree.create(weights);
        huffmanTree.output(huffmanTree.root);
    }

    public void output(Node head) {
        if (head == null) return;

        System.out.println(head.weight);
        output(head.getLNode());
        output(head.getRNode());
    }

    @AllArgsConstructor
    @Data
    public static class Node implements Comparable<Node> {

        // 权重
        int weight;
        // 左节点
        Node lNode;
        // 右节点
        Node rNode;

        public Node(int weight) {
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.weight, node.weight);
        }
    }

}
