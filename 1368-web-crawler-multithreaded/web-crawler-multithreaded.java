/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */
 public class Solution {
    
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostname = getHostname(startUrl);
        
        // Thread-safe collections
        Set<String> visited = ConcurrentHashMap.newKeySet();
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        
        // Add starting URL
        queue.offer(startUrl);
        visited.add(startUrl);
        
        // Thread pool - optimal size based on I/O bound nature
        int numThreads = Math.min(Runtime.getRuntime().availableProcessors() * 2, 10);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        
        // Counter to track active workers
        AtomicInteger activeWorkers = new AtomicInteger(0);
        
        // Submit initial workers
        for (int i = 0; i < numThreads; i++) {
            executor.submit(new CrawlerWorker(queue, visited, htmlParser, hostname, activeWorkers));
        }
        
        // Wait for all work to complete
        try {
            while (true) {
                Thread.sleep(10); // Small delay to prevent busy waiting
                if (queue.isEmpty() && activeWorkers.get() == 0) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        executor.shutdown();
        
        return new ArrayList<>(visited);
    }
    
    private String getHostname(String url) {
        // Remove "http://" prefix
        String withoutProtocol = url.substring(7);
        // Find first '/' or take entire string if no '/'
        int slashIndex = withoutProtocol.indexOf('/');
        return slashIndex == -1 ? withoutProtocol : withoutProtocol.substring(0, slashIndex);
    }
    
    private boolean isSameHostname(String url, String targetHostname) {
        return getHostname(url).equals(targetHostname);
    }
    
    private class CrawlerWorker implements Runnable {
        private final BlockingQueue<String> queue;
        private final Set<String> visited;
        private final HtmlParser htmlParser;
        private final String hostname;
        private final AtomicInteger activeWorkers;
        
        public CrawlerWorker(BlockingQueue<String> queue, Set<String> visited, 
                           HtmlParser htmlParser, String hostname, AtomicInteger activeWorkers) {
            this.queue = queue;
            this.visited = visited;
            this.htmlParser = htmlParser;
            this.hostname = hostname;
            this.activeWorkers = activeWorkers;
        }
        
        @Override
        public void run() {
            while (true) {
                String url = null;
                try {
                    // Try to get work with timeout
                    url = queue.poll(100, TimeUnit.MILLISECONDS);
                    if (url == null) {
                        // No work available, check if we should terminate
                        if (queue.isEmpty() && activeWorkers.get() == 0) {
                            break;
                        }
                        continue;
                    }
                    
                    // Mark as active worker
                    activeWorkers.incrementAndGet();
                    
                    // Process the URL
                    List<String> urls = htmlParser.getUrls(url);
                    
                    for (String newUrl : urls) {
                        if (isSameHostname(newUrl, hostname) && visited.add(newUrl)) {
                            queue.offer(newUrl);
                        }
                    }
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    // Mark as inactive worker
                    if (url != null) {
                        activeWorkers.decrementAndGet();
                    }
                }
            }
        }
    }
}
// class Solution {
    
//     public List<String> crawl(String startUrl, HtmlParser htmlParser) {
//         String hostname = getHostname(startUrl);
//         Set<String> visited = ConcurrentHashMap.newKeySet();
//         Queue<String> queue = new ConcurrentLinkedQueue<>();
        
//         queue.offer(startUrl);
//         visited.add(startUrl);
        
//         List<CompletableFuture<Void>> futures = new ArrayList<>();
        
//         // Process URLs until queue is empty
//         while (!queue.isEmpty()) {
//             int size = queue.size();
            
//             for(int i=0;i<size;i++){
//                 String url = queue.poll();
//                 CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//                     List<String> urls = htmlParser.getUrls(url);
//                     for (String newUrl : urls) {
//                         if (isSameHostname(newUrl, hostname) && !visited.contains(newUrl)) {
//                             visited.add(newUrl);
//                             queue.offer(newUrl);
//                         }
//                     }
//                 });
//                 futures.add(future);
//             }
            
//             // Wait for current batch to complete before processing next batch
//             CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
//             futures.clear();
//         }
        
//         return new ArrayList<>(visited);
//     }
    
//     private String getHostname(String url) {
//         String withoutProtocol = url.substring(7);
//         int slashIndex = withoutProtocol.indexOf('/');
//         return slashIndex == -1 ? withoutProtocol : withoutProtocol.substring(0, slashIndex);
//     }
    
//     private boolean isSameHostname(String url, String targetHostname) {
//         return getHostname(url).equals(targetHostname);
//     }
// }