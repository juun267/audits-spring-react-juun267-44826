package com.wovenreviews.java.projection;

public interface ProjectView {
    Integer getId();

    String getTitle();

    String getDescription();

    UserView getUser();
}
