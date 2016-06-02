package controller;


import model.Direction;

/**
 * Created by Антон on 25.03.2016.
 */
public interface EventListener
{
    //передвинуть объект в определенном направлении.
    void move(Direction direction) ;

    // начать заново текущий уровень.
     void restart();

    //начать следующий уровень.
     void startNextLevel()  ;
    // уровень с номером level завершён.
     void levelCompleted(int level) ;
}
