package io.geekidea.springboot.assembly.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManageApplicationSceneTree extends ManageApplicationScene {
    private List<ManageApplicationSceneTree> children;

    public ManageApplicationSceneTree() {
        super();
        this.children = new ArrayList<>();
    }

    // Getter and Setter for children
    public List<ManageApplicationSceneTree> getChildren() {
        return children;
    }

    public void setChildren(List<ManageApplicationSceneTree> children) {
        this.children = children;
    }
}