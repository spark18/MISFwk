package com.shizhong.db.querier;

public class ModifiedFields {
    private String id;
    private String modifier;

    private String entity;

    private String[] fields;

    public String getEntity() {
        return entity;
    }

    public String[] getFields() {
        return fields;
    }

    public String getId() {
        return id;
    }

    public String getModifier() {
        return modifier;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}