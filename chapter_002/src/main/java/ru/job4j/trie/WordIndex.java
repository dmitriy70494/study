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
                next.indexes.add(index - length);
                if (letter == ' ') {
                    next = this.root;
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
        Set<Integer> noIndexes = null;
        Trie next = root;
        for (int index = 0; index < searchWord.length(); index++) {
            if (next == null) {
                break;
            }
            next = next.chars.get(searchWord.charAt(index));
        }
        if (next != null) {
            noIndexes = next.indexes;
        }
        if (noIndexes == null || noIndexes.size() == 0) {
            noIndexes = new TreeSet<>();
            noIndexes.add(-1);
        }
        return noIndexes;
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
