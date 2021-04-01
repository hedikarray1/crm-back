package com.societe2icom.crmbackend.Entities.WoocommerceEntities;
public class wp_termmeta {

    private long meta_id;
    private long term_id;
    private String meta_key;
    private String meta_value;

    public wp_termmeta() {
    }

    public wp_termmeta(long term_id, String meta_key, String meta_value) {
        this.term_id = term_id;
        this.meta_key = meta_key;
        this.meta_value = meta_value;
    }

    public wp_termmeta(long meta_id, long term_id, String meta_key, String meta_value) {
        this.meta_id = meta_id;
        this.term_id = term_id;
        this.meta_key = meta_key;
        this.meta_value = meta_value;
    }

    public long getMeta_id() {
        return meta_id;
    }

    public void setMeta_id(long meta_id) {
        this.meta_id = meta_id;
    }

    public long getTerm_id() {
        return term_id;
    }

    public void setTerm_id(long term_id) {
        this.term_id = term_id;
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
        return "wp_termmeta{" +
                "meta_id=" + meta_id +
                ", term_id=" + term_id +
                ", meta_key='" + meta_key + '\'' +
                ", meta_value='" + meta_value + '\'' +
                '}';
    }
}