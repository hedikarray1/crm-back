package com.societe2icom.crmbackend.Entities.WoocommerceEntities;



public class wp_term_relationships {

    private long object_id;
    private long term_taxonomy_id ;
    private long term_order ;

    public wp_term_relationships() {
    }

    public wp_term_relationships(long term_taxonomy_id, long term_order) {
        this.term_taxonomy_id = term_taxonomy_id;
        this.term_order = term_order;
    }

    public wp_term_relationships(long object_id, long term_taxonomy_id, long term_order) {
        this.object_id = object_id;
        this.term_taxonomy_id = term_taxonomy_id;
        this.term_order = term_order;
    }

    public long getObject_id() {
        return object_id;
    }

    public void setObject_id(long object_id) {
        this.object_id = object_id;
    }

    public long getTerm_taxonomy_id() {
        return term_taxonomy_id;
    }

    public void setTerm_taxonomy_id(long term_taxonomy_id) {
        this.term_taxonomy_id = term_taxonomy_id;
    }

    public long getTerm_order() {
        return term_order;
    }

    public void setTerm_order(long term_order) {
        this.term_order = term_order;
    }

    @Override
    public String toString() {
        return "wp_term_relationships{" +
                "object_id=" + object_id +
                ", term_taxonomy_id=" + term_taxonomy_id +
                ", term_order=" + term_order +
                '}';
    }
}