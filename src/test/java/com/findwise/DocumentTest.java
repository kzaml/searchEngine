package com.findwise;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocumentTest {

    @Test
    public void tokenizedSplitOnNonWordCharacter() {
        Document doc = new Document("doc1", "a.?,:;\"  (b)  ![]{}=+");
        List<String> excpectedTokenization = new ArrayList<>(Arrays.asList("a","b"));
        Assert.assertEquals("Should split on punctuation marks", excpectedTokenization, doc.tokenized);
    }

    @Test
    public void tokenizedDoesNotSplitOnApostrphe() {
        Document doc = new Document("doc1", "a'b");
        List<String> excpectedTokenization = new ArrayList<>(Arrays.asList("a'b"));
        Assert.assertEquals("Should not split on apostrophe", excpectedTokenization, doc.tokenized);
    }

    @Test
    public void tokenizedIsLowerCase() {
        Document doc = new Document("doc1", "A B");
        List<String> excpectedTokenization = new ArrayList<>(Arrays.asList("a","b"));
        Assert.assertEquals("Should be lower case", excpectedTokenization, doc.tokenized);
    }
}
