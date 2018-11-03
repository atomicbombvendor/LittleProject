package com.company.DesignMode.CommonPattern;

/**
 * Created by atomic on 11/1/2017.
 */
public class Open implements Action {

    private final Editor editor;

    public Open(Editor editor){
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.open();
    }
}
