package com.societe2icom.crmbackend.Entities.WoocommerceEntities;

public class wp_term_taxonomy {

    private long term_taxonomy_id;
    private long term_id;
    private String taxonomy;
    private String description;
    private long parent;
    private long count;

    public wp_term_taxonomy() {
    }

    public wp_term_taxonomy(long term_id, String taxonomy, String description, long parent, long count) {
        this.term_id = term_id;
        this.taxonomy = taxonomy;
        this.description = description;
        this.parent = parent;
        this.count = count;
    }

    public wp_term_taxonomy(long term_taxonomy_id, long term_id, String taxonomy, String description, long parent, long count) {
        this.term_taxonomy_id = term_taxonomy_id;
        this.term_id = term_id;
        this.taxonomy = taxonomy;
        this.description = description;
        this.parent = parent;
        this.count = count;
    }

    public long getTerm_taxonomy_id() {
        return term_taxonomy_id;
    }

    public void setTerm_taxonomy_id(long term_taxonomy_id) {
        this.term_taxonomy_id = term_taxonomy_id;
    }

    public long getTerm_id() {
        return term_id;
    }

    public void setTerm_id(long term_id) {
        this.term_id = term_id;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "wp_term_taxonomy{" +
                "term_taxonomy_id=" + term_taxonomy_id +
                ", term_id=" + term_id +
                ", taxonomy='" + taxonomy + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + parent +
                ", count=" + count +
                '}';
    }
}