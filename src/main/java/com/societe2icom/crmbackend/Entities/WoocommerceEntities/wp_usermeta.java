package com.societe2icom.crmbackend.Entities.WoocommerceEntities;


public class wp_usermeta {

    private long umeta_id;
    private long user_id ;
    private String meta_key  ;
    private String meta_value  ;

    public wp_usermeta() {
    }

    public wp_usermeta(long user_id, String meta_key, String meta_value) {
        this.user_id = user_id;
        this.meta_key = meta_key;
        this.meta_value = meta_value;
    }

    public wp_usermeta(long umeta_id, long user_id, String meta_key, String meta_value) {
        this.umeta_id = umeta_id;
        this.user_id = user_id;
        this.meta_key = meta_key;
        this.meta_value = meta_value;
    }

    public long getUmeta_id() {
        return umeta_id;
    }

    public void setUmeta_id(long umeta_id) {
        this.umeta_id = umeta_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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
        return "wp_usermeta{" +
                "umeta_id=" + umeta_id +
                ", user_id=" + user_id +
                ", meta_key='" + meta_key + '\'' +
                ", meta_value='" + meta_value + '\'' +
                '}';
    }
}