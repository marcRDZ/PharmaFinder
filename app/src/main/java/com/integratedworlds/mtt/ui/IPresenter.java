package com.integratedworlds.mtt.ui;

public interface IPresenter<T> {

    void takeView(T view);

    void dropView();
}
