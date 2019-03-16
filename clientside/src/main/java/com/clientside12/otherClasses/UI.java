package com.clientside12.otherClasses;

import javafx.scene.control.Button;

public class UI {
    private Button edit;
    private Button delete;

    public UI() {
        edit = new Button("Edit");
        delete = new Button("Delete");
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
