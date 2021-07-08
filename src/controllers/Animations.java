package controllers;

import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public enum Animations {
    EXIT_BUT(){
        public EventHandler<MouseEvent> animateEntry(Button exitBut){
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(exitBut);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1.2);
                scaleTransition.setToY(1.2);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
            };
        }

        public EventHandler<MouseEvent> animateExit(Button exitBut){
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(exitBut);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.setAutoReverse(false);
                scaleTransition.play();
            };
        }

    },
    MINI_BUT(){
        public EventHandler<MouseEvent> animateEntry(Button exitBut){
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(exitBut);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1.2);
                scaleTransition.setToY(1.2);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
            };
        }

        public EventHandler<MouseEvent> animateExit(Button exitBut){
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(exitBut);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.setAutoReverse(false);
                scaleTransition.play();
            };
        }

    },
    QUESTION_BUT(){
        public EventHandler<MouseEvent> animateEntry(Button exitBut){
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(exitBut);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1.2);
                scaleTransition.setToY(1.2);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
            };
        }

        public EventHandler<MouseEvent> animateExit(Button exitBut){
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(exitBut);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.setAutoReverse(false);
                scaleTransition.play();
            };
        }

    },
    NEXT_BUT{
        @Override
        public EventHandler<MouseEvent> animateEntry(Button button) {
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(button);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1.2);
                scaleTransition.setToY(1.2);
                scaleTransition.setAutoReverse(false);
                scaleTransition.play();
            };
        }

        @Override
        public EventHandler<MouseEvent> animateExit(Button button) {
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(button);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.setAutoReverse(false);
                scaleTransition.play();
            };
        }
    },
    PICK_DIR{
        @Override
        public EventHandler<MouseEvent> animateEntry(Button button) {
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(button);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1.2);
                scaleTransition.setToY(1.2);
                scaleTransition.setAutoReverse(false);
                scaleTransition.play();
            };
        }

        @Override
        public EventHandler<MouseEvent> animateExit(Button button) {
            return mouseEvent -> {
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setNode(button);
                scaleTransition.setDuration(Duration.seconds(.4));
                scaleTransition.setCycleCount(1);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.setAutoReverse(false);
                scaleTransition.play();
            };
        }
    };




    public abstract EventHandler<MouseEvent> animateEntry(Button button);
    public abstract EventHandler<MouseEvent> animateExit(Button button);

}
