package com.company.DesignMode.CommonPattern;

/**
 * Created by atomic on 11/1/2017.
 */
//保存操作代理给 Editor 方法
public class Save implements Action {

    private final Editor editor;

    public Save(Editor editor){
        this.editor = editor;
    }

    public void perform(){
        editor.save();
    }
}
