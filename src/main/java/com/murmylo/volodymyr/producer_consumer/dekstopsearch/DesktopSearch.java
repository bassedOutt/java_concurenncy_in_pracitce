package com.murmylo.volodymyr.producer_consumer.dekstopsearch;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DesktopSearch {
    private static final int N_CONSUMERS = 10;
    public static int BOUND = 1000;

    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        FileFilter filter = file -> true;
        for (File root : roots)
            new Thread(new FileCrawler(queue, filter, root)).start();
        for (int i = 0; i < N_CONSUMERS; i++)
            new Thread(new Indexer(queue)).start();
    }
}
