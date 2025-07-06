/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */
class Solution {
    
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostname = getHostname(startUrl);
        Set<String> visited = ConcurrentHashMap.newKeySet();
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        
        queue.offer(startUrl);
        visited.add(startUrl);
        
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        
        // Process URLs until queue is empty
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i=0;i<size;i++){
                String url = queue.poll();
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    List<String> urls = htmlParser.getUrls(url);
                    for (String newUrl : urls) {
                        if (isSameHostname(newUrl, hostname) && !visited.contains(newUrl)) {
                            visited.add(newUrl);
                            queue.offer(newUrl);
                        }
                    }
                });
                futures.add(future);
            }
            
            // Wait for current batch to complete before processing next batch
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            futures.clear();
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