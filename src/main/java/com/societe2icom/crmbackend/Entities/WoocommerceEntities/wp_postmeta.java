package com.societe2icom.crmbackend.Entities.WoocommerceEntities;

public class wp_postmeta {


    private long meta_id;
    private long post_id;
    private String meta_key;
    private String meta_value;

    public wp_postmeta() {
    }

    public wp_postmeta(long post_id, String meta_key, String meta_value) {
        this.post_id = post_id;
        this.meta_key = meta_key;
        this.meta_value = meta_value;
    }

    public wp_postmeta(long meta_id, long post_id, String meta_key, String meta_value) {
        this.meta_id = meta_id;
        this.post_id = post_id;
        this.meta_key = meta_key;
        this.meta_value = meta_value;
    }

    public long getMeta_id() {
        return meta_id;
    }

    public void setMeta_id(long meta_id) {
        this.meta_id = meta_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public String getMeta_key() {
        return meta_key;
    }

    public void setMeta_key(String meta_key) {
        this.meta_key = meta_key;
    }

    public String getMeta_value() {
        return meta_value;
    }

    public void setMeta_value(String meta_value) {
        this.meta_value = meta_value;
    }

    @Override
    public String toString() {
        return "wp_postmeta{" +
                "meta_id=" + meta_id +
                ", post_id=" + post_id +
                ", meta_key='" + meta_key + '\'' +
                ", meta_value='" + meta_value + '\'' +
                '}';
    }
}
