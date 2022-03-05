//package com.whh.springboot.springdemo;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.LinkedList;
//import java.util.concurrent.Executors;
//import java.util.concurrent.RejectedExecutionHandler;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringDemoApplicationTests {
//
//
//    public static void main(String[] args) {
//        Node<Integer> first = new Node<>(0);
//        Node<Integer> t=first;
//        for (int i=1;i<10;i++){
//            Node<Integer> p = new Node<Integer>(i);
//            t.next=p;
//            t = p;
//        }
//        printLink(first);
//
//        Node<Integer> item= first;
//        Node<Integer> newFirst= null;
//        while (item != null) {
//            Node<Integer> m = item.next;
//            item.next = newFirst;
//            newFirst = item;
//            item = m;
//        }
//        printLink(newFirst);
//    }
//    private static Node<Integer>  preInsert(Node<Integer> item,Node<Integer> pre) {
//        pre.next = item;
//        return pre;
//    }
//    private static void printLink(Node<Integer> first) {
//        Node<Integer> x= first;
//        while (x != null) {
//            System.out.print(x.item);
//            x = x.next;
//        }
//        System.out.println();
//    }
//
//}
//
//class Node<E> {
//    E item;
//    Node<E> next;
//
//    Node(E element) {
//        this.item = element;
//    }
//
//}
