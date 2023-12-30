package ru.brigada.javaFX.controller;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Border;
import ru.brigada.javaFX.model.Element;
import ru.brigada.javaFX.model.ElementArrayList;
public class Timer extends AnimationTimer {
    private long lastTime, lastTime2, lastTime3=0;
    private int i1=0, i1c= ElementArrayList.getInstance().size()/2,
            i2c=ElementArrayList.getInstance().size() - ElementArrayList.getInstance().size()/2;
    private int i2=ElementArrayList.getInstance().size()/2;
    private int found1 = 1, found2 = 1, worked1 = 0, worked2 = 0, i3=0, step1=0, step2=0, step3=0 ;
    private boolean running;
    @Override
    public void start() {
        super.start();
        running = true;
    }

    @Override
    public void stop() {
        super.stop();
        running = false;
    }

    @Override
    public void handle(long now) {
        long anim = (now-lastTime)/1_000_000_000;
        //System.out.println(anim+" anim");
        //System.out.println(now+"now "+lastTime+" lastTime");
        if(anim >= 2 && found1==1) {
            ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #2850f0");
            //ElementArrayList.getInstance().get(i1+1).setStyle("-fx-background-color: #f0c828");

            if( anim >=4) {
                if (i1+1<((ElementArrayList.getInstance().size() - ElementArrayList.getInstance().size()%2) / 2)){
                    ElementArrayList.getInstance().get(i1 + 1).setStyle("-fx-background-color: #f0c828");
                }
                if(anim>=6){
                if (i1 < ((ElementArrayList.getInstance().size() - 1) / 2) - ElementArrayList.getInstance().size() % 2) {
                    if ((ElementArrayList.getInstance().get(i1).getNumber() >
                            ElementArrayList.getInstance().get(i1 + 1).getNumber())) {

                            ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #008000");
                            ElementArrayList.getInstance().get(i1+1).setStyle("-fx-background-color: #008000");
                        if(anim>=8){
                        worked1 = 1;
                        Element elem = ElementArrayList.getInstance().get(i1);
                        ElementArrayList.getInstance().set(i1, ElementArrayList.getInstance().get(i1 + 1));
                        ElementArrayList.getInstance().set(i1 + 1, elem);

                        MainController.reshape_sort(0,i1c);
                            lastTime = now;
                            ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #fff");
                            ElementArrayList.getInstance().get(i1 + 1).setStyle("-fx-background-color: #2850f0");
                            i1++;
                    }
                    }else{
                        ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #FF0000");
                        ElementArrayList.getInstance().get(i1+1).setStyle("-fx-background-color: #FF0000");
                        if(anim>=8) {
                            lastTime = now;
                            ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #fff");
                            ElementArrayList.getInstance().get(i1 + 1).setStyle("-fx-background-color: #2850f0");
                            i1++;
                        }
                    }



                } else {
                    lastTime = now;
                    ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #fff");
                    i1 = 0;
                    if (worked1 == 1) {
                        worked1 = 0;
                    } else {
                        found1 = 0;
                    }
                }
            }
        }
        }
        long anim2 = (now-lastTime2)/1_000_000_000;
        //System.out.println(now+"now "+lastTime2+" lastTime2");
        //System.out.println(anim2+" anim2");
        if(anim2 >= 2 && found2==1) {
            ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #2850f0");
            //ElementArrayList.getInstance().get(i1+1).setStyle("-fx-background-color: #f0c828");

            if( anim2 >=4) {
                if (i2+1<((ElementArrayList.getInstance().size()))){
                    ElementArrayList.getInstance().get(i2 + 1).setStyle("-fx-background-color: #f0c828");
                }
                if(anim2>=6){
                    if (i2 < (ElementArrayList.getInstance().size() - 1)) {
                        if ((ElementArrayList.getInstance().get(i2).getNumber() >
                                ElementArrayList.getInstance().get(i2 + 1).getNumber())) {

                            ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #008000");
                            ElementArrayList.getInstance().get(i2+1).setStyle("-fx-background-color: #008000");
                            if(anim2>=8){
                                worked2 = 1;
                                Element elem = ElementArrayList.getInstance().get(i2);
                                ElementArrayList.getInstance().set(i2, ElementArrayList.getInstance().get(i2 + 1));
                                ElementArrayList.getInstance().set(i2 + 1, elem);


                                MainController.reshape_sort(0,i1c);
                                lastTime2 = now;
                                ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #fff");
                                ElementArrayList.getInstance().get(i2 + 1).setStyle("-fx-background-color: #2850f0");
                                i2++;
                            }
                        }else{
                            ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #FF0000");
                            ElementArrayList.getInstance().get(i2+1).setStyle("-fx-background-color: #FF0000");
                            if(anim2>=8) {
                                lastTime2 = now;
                                ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #fff");
                                ElementArrayList.getInstance().get(i2 + 1).setStyle("-fx-background-color: #2850f0");
                                i2++;
                            }
                        }



                    } else {
                        lastTime2 = now;
                        ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #fff");
                        i2 = ElementArrayList.getInstance().size()/2;
                        if (worked2 == 1) {
                            worked2 = 0;
                        } else {
                            found2 = 0;
                        }
                    }
                }
            }
        }
        if(found1==0&&found2==0){
            if(lastTime3==0&&lastTime>lastTime2){
                lastTime3=lastTime;
            }else{
                if(lastTime3==0){
                    lastTime3=lastTime2;
                }
            }
            // System.out.println(i1c+" i1c");
           // System.out.println(i2c+" i2c");
            if(i1c>0 && i2c>0){
                ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #2850f0");
                ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #2850f0");
                if((now-lastTime3)/1_000_000_000>=2){
                    if(ElementArrayList.getInstance().get(i1).getNumber()<=ElementArrayList.getInstance().get(i2).getNumber()){
                        ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #008000");
                        ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #FF0000");
                        if(step1==0){
                            step1=1;
                            /*double height = (double) ElementArrayList.getInstance().get(i1).getNumber() / (double) ElementArrayList.getMaxValue() * 200;
                            if(ElementArrayList.getInstance().get(i1).getLayoutY()<=200-height){
                                step1=1;
                            }
                            System.out.println(ElementArrayList.getInstance().get(i1).getLayoutY());
                            ElementArrayList.getInstance().get(i1).setLayoutY(ElementArrayList.getInstance().get(i1).getLayoutY()-1);*/
                        }
                        if(step1==1 && step2==0){
                            int in=i3+1;
                            double weight = 300/ElementArrayList.getInstance().size();
                            int ipo=0;
                            if(ElementArrayList.getInstance().get(in).getLayoutX()>=(ipo)*weight+300){
                                while (in<ElementArrayList.getInstance().size()-i2c){
                                    ElementArrayList.getInstance().get(in).setLayoutX(ElementArrayList.getInstance().get(in).getLayoutX()-1);
                                    in++;
                                    ipo++;
                                }
                            }
                            if(ElementArrayList.getInstance().get(i1).getLayoutX()<=weight*i3){
                                step2=1;
                            }
                            ElementArrayList.getInstance().get(i1).setLayoutX(ElementArrayList.getInstance().get(i1).getLayoutX()-1);
                        }
                        if(step2==1 && step3==0){
                            double height = (double) ElementArrayList.getInstance().get(i1).getNumber() / (double) ElementArrayList.getMaxValue() * 180;
                            System.out.println(ElementArrayList.getInstance().get(i2).getLayoutY());
                            if(ElementArrayList.getInstance().get(i1).getLayoutY()<= 300-height){
                                step3=1;
                            }
                            ElementArrayList.getInstance().get(i1).setLayoutY(ElementArrayList.getInstance().get(i1).getLayoutY()-1);
                        }
                        if(step1!=0&&step2!=0&&step3!=0){
                            ElementArrayList.getInstance().add(i3, ElementArrayList.getInstance().get(i1));
                            ElementArrayList.getInstance().remove(i1 + 1);
                            MainController.reshape_merge(i3+1,ElementArrayList.getInstance().size()-i2c,i3+1);
                            ElementArrayList.getInstance().get(i3).setStyle("-fx-background-color: #fff");
                            ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #fff");
                            lastTime3=now;
                            i1++;
                            i3++;
                            i1c--;
                            step1=step2=step3=0;
                        }
                    }else{
                        ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #008000");
                        ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #FF0000");
                        if(step1==0){
                            step1=1;
                            /*
                            double height = (double) ElementArrayList.getInstance().get(i2).getNumber() / (double) ElementArrayList.getMaxValue() * 200;
                            if(ElementArrayList.getInstance().get(i2).getLayoutY()<=200-height){
                                step1=1;
                            }
                            System.out.println(ElementArrayList.getInstance().get(i2).getLayoutY());
                            ElementArrayList.getInstance().get(i2).setLayoutY(ElementArrayList.getInstance().get(i2).getLayoutY()-1);
                            */
                        }
                        if(step1==1 && step2==0){

                            int in=ElementArrayList.getInstance().size()-i2c+1;
                            double weight = 300/ElementArrayList.getInstance().size();
                            int ipo=0;
                            if(i2c!=1)
                            if(ElementArrayList.getInstance().get(in).getLayoutX()>=(ipo)*weight+300){
                                while (in<ElementArrayList.getInstance().size()){
                                    ElementArrayList.getInstance().get(in).setLayoutX(ElementArrayList.getInstance().get(in).getLayoutX()-1);
                                    in++;
                                    ipo++;
                                }
                            }
                            if(ElementArrayList.getInstance().get(i2).getLayoutX()<= weight*i3){
                                step2=1;
                            }
                            ElementArrayList.getInstance().get(i2).setLayoutX(ElementArrayList.getInstance().get(i2).getLayoutX()-1);
                        }
                        if(step2==1 && step3==0){
                            double height = (double) ElementArrayList.getInstance().get(i2).getNumber() / (double) ElementArrayList.getMaxValue() * 180;
                            System.out.println(ElementArrayList.getInstance().get(i2).getLayoutY());
                            if(ElementArrayList.getInstance().get(i2).getLayoutY()>= 300-height){
                                step3=1;
                            }

                            ElementArrayList.getInstance().get(i2).setLayoutY(ElementArrayList.getInstance().get(i2).getLayoutY()+1);
                        }
                        if(step2==1 && step3==1){
                            i2c--;
                            ElementArrayList.getInstance().add(i3,ElementArrayList.getInstance().get(i2));
                            ElementArrayList.getInstance().remove(i2+1);
                            MainController.reshape_merge(i3+1,ElementArrayList.getInstance().size()-i2c,i3+1);
                            ElementArrayList.getInstance().get(i3).setStyle("-fx-background-color: #fff");
                            ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #fff");
                            lastTime3=now;
                            step1=step2=step3=0;
                            i1++;
                            i2++;
                            i3++;

                        }
                    }
                }

            }else{
                if(i1c>0 && i2c==0){
                    ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #008000");
                    int in=ElementArrayList.getInstance().size()-i1c;
                    double weight = 300/ElementArrayList.getInstance().size();
                    if(step1==0){
                        if(ElementArrayList.getInstance().get(i1).getLayoutX()<=weight*i3){
                            step1=1;
                        }
                        ElementArrayList.getInstance().get(i1).setLayoutX(ElementArrayList.getInstance().get(i1).getLayoutX()-1);
                        int ipo=0;
                        if(ElementArrayList.getInstance().get(in).getLayoutX()<=(ipo)*weight+300){
                            while (in<ElementArrayList.getInstance().size()){
                                ElementArrayList.getInstance().get(in).setLayoutX(ElementArrayList.getInstance().get(in).getLayoutX()-1);
                                in++;
                                ipo++;
                            }
                        }
                        /*
                        double height = (double) ElementArrayList.getInstance().get(i1).getNumber() / (double) ElementArrayList.getMaxValue() * 180;
                        if(ElementArrayList.getInstance().get(i1).getLayoutY()<=200-height){
                            step1=1;
                        }
                        System.out.println(ElementArrayList.getInstance().get(i1).getLayoutY());
                        ElementArrayList.getInstance().get(i1).setLayoutY(ElementArrayList.getInstance().get(i1).getLayoutY()-1);
                         */
                    }

                    if(step1==1 && step3==0){
                        double height = (double) ElementArrayList.getInstance().get(i1).getNumber() / (double) ElementArrayList.getMaxValue() * 180;
                        if(ElementArrayList.getInstance().get(i1).getLayoutY()== 300-height){
                            step3=1;
                        }

                        ElementArrayList.getInstance().get(i1).setLayoutY(ElementArrayList.getInstance().get(i1).getLayoutY()-1);
                    }
                    if(step3==1) {
                        ElementArrayList.getInstance().add(i3, ElementArrayList.getInstance().get(i1));
                        ElementArrayList.getInstance().remove(i1 + 1);
                        MainController.reshape_merge(i3+1,ElementArrayList.getInstance().size()-i2c,i3+1);
                        ElementArrayList.getInstance().get(i1).setStyle("-fx-background-color: #fff");
                        i1++;
                        i3++;
                        i1c--;
                        step1=step2=step3=0;
                    }
                }else{
                    if(i1c==0 && i2c>0){
                        int in = ElementArrayList.getInstance().size()-i2c;
                        double weight = 300/ElementArrayList.getInstance().size();
                        ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #008000");
                        if(step1==0){
                            if(ElementArrayList.getInstance().get(i1).getLayoutX()<=weight*i3){
                                step1=1;
                            }
                            ElementArrayList.getInstance().get(i1).setLayoutX(ElementArrayList.getInstance().get(i1).getLayoutX()-1);
                            int ipo=0;
                            if(ElementArrayList.getInstance().get(in).getLayoutX()>=(ipo)*weight+300){
                                while (in<ElementArrayList.getInstance().size()){
                                    ElementArrayList.getInstance().get(in).setLayoutX(ElementArrayList.getInstance().get(in).getLayoutX()-1);
                                    in++;
                                    ipo++;
                                }
                            }
                            /*
                            double height = (double) ElementArrayList.getInstance().get(i2).getNumber() / (double) ElementArrayList.getMaxValue() * 180;
                            if(ElementArrayList.getInstance().get(i2).getLayoutY()<=200-height){
                                step1=1;
                            }
                            System.out.println(ElementArrayList.getInstance().get(i2).getLayoutY());
                            ElementArrayList.getInstance().get(i2).setLayoutY(ElementArrayList.getInstance().get(i2).getLayoutY()-1);
                             */
                        }
                        if(step1==1 && step3==0){
                            double height = (double) ElementArrayList.getInstance().get(i1).getNumber() / (double) ElementArrayList.getMaxValue() * 180;
                            if(ElementArrayList.getInstance().get(i1).getLayoutY()== 300-height){
                                step3=1;
                            }
                            ElementArrayList.getInstance().get(i1).setLayoutY(ElementArrayList.getInstance().get(i1).getLayoutY()+1);
                        }
                        if(step3==1) {
                            ElementArrayList.getInstance().add(i3, ElementArrayList.getInstance().get(i2));
                            ElementArrayList.getInstance().remove(i2 + 1);
                            MainController.reshape_merge(i3+1,ElementArrayList.getInstance().size()-i2c,i3+1);
                            ElementArrayList.getInstance().get(i2).setStyle("-fx-background-color: #fff");
                            i1++;
                            i2++;
                            i3++;
                            i2c--;
                            step1=step2=step3=0;
                        }
                    }else{
                        System.out.println("Xnj");
                        this.stop();
                    }
                }
            }

        }
    }
    public Timer(long lastTime){
        this.lastTime = lastTime;
        this.lastTime2=lastTime;
        this.i2=ElementArrayList.getInstance().size()/2;

    }

    public boolean isRunning() {
        return running;
    }
}
