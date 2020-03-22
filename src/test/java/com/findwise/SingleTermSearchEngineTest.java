package com.findwise;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SingleTermSearchEngineTest {


    @Test
    public void cannotAddDocumentWithSameId() {
        SingleTermSearchEngine se = new SingleTermSearchEngine();
        se.indexDocument("doc", "first document");
        se.indexDocument("doc", "second document with the same id");
        Assert.assertEquals(1, se.getDocuments().size());
    }

    @Test
    public void emptySearchEngineReturnsEmptySearchResult() {
        SearchEngine se = new SingleTermSearchEngine();
        List<IndexEntry> result = se.search("example");
        Assert.assertTrue(result.isEmpty());
    }


    @Test
    public void searchResultEmptyWhenTfIdfScoreZeroForAllDocuments() {
        SingleTermSearchEngine se = new SingleTermSearchEngine();
        se.indexDocument("doc1", "first doc");
        se.indexDocument("doc2", "second doc");
        List<IndexEntry> result = se.search("doc");
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void searchResultDoesNotIncludeDocumentsWithZeroTfIdfScore() {
        SingleTermSearchEngine se = new SingleTermSearchEngine();
        se.indexDocument("doc1", "first doc");
        se.indexDocument("doc2", "second");
        se.indexDocument("doc3", "third doc");
        List<IndexEntry> result = se.search("doc");
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void searchResultSortedByTfIdfScore() {
        SingleTermSearchEngine se = new SingleTermSearchEngine();
        se.indexDocument("doc1", "first doc");
        se.indexDocument("doc2", "second");
        se.indexDocument("doc3", "third doc doc");

        List<IndexEntry> result = se.search("doc");
        Assert.assertTrue(result.get(0).getScore() > result.get(1).getScore());
    }


}
