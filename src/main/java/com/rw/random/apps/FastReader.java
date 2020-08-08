package com.rw.random.apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.StringTokenizer;


public class FastReader implements AutoCloseable {
    private final BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;


    public FastReader() {
        this(new InputStreamReader(System.in));
    }

    public FastReader(Reader reader) {
        bufferedReader = new BufferedReader(reader);
        stringTokenizer = new StringTokenizer("");
    }


    public String next() throws IOException {
        while (!stringTokenizer.hasMoreTokens()) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        }
        return stringTokenizer.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public String nextLine() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }
}
