/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */
//  public class Solution {

//     public List<String> crawl(String startUrl, HtmlParser htmlParser) {
//         String hostname = getHostname(startUrl);

//         // Thread-safe collections
//         Set<String> visited = ConcurrentHashMap.newKeySet();
//         BlockingQueue<String> queue = new LinkedBlockingQueue<>();

//         // Add starting URL
//         queue.offer(startUrl);
//         visited.add(startUrl);

//         // Thread pool - optimal size based on I/O bound nature
//         int numThreads = Math.min(Runtime.getRuntime().availableProcessors() * 2, 10);
//         ExecutorService executor = Executors.newFixedThreadPool(numThreads);

//         // Counter to track active workers
//         AtomicInteger activeWorkers = new AtomicInteger(0);

//         // Submit initial workers
//         for (int i = 0; i < numThreads; i++) {
//             executor.submit(new CrawlerWorker(queue, visited, htmlParser, hostname, activeWorkers));
//         }

//         // Wait for all work to complete
//         try {
//             while (true) {
//                 Thread.sleep(10); // Small delay to prevent busy waiting
//                 if (queue.isEmpty() && activeWorkers.get() == 0) {
//                     break;
//                 }
//             }
//         } catch (InterruptedException e) {
//             Thread.currentThread().interrupt();
//         }

//         executor.shutdown();

//         return new ArrayList<>(visited);
//     }

//     private String getHostname(String url) {
//         // Remove "http://" prefix
//         String withoutProtocol = url.substring(7);
//         // Find first '/' or take entire string if no '/'
//         int slashIndex = withoutProtocol.indexOf('/');
//         return slashIndex == -1 ? withoutProtocol : withoutProtocol.substring(0, slashIndex);
//     }

//     private boolean isSameHostname(String url, String targetHostname) {
//         return getHostname(url).equals(targetHostname);
//     }

//     private class CrawlerWorker implements Runnable {
//         private final BlockingQueue<String> queue;
//         private final Set<String> visited;
//         private final HtmlParser htmlParser;
//         private final String hostname;
//         private final AtomicInteger activeWorkers;

//         public CrawlerWorker(BlockingQueue<String> queue, Set<String> visited, 
//                            HtmlParser htmlParser, String hostname, AtomicInteger activeWorkers) {
//             this.queue = queue;
//             this.visited = visited;
//             this.htmlParser = htmlParser;
//             this.hostname = hostname;
//             this.activeWorkers = activeWorkers;
//         }

//         @Override
//         public void run() {
//             while (true) {
//                 String url = null;
//                 try {
//                     // Try to get work with timeout
//                     url = queue.poll(100, TimeUnit.MILLISECONDS);
//                     if (url == null) {
//                         // No work available, check if we should terminate
//                         if (queue.isEmpty() && activeWorkers.get() == 0) {
//                             break;
//                         }
//                         continue;
//                     }

//                     // Mark as active worker
//                     activeWorkers.incrementAndGet();

//                     // Process the URL
//                     List<String> urls = htmlParser.getUrls(url);

//                     for (String newUrl : urls) {
//                         if (isSameHostname(newUrl, hostname) && visited.add(newUrl)) {
//                             queue.offer(newUrl);
//                         }
//                     }

//                 } catch (InterruptedException e) {
//                     Thread.currentThread().interrupt();
//                     break;
//                 } finally {
//                     // Mark as inactive worker
//                     if (url != null) {
//                         activeWorkers.decrementAndGet();
//                     }
//                 }
//             }
//         }
//     }
// }

// public class Solution {
//     public List<String> crawl(String startUrl, HtmlParser htmlParser) {
//         String hostname = getHostname(startUrl);
//         Set<String> visited = ConcurrentHashMap.newKeySet();
//         BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        
//         visited.add(startUrl);
//         queue.offer(startUrl);
        
//         int numThreads = Math.min(Runtime.getRuntime().availableProcessors() * 2, 10);
//         ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        
//         // Use a more robust completion detection mechanism
//         final AtomicInteger tasksInProgress = new AtomicInteger(1); // Start with 1 for initial URL
//         final CountDownLatch allDone = new CountDownLatch(1);
        
//         // Submit workers
//         for (int i = 0; i < numThreads; i++) {
//             executor.submit(() -> {
//                 while (true) {
//                     String url = null;
//                     try {
//                         url = queue.poll(10, TimeUnit.MILLISECONDS);
                        
//                         if (url == null) {
//                             // No work available - check if completely done
//                             if (tasksInProgress.get() == 0) {
//                                 allDone.countDown();
//                                 break;
//                             }
//                             continue;
//                         }
                        
//                         // Process the URL
//                         List<String> urls = htmlParser.getUrls(url);
                        
//                         int newUrlsAdded = 0;
//                         for (String newUrl : urls) {
//                             if (isSameHostname(newUrl, hostname) && visited.add(newUrl)) {
//                                 queue.offer(newUrl);
//                                 newUrlsAdded++;
//                             }
//                         }
                        
