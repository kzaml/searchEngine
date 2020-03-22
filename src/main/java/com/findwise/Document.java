package com.findwise;

import java.util.ArrayList;
import java.util.List;

class Document {
    List<String> tokenized;
    private String content;
    private String id;

    Document(String id, String content) {
        this.content = content;
        this.id = id;
        this.tokenized = tokenizeContent();


    }

    private List<String> tokenizeContent() {
        String[] splittedContent = this.content.split("[^\\w']");
        List<String> tokenizedContent = new ArrayList<>();
        for (String term : splittedContent) {
            if (term.matches("[\\w']+") && !term.isBlank()) {
                tokenizedContent.add(term.strip().toLowerCase());
            }

        }


        return tokenizedContent;
    }
}
