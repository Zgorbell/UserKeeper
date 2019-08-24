package controller;

import dialog.DialogFactory;
import dialog.DialogFactoryImpl;

public class Controller {

    private DialogFactory dialogFactory = new DialogFactoryImpl(System.in);

    public void start(){
         dialogFactory.getStartDialog().start();
    }
}