//                         // Update task count: remove current task, add new tasks
//                         int remaining = tasksInProgress.addAndGet(newUrlsAdded - 1);
                        
//                         // If this was the last task, signal completion
//                         if (remaining == 0) {
//                             allDone.countDown();
//                             break;
//                         }
                        
//                     } catch (InterruptedException e) {
//                         Thread.currentThread().interrupt();
//                         break;
//                     }
//                 }
//             });
//         }
        
//         try {
//             // Wait for completion with timeout to prevent infinite waiting
//             if (!allDone.await(30, TimeUnit.SECONDS)) {
//                 System.err.println("Crawling timed out");
//             }
//         } catch (InterruptedException e) {
//             Thread.currentThread().interrupt();
//         }
        
//         executor.shutdownNow();
        
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


// public class Solution {
//     public List<String> crawl(String startUrl, HtmlParser htmlParser) {
//         String hostname = getHostname(startUrl);
//         Set<String> visited = ConcurrentHashMap.newKeySet();
//         BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        
//         visited.add(startUrl);
//         queue.offer(startUrl);
        
//         int numThreads = Math.min(Runtime.getRuntime().availableProcessors() * 2, 10);
//         ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        
//         // Track active workers processing URLs
//         AtomicInteger activeWorkers = new AtomicInteger(0);
//         CountDownLatch latch = new CountDownLatch(numThreads);
        
//         // Start all worker threads - they all do the same work
//         for (int i = 0; i < numThreads; i++) {
//             executor.submit(() -> {
//                 try {
//                     while (true) {
//                         String url = null;
//                         try {
//                             // Each thread polls the queue directly
//                             url = queue.poll(10, TimeUnit.MILLISECONDS);
                            
//                             if (url == null) {
//                                 // No work available - check if we should terminate
//                                 if (activeWorkers.get() == 0) {
//                                     // No active workers and no work - we're done
//                                     break;
//                                 }
//                                 continue;
//                             }
                            
//                             // Mark as active before processing
//                             activeWorkers.incrementAndGet();
                            
//                             // Process the URL
//                             List<String> urls = htmlParser.getUrls(url);
//                             for (String newUrl : urls) {
//                                 if (isSameHostname(newUrl, hostname) && visited.add(newUrl)) {
//                                     queue.offer(newUrl);
//                                 }
//                             }
                            
//                         } catch (InterruptedException e) {
//                             Thread.currentThread().interrupt();
//                             break;
//                         } catch (Exception e) {
//                             // Handle any other exceptions
//                         } finally {
//                             // Always decrement active workers
//                             if (url != null) {
//                                 activeWorkers.decrementAndGet();
//                             }
//                         }
//                     }
//                 } finally {
//                     latch.countDown();
//                 }
//             });
//         }
        
//         try {
//             // Wait for all threads to complete
//             latch.await();
//         } catch (InterruptedException e) {
//             Thread.currentThread().interrupt();
//         }
        
//         executor.shutdown();
        
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

class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostname = getHostname(startUrl);
        Set<String> visited = ConcurrentHashMap.newKeySet();
        Queue<CompletableFuture<List<String>>> pendingFutures = new ConcurrentLinkedQueue<>();
        
        int numThreads = Math.min(Runtime.getRuntime().availableProcessors() * 2, 10);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        
        try {
            visited.add(startUrl);
            
            // Start with initial URL
            CompletableFuture<List<String>> initialFuture = CompletableFuture
                .supplyAsync(() -> htmlParser.getUrls(startUrl), executor);
            pendingFutures.offer(initialFuture);
            
            // Process futures iteratively
            while (!pendingFutures.isEmpty()) {
                CompletableFuture<List<String>> currentFuture = pendingFutures.poll();
                
                try {
                    if(!currentFuture.isDone()){
                        pendingFutures.offer(currentFuture);
                        continue;
                    }
                    List<String> urls = currentFuture.get();
                    List<CompletableFuture<List<String>>> newFutures = new ArrayList<>();
                    
                    for (String url : urls) {
                        if (isSameHostname(url, hostname) && visited.add(url)) {
                            CompletableFuture<List<String>> future = CompletableFuture
                                .supplyAsync(() -> htmlParser.getUrls(url), executor);
                            newFutures.add(future);
                        }
                    }
                    
                    // Add new futures to the queue
                    pendingFutures.addAll(newFutures);
                    
                } catch (InterruptedException | ExecutionException e) {
                    // Handle exceptions
                    Thread.currentThread().interrupt();
                }
            }
            
        } finally {
            executor.shutdown();
        }
        
        return new ArrayList<>(visited);
    }
    
    private String getHostname(String url) {
        String withoutProtocol = url.substring(7);
        int slashIndex = withoutProtocol.indexOf('/');
        return slashIndex == -1 ? withoutProtocol : withoutProtocol.substring(0, slashIndex);
    }
    
    private boolean isSameHostname(String url, String targetHostname) {
        return getHostname(url).equals(targetHostname);
    }
}