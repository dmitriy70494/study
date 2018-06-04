package ru.job4j.trie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordIndex {

    private Trie root;

    private int depth;

    public WordIndex() {
        root = new Trie();
    }

    public void loadFile(String filename) {
        try {
            int index = 0;
            int length = 0;
            Trie next = this.root;
            char letter;
            int read;
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename),
                            "UTF-8"
                    )
            );
            do {
                read = br.read();
                letter = (char) read;
                next = next.chars.computeIfAbsent(letter, k -> new Trie());
                next.indexes.add(index);
                if (letter == ' ') {
                    next = this.root;
                    if (depth < length) {
                        depth = length;
                    }
                    length = 0;
                }
                index++;
                length++;
            } while (read != -1);
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
            System.out.println("файл" + filename + " не найден");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Ошибка чтения из файла значения символа");
        }
    }

    public Set<Integer> getIndexes4Word(String searchWord) {
        Set<Integer> indexes = new TreeSet<>();
        Queue<Trie> tries = new LinkedList<>();
        Trie next = root;
        if (next != null && searchWord != null) {
            String[] words = searchWord.split(" ");
            ArrayList<Set<Integer>> sets = new ArrayList<Set<Integer>>();
            while (next != null) {
                for (int index = 0; index < words.length; index++) {
                    Set<Integer> set = findIndexes(words[index], next, 0);
                        if (set != null) {
                        sets.add(set);
                    } else {
                        sets.add(new TreeSet<Integer>());
                    }
                }
                if (sets.size() > 1) {
                    Integer result = 0;
                    for (Integer index : sets.get(0)) {
                        result = index;
                        for (int count = 1; count < sets.size(); count++) {
                            result += words[count].length() + 1;
                            if (!sets.get(count).contains(result)) {
                                result = 0;
                                break;
                            }
                        }
                        if (result != 0) {
                            indexes.add(result - searchWord.length() + 1);
                        }
                    }
                } else if (sets.size() != 0 && sets.get(0) != null) {
                    for (Integer result : sets.get(0)) {
                        indexes.add(result - searchWord.length() + 1);
                    }
                }
                tries.addAll(next.chars.values());
                next = (Trie) tries.poll();
                sets.clear();
            }
        }
        return indexes;
    }

    private Set<Integer> findIndexes(String search, Trie next, int index) {
        if (next == null) {
            return null;
        }
        if (search.length() == index) {
            return next.indexes;
        }
        return findIndexes(search, next.chars.get(search.charAt(index)), index + 1);
    }

    class Trie {
        HashMap<Character, Trie> chars;

        Set<Integer> indexes;

        Trie() {
            chars = new HashMap<Character, Trie>(64);
            indexes = new HashSet<Integer>();
        }
    }
}
