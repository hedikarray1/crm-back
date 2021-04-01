package com.societe2icom.crmbackend.Entities.WoocommerceEntities;

public class wp_term {

    private long term_id;
    private String name;
    private String slug;
    private long term_group;

    public wp_term() {
    }

    public wp_term(String name, String slug, long term_group) {
        this.name = name;
        this.slug = slug;
        this.term_group = term_group;
    }

    public wp_term(long term_id, String name, String slug, long term_group) {
        this.term_id = term_id;
        this.name = name;
        this.slug = slug;
        this.term_group = term_group;
    }

    public long getTerm_id() {
        return term_id;
    }

    public void setTerm_id(long term_id) {
        this.term_id = term_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public long getTerm_group() {
        return term_group;
    }

    public void setTerm_group(long term_group) {
        this.term_group = term_group;
    }

    @Override
    public String toString() {
        return "wp_term{" +
                "term_id=" + term_id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", term_group=" + term_group +
                '}';
    }
}
