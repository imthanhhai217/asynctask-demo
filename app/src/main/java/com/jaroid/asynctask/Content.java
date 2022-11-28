package com.jaroid.asynctask;

public class Content {
    private String id, title, tag, createAt, cover, body;

    private boolean isDraft;

    public Content(String id, String title, String tag, String createAt, String cover, String body, boolean isDraft) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.createAt = createAt;
        this.cover = cover;
        this.body = body;
        this.isDraft = isDraft;
    }

    public Content() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }
}
