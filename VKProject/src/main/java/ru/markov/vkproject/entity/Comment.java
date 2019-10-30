/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.markov.vkproject.entity;

/**
 *
 * @author rodion
 */
public class Comment {
    
    Integer post_id;
    
    Integer from_id;
    
    String owner_id;
    
    String date;
    
    String text;
    
    

    public Comment() {
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getFrom_id() {
        return from_id;
    }

    public void setFrom_id(Integer from_id) {
        this.from_id = from_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public boolean isThread() {
//        return thread;
//    }
//
//    public void setThread(boolean thread) {
//        this.thread = thread;
//    }
//
//    public Integer getPerent_post_id() {
//        return perent_post_id;
//    }
//
//    public void setPerent_post_id(Integer perent_post_id) {
//        this.perent_post_id = perent_post_id;
//    }
    
    

    @Override
    public String toString() {
        return "Comment{" + "post_id=" + post_id + ", from_id=" + from_id + ", owner_id=" + owner_id + ", date=" + date + ", text=" + text + '}';
    }
    
    
    
    
}
