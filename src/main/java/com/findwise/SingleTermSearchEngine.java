package com.findwise;

import java.util.*;

public class SingleTermSearchEngine implements SearchEngine {


    private Map<String, Document> documents = new HashMap<>();
    private Map<String, Integer> frequencyMap = new HashMap<>();
    private Map<String, Integer> occurrenceMap = new HashMap<>();

    public void indexDocument(String id, String content) {
        if (this.documents.containsKey(id)) {
            System.out.println(id + ": Document with this id is already in the databese. Document not added");
        } else {
            Document doc = new Document(id, content);
            documents.put(id, doc);
        }
    }


    public List<IndexEntry> search(String term) {
        List<IndexEntry> searchResult = new ArrayList<>();
        occurrenceCheck(term.toLowerCase());
        if (this.occurrenceMap.size() == 0 || this.documents.size() == this.occurrenceMap.size()) {
            return searchResult;
        }
        double idf = Math.log((double) this.documents.size() / (double) this.occurrenceMap.size());

        for (Map.Entry<String, Integer> entry : this.occurrenceMap.entrySet()) {
            searchResult.add(new SearchIndexEntry(entry.getKey(), countTf(entry.getKey()) * idf));
        }

        searchResult.sort(Comparator.comparing(IndexEntry::getId));
        searchResult.sort(Comparator.comparing(IndexEntry::getScore, Comparator.reverseOrder()));
        return searchResult;

    }

    private void occurrenceCheck(String term) {
        this.documents.forEach((key, value) -> {
            int frequency = Collections.frequency(value.tokenized, term);
            if (frequency > 0) {
                frequencyMap.put(key, frequency);
                occurrenceMap.put(key, 1);
            }
        });
    }

    private double countTf(String id) {
        return (double) this.frequencyMap.get(id) / ((double) this.documents.get(id).tokenized.size());
    }

    Map<String, Document> getDocuments() {
        return documents;
    }

}
